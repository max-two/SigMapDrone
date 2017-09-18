package come.wolfpack.sigmapdrone;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DbContract.Entry._ID,
                DbContract.Entry.COLUMN_TIME,
                DbContract.Entry.COLUMN_WIFI,
        };

        String sortOrder = DbContract.Entry.COLUMN_TIME;

        Cursor cursor = db.query(
                DbContract.Entry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List ids = new ArrayList<>();
        List columnTime = new ArrayList<>();
        List columnWifi = new ArrayList<>();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.Entry._ID));
            int time = cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.Entry.COLUMN_TIME));
            int wifi = cursor.getInt(cursor.getColumnIndexOrThrow(DbContract.Entry.COLUMN_WIFI));
            ids.add(id);
            columnTime.add(time);
            columnWifi.add(wifi);
        }
        cursor.close();

        TableLayout tbl = (TableLayout)findViewById(R.id.RHE);

        for (int i = 0; i < columnTime.size(); i++) {
            TableRow newRow = new TableRow(this);

            TextView txtId = new TextView(this);
            txtId.setText(ids.get(i).toString());
            txtId.setPadding(5, 0, 0, 0);
            newRow.addView(txtId);
            TextView txtA = new TextView(this);
            txtA.setText(columnTime.get(i).toString());
            txtA.setPadding(5, 0, 0, 0);
            newRow.addView(txtA);
            TextView txtB = new TextView(this);
            txtB.setText(columnWifi.get(i).toString());
            txtB.setPadding(5, 0, 0, 0);
            newRow.addView(txtB);

            tbl.addView(newRow);
        }
    }
}
