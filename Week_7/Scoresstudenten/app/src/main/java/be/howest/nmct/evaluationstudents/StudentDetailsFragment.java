package be.howest.nmct.evaluationstudents;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDetailsFragment extends Fragment {


    public StudentDetailsFragment() {
        // Required empty public constructor
    }

    public static final String ARG_STUDENT_DETAIL = "student_detail";
    public TextView tvNaam;
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
        tvNaam = (TextView) v.findViewById(R.id.tvNaam);
        tvDiplomaGraad = (TextView) v.findViewById(R.id.tvDiplomaGraad);
        gridView = (GridView) v.findViewById(R.id.gridView);

        return v;
    }


}
