package be.howest.nmct.launching;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;


public class MainActivity2 extends Activity {
    public static final String EXTRA_INFO = "be.howest.nmct.android.launching.EXTRA_INFO";
    public static final int RESULT_MY_OWN=1;
    public static final int RESULT_SCORE=2;

    private TextView helloWorldTextview;
    private Button btnOK;
    private Button btnCancel;
    private Button btnOwnResultCode;
    private Button btnScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        String value = getIntent().getStringExtra(MainActivity2.EXTRA_INFO);

        this.helloWorldTextview = (TextView) findViewById(R.id.textViewHelloWorld);
        this.btnCancel = (Button) findViewById(R.id.btnCancel);
        this.btnOK = (Button) findViewById(R.id.btnOK);
        this.btnOwnResultCode = (Button) findViewById(R.id.btnOwnResultCode);
        this.btnScore = (Button) findViewById(R.id.btnScore);
        helloWorldTextview.setText(value+"");


        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
        btnOwnResultCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_INFO_LASTNAME,"Lisabeth");
                intent.putExtra(MainActivity.EXTRA_INFO_BACK_AGE, 19);
                setResult(MainActivity2.RESULT_MY_OWN, intent);
                //setResult(RESULT_MY_OWN);
                finish();
            }
        });


    }

    public void showDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Score");
        alert.setMessage("Fill in your score");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_INFO_SCORE,input.getText().toString());
                setResult(MainActivity2.RESULT_SCORE, intent);
                //setResult(RESULT_MY_OWN);
                finish();
            }
        });
        alert.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
