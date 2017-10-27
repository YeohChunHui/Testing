package fitnesscompanion.com.View.Food;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import fitnesscompanion.com.R;

/**
 * Created by Kok Fung on 10/3/2017.
 */

public class FoodFragment extends Fragment{
    private Context context;
    public FoodFragment(Context context) {
        this.context=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_fragment, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
