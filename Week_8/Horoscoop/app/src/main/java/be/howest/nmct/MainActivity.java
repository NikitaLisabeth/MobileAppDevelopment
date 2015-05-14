package be.howest.nmct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;


public class MainActivity extends Activity {
    public static final String EXTRA_BIRTHYEAR = "be.howest.nmct.week5oef1.BIRTHYEAR";
    private static final int REQUEST_BIRTHDAY = 1;
    public static final String EXTRA_HOROSCOOP = "be.howest.nmct.week5oef1.HOROSCOOP";
    private static final int REQUEST_HOROSCOOP = 2;
    public Button btnSelecteerGeboortejaar;
    public Button btnSelecteerHoroscoop;
    public TextView tvGeboortejaar;
    public ImageView imageHoroscoop;

    public String Sterrebeeld;

    public static String year;
    public static int nummerHoroscoop=-1;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelecteerGeboortejaar = (Button) findViewById(R.id.btnSelecteerGeboortejaar);
        btnSelecteerHoroscoop = (Button) findViewById(R.id.btnSelecteerHoroscoop);
        tvGeboortejaar = (TextView) findViewById(R.id.tvGeboortejaar);
        imageHoroscoop = (ImageView) findViewById(R.id.imgHoroscoopImage);


        if(year!=null){
            tvGeboortejaar.setText("Geboortejaar: "+year);
        }
        if(nummerHoroscoop!=-1){
            Data.Horoscoop horoscoop = Data.Horoscoop.values()[nummerHoroscoop];
            imageHoroscoop.setImageResource(getResourceId(horoscoop));
        }

        btnSelecteerGeboortejaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteerGeboortejaar(v);
            }
        });

        btnSelecteerHoroscoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecteerHoroscoop(v);
            }
        });

    }

    public void selecteerGeboortejaar(View v){
        Intent intent = new Intent(MainActivity.this,
                SelectGeboortejaarActivity.class);
        startActivityForResult(intent, REQUEST_BIRTHDAY);
    }

    public void selecteerHoroscoop(View v){
        Intent intent = new Intent(MainActivity.this,
                HoroscoopActivity.class);
        startActivityForResult(intent, REQUEST_HOROSCOOP);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

       // MenuItem ophalen getActionprovider (mshareactionprovider)
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
        mShareActionProvider.setShareIntent(updateShareIntent());

        return true;
    }

   private Intent  updateShareIntent(){
       Intent intent = new Intent(Intent.ACTION_SEND);
       intent.setType("text/plain");
       intent.putExtra(Intent.EXTRA_TEXT,setShareIntent());
       mShareActionProvider.setShareIntent(intent);
       return intent;
   }
    private String setShareIntent(){
        String message = "";
        if(tvGeboortejaar.getText().toString() != ""){
            message = "Mijn geboortejaar is "+tvGeboortejaar.getText().toString();
        }
        if(Sterrebeeld != null){
            message+= "\r\n"+"Mijn horoscoop is "+Sterrebeeld;
        }
        return message;
    }


    //methode wordt uitgevoerd als er een menuitem geselecteerd wordt.
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_BIRTHDAY:
                switch (resultCode) {
                    case RESULT_CANCELED:
                        break;
                    case RESULT_OK:
                        year = data.getStringExtra(EXTRA_BIRTHYEAR);
                        if (year != null) {
                            tvGeboortejaar.setText("Geboortejaar: " + year);
                        }
                        updateShareIntent();
                        //in method een impliciete intent maken
                        //set type plain
                        //put extra die string teruggeeft
                        //setShareIntent
                        break;
                }
                break;


            case REQUEST_HOROSCOOP:
                switch (resultCode) {
                    case RESULT_CANCELED:
                        break;
                    case RESULT_OK:
                        nummerHoroscoop = data.getIntExtra(MainActivity.EXTRA_HOROSCOOP,-1);
                        if(nummerHoroscoop !=-1) {
                            Data.Horoscoop horoscoop = Data.Horoscoop.values()[nummerHoroscoop];
                            imageHoroscoop.setImageResource(getResourceId(horoscoop));
                            Sterrebeeld = horoscoop.getNaamHoroscoop();
                        }
                        updateShareIntent();
                        break;
                }
                break;
            default:
                break;
        }
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

