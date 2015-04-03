package be.howest.nmct;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Nikita on 27/02/2015.
 */
public class LifeCycleFragment extends Fragment {

    //Control-views bijhouden als attribuut
    private Button btnAfsluiten;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("LifeCycleFragment", "onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeCycleFragment", "onCreate");
    }


    //inladen van de layout-file (fragment_life_cycle.xml)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_life_cycle, container,false);
        btnAfsluiten = (Button) v.findViewById(R.id.btnAfsluiten);
        btnAfsluiten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afsluiten();
            }
        });
        Log.d("LifeCycleFragment", "onCreateView");

        return v;
    }
    private void afsluiten(){
        //app afsluiten -> Activity afsluiten
        getActivity().finish();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("LifeCycleFragment", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("LifeCycleFragment", "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LifeCycleFragment", "onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LifeCycleFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("LifeCycleFragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("LifeCycleFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycleFragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("LifeCycleFragment", "onDetach");

    }
}
