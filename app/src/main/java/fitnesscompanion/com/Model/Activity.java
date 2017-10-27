package fitnesscompanion.com.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Kok Fung on 9/27/2017.
 */

public class Activity {
    private int no;
    private String name;
    private String description;
    private int calories;
    private int time;
    private String image;
    private int maxHR;

    public Activity() {
    }

    public Activity(int no, String name, String description, int calories, int time, String image, int maxHR) {
        this.no = no;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.time = time;
        this.image = image;
        this.maxHR = maxHR;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMaxHR() {
        return maxHR;
    }

    public void setMaxHR(int maxHR) {
        this.maxHR = maxHR;
    }

    public Bitmap getImageFromJSon(){
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
