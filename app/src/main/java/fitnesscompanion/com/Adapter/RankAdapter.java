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
 * Created by David on 10/12/2017.
 */

public class RankAdapter extends BaseAdapter {
    @BindView(R.id.img_profile)ImageView imgprofile;
    @BindView(R.id.txt_name)TextView txtname;
    @BindView(R.id.txt_rankdesc)TextView txtdesc;
    @BindView(R.id.img_challenge) ImageView Imagechallenge;

    private TypedArray imgtypedArray;
    private String[] name;
    private String[] rankdesc;
    private Context context;

    public RankAdapter(Context context, TypedArray imgtypedArray, String[] namelist, String[] desclist){
        this.context = context;
        this.imgtypedArray = imgtypedArray;
        this.name = namelist;
        this.rankdesc = desclist;
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
        View rankview = inflater.inflate(R.layout.ranklist, null);
        ButterKnife.bind(this, rankview);

        if(index<=2) {
            Imagechallenge.setVisibility(View.VISIBLE);
        }
        imgprofile.setImageDrawable(imgtypedArray.getDrawable(index));
        txtname.setText(name[index]);
        txtdesc.setText(rankdesc[index]);

        return rankview;
    }
}
