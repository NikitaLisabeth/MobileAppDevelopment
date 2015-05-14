package be.howest.nmct.studentenhuizenkortrijk;


import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


import be.howest.nmct.studentenhuizenkortrijk.loader.Contract;
import be.howest.nmct.studentenhuizenkortrijk.loader.StudentenhuizenLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentenhuizenFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>  {
    private StudentenhuizenAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public StudentenhuizenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_studentenhuizen,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] columns = new String[]{Contract.StudentenKamersColumns.COLUMN_ADRES,
                Contract.StudentenKamersColumns.COLUMN_HUISNUMMER, Contract.StudentenKamersColumns.COLUMN_GEMEENTE, Contract.StudentenKamersColumns.COLUMN_KAMERS};
        int[] viewIds = new int[]{R.id.tvStraat, R.id.tvHuisnummer, R.id.tvStad, R.id.tvAantalKamers};
        // Create an empty adapter we will use to display the loaded data.
        //mAdapter = new StudentenhuizenAdapter(getActivity(),R.layout.row_studentenhuis, null,columns, viewIds, 0);
        //setListAdapter(mAdapter);
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new StudentenhuizenLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mAdapter = new StudentenhuizenAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter = new StudentenhuizenAdapter(null);

    }

    class StudentenhuizenAdapter extends RecyclerView.Adapter<StudentenhuizenAdapter.KotViewHolder> {

        private int layout;
        private Cursor cursor;
        /*public StudentenhuizenAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }*/
        public StudentenhuizenAdapter(Cursor cursor){
            this.cursor = cursor;
        }

        @Override
        public KotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_studentenhuis, parent, false);
            return new KotViewHolder(v);
        }

        /*
        @Override
        public KotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_studentenhuis, parent, false);
            return new KotViewHolder(v);
        }*/


        @Override
        public void onBindViewHolder(KotViewHolder holder, int position) {

            cursor.moveToPosition(position);

            int colnr1 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_ADRES);
            int colnr2 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_HUISNUMMER);
            int colnr3 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_GEMEENTE);
            int colnr4 = cursor.getColumnIndex(Contract.StudentenKamersColumns.COLUMN_KAMERS);

            String adres = cursor.getString(colnr1);
            String huisnr = cursor.getString(colnr2);
            String gemeente = cursor.getString(colnr3);
            String kamers = cursor.getString(colnr4);

            holder.tvAdres.setText(adres);
            holder.tvAantalKamers.setText(kamers);
            holder.imgHuis.setImageResource(R.drawable.house);
            holder.tvGemeente.setText(gemeente);
            holder.tvHuisnummer.setText(huisnr);

        }

        @Override
        public int getItemCount() {
            return cursor.getCount();
        }

        class KotViewHolder extends RecyclerView.ViewHolder {
            public ImageView imgHuis = null;
            public TextView tvAdres = null;
            public TextView tvHuisnummer = null;
            public TextView tvGemeente = null;
            public TextView tvAantalKamers = null;

            public KotViewHolder(View row) {
                super(row);
                this.imgHuis = (ImageView) row.findViewById(R.id.imgHuis);
                this.tvAdres = (TextView) row.findViewById(R.id.tvStraat);
                this.tvHuisnummer = (TextView) row.findViewById(R.id.tvHuisnummer);
                this.tvGemeente = (TextView) row.findViewById(R.id.tvStad);
                this.tvAantalKamers = (TextView) row.findViewById(R.id.tvAantalKamers);
            }
        }
    }
}
