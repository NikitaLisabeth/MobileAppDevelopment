package be.howest.nmct.Loader;

import android.provider.BaseColumns;

/**
 * Created by Nikita on 17/04/2015.
 */
public class Contract {
    public interface TheatreColumns extends BaseColumns {
        public static final String COLUMN_THEATRE_NAME = "theatre_name";
        public static final String COLUMN_CURRENT_MUSICAL = "current_musical";
        public static final String COLUMN_ADDRESS = "theatre_address";
    }

    public interface TheatreDetailColumns extends BaseColumns {
        public static final String COLUMN_THEATRE_NAME = "theatre_name";
        public static final String COLUMN_CURRENT_MUSICAL = "current_musical";
        public static final String COLUMN_ADDRESS = "theatre_address";
        public static final String COLUMN_STAGEDOOR = "theatre_stagedoor";
        public static final String COLUMN_LOCATION = "theatre_location";
    }

}
