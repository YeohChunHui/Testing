package fitnesscompanion.com.View.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Adapter.MenuAdapter;
import fitnesscompanion.com.R;

/**
 * Created by Kok Fung on 10/3/2017.
 */

public class HomeFragment extends Fragment {
    @BindView(R.id.lvhome)ListView HomeList;

    private MenuAdapter menuAdapter;

    private Context context;
    public HomeFragment(Context context) {
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, rootView);

        menuAdapter = new MenuAdapter(context,getResources().obtainTypedArray(R.array.HomeListImage),getResources().getStringArray(R.array.HomeList));
        HomeList.setAdapter(menuAdapter);
        HomeList.setOnItemClickListener(new onClickMenu());

        return rootView;
    }
    private class onClickMenu implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position){
                case 0:
                    startActivity(new Intent(context,BMIActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(context,GoalsActivity.class));
                    break;
                case 2:

                    break;
                case 3:

                    break;
            }
        }
    }
}
