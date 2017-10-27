package fitnesscompanion.com.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Kok Fung on 9/30/2017.
 */

public class User {
    private int id;
    private String name;
    private int gender;
    private String dob;
    private String image;
    private int height;
    private int weight;
    private String email;
    private String password;


    public User() {
    }

    public User(int id, String name, int gender, String dob, String image, int height, int weight, String email, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.image = image;
        this.height = height;
        this.weight = weight;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenderText() {
        String[] genderArray = {"Male","Female"};
        return genderArray[gender];
    }
    public Bitmap getImageFromJSon(){
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
    public void encodeImagetoString(Bitmap bitmap) {

        if(bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byte_arr = stream.toByteArray();
            image = Base64.encodeToString(byte_arr, Base64.DEFAULT);
        }
    }
    public int getAge() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-mm");
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        try {
            birthDate.setTime(dateFormat.parse(dob));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int todayYear = today.get(Calendar.YEAR);
        int birthDateYear = birthDate.get(Calendar.YEAR);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int birthDateMonth = birthDate.get(Calendar.MONTH);
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
        int age = todayYear - birthDateYear;

        if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)){
            age--;

        } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)){
            age--;
        }
        return age;
    }
    public double getBmi() {
        return Double.valueOf(new DecimalFormat("##.#").format((weight / Math.pow(height,2))*10000));
    }
    public int getBmiString() {
        double bmi  = getBmi();
        if(bmi<18.5)
            return 0;
        else if(bmi>=18.5 & bmi<=24.9)
            return 1;
        else if(bmi>=25)
            return 2;

        return -1;
    }
}
