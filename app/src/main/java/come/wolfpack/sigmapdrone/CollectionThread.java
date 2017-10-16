package come.wolfpack.sigmapdrone;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by max on 10/1/17.
 */

public class CollectionThread implements Runnable {
    private static final String TAG = "CollectionThread";

    private Thread thread;
    private static DbHelper dbHelper;
    private static WifiHelper wifiHelper;
    private static CellHelper cellHelper;

    private static PowerManager pm;
    private static PowerManager.WakeLock wl;

    public CollectionThread(Context context) {
        this.dbHelper = new DbHelper(context);
        this.wifiHelper = new WifiHelper(context);
        this.cellHelper = new CellHelper(context);

        this.pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        this.wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
    }

    public void start() {
        wl.acquire();

        if( thread == null ) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        wl.release();

        if( thread != null ) {
            thread.interrupt();
        }
    }

    public void run() {
        try {
            Log.i(TAG, "Thread starting.");
            while( !thread.interrupted() ) {
                collectWifi();
            }
            Log.i(TAG, "Thread stopping.");
        } finally {
            thread = null;
        }
    }

    public void collectWifi () {
        int rssi = wifiHelper.getRSSI();
        CellData cell = cellHelper.getCellData();
        String lte = cell.getLte();
        String cdma = cell.getCdma();
        String gsm = cell.getGsm();

        dbHelper.insertSignal(rssi, lte, cdma, gsm);
    }
}
