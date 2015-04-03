package be.howest.nmct.demofragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Stijn on 11/02/2015.
 */
public class Fragment2 extends Fragment {

    private static final String ARG_STUDENT_NAAM = "naam_student";


    //parameters voor fragment2
    private String naamStudent;

    private Button buttonVerstuurNaarFragment1;
    private EditText editTextScore;
    private TextView textViewNaamStudent;

    //link naar activity
    private OnFragment2Listener onFragment2Listener;

    //vereist
    public Fragment2() {
    }

    public static Fragment2 newInstance(String naamStudent) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_STUDENT_NAAM, naamStudent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFragment2Listener = (OnFragment2Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragment1Listener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            naamStudent = getArguments().getString(ARG_STUDENT_NAAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, container, false);
        editTextScore = (EditText) v.findViewById(R.id.editTextScore);
        buttonVerstuurNaarFragment1 = (Button) v.findViewById(R.id.buttonVerstuurNaarFragment1);
        buttonVerstuurNaarFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verstuurDataNaarFragment1();
            }
        });
        textViewNaamStudent = (TextView) v.findViewById(R.id.textViewNaamStudent);
        if (naamStudent != null) textViewNaamStudent.setText("Naam Student: " + naamStudent);
        return v;
    }

    private void verstuurDataNaarFragment1() {
        float score = Float.parseFloat(this.editTextScore.getText().toString());
        if (onFragment2Listener != null)
            onFragment2Listener.onNewScoreStudent(score);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragment2Listener = null;
    }


    public String getNaamStudent() {
        return naamStudent;
    }

    public void setNaamStudent(String naamStudent) {
        this.naamStudent = naamStudent;
    }


    public interface OnFragment2Listener {
        public void onNewScoreStudent(float score);
    }
}
