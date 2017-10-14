package come.wolfpack.sigmapdrone;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by max on 9/13/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "SigMap.db";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + DbContract.Entry.TABLE_NAME + " (" +
                    DbContract.Entry._ID + " INTEGER PRIMARY KEY," +
                    DbContract.Entry.COLUMN_TIME + " TEXT," +
                    DbContract.Entry.COLUMN_WIFI + " REAL," +
                    DbContract.Entry.COLUMN_LTE + " TEXT," +
                    DbContract.Entry.COLUMN_X + " INTEGER," +
                    DbContract.Entry.COLUMN_Y + " INTEGER," +
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
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void insertSignal(int signal, CellData cell) {
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat src = new SimpleDateFormat("HH:mm:ss");
        Date time = Calendar.getInstance().getTime();
        String timestamp = src.format(time);

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.Entry.COLUMN_TIME, timestamp);
        contentValues.put(DbContract.Entry.COLUMN_WIFI, signal);
        contentValues.put(DbContract.Entry.COLUMN_LTE, cell.getLte());
        contentValues.put(DbContract.Entry.COLUMN_X, "0");
        contentValues.put(DbContract.Entry.COLUMN_Y, "0");
        contentValues.put(DbContract.Entry.COLUMN_Z, "0");

        db.insert(DbContract.Entry.TABLE_NAME, null, contentValues);
    }
}
