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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Nikita on 20/02/2015.
 */
public class StopAfstandVersie2Fragment extends Fragment {
    //attributen: de verschillende controls (views)

    private SeekBar seekbarSnelheid;
    private SeekBar seekBarReactietijd;
    private TextView textViewGekozenSnelheid;
    private TextView textViewGekozenReactietijd;
    private RadioButton radioButtonDroog;
    private RadioButton radioButtonNat;
    private Button button_bereken_stopafstand;
    private TextView textViewStopafstandInMeter;

    private StopAfstandInfo oInfo = new StopAfstandInfo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // bijhorende XML layout file gaan inladen an als view terug geven
        View v = inflater.inflate(R.layout.fragment_stop_afstand_versie2,container,false);

        //de attribuen linken aan de controls(view)
        seekbarSnelheid = (SeekBar)v.findViewById(R.id.seekbarSnelheid);
        seekBarReactietijd = (SeekBar)v.findViewById(R.id.seekbarReactietijd);
        textViewGekozenSnelheid = (TextView)v.findViewById(R.id.textViewGekozenSnelheid);
        textViewGekozenReactietijd = (TextView)v.findViewById(R.id.textViewGekozenReactietijd);
        radioButtonDroog = (RadioButton)v.findViewById(R.id.radioButtonDroog);
        radioButtonNat = (RadioButton)v.findViewById(R.id.radioButtonNat);
        button_bereken_stopafstand = (Button)v.findViewById(R.id.button_bereken_stopafstand);
        textViewStopafstandInMeter = (TextView)v.findViewById(R.id.textViewStopafstandInMeter);

        seekbarSnelheid.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                float value = progress / 10.0f;
                textViewGekozenSnelheid.setText(String.valueOf(progress));
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBarReactietijd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                float value = progress / 10.00f;

                textViewGekozenReactietijd.setText(String.valueOf(value));
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        button_bereken_stopafstand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BerekenStopAfstand2();
            }
        });
        //listener koppelen aan mijn button
       /* button_bereken_stopafstand.setOnClickListener( new View.OnClickListener(){
            //dit is een anonieme klasse (deze heeft geen naam)
            @Override
            public void onClick(View v) {
                BerekenStopAfstand2();
            }
        });*/
        return  v;
    }
    private void BerekenStopAfstand2(){
        float reactietijd = Float.parseFloat( textViewGekozenReactietijd.getText().toString());
        int snelheid =Integer.parseInt( textViewGekozenSnelheid.getText().toString());

        System.out.println(snelheid);
        System.out.println(reactietijd);


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
