package be.howest.nmct.evaluationstudents;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import be.howest.nmct.evaluationstudents.admin.Student;
import be.howest.nmct.evaluationstudents.admin.StudentAdmin;
import be.howest.nmct.evaluationstudents.be.howest.nmct.evaluationsstudents.loader.Contract;
import be.howest.nmct.evaluationstudents.be.howest.nmct.evaluationsstudents.loader.ModulePuntLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public ModulePuntAdapter mAdapter;
    public StudentDetailsFragment() {
        // Required empty public constructor
    }

    public static final String ARG_STUDENT_DETAIL = "student_detail";
    public TextView tvNaam;
    public TextView tvVoornaam;
    public GridView gridView;
    public TextView tvDiplomaGraad;

    public static StudentDetailsFragment newInstance(String email) {
        StudentDetailsFragment fragment = new StudentDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STUDENT_DETAIL, email);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_student_details, container, false);
        tvNaam = (TextView) v.findViewById(R.id.tvDetailNaam);
        tvVoornaam = (TextView) v.findViewById(R.id.tvDetailVoornaam);
        tvDiplomaGraad = (TextView) v.findViewById(R.id.tvDetailDiplomaGraad);
        gridView = (GridView) v.findViewById(R.id.gridView);

        Student selectedStudent = StudentAdmin.getInstance().getStudent(getArguments().getString(ARG_STUDENT_DETAIL));
        tvNaam.setText(selectedStudent.getNaamStudent());
        tvVoornaam.setText(selectedStudent.getVoornaamStudent());
        return v;
    }

    private void printDiplomaGraad(Cursor data) {
        ArrayList<Double> scoreModules = new ArrayList<>();
        ArrayList<Integer> studiepuntenModules = new ArrayList<>();

        int aantalRijen = data.getCount();

        for(int i = 0; i < aantalRijen; i++)
        {
            data.moveToPosition(i);
            int colnr1 = data.getColumnIndex(Contract.ModulePuntColumns.COLUMN_MODULE_STUDIEPUNTEN);
            studiepuntenModules.add(data.getInt(colnr1));

            int colnr2 = data.getColumnIndex(Contract.ModulePuntColumns.COLUMN_MODULE_SCORE);
             scoreModules.add(data.getDouble(colnr2));
        }

        int iTotaalStudiepunten = 0;
        for(int aantalSP : studiepuntenModules)
        {
            iTotaalStudiepunten += aantalSP;
        }

        double dTotaleScore = 0;
        for(int iTeller = 0; iTeller < scoreModules.size(); iTeller+=1)
        {
            double dGewicht = (double) studiepuntenModules.get(iTeller) / iTotaalStudiepunten;
            dTotaleScore += (scoreModules.get(iTeller) * dGewicht);
        }
        String diplomaGraad = Student.getDiplomagraad((float)dTotaleScore);

        tvDiplomaGraad.setText(Student.DIPLOMAGRAAD.getDiplomagraad((float)dTotaleScore).getOmschrijving());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new ModulePuntAdapter(getActivity(), R.layout.cel_module,null, new String[]{
                Contract.ModulePuntColumns.COLUMN_MODULE_NAAM,
                Contract.ModulePuntColumns.COLUMN_MODULE_SCORE,
                Contract.ModulePuntColumns.COLUMN_MODULE_STUDIEPUNTEN}
                , new int[]{
                R.id.tvCelModule, R.id.tvCelPunt}, 0);

        gridView.setAdapter(mAdapter);

        //De loader wordt hier geactiveerd
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new ModulePuntLoader(getActivity(), getArguments().getString(ARG_STUDENT_DETAIL));

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        printDiplomaGraad(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public class ModulePuntAdapter extends SimpleCursorAdapter {
        private int layout;
        public TextView lblModuleNaam;
        public TextView lblModulePunt;

        public ModulePuntAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            super.bindView(view, context, cursor);

            this.lblModuleNaam = (TextView) view.findViewById(R.id.tvCelModule);
            this.lblModulePunt = (TextView) view.findViewById(R.id.tvCelPunt);
        }
    }


}
