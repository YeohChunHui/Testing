package fitnesscompanion.com.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fitnesscompanion.com.Model.User;

/**
 * Created by Kok Fung on 10/3/2017.
 */

public class UserDB {
    private Context context;
    private FitnessDB fitnessDB;
    public UserDB(Context context) {
        this.context=context;
        fitnessDB = new FitnessDB(context);
    }
    public void deleteData() {
        SQLiteDatabase db = fitnessDB.getWritableDatabase();
        db.delete("User",null,null);
        db.close();
    }
    public void insertData(User user) {
        SQLiteDatabase db = fitnessDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id",user.getId());
        contentValues.put("name",user.getName());
        contentValues.put("gender",user.getGender());
        contentValues.put("dob",user.getDob());
        contentValues.put("image",user.getImage());
        contentValues.put("height",user.getHeight());
        contentValues.put("weight",user.getWeight());
        contentValues.put("email",user.getEmail());
        contentValues.put("password",user.getPassword());

        db.insert("User",null,contentValues);
        db.close();;
    }
    public User getData() {
        User user = new User();

        SQLiteDatabase db = fitnessDB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User",null);
        if(cursor.moveToFirst()) {
            do{
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setGender(cursor.getInt(2));
                user.setDob(cursor.getString(3));
                user.setImage(cursor.getString(4));
                user.setHeight(cursor.getInt(5));
                user.setWeight(cursor.getInt(6));
                user.setEmail(cursor.getString(7));
                user.setPassword(cursor.getString(8));

            }while (cursor.moveToNext());
        }
        db.close();
        return user;
    }
}
