package fitnesscompanion.com.View.Profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fitnesscompanion.com.R;

/**
 * Created by Kok Fung on 10/19/2017.
 */

public class EditNameDialog  extends DialogFragment {

    private TextView textView;
    private EditText editText;
    private TextInputLayout textInputLayout;
    private Button buttonOk;
    private Button buttonCancel;
    private Context context;

    private AlertDialog.Builder builder;


    public EditNameDialog(Context context) {
        this.context=context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_text,null);

        textView = (TextView)rootView.findViewById(R.id.txtAll);
        editText = (EditText) rootView.findViewById(R.id.editAll);
        buttonOk = (Button) rootView.findViewById(R.id.btnOkAll);
        buttonCancel = (Button) rootView.findViewById(R.id.btnCancelAll);

        textInputLayout = (TextInputLayout) rootView.findViewById(R.id.layoutAll);

        editText.setHint(context.getString(R.string.name));

        builder.setView(rootView);



        return builder.create();
    }
}
