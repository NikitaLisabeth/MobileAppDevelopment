package be.howest.nmct.evaluationstudents.be.howest.nmct.evaluationsstudents.loader;

import android.provider.BaseColumns;

/**
 * Created by Nikita on 3/04/2015.
 */
public final class Contract {
    public interface StudentColumns extends BaseColumns{
        public static final String COLUMN_STUDENT_NAAM = "student_naam";
        public static final String COLUMN_STUDNET_VOORNAAM = "student_voornaam";
        public static final String COLUMN_STUDENT_EMAIL = "student_email";
        public static final String COLUMN_STUDENT_SCORE_TOTAAL = "student_score_totaal";
    }
}
