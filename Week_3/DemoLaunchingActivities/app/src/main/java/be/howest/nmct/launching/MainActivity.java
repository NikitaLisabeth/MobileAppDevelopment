package be.howest.nmct.launching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button buttonStartActivity2;
    public static final int REQUEST_CODE_EXPLICIT = 1;
    public static String EXTRA_INFO_LASTNAME = "Naam";
    public static String EXTRA_INFO_BACK_AGE;
    public static String EXTRA_INFO_SCORE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartActivity2 = (Button) findViewById(R.id.button);
        buttonStartActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startSecondActivity(v);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE_EXPLICIT:
                switch (resultCode){
                    case RESULT_CANCELED:
                        Toast.makeText(MainActivity.this, "User selects OK", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this, "User selects Cancel", Toast.LENGTH_SHORT).show();
                        break;
                    case MainActivity2.RESULT_MY_OWN:
                        //Toast.makeText(MainActivity.this, "User selects Own Result Code", Toast.LENGTH_SHORT).show();
                        String value = data.getStringExtra(MainActivity.EXTRA_INFO_LASTNAME);
                        int age = data.getIntExtra(MainActivity.EXTRA_INFO_BACK_AGE, 0);
                        Toast.makeText(MainActivity.this, "No Idea what's happening there, "+ value+" - "+age, Toast.LENGTH_SHORT).show();
                        break;
                    case MainActivity2.RESULT_SCORE:
                        String score = data.getStringExtra(MainActivity.EXTRA_INFO_SCORE);
                        Toast.makeText(MainActivity.this, "My score is "+score, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startSecondActivity(View v){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra(MainActivity2.EXTRA_INFO,"2NMCT");
        //startActivity(intent);
        startActivityForResult(intent,REQUEST_CODE_EXPLICIT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
