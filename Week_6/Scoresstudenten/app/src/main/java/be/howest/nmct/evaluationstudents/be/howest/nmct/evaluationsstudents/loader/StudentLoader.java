package be.howest.nmct.evaluationstudents.be.howest.nmct.evaluationsstudents.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;

import java.util.List;
import java.util.Objects;

import be.howest.nmct.evaluationstudents.admin.Student;
import be.howest.nmct.evaluationstudents.admin.StudentAdmin;

/**
 * Created by Nikita on 3/04/2015.
 */
public class StudentLoader extends AsyncTaskLoader<Cursor> {
    private Cursor mCursor;
    private final String[] mColumnNames = new String []{
            BaseColumns._ID,
            Contract.StudentColumns.COLUMN_STUDENT_NAAM,
            Contract.StudentColumns.COLUMN_STUDNET_VOORNAAM,
            Contract.StudentColumns.COLUMN_STUDENT_EMAIL,
            Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL};
    private static Object lock = new Object();

    public StudentLoader(Context context) {
        super(context);
    }

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


    private void loadCursor(){
        synchronized (lock){
            if(mCursor!=null)return;

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            int id = 1;
            List<Student> studenten = StudentAdmin.getInstance().getStudenten();
            for(Student student: studenten){
                MatrixCursor.RowBuilder row = cursor.newRow();
                row.add(id);
                row.add(student.getNaamStudent());
                row.add(student.getVoornaamStudent());
                row.add(student.getEmailStudent());
                row.add(Math.round(student.getTotaleScoreStudent()));
                id++;
            }
            mCursor = cursor;
        }
    }
}
