package fitnesscompanion.com.View;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Controller.UserController;
import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.ConnectionDetector;
import fitnesscompanion.com.Util.DatePicker;
import fitnesscompanion.com.Util.ImageScale;
import fitnesscompanion.com.Util.Listener;
import fitnesscompanion.com.Util.Validation;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.editName)  EditText editName;
    @BindView(R.id.editEmail)  EditText editEmail;
    @BindView(R.id.editPass)  EditText editPass;
    @BindView(R.id.editPass2)  EditText editPass2;
    @BindView(R.id.editDob) EditText editDob;
    @BindView(R.id.imageProfile)  ImageView imageProfile;
    @BindView(R.id.rgbGender) RadioGroup rgbGender;


    @BindView(R.id.txtGender)  TextView txtGender;
    @BindView(R.id.layoutName)  TextInputLayout layoutName;
    @BindView(R.id.layoutEmail)  TextInputLayout layoutEmail;
    @BindView(R.id.layoutPass)  TextInputLayout layoutPass;
    @BindView(R.id.layoutPass2)  TextInputLayout layoutPass2;
    @BindView(R.id.layoutDob)  TextInputLayout layoutDob;

    private boolean isImage = false;
    private Validation validation;
    private User user;
    private ConnectionDetector detector;

    private Bitmap bitmapProfile;
    private ImageScale imageScale;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(R.string.signUp));
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        validation= new Validation(this);
        detector = new ConnectionDetector(this);
        userController = new UserController(this);
        user = new User();


        new Listener(this).setListenerName(editName,layoutName);
        new Listener(this).setListenerEmail(editEmail,layoutEmail);
        new Listener(this).setListenerPass(editPass,layoutPass);
        new Listener(this).setListenerCPass(editPass2,editPass,layoutPass2);

        editDob.addTextChangedListener(new onTextWatcherDob());
        rgbGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId!=-1)
                    txtGender.setText(null);
            }
        });
    }
    public void onClickRegister(View view) {

        if(validation()){
            if(detector.isConnectingToInternet()){
                user.setName(editName.getText().toString());
                user.setEmail(editEmail.getText().toString());
                user.setDob(editDob.getText().toString());
                user.setPassword(editPass.getText().toString());

                if(rgbGender.getCheckedRadioButtonId()== R.id.rbMale)
                    user.setGender(0);
                else
                    user.setGender(1);

                if(isImage) {
                    imageProfile.setDrawingCacheEnabled(true);
                    imageProfile.buildDrawingCache();
                    user.encodeImagetoString(bitmapProfile);
                }
                else{
                    user.setImage("No");
                }
                upload();
            }
            else
                    Toast.makeText(this,getString(R.string.noInternet),Toast.LENGTH_LONG).show();
        }
    }
    public void onClickClear(View view) {
        clear();
    }
    public void onClickDobDialog(View view) {
        DatePicker datePicker = new DatePicker(this,editDob);
        datePicker.show(getFragmentManager(),"Date Picker");

    }
    public void onClickImageDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.addPhoto)).setItems(getResources().
                getStringArray(R.array.photoArray),new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0) {
                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),0);
                }
                else if(which==1){
                    startActivityForResult(new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI),1);
                }
            }
        });
        builder.show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        isImage = false;
        imageScale = new ImageScale();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            isImage = true;
            bitmapProfile= imageScale.getResizedBitmap(bitmap, 400);
            imageProfile.setImageBitmap(bitmapProfile);
        } else if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            InputStream imageStream = null;
            isImage = true;
            Uri uri = data.getData();
            try {
                imageStream = getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            bitmapProfile=imageScale.getResizedBitmap(selectedImage, 400);
            imageProfile.setImageBitmap(bitmapProfile);

        }

    }
    public void clearValidation() {
        txtGender.setText(null);
        layoutName.setError(null);
        layoutName.setError(null);
        layoutEmail.setError(null);
        layoutPass.setError(null);
        layoutPass2.setError(null);
        layoutDob.setError(null);
    }
    public void clear() {
        isImage = false;
        imageProfile.setImageDrawable(getResources().getDrawable(R.drawable.logo_drawer));

        editName.setText(null);
        editEmail.setText(null);
        editPass.setText(null);
        editPass2.setText(null);
        editDob.setText(null);
        rgbGender.clearCheck();
        clearValidation();
    }
    public Boolean checkGender() {
        txtGender.setText(null);

        if(rgbGender.getCheckedRadioButtonId()==-1){
            txtGender.setText(getString(R.string.required));
            return false;
        }

        return true;
    }
    public Boolean checkDob() {
        layoutDob.setError(null);

        if(editDob.getText().toString().trim().length()==0) {
            layoutDob.setError(getString(R.string.required));
            return false;
        }
        return true;
    }
    public Boolean validation() {
        return validation.checkName(editName,layoutName)&
        validation.checkEmail(editEmail,layoutEmail)&
        validation.checkPassword(editPass,layoutPass)&
        validation.checkConfirmPassword(editPass2,editPass,layoutPass2)&checkGender()&checkDob();
    }
    private class onTextWatcherDob implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(checkDob())
                layoutDob.setError(null);
        }
    }

    public void upload() {
        userController.checkMail(new UserController.VolleyCallCheckMail() {
            @Override
            public void onSuccess(int count) {
                if(count==0) {
                    userController.signUp(user);
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),getString(R.string.signUpEmail),Toast.LENGTH_LONG).show();
                }
            }
        },editEmail.getText().toString());


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,LoginActivity.class));
    }
}
