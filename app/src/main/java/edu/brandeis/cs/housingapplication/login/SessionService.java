package edu.brandeis.cs.housingapplication.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by dvili on 11/28/2017.
 */

//This class is called SessionService, but it's really just a utility class that lets the app connect to
//the login database. Simulates sessions. In the future will be adding support for session tokens
//and cookies and all that good stuff.
public class SessionService {
    //Id of the user in the backend. What if there's a change to the backend? Will that be
    //reflected here? Nope.
    public static String CURRENT_USER_ID = ""; //global variables considered harmful

    private Cursor cursor;
    private SQLiteDatabase mDb;

    public SessionService(Context context) {
        DBUserHelper dbUserHelper = new DBUserHelper(context);
        this.mDb = dbUserHelper.getWritableDatabase();
    }

    private Cursor getLoggedInUser() {
        return mDb.query(DBUserContract.DBUserEntry.TABLE_NAME,
               null,
                DBUserContract.DBUserEntry.COLUMN_ISLOGGEDIN + " = ?",
                new String[] {"1"},
                null,
                null,
                null,
                null);
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void updateLoggedInUser(String userName, String userID) {
        CURRENT_USER_ID = userID;
        String SQL_UPDATE_ALL_EXCEPT = "UPDATE " + DBUserContract.DBUserEntry.TABLE_NAME +
                " SET " + DBUserContract.DBUserEntry.COLUMN_ISLOGGEDIN + " =0 WHERE " +
                DBUserContract.DBUserEntry.COLUMN_USERNAME + " != '" + userName + "';";
        mDb.execSQL(SQL_UPDATE_ALL_EXCEPT);
        String SQL_UPDATE_LOGGED_IN_USER = "UPDATE " + DBUserContract.DBUserEntry.TABLE_NAME +
                " SET " + DBUserContract.DBUserEntry.COLUMN_ISLOGGEDIN + " =1 WHERE " +
                DBUserContract.DBUserEntry.COLUMN_USERNAME + " = '" + userName + "';";
        mDb.execSQL(SQL_UPDATE_LOGGED_IN_USER);
    }

    public void addUser(String userName, String userID) {
        ContentValues values = new ContentValues();
        values.put(DBUserContract.DBUserEntry.COLUMN_USERNAME, userName);
        values.put(DBUserContract.DBUserEntry.COLUMN_ISLOGGEDIN, 0);
        values.put(DBUserContract.DBUserEntry.COLUMN_USERID, userID);
        long id = mDb.insert(DBUserContract.DBUserEntry.TABLE_NAME, null, values);
        Log.d("INSERTED ID:", Long.toString(id));
    }

    public void logout() {
        CURRENT_USER_ID = "";
        String SQL_LOGOUT = "UPDATE " + DBUserContract.DBUserEntry.TABLE_NAME +
                " SET " + DBUserContract.DBUserEntry.COLUMN_ISLOGGEDIN + " =0;";
        mDb.execSQL(SQL_LOGOUT);
    }

    public boolean isSomeoneLoggedIn() {
        //If there are no records where isLoggedIn=1, then nobody is logged in
        Cursor cursor = getLoggedInUser();
        int count = cursor.getCount();
        if (count != 0) {
            cursor.moveToFirst();
            Log.d("COLUMN NAMES", Arrays.toString(cursor.getColumnNames()));
            CURRENT_USER_ID = cursor.getString(cursor.getColumnIndex(DBUserContract.DBUserEntry.COLUMN_USERID));
        }
        return count != 0;
    }
}
