package fitnesscompanion.com.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import fitnesscompanion.com.Controller.ActivityController;
import fitnesscompanion.com.Database.ActivityDB;
import fitnesscompanion.com.Database.ActivityVersionDB;
import fitnesscompanion.com.Model.Activity;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;
import fitnesscompanion.com.View.Home.MenuActivity;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private ActivityController activityController;
    private ActivityVersionDB versionDB;
    private ActivityDB activityDB;
    private ConnectionDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        activityController = new ActivityController(this);
        versionDB= new ActivityVersionDB(this);
        activityDB = new ActivityDB(this);
        detector = new ConnectionDetector(this);

        if(detector.isConnectingToInternet()) {
            activityController.getVersion(new ActivityController.VolleyCallVer() {
                @Override
                public void onSuccess(final int version) {
                    if(versionDB.getData()!=version) {
                        activityController.getActivity(new ActivityController.VolleyCallActivity() {
                            @Override
                            public void onSuccess(ArrayList<Activity> activity) {
                                activityDB.deleteData();
                                activityDB.insertData(activity);
                                versionDB.updateData(version);
                            }
                        });
                        Toast.makeText(getApplicationContext(),getString(R.string.checkVer_msg),Toast.LENGTH_LONG).show();
                        login();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),getString(R.string.checkVer2_msg),Toast.LENGTH_LONG).show();
                        login();
                    }
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),getString(R.string.noInternet),Toast.LENGTH_LONG).show();
        }
    }
    public void login() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(preferences.getInt("id",0)!=0){
            startActivity(new Intent(this,MenuActivity.class));
        }
        else{
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
