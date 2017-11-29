package edu.brandeis.cs.housingapplication.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dvili on 11/28/2017.
 */

//TODO: this class basically needs to connect to the database and see if there is some username/pw info
public class SessionService {
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

    public boolean isSomeoneLoggedIn() {
        //If there are no records where isLoggedIn=1, then nobody is logged in
        return getLoggedInUser().getCount() != 0;
    }
}
