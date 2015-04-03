package be.howest.nmct.colorpicker;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

/**
 * Created by Nikita on 27/02/2015.
 */
public class ColorFragment extends Fragment {
    private ColorView myColorView;
    static final String STATE_COLOR = "Color";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_color_view, container, false);
        this.myColorView = (ColorView) v.findViewById(R.id.myColorView1);
        if(savedInstanceState !=null){
            Log.d("ColorView", "OnRestore"+savedInstanceState.getString(STATE_COLOR));
            myColorView.setColor(savedInstanceState.getString(STATE_COLOR));
        }
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_COLOR, myColorView.getColor());
        Log.d("ColorView", "OnSaveInstanceState"+myColorView.getColor());
    }
}
