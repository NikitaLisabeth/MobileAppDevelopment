package be.howest.nmct.politiecontroles.loader;

import android.provider.BaseColumns;

/**
 * Created by Nikita on 20/05/2015.
 */
public class Contract {
    public interface SnelheidsControleColumns extends BaseColumns {
        public static final String COLUMN_CONTROLE_MAAND = "controle_maand";
        public static final String COLUMN_CONTROLE_STRAAT = "controle_straat";
        public static final String COLUMN_CONTROLE_POSTCODE = "controle_postcode";
        public static final String COLUMN_CONTROLE_GEMEENTE = "controle_gemeente";
        public static final String COLUMN_CONTROLE_AANTAL = "controle_aantal";
        public static final String COLUMN_CONTROLE_GEPASSEERDEVOERTUIGEN = "controle_gepasseerde_voertuigen";
        public static final String COLUMN_CONTROLE_OVERTREDINGEN = "controle_in_overtreding";
        public static final String COLUMN_CONTROLE_X = "controle_x";
        public static final String COLUMN_CONTROLE_Y = "controle_y";
    }
}
