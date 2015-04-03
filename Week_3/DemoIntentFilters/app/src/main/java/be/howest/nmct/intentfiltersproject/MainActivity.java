package be.howest.nmct.intentfiltersproject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.content.pm.ResolveInfo;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    private Button btnLaunch;
    private Button btnTestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        btnTestData = (Button) findViewById(R.id.btnTestData);
        btnLaunch = (Button) findViewById(R.id.btnlaunch);

        btnTestData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onderzoek();
            }
        });

        btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWithAction(v);
            }
        });
    }

    private  void onderzoek(){
        //Intent intent = new Intent(Constants.ACTION_IMPLY, Uri.parse("xtp://somedata"));
        //startActivity(intent);

        //test zonder data
        Intent intent = new Intent(Constants.ACTION_IMPLY);
        startActivity(intent);
    }

    public void launchWithAction(View v){
        //Intent intent = new Intent(Constants.ACTION_IMPLY);
        Intent intent = new Intent("android.intent.action.VIEW");
        //intent.addCategory(Intent.CATEGORY_CAR_DOCK);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe) {
            startActivity(intent);
        }else{
            Toast.makeText(MainActivity.this, "De intent bestaat niet!!", Toast.LENGTH_SHORT).show();
        }
        //startActivity(intent);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main_fragment, container, false);
            return rootView;
        }
    }
}
