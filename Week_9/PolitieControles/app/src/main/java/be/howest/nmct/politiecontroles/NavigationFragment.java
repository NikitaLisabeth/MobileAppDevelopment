package be.howest.nmct.politiecontroles;


import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.howest.nmct.politiecontroles.loader.Contract;
import be.howest.nmct.politiecontroles.loader.SnelheidsControleLoader;
import be.howest.nmct.politiecontroles.models.SnelheidsControle;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SnelheidsControleAdapter mAdapter;
    private OnStartFragmentListener onStartFragmentListener;
    public Cursor baseCursor;

    public NavigationFragment() {
        // Required empty public constructor
    }

    public interface OnStartFragmentListener {

        public void changeElement(SnelheidsControle controle);
    }
    public static NavigationFragment newInstance() {
        NavigationFragment fragment = new NavigationFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onStartFragmentListener = (OnStartFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragment1Listener");
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] columns = new String[]{
                Contract.SnelheidsControleColumns.COLUMN_CONTROLE_MAAND,
                Contract.SnelheidsControleColumns.COLUMN_CONTROLE_STRAAT,
                Contract.SnelheidsControleColumns.COLUMN_CONTROLE_GEMEENTE,
                Contract.SnelheidsControleColumns.COLUMN_CONTROLE_OVERTREDINGEN
        };

        int[] viewIds = new int[]{R.id.tvNavMaand, R.id.tvNavStraat, R.id.tvNavStad, R.id.tvNavAantalControles};

        mAdapter = new SnelheidsControleAdapter(getActivity(), R.layout.row_nav, null, columns, viewIds, 0);
        setListAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this); //laten activeren
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cursor cursor=mAdapter.getCursor();
        int iMaand=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_MAAND);
        int iStraat=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_STRAAT);
        int iGemeente=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_GEMEENTE);
        int iGeo_x=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_X);
        int iGeo_y=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_Y);

        String maand=cursor.getString(iMaand);
        String straat=cursor.getString(iStraat);
        String gemeente=cursor.getString(iGemeente);
        String geo_x=cursor.getString(iGeo_x);
        String geo_y=cursor.getString(iGeo_y);


        SnelheidsControle controle= new SnelheidsControle();
        controle.Maand=maand;
        controle.Straat=straat;
        controle.Gemeente=gemeente;
        controle.X=geo_x;
        controle.Y=geo_y;

        onStartFragmentListener.changeElement(controle);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new SnelheidsControleLoader(getActivity());

    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
        baseCursor=cursor;


        LoadAllData(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);
    }

    private void LoadAllData(Cursor cursor){
        SnelheidsControle.Controles= new ArrayList<SnelheidsControle>();

        cursor.moveToFirst();
        while (cursor.moveToNext()){
            int iMaand=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_MAAND);
            int iStraat=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_STRAAT);
            int iGemeente=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_GEMEENTE);
            int iGeo_x=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_X);
            int iGeo_y=cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_Y);

            String maand=cursor.getString(iMaand);
            String straat=cursor.getString(iStraat);
            String gemeente=cursor.getString(iGemeente);
            String geo_x=cursor.getString(iGeo_x);
            String geo_y=cursor.getString(iGeo_y);


            SnelheidsControle controle= new SnelheidsControle();
            controle.Maand=maand;
            controle.Straat=straat;
            controle.Gemeente=gemeente;
            controle.X=geo_x;
            controle.Y=geo_y;


            SnelheidsControle.Controles.add(controle);
        }
    }

    public class SnelheidsControleAdapter extends SimpleCursorAdapter //visualiseren van de data afkomstig van de cursor
    {
        private int layout;

        public SnelheidsControleAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView txtMaand = (TextView) view.findViewById(R.id.tvNavMaand);
            TextView txtStraat = (TextView) view.findViewById(R.id.tvNavStraat);
            TextView txtOvertredeingen = (TextView) view.findViewById(R.id.tvNavAantalControles);
            TextView txtStad = (TextView) view.findViewById(R.id.tvNavStad);

            int colnrMaand = cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_MAAND);
            int colnrStraat = cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_STRAAT);
            int colnrOvertedeingen = cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_OVERTREDINGEN);
            int colnrGepaseerdeVoertuigen= cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_GEPASSEERDEVOERTUIGEN);
            int colnrStad = cursor.getColumnIndex(Contract.SnelheidsControleColumns.COLUMN_CONTROLE_GEMEENTE);

            double overtredingen = cursor.getInt(colnrOvertedeingen);
            double gepasseerdeVoertuigen= cursor.getInt(colnrGepaseerdeVoertuigen);
            double overtredingsGraad= overtredingen/ gepasseerdeVoertuigen;

            txtMaand.setText(convertToMonth(cursor.getString(colnrMaand)));
            txtStraat.setText(cursor.getString(colnrStraat));
            txtOvertredeingen.setText("Overtredingen: " + cursor.getString(colnrOvertedeingen));
            txtStad.setText(cursor.getString(colnrStad));



            if (overtredingsGraad<0.2){
                view.setBackgroundColor(Color.GREEN);

            }else if(overtredingsGraad>0.3){
                view.setBackgroundColor(Color.RED);
            }else{
                view.setBackgroundColor(Color.YELLOW);
            }

            //super.bindView(view, context, cursor);
        }

        private String convertToMonth(String month){
            switch (month){
                case "1":
                    return "Januari";
                case "2":
                    return "Februari";
                case "3":
                    return "Maart";
                case "4":
                    return "April";
                case "5":
                    return "Mei";
                case "6":
                    return "Juni";
                case "7":
                    return "Juli";
                case "8":
                    return "Augustus";
                case "9":
                    return "September";
                case "10":
                    return "Oktober";
                case "11":
                    return "November";
                default:
                    return "December";


            }
        }


    }
}
