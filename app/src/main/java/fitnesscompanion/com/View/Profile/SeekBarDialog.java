package fitnesscompanion.com.View.Profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import fitnesscompanion.com.Database.UserDB;
import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.SeekBarController;

/**
 * Created by Kok Fung on 10/19/2017.
 */

public class SeekBarDialog extends DialogFragment {

    private TextView txtTitle;
    private TextView txtType;
    private TextView txtValue;
    private SeekBar seekBar;
    private Button btnUpdate;
    private Button btnCancel;

    private Context context;
    private int type;
    private AlertDialog.Builder builder;
    private User user;
    private UserDB userDB;

    public SeekBarDialog(Context context,int type) {
        this.context=context;
        this.type=type;
        userDB = new UserDB(context);
        user = userDB.getData();
        user.setWeight(10);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_seekbar,null);

        txtTitle = (TextView) rootView.findViewById(R.id.txtAll);
        txtType = (TextView)  rootView.findViewById(R.id.txtType);
        txtValue = (TextView)  rootView.findViewById(R.id.txtValue);
        seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
        btnUpdate = (Button) rootView.findViewById(R.id.btnUpdate);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);

        txtTitle.setText(context.getResources().getStringArray(R.array.seekBarTitle)[type]);
        txtType.setText(context.getResources().getStringArray(R.array.seekBarType)[type]);

        if(type==0) {
            new SeekBarController(30,250,user.getHeight(),1,seekBar,txtValue);
        }
        else{
            new SeekBarController(3,200,user.getWeight(),1,seekBar,txtValue);
        }

        builder.setView(rootView);

        return builder.create();
    }
}
