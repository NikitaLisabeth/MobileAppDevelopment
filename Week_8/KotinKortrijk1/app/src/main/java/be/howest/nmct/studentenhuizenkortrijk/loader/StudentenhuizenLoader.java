package be.howest.nmct.studentenhuizenkortrijk.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Nikita on 24/04/2015.
 */
public class StudentenhuizenLoader extends AsyncTaskLoader<Cursor> {

    public StudentenhuizenLoader(Context context) {
        super(context);
    }

    private Cursor mCursor;
    private final String[] mColumnNames = new String[]{
            BaseColumns._ID,
            Contract.StudentenKamersColumns.COLUMN_ADRES,
            Contract.StudentenKamersColumns.COLUMN_GEMEENTE,
            Contract.StudentenKamersColumns.COLUMN_HUISNUMMER,
            Contract.StudentenKamersColumns.COLUMN_KAMERS};

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }
    public Cursor loadInBackground() {
        if (mCursor == null) {
            loadCursor();
        }
        return mCursor;
    }

    private static Object lock = new Object();

    public  void loadCursor(){
        synchronized (lock){
            if(mCursor != null)return;

            MatrixCursor cursor = new MatrixCursor(mColumnNames);
            InputStream input = null;
            JsonReader reader = null;

            try{
                input = new URL("http://data.kortrijk.be/studentenvoorzieningen/koten.json").openStream();
                reader = new JsonReader(new InputStreamReader(input, "UTF-8"));

                int id = 1;
                reader.beginArray();
                while (reader.hasNext()){
                    reader.beginObject();

                    String adres = "";
                    String huisnummer = "";
                    String gemeente = "";
                    int aantalKamers = 0;
                    while (reader.hasNext()){

                        String name = reader.nextName();
                        if(name.equals("ADRES")){
                            adres = reader.nextString();
                        }else if(name.equals("HUISNR")){
                            //opgelet zowel numerieke waarden (nextint) als string-waarden
                            //(nextstring) komen voor!
                            //voer controle uit via 'peek' methode

                            if(reader.peek().equals(JsonToken.NULL)){
                                reader.skipValue();
                            } else if(reader.peek().equals(JsonToken.STRING)) {
                                huisnummer = reader.nextString();
                            }else if(reader.peek().equals(JsonToken.NUMBER)){
                                huisnummer = reader.nextString();
                            }
                            //enz...
                        }else if(name.equals("GEMEENTE")){
                            gemeente = reader.nextString();
                        }else if(name.equals("aantal kamers")){
                            aantalKamers = reader.nextInt();
                        }else {
                            reader.skipValue();
                        }
                    }

                    MatrixCursor.RowBuilder row = cursor.newRow();
                    row.add(id);
                    row.add(adres);
                    row.add(huisnummer);
                    row.add(gemeente);
                    row.add(aantalKamers);
                    id++;

                    reader.endObject();
                }
                reader.endArray();
                mCursor = cursor;
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    reader.close();
                }catch (IOException e){
                }

                try {
                    input.close();
                }catch(IOException e){

                }
            }
        }
    }
}

