package fitnesscompanion.com.View.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Database.UserDB;
import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;
import fitnesscompanion.com.Util.DatePicker;
import fitnesscompanion.com.Util.ImageScale;
import fitnesscompanion.com.View.Home.MenuActivity;

public class ProfileActivity extends AppCompatActivity{
    @BindView(R.id.imageProfile) ImageView imageView;
    @BindView(R.id.txtName) TextView txtName;
    @BindView(R.id.txtGender) TextView txtGender;
    @BindView(R.id.txtDob) TextView txtDob;
    @BindView(R.id.txtAge) TextView txtAge;
    @BindView(R.id.txtEmail) TextView txtEmail;
    @BindView(R.id.txtHeight) TextView txtHeight;
    @BindView(R.id.txtWeight) TextView txtWeight;

    private UserDB userDB;
    private User user;
    private AlertDialog dialog;
    private DatePicker datePicker;
    private EditNameDialog editNameDialog;
    private SeekBarDialog seekBarDialog;

    private boolean isImage = false;
    private Bitmap bitmapProfile;
    private ImageScale imageScale;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle(getResources().getString(R.string.profile));
        ButterKnife.bind(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        getData();
    }
    public void getData() {
        userDB = new UserDB(this);
        user = userDB.getData();

        imageView.setImageBitmap(user.getImageFromJSon());
        txtName.setText(user.getName());
        txtGender.setText(getResources().getStringArray(R.array.genderArray)[user.getGender()]);
        txtDob.setText(user.getDob());
        txtAge.setText(String.valueOf(user.getAge()));
        txtEmail.setText(user.getEmail());
        txtHeight.setText(String.valueOf(user.getHeight()));
        txtWeight.setText(String.valueOf(130));
    }

    public void onClickEdit(View view) {

        switch (view.getId()){

            case R.id.imageProfile:
                showImageDialog();
                break;
            case R.id.btnName:
                editNameDialog = new EditNameDialog(this);
                editNameDialog.show(getFragmentManager(),"Edit Name");
                break;
            case R.id.btnGender:
                showGenderDialog();
                break;
            case R.id.btnDob:
                DatePicker datePicker = new DatePicker(this,txtDob);
                datePicker.show(getFragmentManager(),"Date Picker");
                break;
            case R.id.btnHeight:
                seekBarDialog = new SeekBarDialog(this,0);
                seekBarDialog.show(getFragmentManager(),"Height Picker");
                break;
            case R.id.btnWeight:
                seekBarDialog = new SeekBarDialog(this,1);
                seekBarDialog.show(getFragmentManager(),"Weight Picker");
                break;
            default:break;
        }
        getData();

    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MenuActivity.class).putExtra("index",3));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showGenderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Gender")
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.setSingleChoiceItems(getResources().getStringArray(R.array.genderArray),user.getGender(),new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showImageDialog() {
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
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        isImage = false;
        imageScale = new ImageScale();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            isImage = true;
            bitmapProfile= imageScale.getResizedBitmap(bitmap, 400);
            imageView.setImageBitmap(bitmapProfile);
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
            imageView.setImageBitmap(bitmapProfile);
        }
    }
}
