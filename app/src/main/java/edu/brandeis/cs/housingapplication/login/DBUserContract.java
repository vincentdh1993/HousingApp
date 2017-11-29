package edu.brandeis.cs.housingapplication.login;

import android.provider.BaseColumns;

/**
 * Created by dvili on 11/28/2017.
 */

public class DBUserContract {
    public DBUserContract() {

    }

    public static class DBUserEntry implements BaseColumns {
        public static final String TABLE_NAME = "current_user";
        public static final String COLUMN_USERNAME = "usernames";
        public static final String COLUMN_ISLOGGEDIN = "loggedInStatus";
    }
}
