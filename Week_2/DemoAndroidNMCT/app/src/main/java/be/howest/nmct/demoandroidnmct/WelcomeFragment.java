package be.howest.nmct.demoandroidnmct;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nikita on 20/02/2015.
 */
public class WelcomeFragment  extends Fragment{

    //attributen: de verschillende controls (views)
    private EditText editTextMaand;
    private Button buttonOK;
    private TextView textViewAntwoord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // bijhorende XML layout file gaan inladen an als view terug geven
        View v = inflater.inflate(R.layout.fragment_welcome,container,false);

        //de attribuen linken aan de controls(view)
        editTextMaand = (EditText)v.findViewById(R.id.editTextMaand);
        buttonOK = (Button)v.findViewById(R.id.buttonOK);
        textViewAntwoord = (TextView)v.findViewById(R.id.textViewAntwoord);


        //listener koppelen aan mijn button
        buttonOK.setOnClickListener( new View.OnClickListener(){
            //dit is een anonieme klasse (deze heeft geen naam)
            @Override
            public void onClick(View v) {
                verwerkClickOk();
            }
        });


        return  v;
    }

    private  void verwerkClickOk(){
        String maand = editTextMaand.getText().toString();
        if(maand.equals("januari")){
            textViewAntwoord.setText("Goede keuze!!");
        }else {
            textViewAntwoord.setText("Slechte keuze!!");
        }

        Toast.makeText(getActivity(),"Dit is een test",Toast.LENGTH_LONG).show();
    }
}
