package be.howest.nmct.evaluationstudents;


import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import be.howest.nmct.evaluationstudents.admin.Student;
import be.howest.nmct.evaluationstudents.admin.StudentAdmin;
import be.howest.nmct.evaluationstudents.be.howest.nmct.evaluationsstudents.loader.Contract;
import be.howest.nmct.evaluationstudents.be.howest.nmct.evaluationsstudents.loader.StudentLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private StudentAdapter mAdapter;

    private OnStudentFragmentListener mListener;
    public interface OnStudentFragmentListener {
        public void onSelectStudent(String mail);
    }

    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       /* try {
            mListener = (OnStudentFragmentListener) activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(activity.toString() + " must implement OnModulesFragmentListener");
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //opdracht geven
        return new StudentLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //loader is klaar
        String[] columns = new String[]{
                Contract.StudentColumns.COLUMN_STUDENT_NAAM,
                Contract.StudentColumns.COLUMN_STUDNET_VOORNAAM,
                Contract.StudentColumns.COLUMN_STUDENT_EMAIL,
                Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL};
        int[]viewIds = new int[]{R.id.tvNaam,R.id.tvVoornaam,R.id.tvEmail,R.id.tvScore};
        mAdapter = new StudentAdapter(getActivity(),R.layout.row_student, null,columns,viewIds,0);
        mAdapter.swapCursor(data);
        setListAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //opdrachtgever is klaar en loader moet vrijgegeven worden!
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor c = (Cursor)mAdapter.getItem(position);
        String email = c.getString(c.getColumnIndex(Contract.StudentColumns.COLUMN_STUDENT_EMAIL));
        if (mListener!=null) mListener.onSelectStudent(email);
    }

    class StudentAdapter extends SimpleCursorAdapter{
        private int layout;

        public StudentAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            View row = inflater.inflate(layout,parent,false);
            ImageView icon = (ImageView) row.findViewById(R.id.imageViewStudent);
            int colnr = cursor.getColumnIndex(Contract.StudentColumns.COLUMN_STUDENT_SCORE_TOTAAL);
            double score = cursor.getDouble(colnr);
            DecimalFormat df = new DecimalFormat("##.00");
            if(score<8){
                icon.setImageResource(R.drawable.student_red);
            }else if(score < 10) {
                icon.setImageResource(R.drawable.student_orange);
            }else {
                icon.setImageResource(R.drawable.student_green);
            }
            return row;
        }
    }
}
