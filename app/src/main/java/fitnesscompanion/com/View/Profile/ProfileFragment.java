package fitnesscompanion.com.View.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Adapter.MenuAdapter;
import fitnesscompanion.com.Database.UserDB;
import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;


/**
 * Created by Kok Fung on 10/3/2017.
 */

public class ProfileFragment extends Fragment {
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.imageProfile)
    ImageView imageProfile;
    @BindView(R.id.btnImage)
    ImageButton btnImage;
    @BindView(R.id.listView)
    ListView listView;

    private Context context;
    private User user;
    private MenuAdapter menuAdapter;


    public ProfileFragment(Context context) {
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.bind(this, rootView);

        user = new UserDB(context).getData();
        initialize();

        btnImage.setOnClickListener(new onClickEdit());
        imageProfile.setOnClickListener(new onClickEdit());
        menuAdapter = new MenuAdapter(context,getResources().obtainTypedArray(R.array.profileMenuImage),getResources().getStringArray(R.array.profileMenuTitle));
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(new onClickMenu());

        return rootView;
    }

    public void initialize() {
        if(!user.getImage().equals("No")) {
            imageProfile.setImageBitmap(user.getImageFromJSon());
        }
        else{
            imageProfile.setImageDrawable(getResources().getDrawable(R.drawable.male_icon));
            if(user.getGender()==1)
                imageProfile.setImageDrawable(getResources().getDrawable(R.drawable.female_icon));
        }
        txtName.setText(user.getName());
    }
    private class onClickMenu implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    startActivity(new Intent(context,AchievementActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(context,ReminderActivity.class));
                    break;
                case 2:
                    //new ChangePassDialog(context).show(getActivity(,"ff");
                    //ChangePassDialog dialog = ChangePassDialog.newInstance(context);
                    //dialog.show(getFragmentManager(),"Forget Password");
                    break;
                case 3:
                    startActivity(new Intent(context,SettingActivity.class));
                    break;
            }

        }
    }
    private class onClickEdit implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startActivity(new Intent(context,ProfileActivity.class));
        }
    }


}
