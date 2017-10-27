package fitnesscompanion.com.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Controller.UserController;
import fitnesscompanion.com.Database.UserDB;
import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;
import fitnesscompanion.com.Util.Listener;
import fitnesscompanion.com.Util.Validation;
import fitnesscompanion.com.View.Home.MenuActivity;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editEmail) EditText editEmail;
    @BindView(R.id.editPass) EditText editPass;
    @BindView(R.id.layoutEmail) TextInputLayout layoutEmail;
    @BindView(R.id.layoutPass) TextInputLayout layoutPass;
    private Validation validation;
    private ConnectionDetector detector;
    private UserDB userDB;
    private UserController userController;
    private int backButtonCount=0;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        validation = new Validation(this);
        detector = new ConnectionDetector(this);
        userController= new UserController(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userDB = new UserDB(this);

        new Listener(this).setListenerEmail(editEmail,layoutEmail);
        new Listener(this).setListenerPass(editPass,layoutPass);
    }
    public void onClickSignIn(View view) {

        if(validation()) {
            if(detector.isConnectingToInternet()) {
                userController.loginIn(new UserController.VolleyCallCheckActive() {
                    @Override
                    public void onSuccess(int active) {
                        if(active==0) {
                            Toast.makeText(getApplicationContext(),getString(R.string.login_fail),Toast.LENGTH_LONG).show();
                        }
                        else if(active==-1) {
                            Toast.makeText(getApplicationContext(),getString(R.string.login_active),Toast.LENGTH_LONG).show();
                        }
                        else if(active==1) {
                            userController.getUser(new UserController.VolleyCallgetUser() {
                                @Override
                                public void onSuccess(User user) {
                                    user.setEmail(editEmail.getText().toString());
                                    user.setPassword(editPass.getText().toString());
                                    userDB.deleteData();
                                    userDB.insertData(user);
                                    preferences.edit().putInt("id",user.getId()).commit();
                                    finish();
                                    if(userDB.getData().getHeight()==0) {
                                        startActivity(new Intent(getApplicationContext(),RecordActivity.class));
                                    }
                                    else{
                                        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                                    }

                                }
                            },editEmail.getText().toString());
                        }
                    }
                },editEmail.getText().toString(),editPass.getText().toString());
            }
        }
    }
    public void onClickForgetPass(View view) {
        PassRecoveryDialog textDialog = new PassRecoveryDialog(this);
        textDialog.show(getFragmentManager(),"text dialog");

    }
    public void onClickSignUp(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
    }

    public Boolean validation() {
        return validation.checkEmail(editEmail,layoutEmail)&
                validation.checkPassword(editPass,layoutPass);
    }
    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            System.exit(0);
        }
        else
        {
            Toast.makeText(this,getString(R.string.exitMsg), Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }


}
