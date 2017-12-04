package come.wolfpack.sigmapdrone;

/**
 * Created by max on 12/3/17.
 */

public class Coordinate {
    int x;
    int y;
    int wifi;
    int lte;

    public Coordinate() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getLte() {
        return lte;
    }

    public void setLte(int lte) {
        this.lte = lte;
    }
}
