package be.howest.nmct;


import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import be.howest.nmct.Loader.Contract;
import be.howest.nmct.Loader.TheatreLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class TheatreListFragment  extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>  {
    private TheatreAdapter mAdapter;
    private OnTheatresFragmentListener mListener;
    public static ImageView imgTheatre;
    public TheatreListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] columns = new String[]{Contract.TheatreColumns.COLUMN_THEATRE_NAME,
                Contract.TheatreColumns.COLUMN_CURRENT_MUSICAL, Contract.TheatreColumns.COLUMN_ADDRESS};
        int[] viewIds = new int[]{R.id.tvTheatre, R.id.tvMusical, R.id.tvAddress};
        // Create an empty adapter we will use to display the loaded data.
        mAdapter = new TheatreAdapter(getActivity(),R.layout.row_theatre, null,columns, viewIds, 0);
        setListAdapter(mAdapter);
        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cursor c = (Cursor)mAdapter.getItem(position);
        String selectedTheatre = c.getString(c.getColumnIndex(Contract.TheatreColumns.COLUMN_THEATRE_NAME));
        if (mListener!=null) mListener.onSelectTheatre(selectedTheatre);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_theatre_list, container, false);
        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new TheatreLoader(getActivity());
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }


    class TheatreAdapter extends SimpleCursorAdapter {

        private int layout;

        public TheatreAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            this.layout = layout;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            View row = inflater.inflate(layout, parent, false);
            TextView textViewNameTheatre = (TextView) row.findViewById(R.id.tvTheatre);
            return row;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            super.bindView(view, context, cursor);
            TextView tvTheatreName = (TextView) view.findViewById(R.id.tvTheatre);
            int colnr1 = cursor.getColumnIndex(Contract.TheatreColumns.COLUMN_THEATRE_NAME);
            String theatreName = cursor.getString(colnr1);
            imgTheatre = (ImageView) view.findViewById(R.id.imageViewTheatre);
            Log.d(theatreName, theatreName);
            switch (theatreName){
                case "Aldwych Theatre":
                    imgTheatre.setImageResource(R.drawable.beautiful);
                    break;
                case "Phoenix Theatre":
                    imgTheatre.setImageResource(R.drawable.beckham);
                    break;
                case "Victoria Palace Theatre":
                    imgTheatre.setImageResource(R.drawable.billy);
                    break;
                case "Prince of Wales Theatre":
                    imgTheatre.setImageResource(R.drawable.bookofmormon);
                    break;
                case "London Palladium":
                    imgTheatre.setImageResource(R.drawable.cats);
                    break;
                case "Queens Theatre":
                    imgTheatre.setImageResource(R.drawable.lesmis);
                    break;
                case "Majesty's Theatre":
                    imgTheatre.setImageResource(R.drawable.phantom);
                    break;
                case "Apollo Victoria Theatre":
                    imgTheatre.setImageResource(R.drawable.wicked);
                    break;
                case "Prince Edward Theatre":
                    imgTheatre.setImageResource(R.drawable.missaigon);
                    break;
                case "Savoy Theatre":
                    imgTheatre.setImageResource(R.drawable.gypsy);
                    break;
                case "Piccadilly Theatre":
                    imgTheatre.setImageResource(R.drawable.jerseyboys);
                    break;
                default:
                    imgTheatre.setImageResource(R.drawable.comedytragedy);
                    break;
            }

        }
    }

    public interface OnTheatresFragmentListener {
        public void onSelectTheatre(String sTheatreName);
    }
}
