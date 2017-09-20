package come.wolfpack.sigmapdrone;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void collectWifi (View view) {

        Context context = getApplicationContext();

        final TextView ssidText = (TextView) findViewById(R.id.textView);
        final TextView rssiText = (TextView) findViewById(R.id.textView2);

        final Handler handler = new Handler();
        final int delay = 100;

        final DbHelper dbHelper = new DbHelper(context);
        final WifiHelper wifiHelper = new WifiHelper(context);

        String ssid = wifiHelper.getSSID();
        ssidText.setText("Connected: " + ssid);

        // TODO: Use a thread pool manager to execute runnables
        handler.postDelayed(new Runnable(){
            public void run(){
                int rssi = wifiHelper.getRSSI();
                rssiText.setText("Wifi Strength: " + String.valueOf(rssi) + " dBm");

                dbHelper.insertSignal(rssi);

                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    public void viewDatabase(View view) {
        Intent intent = new Intent(this, ViewDatabaseActivity.class);
        startActivity(intent);
    }
}
