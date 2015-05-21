package be.howest.nmct.politiecontroles.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Nikita on 20/05/2015.
 */
public class SnelheidsControleLoader extends AsyncTaskLoader<Cursor> {
    private Cursor cursor;
    private final String[] columnNames = new String[]
            {
                    BaseColumns._ID,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_MAAND,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_STRAAT,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_POSTCODE,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_GEMEENTE,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_AANTAL,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_GEPASSEERDEVOERTUIGEN,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_OVERTREDINGEN,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_X,
                    Contract.SnelheidsControleColumns.COLUMN_CONTROLE_Y

            };
    private static Object lock = new Object();

    public SnelheidsControleLoader(Context context) {
        super(context);
    }


    @Override
    protected void onStartLoading() {
        if (cursor != null) {
            deliverResult(cursor);
        }

        if (takeContentChanged() || cursor == null) {
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        if (cursor == null) {
            loadCursor();
        }
        return cursor;
    }

    private void loadCursor(){
        synchronized (lock) {
            if (cursor != null) {return;}

            MatrixCursor matrixCursor = new MatrixCursor(columnNames);

            String url = "http://data.kortrijk.be/snelheidsmetingen/pz_vlas.json";
            InputStream input = null;
            JsonReader reader = null;

            System.out.print(">>>>>>>>>>>>>>>>>>LOADER");

            try {
                //json ophalen
                input = new URL(url).openStream();
                reader = new JsonReader(new InputStreamReader(input, "UTF-8"));

                int id = 1;
                reader.beginArray();

                while (reader.hasNext()) {
                    reader.beginObject();

                    int maand = 0;
                    String straat = "";
                    int postcode = 0;
                    String gemeente = "";
                    int aantalControles = 0;
                    int gepasseerdeVoertuigen = 0;
                    int overtreding= 0;
                    double x = 0;
                    double y = 0;

                    while (reader.hasNext()) {
                        String name = reader.nextName();

                        //controle welke naam json object heeft
                        if (name.equals("Maand")) {
                            maand = reader.nextInt();

                        } else if (name.equals("Straat")) {
                            straat = reader.nextString();

                        } else if (name.equals("Postcode")) {
                            postcode = reader.nextInt();

                        } else if (name.equals("Gemeente")) {
                            gemeente = reader.nextString();

                        }else if (name.equals("Aantal controles")) {
                            aantalControles= reader.nextInt();

                        }else if (name.equals("Gepasseerde voertuigen")) {
                            gepasseerdeVoertuigen = reader.nextInt();

                        }else if (name.equals("Vtg in overtreding")) {
                            overtreding= reader.nextInt();
                        }else if (name.equals("X")) {
                            x=reader.nextDouble();
                        }else if (name.equals("Y")) {
                            y=reader.nextDouble();
                        } else {
                            reader.skipValue();
                        }
                    }

                    MatrixCursor.RowBuilder row = matrixCursor.newRow();
                    row.add(id);
                    row.add(maand);
                    row.add(straat);
                    row.add(postcode);
                    row.add(gemeente);
                    row.add(aantalControles);
                    row.add(gepasseerdeVoertuigen);
                    row.add(overtreding);
                    row.add(x);
                    row.add(y);
                    id++;

                    reader.endObject();
                }

                reader.endArray();
                cursor = matrixCursor;

            } catch (IOException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    reader.close();

                } catch (IOException ex) {

                }
                try {
                    input.close();
                } catch (IOException ex) {

                }
            }
            cursor = matrixCursor;
        }
    }

}
