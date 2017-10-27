package fitnesscompanion.com.View.Profile;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import fitnesscompanion.com.R;

/**
 * Created by Kok Fung on 10/3/2017.
 */

public class ChangePassDialog extends DialogFragment {
    private Context context;
    private AlertDialog.Builder builder;
    public ChangePassDialog(Context context) {
        this.context=context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_pass,null);
        builder.setView(rootView);

        return builder.create();
    }

    public static ChangePassDialog newInstance(Context context) {
        return new ChangePassDialog(context);
    }



}
