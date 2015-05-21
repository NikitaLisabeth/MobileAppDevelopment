package be.howest.nmct.politiecontroles;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import be.howest.nmct.politiecontroles.models.SnelheidsControle;
import be.howest.nmct.politiecontroles.Convert;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ControleMapFragment extends Fragment {

    private GoogleMap map;
    private MapView mapView;
    private LatLng PLACE;

    private SnelheidsControle element;
    private List<SnelheidsControle> elements;

    public static ControleMapFragment newInstance(SnelheidsControle element) {
        ControleMapFragment fragment = new ControleMapFragment();

        Bundle bundle = new Bundle();
        JSONSerializer ser = new JSONSerializer();
        bundle.putString(MainActivity.EXTRA_CONTROLE, ser.deepSerialize(element));// to json format
       // bundle.putString(MainActivity.EXTRA_CONTROLE, element.toString());
        fragment.setArguments(bundle);

        return fragment;
    }
    public static ControleMapFragment newInstance(List<SnelheidsControle> elements) {
        ControleMapFragment fragment = new ControleMapFragment();

        Bundle bundle = new Bundle();
        JSONSerializer ser = new JSONSerializer();
        bundle.putString(MainActivity.EXTRA_CONTROLES, ser.deepSerialize(elements));// to json format
        // bundle.putString(MainActivity.EXTRA_CONTROLE, element.toString());
        fragment.setArguments(bundle);

        return fragment;
    }



    public ControleMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(getActivity() != null)
        {
            Bundle bundle = getArguments();

            String s = bundle.getString(MainActivity.EXTRA_CONTROLE);
            String t= bundle.getString(MainActivity.EXTRA_CONTROLES);

            if (s !=null){
                JSONDeserializer<SnelheidsControle> der = new JSONDeserializer<SnelheidsControle>();
                element = der.deserialize(s);

                if(element.X=="" && element.Y==""){
                    PLACE= new LatLng(50.832,3.22);
                }else{
                    PLACE = Convert.lambert72toWGS84(Double.parseDouble(element.X),Double.parseDouble(element.Y));
                    //PLACE= new LatLng(50.832,3.22);

                }

            }

            if(t!=null){
                JSONDeserializer<List<SnelheidsControle>> der = new JSONDeserializer<List<SnelheidsControle>>();
                elements = der.deserialize(t);

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_controle_map, container, false);

        mapView = (MapView) v.findViewById(R.id.mapView);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        MapsInitializer.initialize(getActivity().getApplicationContext());
        map = mapView.getMap();

        MarkerOptions marker;


        if(elements!=null){
            for (int i=0;i<elements.size();i++){
                PLACE =  Convert.lambert72toWGS84(Double.parseDouble(elements.get(i).X),Double.parseDouble(elements.get(i).Y));

                MarkerOptions markerControle = new MarkerOptions().position(PLACE)
                        .title(elements.get(i).InOverTreding)
                        .snippet(elements.get(i).Straat);

                /*marker = new MarkerOptions()
                        .position(PLACE)
                        .title(elements.get(i).InOverTreding)
                        .snippet(elements.get(i).Straat);*/


                markerControle.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(markerControle);
            }

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(50.8028051,3.279785)).zoom(13).build();
            map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));


            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(50.8028051,3.279785), 11));
        }else{

            /*marker = new MarkerOptions()
                    .position(PLACE)
                    .title(element.InOverTreding)
                    .snippet(element.Straat);*/

            MarkerOptions markerControle = new MarkerOptions().position(PLACE)
                    .title(element.InOverTreding)
                    .snippet(element.Straat);
            markerControle.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            map.addMarker(markerControle);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(PLACE).zoom(15).build();
            map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));


            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    PLACE, 15));
        }





        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}
