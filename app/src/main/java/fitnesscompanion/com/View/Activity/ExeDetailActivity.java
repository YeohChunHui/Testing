package fitnesscompanion.com.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.R;
import fitnesscompanion.com.View.Home.MenuActivity;

public class ExeDetailActivity extends AppCompatActivity {

    @BindView(R.id.img_exelogo)ImageView imgexercise;
    @BindView(R.id.txt_name)TextView name;
    @BindView(R.id.txt_time)TextView time;
    @BindView(R.id.txt_duration)TextView duration;
    @BindView(R.id.txt_calories)TextView calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe_detail);
        ButterKnife.bind(this);
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
