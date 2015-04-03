package be.howest.nmct;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class LifeCycleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new LifeCycleFragment())       // Fragment aanpassen!!!
                    .commit();
        }
        Log.d("LifeCycleActivity", "OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycleActivity", "OnStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycleActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycleActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycleActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycleActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycleActivity", "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_life_cycle, menu);
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
