package come.wolfpack.sigmapdrone;

/**
 * Created by max on 10/11/17.
 */

public class CellData {
    String lte;
    String cdma;
    String gsm;


    public CellData() {}

    public String getLte() {
        return lte;
    }

    public void setLte(String lte) {
        this.lte = lte;
    }

    public String getCdma() {
        return cdma;
    }

    public void setCdma(String cdma) {
        this.cdma = cdma;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }
}
