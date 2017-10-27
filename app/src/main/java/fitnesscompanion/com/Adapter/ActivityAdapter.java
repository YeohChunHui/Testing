package fitnesscompanion.com.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.R;

/**
 * Created by David on 10/19/2017.
 */

public class ActivityAdapter extends BaseAdapter {
    @BindView(R.id.img_logo)ImageView imglogo;
    @BindView(R.id.txt_name)TextView txtname;
    @BindView(R.id.txt_calories)TextView txtcalories;
    @BindView(R.id.txt_time)TextView txttime;

    private TypedArray imgtypedArray;
    private String[] name;
    private String[] calories;
    private String[] time;
    private Context context;

    public ActivityAdapter(Context context, TypedArray imgtypedArray, String[] namelist, String[] calorieslist, String[] timelist){
        this.context = context;
        this.imgtypedArray = imgtypedArray;
        this.name = namelist;
        this.calories = calorieslist;
        this.time = timelist;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int index) {
        return index;
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        LayoutInflater inflater  = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View commonview = inflater.inflate(R.layout.commonlist, null);
        ButterKnife.bind(this, commonview);

        imglogo.setImageDrawable(imgtypedArray.getDrawable(index));
        txtname.setText(name[index]);
        txtcalories.setText(calories[index]);
        txttime.setText(time[index]);

        return commonview;
    }
}
