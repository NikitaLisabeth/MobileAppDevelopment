package be.howest.nmct.studentenhuizenkortrijk.loader;

import android.provider.BaseColumns;

/**
 * Created by Nikita on 24/04/2015.
 */
public class Contract {
    public interface StudentenKamersColumns extends BaseColumns {
        public static final String COLUMN_ADRES = "studentenkamer_adres";
        public static final String COLUMN_HUISNUMMER = "studentenkamer_huisnummer";
        public static final String COLUMN_GEMEENTE = "studentenkamer_gemeente";
        public static final String COLUMN_KAMERS = "studentenkamer_kamers";
     }
}
