package fitnesscompanion.com.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Database.UserDB;
import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;

public class BMIActivity extends AppCompatActivity {
    @BindView(R.id.txtBmi)
    TextView txtBmi;
    @BindView(R.id.txtBmiDesc)
    TextView txtBmiDesc;

    private UserDB userDB;
    private User user;
    private String[] statusArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        getSupportActionBar().setTitle(getResources().getString(R.string.BMI_title));
        ButterKnife.bind(this);
        statusArray = getResources().getStringArray(R.array.bmi_status);
        user = new User();
        userDB = new UserDB(this);
        user=userDB.getData();
        user.setHeight(170);
        user.setWeight(80);
        txtBmi.setText(String.valueOf(user.getBmi()));
        txtBmiDesc.setText(statusArray[user.getBmiString()]);


    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MenuActivity.class).putExtra("index",0));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
