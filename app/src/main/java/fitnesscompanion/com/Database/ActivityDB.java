package fitnesscompanion.com.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fitnesscompanion.com.Model.Activity;

/**
 * Created by Kok Fung on 9/27/2017.
 */

public class ActivityDB {
    private Context context;
    private FitnessDB fitnessDB;

    public ActivityDB(Context context) {
        this.context=context;
        fitnessDB = new FitnessDB(context);
    }
    public void deleteData() {
        SQLiteDatabase db = fitnessDB.getWritableDatabase();
        db.delete("Activity",null,null);
        db.close();
    }
    public void insertData(ArrayList<Activity> arrayList) {
        SQLiteDatabase db = fitnessDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for(int x=0; x<arrayList.size();x++) {
            contentValues.put("no",arrayList.get(x).getNo());
            contentValues.put("name",arrayList.get(x).getName());
            contentValues.put("description",arrayList.get(x).getDescription());
            contentValues.put("calories",arrayList.get(x).getCalories());
            contentValues.put("recommandTime",arrayList.get(x).getTime());
            contentValues.put("image",arrayList.get(x).getImage());
            contentValues.put("maxHR",arrayList.get(x).getNo());

            db.insert("Activity",null,contentValues);
        }
        db.close();

    }
    public ArrayList<Activity> getAllData() {
        ArrayList<Activity> arrayList = new ArrayList<Activity>();
        SQLiteDatabase db = fitnessDB.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Activity",null);

        if(cursor.moveToFirst()) {
            do{

                arrayList.add(new Activity(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6)));
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
    public String[] getName() {
        String[] name = new String[getAllData().size()];
        SQLiteDatabase db = fitnessDB.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT name FROM Activity ORDER BY name ASC",null);
        int x=0;
        if(cursor.moveToFirst()) {
            do{
                name[x] = cursor.getString(0);
                x++;
            }while(cursor.moveToNext());
        }

        return name;
    }
}
