package fitnesscompanion.com.View.Profile;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Adapter.MenuAdapter;
import fitnesscompanion.com.R;
import fitnesscompanion.com.View.Home.MenuActivity;
import fitnesscompanion.com.View.LoginActivity;

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.listView)
    ListView listView;

    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setTitle(getResources().getString(R.string.setting));

        ButterKnife.bind(this);

        menuAdapter = new MenuAdapter(this,getResources().obtainTypedArray(R.array.settingMenuImage),getResources().getStringArray(R.array.settingMenuTitle));
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new onClickMenu());
    }

    private class onClickMenu implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    new AboutUsDialog(SettingActivity.this).show(getFragmentManager(),"About Us");
                    break;
                case 1:
                    new FeedbackDialog(SettingActivity.this).show(getFragmentManager(),"Feedback");
                    break;
                case 2:
                    new signOutFragment().show(getFragmentManager(),"Sign Out");
                    break;

            }

        }
    }
    public class signOutFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.signOut_Msg)
                    .setPositiveButton(R.string.signOut, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().clear().commit();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //dismiss();
                        }
                    });

            return builder.create();
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
