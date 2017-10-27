package fitnesscompanion.com.View.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Database.ActivityDB;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;
import fitnesscompanion.com.View.Home.MenuActivity;
//import fitnesscompanion.com.Util.TimerCount;

public class ExerciseActivity extends AppCompatActivity {
    @BindView(R.id.img_exercise)ImageView imgexercise;
    @BindView(R.id.img_timer)ImageView imgtimer;
    @BindView(R.id.txt_name)TextView txtname;
    @BindView(R.id.txt_description)TextView txtdescription;
    @BindView(R.id.chronometer)Chronometer chronometer;
    @BindView(R.id.txt_timer)TextView txttimer;
    @BindView(R.id.btn_start)Button btnestart;
    @BindView(R.id.btn_resume)Button btnresume;

    private ConnectionDetector detector;
    private Activity activity;
    private ActivityDB activityDB;

    private CountDownTimer countDownTimer;
    private Context context;
    private Thread thread;

    int count = 5;
    long time;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        getSupportActionBar().setTitle(getResources().getString(R.string.exercise));
        ButterKnife.bind(this);
        context = this;

        activityDB = new ActivityDB(this);
        ArrayList<fitnesscompanion.com.Model.Activity> activityArrayList;
        activityArrayList = new ActivityDB(context).getAllData();

        position = getIntent().getIntExtra("POSITION", 0);
        imgexercise.setImageBitmap(activityArrayList.get(position).getImageFromJSon());
        txtname.setText(activityDB.getName()[position]);
        txttimer.setText(count + " seconds");
    }

    public void onClickExercise(View view){
        switch (view.getId()){
            case R.id.btn_start:
                if(btnestart.getText().equals("Start")){
                    btnestart.setText(getResources().getString(R.string.stop));
                    countDownTimer = new CountDownTimer(count*1000, 100){
                        @Override
                        public void onTick(long millisUntilFinish) {
                            btnresume.setEnabled(false);
                            imgtimer.setVisibility(View.VISIBLE);
                            txttimer.setVisibility(View.VISIBLE);
                            chronometer.setVisibility(View.INVISIBLE);
                            txttimer.setText("" + millisUntilFinish/1000 + " seconds");
                            btnestart.setText(getResources().getString(R.string.cancel));
                        }
                        @Override
                        public void onFinish() {
                            imgtimer.setVisibility(View.INVISIBLE);
                            txttimer.setVisibility(View.INVISIBLE);
                            chronometer.setVisibility(View.VISIBLE);
                            btnestart.setText(getResources().getString(R.string.stop));
                            chronometer.setBase(SystemClock.elapsedRealtime());
                            chronometer.start();
                            time = 0;
                        }
                    }.start();
                }else if(btnestart.getText().equals("Cancel")){
                    btnestart.setText(getResources().getString(R.string.start));
                    countDownTimer.cancel();
                    txttimer.setText(count + " seconds");
                }else if(btnestart.getText().equals("Stop")){
                    time = SystemClock.elapsedRealtime();
                    chronometer.stop();
                    btnestart.setText(getResources().getString(R.string.end));
                    btnresume.setEnabled(true);
                }else{
                    //End Button (Save Data)S
                }
                break;
            case R.id.btn_resume:
                btnestart.setText(getResources().getString(R.string.stop));
                btnresume.setEnabled(false);
                chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - time);
                chronometer.start();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MenuActivity.class).putExtra("index",1));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
