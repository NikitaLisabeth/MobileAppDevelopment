package be.howest.nmct.evaluationstudents.be.howest.nmct.evaluationsstudents.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;

import java.util.List;

import be.howest.nmct.evaluationstudents.admin.ModulePunt;
import be.howest.nmct.evaluationstudents.admin.Student;
import be.howest.nmct.evaluationstudents.admin.StudentAdmin;

/**
 * Created by Nikita on 19/05/2015.
 */
public class ModulePuntLoader extends AsyncTaskLoader<Cursor> {

    private Cursor mCursor;
    private final String[] mColumnNames = new String []{
            BaseColumns._ID,
            Contract.ModulePuntColumns.COLUMN_MODULE_NAAM,
            Contract.ModulePuntColumns.COLUMN_MODULE_SCORE,
            Contract.ModulePuntColumns.COLUMN_MODULE_STUDIEPUNTEN};
    private static Object lock = new Object();


    @Override
    protected void onStartLoading() {
        if(mCursor != null){
            deliverResult(mCursor);
        }
        if(takeContentChanged() || mCursor == null){
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        //METHODDE DAT IN DE ACHTERGROND DRAAIT
        if(mCursor == null){
            loadCursor();
        }
        return mCursor;
    }

    public String email;

    public ModulePuntLoader(Context context, String email) {
        super(context);
        this.email = email;
    }

    private void loadCursor() {
        synchronized (lock)
        {
            if(mCursor != null) return;

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            int id = 1;

            for(ModulePunt mp : StudentAdmin.getInstance().getStudent(email).getScoresStudent().values())
            {
                // data die het manneke (loader) heeft opgehaald gaan plaatsen in een tabel formaat (in de matrixcursor)
                MatrixCursor.RowBuilder row = cursor.newRow();
                //UNIEK ID IS VEREIST!!! VANDAAR ONZE ID++!! BELANGRIJK!!
                row.add(id);
                row.add(mp.getModule());
                row.add(mp.getScore());
                row.add(mp.getAantalStudiePunten());
                id++;
            }

            mCursor = cursor;
        }
    }

}
