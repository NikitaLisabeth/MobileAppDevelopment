package be.howest.nmct;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SelectGeboortejaarActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setTitle("Select geboortejaar");
    }

    private final static List<String> GEBOORTEJAREN;
    static{
        GEBOORTEJAREN = new ArrayList<>(Calendar.getInstance().get(Calendar.YEAR)-1900);
        for(int jaar = 1900; jaar< Calendar.getInstance().get(Calendar.YEAR);jaar++){
            GEBOORTEJAREN.add(""+jaar);
        }
    }

    private ListAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_select_geboortejaar);
        myListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,GEBOORTEJAREN);
        setListAdapter(myListAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        String sGeboortejaar = GEBOORTEJAREN.get(position);
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_BIRTHYEAR, sGeboortejaar);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_geboortejaar, menu);
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
