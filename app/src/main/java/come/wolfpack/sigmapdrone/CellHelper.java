package come.wolfpack.sigmapdrone;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;

import java.util.List;

import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;

/**
 * Created by max on 10/11/17.
 */

public class CellHelper {

    TelephonyManager cellManager;

    public CellHelper(Context context) {
        cellManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public CellData getCellData() {
        List<CellInfo> ci = cellManager.getAllCellInfo();
        CellData cellData = new CellData();

        if(ci != null){
            for (int i = 0; i < ci.size(); i++) {
                if (ci.get(i).isRegistered()) {
                    if (ci.get(i) instanceof CellInfoWcdma) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) ci.get(i);
                        CellSignalStrengthWcdma cellSignalStrengthWcdma = cellInfoWcdma.getCellSignalStrength();
                        cellData.setCdma(String.valueOf(cellSignalStrengthWcdma.getDbm()));
                    } else if (ci.get(i) instanceof CellInfoGsm) {
                        CellInfoGsm cellInfogsm = (CellInfoGsm) ci.get(i);
                        CellSignalStrengthGsm cellSignalStrengthGsm = cellInfogsm.getCellSignalStrength();
                        cellData.setGsm(String.valueOf(cellSignalStrengthGsm.getDbm()));
                    } else if (ci.get(i) instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) ci.get(i);
                        CellSignalStrengthLte cellSignalStrengthLte = cellInfoLte.getCellSignalStrength();
                        cellData.setLte(String.valueOf(cellSignalStrengthLte.getDbm()));
                    }
                }
            }
        }

        return cellData;
    }
}
