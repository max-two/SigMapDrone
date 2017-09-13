package come.wolfpack.sigmapdrone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by max on 9/13/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SigMap.db";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + DbContract.Entry.TABLE_NAME + " (" +
                    DbContract.Entry._ID + " INTEGER PRIMARY KEY," +
                    DbContract.Entry.COLUMN_TIME + " NUMERIC," +
                    DbContract.Entry.COLUMN_WIFI + " REAL," +
                    DbContract.Entry.COLUMN_LTE + " REAL)" +
                    DbContract.Entry.COLUMN_X + " INTEGER)" +
                    DbContract.Entry.COLUMN_Y + " INTEGER)" +
                    DbContract.Entry.COLUMN_Z + " INTEGER)";


    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + DbContract.Entry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
