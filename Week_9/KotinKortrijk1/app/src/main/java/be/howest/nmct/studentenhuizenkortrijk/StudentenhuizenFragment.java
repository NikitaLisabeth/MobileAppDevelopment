package be.howest.nmct.studentenhuizenkortrijk;


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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import be.howest.nmct.studentenhuizenkortrijk.loader.Contract;
import be.howest.nmct.studentenhuizenkortrijk.loader.StudentenhuizenLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentenhuizenFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>  {
    private StudentenhuizenAdapter mAdapter;

    public StudentenhuizenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_studentenhuizen, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] columns = new String[]{Contract.StudentenKamersColumns.COLUMN_ADRES,
                Contract.StudentenKamersColumns.COLUMN_HUISNUMMER, Contract.StudentenKamersColumns.COLUMN_GEMEENTE, Contract.StudentenKamersColumns.COLUMN_KAMERS};
        int[] viewIds = new int[]{R.id.tvStraat, R.id.tvHuisnummer, R.id.tvStad, R.id.tvAantalKamers};
        // Create an empty adapter we will use to display the loaded data.
        mAdapter = new StudentenhuizenAdapter(getActivity(),R.layout.row_studentenhuis, null,columns, viewIds, 0);
        setListAdapter(mAdapter);
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        /*Cursor c = (Cursor)mAdapter.getItem(position);
        String selectedTheatre = c.getString(c.getColumnIndex(Contract.TheatreColumns.COLUMN_THEATRE_NAME));
        if (mListener!=null) mListener.onSelectTheatre(selectedTheatre);*/

        Cursor c = (Cursor) mAdapter.getItem(position);

        String selectedKotAdres = c.getString(c.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_ADRES));
        String selectedKotNummer = c.getString(c.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_HUISNUMMER));
        String selectedKotGemeente = c.getString(c.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_GEMEENTE));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new StudentenhuizenLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    class StudentenhuizenAdapter extends SimpleCursorAdapter {

        private int layout;

        public StudentenhuizenAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

       class ViewHolder{
           public ImageView imgHuis = null;
           public TextView tvAdres = null;
           public TextView tvHuisnummer = null;
           public TextView tvGemeente = null;
           public TextView tvAantalKamers = null;

           public ViewHolder(View row){
               this.imgHuis = (ImageView) row.findViewById(R.id.imgHuis);
               this.tvAdres = (TextView) row.findViewById(R.id.tvStraat);
               this.tvHuisnummer = (TextView) row.findViewById(R.id.tvHuisnummer);
               this.tvGemeente = (TextView) row.findViewById(R.id.tvStad);
               this.tvAantalKamers = (TextView) row.findViewById(R.id.tvAantalKamers);
           }
       }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            super.bindView(view, context, cursor);
            int colnr1 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_ADRES);
            int colnr2 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_HUISNUMMER);
            int colnr3 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_GEMEENTE);
            int colnr4 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_KAMERS);

            String adres = cursor.getString(colnr1);
            String huisnr = cursor.getString(colnr2);
            String gemeente = cursor.getString(colnr3);
            String kamers = cursor.getString(colnr4);

            ViewHolder v = new ViewHolder(view);
            v.tvAdres.setText(adres);
            v.tvHuisnummer.setText(gemeente);
            v.tvGemeente.setText(huisnr);
            v.tvAantalKamers.setText(kamers);
            v.imgHuis.setImageResource(R.drawable.house);
        }
    }
}
