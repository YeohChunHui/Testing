package fitnesscompanion.com.View.Profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import fitnesscompanion.com.Controller.FeedbackController;
import fitnesscompanion.com.Model.Feedback;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;

/**
 * Created by Kok Fung on 10/3/2017.
 */

public class FeedbackDialog extends DialogFragment {

    private Context context;
    private AlertDialog.Builder builder;
    private RatingBar ratingBar;
    private EditText editText;
    private Button btnOk,btnCancel;

    private Feedback feedback;
    private SharedPreferences preferences;
    private ConnectionDetector detector;
    private FeedbackController feedbackController;

    public FeedbackDialog(Context context) {
        this.context=context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_feedback,null);

        ratingBar = (RatingBar) rootView.findViewById(R.id.rateBar);
        editText = (EditText) rootView.findViewById(R.id.editComment);
        btnOk = (Button) rootView.findViewById(R.id.btnOk);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        feedback = new Feedback();
        detector = new ConnectionDetector(context);
        feedbackController = new FeedbackController(context);

        btnOk.setOnClickListener(new onClickOk());
        btnCancel.setOnClickListener(new onClickCancel());

        builder.setView(rootView);

        return builder.create();
    }
    private class onClickOk implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(detector.isConnectingToInternet()) {
                feedback.setDesc(editText.getText().toString());
                feedback.setRate(ratingBar.getRating());
                feedbackController.addFeedback(feedback,preferences.getInt("id",0));
                getDialog().dismiss();
            }
            else{
                Toast.makeText(context,getString(R.string.noInternet),Toast.LENGTH_LONG).show();
            }
        }
    }
    private class onClickCancel implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            getDialog().dismiss();
        }
    }
}
