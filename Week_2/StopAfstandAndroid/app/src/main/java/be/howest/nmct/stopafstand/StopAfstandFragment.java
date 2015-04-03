package be.howest.nmct.stopafstand;

import android.app.Fragment;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nikita on 20/02/2015.
 */
public class StopAfstandFragment extends Fragment {
    //attributen: de verschillende controls (views)
    private EditText editTextSnelheid;
    private EditText editTextReactietijd;
    private RadioButton radioButtonDroog;
    private RadioButton radioButtonNat;
    private Button button_bereken_stopafstand;
    private TextView textViewStopafstandInMeter;

    private StopAfstandInfo oInfo = new StopAfstandInfo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // bijhorende XML layout file gaan inladen an als view terug geven
        View v = inflater.inflate(R.layout.fragment_stop_afstand,container,false);

        //de attribuen linken aan de controls(view)
        editTextSnelheid = (EditText)v.findViewById(R.id.editTextSnelheid);
        editTextReactietijd = (EditText)v.findViewById(R.id.editTextReactietijd);
        radioButtonDroog = (RadioButton)v.findViewById(R.id.radioButtonDroog);
        radioButtonNat = (RadioButton)v.findViewById(R.id.radioButtonNat);
        button_bereken_stopafstand = (Button)v.findViewById(R.id.button_bereken_stopafstand);
        textViewStopafstandInMeter = (TextView)v.findViewById(R.id.textViewStopafstandInMeter);

        //listener koppelen aan mijn button
        button_bereken_stopafstand.setOnClickListener( new View.OnClickListener(){
            //dit is een anonieme klasse (deze heeft geen naam)
            @Override
            public void onClick(View v) {
               BerekenStopAfstand();
            }
        });
        return  v;
    }
    private void BerekenStopAfstand(){
        float reactietijd = Float.parseFloat( editTextReactietijd.getText().toString());
        int snelheid = Integer.parseInt( editTextSnelheid.getText().toString());
        if(radioButtonDroog.isChecked() == true){
            StopAfstandInfo.WegType wegtype = StopAfstandInfo.WegType.WEGDEK_DROOG;
            oInfo.setWegType(wegtype);
        }else{
            if(radioButtonNat.isChecked() == true){
                StopAfstandInfo.WegType wegtype = StopAfstandInfo.WegType.WEGDEK_NAT;
                oInfo.setWegType(wegtype);
            }
        }
        oInfo.setReactietijd(reactietijd);
        oInfo.setSnelheid(snelheid);

        float resultaat = oInfo.getStopAfstand();
        textViewStopafstandInMeter.setText(resultaat+" meter");
    }

}
