package come.wolfpack.sigmapdrone;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by max on 9/17/17.
 */

public class WifiHelper {

    WifiManager wifiManager;

    public WifiHelper (Context context) {

        //  TODO: Check if wifi is enabled

        wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);

        // TODO: Potentially get rid of lock or release it later on
        WifiManager.WifiLock wifiLock = wifiManager.createWifiLock("WakeLockPermissionTest");
        wifiLock.acquire();
    }

    String getSSID () {
        WifiInfo wifi_info = wifiManager.getConnectionInfo();
        return wifi_info.getSSID();
    }

    int getRSSI () {
        WifiInfo wifi_info = wifiManager.getConnectionInfo();
        return wifi_info.getRssi();
    }
}
