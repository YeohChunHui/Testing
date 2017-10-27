package fitnesscompanion.com.View;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.SeekBarController;
import fitnesscompanion.com.View.Home.MenuActivity;

public class RecordActivity extends AppCompatActivity {
    @BindView(R.id.txtHeight)TextView txtHeight;
    @BindView(R.id.txtWeight)TextView txtWeight;
    @BindView(R.id.txtStep)TextView txtStep;
    @BindView(R.id.txtGoal)TextView txtGoal;
    @BindView(R.id.txtCalories)TextView txtCalories;
    @BindView(R.id.seekWeight)SeekBar seekWeight;
    @BindView(R.id.seekHeight)SeekBar seekHeight;
    @BindView(R.id.seekStep)SeekBar seekStep;
    @BindView(R.id.seekGoal)SeekBar seekGoal;
    @BindView(R.id.seekCalories)SeekBar seekCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        getSupportActionBar().setTitle(getResources().getString(R.string.record_setting));
        ButterKnife.bind(this);

        new SeekBarController(30,250,160,1,seekHeight,txtHeight);
        new SeekBarController(3,200,60,1,seekWeight,txtWeight);
        new SeekBarController(500,30000,8000,500,seekStep,txtStep);
        new SeekBarController(3,200,60,1,seekGoal,txtGoal);
        new SeekBarController(100,800,400,100,seekCalories,txtCalories);
    }
    public void onClick(View view) {
        startActivity(new Intent(this,MenuActivity.class));
    }

    @Override
    public void onBackPressed() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
        finish();
    }
}
