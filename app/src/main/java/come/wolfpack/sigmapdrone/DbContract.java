package come.wolfpack.sigmapdrone;

import android.provider.BaseColumns;

/**
 * Created by max on 9/13/17.
 */

public final class DbContract {
    private DbContract() {}

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "data";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_WIFI = "wifi";
        public static final String COLUMN_LTE = "lte";
        public static final String COLUMN_X = "x";
        public static final String COLUMN_Y = "y";
        public static final String COLUMN_Z = "z";
    }
}