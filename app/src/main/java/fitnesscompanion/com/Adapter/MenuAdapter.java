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
 * Created by Kok Fung on 10/3/2017.
 */

public class MenuAdapter extends BaseAdapter {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txtTitle)
    TextView textView;

    private Context context;
    private TypedArray typedArray;
    private String[]title;

    public MenuAdapter(Context context, TypedArray typedArray,String[]title){
        this.context=context;
        this.typedArray=typedArray;
        this.title=title;

    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater  = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.menu_layout,null);
        ButterKnife.bind(this, rowView);


        imageView.setImageDrawable(typedArray.getDrawable(position));
        textView.setText(title[position]);


        return rowView;
    }

}
