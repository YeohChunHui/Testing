package fitnesscompanion.com.View.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Adapter.RankAdapter;
import fitnesscompanion.com.Database.ActivityDB;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;
import fitnesscompanion.com.View.Home.MenuActivity;

public class AchievementActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ConnectionDetector detector;
    private Activity activity;
    private ActivityDB activityDB;

    @BindView(R.id.img_award)ImageView imgAward;
    @BindView(R.id.view_awardtitle)TextView viewAwardTitle;
    @BindView(R.id.sp_activitytype)Spinner spType;
    @BindView(R.id.lv_ranklist)ListView lvRank;

    private RankAdapter rankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        getSupportActionBar().setTitle(getResources().getString(R.string.achievement));
        ButterKnife.bind(this);

        activityDB = new ActivityDB(this);
        spType.setOnItemSelectedListener(this);
        ArrayAdapter<String> typeadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activityDB.getName());
        typeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(typeadapter);
        rankAdapter = new RankAdapter(this ,getResources().obtainTypedArray(R.array.AwardImage),getResources().getStringArray(R.array.AwardTitle), getResources().getStringArray(R.array.AwardDesc));
        lvRank.setAdapter(rankAdapter);
        lvRank.setOnItemClickListener(new OnClickItem());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private class OnClickItem implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
            if(index<3){
                //startActivity(new Intent(getApplicationContext(), RecordActivity.class));
            }else if(index==3){
                Toast.makeText(AchievementActivity.this, "You cannot challenge yourself", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(AchievementActivity.this, "Your result had higher than them", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MenuActivity.class).putExtra("index",3));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
