package fitnesscompanion.com.View.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Adapter.ActivityAdapter;
import fitnesscompanion.com.Database.ActivityDB;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;

/**
 * Created by Kok Fung on 10/3/2017.
 */

public class ActivityFragment extends Fragment {
    @BindView(R.id.lv_recomand)ListView listrecommand;
    @BindView(R.id.lv_common)ListView listcommon;

    private ConnectionDetector detector;
    private Activity activity;
    private ActivityDB activityDB;

    private ActivityAdapter activityAdapter;
    private Context context;
    public ActivityFragment(Context context) {
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment, container, false);
        ButterKnife.bind(this, rootView);

        activityDB = new ActivityDB(this.getActivity());
        ArrayList<fitnesscompanion.com.Model.Activity> activityArrayList = new ArrayList<fitnesscompanion.com.Model.Activity>();
        activityArrayList = new ActivityDB(context).getAllData();
        //activityArrayList.;

        activityAdapter = new ActivityAdapter(getActivity() ,getResources().obtainTypedArray(R.array.AwardImage),getResources().getStringArray(R.array.AwardTitle), getResources().getStringArray(R.array.AwardDesc), getResources().getStringArray(R.array.AwardDesc));
        listrecommand.setAdapter(activityAdapter);
        listrecommand.setOnItemClickListener(new OnClickItem());

        activityAdapter = new ActivityAdapter(getActivity() ,getResources().obtainTypedArray(R.array.AwardImage),activityDB.getName(), getResources().getStringArray(R.array.AwardDesc), getResources().getStringArray(R.array.AwardDesc));
        listcommon.setAdapter(activityAdapter);
        listcommon.setOnItemClickListener(new OnClickItem());
        return rootView;
    }

    private class OnClickItem implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
            Intent intent = new Intent(getActivity(), ExerciseActivity.class);
            intent.putExtra("POSITION", index);
            startActivity(intent);
        }
    }
}
