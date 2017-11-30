package edu.brandeis.cs.housingapplication.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dvili on 11/28/2017.
 */

public class DBUserHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_login.db";
    private static final int VERSION = 2;

    public DBUserHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CURRENT_USER_TABLE = "CREATE TABLE " +
                DBUserContract.DBUserEntry.TABLE_NAME + " ( " +
                DBUserContract.DBUserEntry._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                DBUserContract.DBUserEntry.COLUMN_USERNAME + " TEXT NOT NULL, " +
                DBUserContract.DBUserEntry.COLUMN_USERID + " INTEGER NOT NULL, " +
                DBUserContract.DBUserEntry.COLUMN_ISLOGGEDIN + " INTEGER NOT NULL )";
        db.execSQL(SQL_CREATE_CURRENT_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBUserContract.DBUserEntry.TABLE_NAME);
        onCreate(db);
    }
}
