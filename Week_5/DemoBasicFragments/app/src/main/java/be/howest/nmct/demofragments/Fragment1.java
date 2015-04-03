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
public class Fragment1 extends Fragment {

    private static final String ARG_SCORE_NAAM = "score_student";

    //parameters voor fragment2
    private float scoreStudent;


    private Button buttonVerstuurNaarFragment2;
    private EditText editTextNaam;
    private TextView textViewScoreStudent;

    private OnFragment1Listener onFragment1Listener;

    public interface OnFragment1Listener {
        public void onNewNameStudent(String naamStudent);
    }

    //verplicht
    public Fragment1() {
    }

    public static Fragment1 newInstance(float score) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putFloat(ARG_SCORE_NAAM, score);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFragment1Listener = (OnFragment1Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragment1Listener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            scoreStudent = getArguments().getFloat(ARG_SCORE_NAAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, container, false);
        editTextNaam = (EditText) v.findViewById(R.id.editTextNaam);
        buttonVerstuurNaarFragment2 = (Button) v.findViewById(R.id.buttonVerstuurFragment2);
        buttonVerstuurNaarFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verstuurDataNaarFragment2();
            }
        });
        //score afprinten
        textViewScoreStudent = (TextView) v.findViewById(R.id.textViewScoreStudent);
        textViewScoreStudent.setText("Score van student: " +scoreStudent);
        return v;
    }

    private void verstuurDataNaarFragment2(){
        String naamStudent = editTextNaam.getText().toString();
        if (onFragment1Listener != null)
            onFragment1Listener.onNewNameStudent(naamStudent);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        onFragment1Listener = null;
    }

    public float getScoreStudent() {
        return scoreStudent;
    }

    public void setScoreStudent(float scoreStudent) {
        this.scoreStudent = scoreStudent;
    }
}
