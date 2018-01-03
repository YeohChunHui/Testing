package fitnesscompanion.com.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fitnesscompanion.com.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, IChoiceActivity.class));

        /*if(detector.isConnectingToInternet()) {
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
        }*/
    }
}
