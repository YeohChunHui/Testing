package fitnesscompanion.com.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fitnesscompanion.com.Controller.UserController;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;
import fitnesscompanion.com.Util.Listener;
import fitnesscompanion.com.Util.Validation;

/**
 * Created by Kok Fung on 9/29/2017.
 */

public class PassRecoveryDialog extends DialogFragment {
    private TextView textView;
    private EditText editText;
    private TextInputLayout textInputLayout;
    private Button buttonOk;
    private Button buttonCancel;
    private Context context;

    private AlertDialog.Builder builder;
    private Validation validation;

    private ConnectionDetector detector;
    private UserController userController;

    public PassRecoveryDialog(Context context) {
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

        builder.setView(rootView);

        validation = new Validation(context);
        detector = new ConnectionDetector(context);
        userController= new UserController(context);

        textView.setText(getString(R.string.passRecovery));
        editText.setHint(getString(R.string.email));
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        new Listener(context).setListenerEmail(editText,textInputLayout);

        buttonOk.setText(getString(R.string.recovery));
        buttonOk.setOnClickListener(new onclickRecovery());

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        return builder.create();
    }

    public class onclickRecovery implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            if(validation()) {
                if(detector.isConnectingToInternet()) {
                    userController.checkMail(new UserController.VolleyCallCheckMail() {
                        @Override
                        public void onSuccess(int count) {
                            if(count==1) {
                                userController.resetPass(editText.getText().toString());
                                getDialog().dismiss();
                            }
                            else{
                                Toast.makeText(context,context.getString(R.string.recoveryFail),Toast.LENGTH_LONG).show();
                            }
                        }
                    },editText.getText().toString());

                }
                else {
                    Toast.makeText(context,getString(R.string.noInternet),Toast.LENGTH_LONG).show();
                }
            }


        }
    }
    public Boolean validation() {
        return validation.checkEmail(editText,textInputLayout);
    }
}
