package fitnesscompanion.com.Util;


import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.regex.Pattern;

import fitnesscompanion.com.R;


/**
 * Created by Kok Fung on 9/28/2017.
 */

public class Validation {

    private Context context;

    public Validation(Context context) {
        this.context=context;
    }

    public boolean checkEmpty(String text) {

        return text.trim().length()==0;
    }
    public boolean checkLength(int size,int textLength) {

        return textLength<size;
    }
    public boolean checkName(EditText editText, TextInputLayout textInputLayout){
        textInputLayout.setError(null);
        String text = editText.getText().toString();
        if(checkEmpty(text))
        {
            textInputLayout.setError(context.getString(R.string.required));
            return false;
        }
        else if(!Pattern.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", text)){
            textInputLayout.setError(context.getString(R.string.error_name));
            return false;
        }
        return true;
    }

    public Boolean checkEmail(EditText editText, TextInputLayout textInputLayout){
        textInputLayout.setError(null);
        String text = editText.getText().toString();
        if(checkEmpty(text))
        {
            textInputLayout.setError(context.getString(R.string.required));
            return false;
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()){
            textInputLayout.setError(context.getString(R.string.error_email));
            return false;
        }
           return true;
    }
    public Boolean checkPassword(EditText editText, TextInputLayout textInputLayout) {
        textInputLayout.setError(null);
        String text = editText.getText().toString();
        if(checkEmpty(text))
        {
            textInputLayout.setError(context.getString(R.string.required));
            return false;
        }
        else if(checkLength(6,text.length())) {
            textInputLayout.setError(context.getString(R.string.error_pass2));
            return false;
        }
        return true;
    }
    public Boolean checkConfirmPassword(EditText editText, EditText editText2,TextInputLayout textInputLayout){
        textInputLayout.setError(null);
        String text = editText.getText().toString();
        String text2 = editText2.getText().toString();
        if(!checkPassword(editText,textInputLayout)){
            return false;
        }
        else if(!text.equals(text2)){
            textInputLayout.setError(context.getString(R.string.error_pass3));
            return false;
        }
        return true;
    }


}
