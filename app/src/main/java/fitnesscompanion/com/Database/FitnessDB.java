package fitnesscompanion.com.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kok Fung on 9/27/2017.
 */

public class FitnessDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FitnessDb";
    private static final int DATABASE_VERSION = 1;

    private static final String queryCreateActivityVer = "CREATE TABLE ActivityVersion(ver INTEGER);";
    private static final String queryCreateTableActivity="CREATE TABLE Activity(no int " +
            "PRIMARY KEY ,name VARCHAR(255) ,description VARCHAR(255)," +
            "calories INTEGER,recommandTime INTEGER,image MEDIUMTEXT,maxHR INTEGER);";
    private static final String queryCreateTableUser = "CREATE TABLE User (id int primary key,name varchar(255)," +
            "gender int,dob varchar(10),image MEDIUMTEXT,height int,weight int,email varchar(255)," +
            "password varchar(20),active int,createdDate varchar(50));";



    private static final String queryInsertActivityVer = "INSERT INTO ActivityVersion VALUES (0);";
    private static final String dropTableActivityVersion = "DROP TABLE IF EXISTS ActivityVersion;";
    private static final String dropTableActivity = "DROP TABLE IF EXISTS Activity;";
    private static final String dropTableUser = "DROP TABLE IF EXISTS User;";


    public FitnessDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryCreateActivityVer);
        db.execSQL(queryCreateTableActivity);
        db.execSQL(queryCreateTableUser);

        db.execSQL(queryInsertActivityVer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTableActivityVersion);
        db.execSQL(dropTableActivity);
        db.execSQL(dropTableUser);
        onCreate(db);

    }

}
