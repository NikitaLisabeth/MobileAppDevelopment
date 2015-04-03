package be.howest.nmct;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class HoroscoopActivity extends ListActivity {


    class HoroscoopAdapter extends ArrayAdapter<Data.Horoscoop>{
        public HoroscoopAdapter(){
            super(HoroscoopActivity.this,R.layout.row_horoscoop,
                    R.id.tvNaamHoroscoop, Data.Horoscoop.values());
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            final Data.Horoscoop horoscoop = Data.Horoscoop.values()[position];
            Log.d("HOROSCOOP", "findViewById");

            ViewHolder holder = (ViewHolder) row.getTag();
            if(holder==null){
                holder = new ViewHolder(row);
                //row
            }
            TextView textViewNaamHoroscoop = (TextView) row.findViewById(R.id.tvNaamHoroscoop);
            textViewNaamHoroscoop.setText(horoscoop.getNaamHoroscoop());

            //image opvragen --> setImageResource
            ImageView imgHoroscoop = (ImageView) row.findViewById(R.id.imageView);
            imgHoroscoop.setImageResource(getResourceId(horoscoop));

            //button ophalen
            //setonclicklistener
            Button btnInfo = (Button) row.findViewById(R.id.btnInfo);
            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tekst = horoscoop.getBeginDatum()+" tot en met "+horoscoop.getEindDatum();
                    Toast t = Toast.makeText(getContext(),tekst,Toast.LENGTH_SHORT);
                    t.show();
                }
            });



            return row;
        }

        public int getResourceId(Data.Horoscoop horoscoop){
            switch (horoscoop){
                case WATERMAN:
                    return R.drawable.waterman;
                case BOOGSCHUTTER:
                    return R.drawable.boogschutter;
                case KREEFT:
                    return R.drawable.kreeft;
                case LEEUW:
                    return R.drawable.leeuw;
                case MAAGD:
                    return R.drawable.maagd;
                case RAM:
                    return R.drawable.ram;
                case SCHORPIOEN:
                    return R.drawable.schorpioen;
                case STEENBOK:
                    return R.drawable.steenbok;
                case STIER:
                    return R.drawable.stier;
                case TWEELING:
                    return R.drawable.tweeling;
                case VISSEN:
                    return R.drawable.vissen;
                case WEEGSCHAAL:
                    return R.drawable.weegschaal;
                default:
                    break;
            }
            return -1;
        }
    }
    class ViewHolder{
        public ImageView imageViewHoroscoop = null;
        public TextView textViewHoroscoop = null;
        public Button btnToonInfo = null;
        public ViewHolder(View row){
            this.imageViewHoroscoop = (ImageView) row.findViewById(R.id.imageView);
            this.textViewHoroscoop = (TextView) row.findViewById(R.id.tvNaamHoroscoop);
            this.btnToonInfo = (Button) row.findViewById(R.id.btnInfo);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Data.Horoscoop horoscoop = Data.Horoscoop.values()[position];
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_HOROSCOOP, position);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.row_horoscoop);
        //setListAdapter(new ArrayAdapter<>(this,R.layout.row_horoscoop,R.id.tvNaamHoroscoop,Data.Horoscoop.values()));
        setListAdapter(new HoroscoopAdapter());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horoscoop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
