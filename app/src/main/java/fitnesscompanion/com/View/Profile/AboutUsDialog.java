package fitnesscompanion.com.View.Profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import fitnesscompanion.com.R;

/**
 * Created by Kok Fung on 10/3/2017.
 */

public class AboutUsDialog extends DialogFragment {
    public AboutUsDialog(Context context) {
        this.context=context;
    }

    private Context context;
    private AlertDialog.Builder builder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_aboutus,null);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.setView(rootView);

        return builder.create();
    }
}
