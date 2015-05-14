package be.howest.nmct.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.Log;

import be.howest.nmct.Model.Theatre;
import be.howest.nmct.provider.TheatreProvider;

/**
 * Created by Nikita on 17/04/2015.
 */
public class TheatreLoader extends AsyncTaskLoader<Cursor> {
    public TheatreLoader(Context context) {
        super(context);
    }
    private Cursor mCursor;
    private final String[] mColumnNames = new String[]{
            BaseColumns._ID,
            Contract.TheatreColumns.COLUMN_THEATRE_NAME,
            Contract.TheatreColumns.COLUMN_CURRENT_MUSICAL,
            Contract.TheatreColumns.COLUMN_ADDRESS};


    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        if (mCursor == null) {
            loadCursor();
        }
        return mCursor;
    }
    private static Object lock = new Object();

    private void loadCursor() {
        synchronized (lock) {
            if (mCursor != null) return;
            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            int id = 1;
            for(Theatre theatre : TheatreProvider.getTheatresWestEnd()){
                MatrixCursor.RowBuilder row = cursor.newRow();
                row.add(id);
                row.add(theatre.getName());
                row.add(theatre.getCurrentMusical());
                row.add(theatre.getAddress());
                id++;
            }
            mCursor = cursor;
        }
    }
}
