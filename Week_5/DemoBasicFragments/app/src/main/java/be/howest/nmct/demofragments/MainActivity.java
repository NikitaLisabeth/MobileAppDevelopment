package be.howest.nmct.demofragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements Fragment1.OnFragment1Listener, Fragment2.OnFragment2Listener {


    private String nameStudent = "";
    private float scoreStudent = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //Initial start
            //To manage the fragments in your activity, you need to use FragmentManager
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment1 fragment1 = Fragment1.newInstance(scoreStudent);

            //parameters:
            //1: ID container
            //2: fragment
            //3: Optional tag name for the fragment, to later retrieve the fragment with FragmentManager.findFragmentByTag(String).
            fragmentTransaction.add(R.id.container, fragment1, "fragment1");
            fragmentTransaction.commit();

            setTitle("Fragment 1");
        }
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

    private void showFragment2(String sNaamStudent) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment2 fragment2 = Fragment2.newInstance(sNaamStudent);
        fragmentTransaction.replace(R.id.container, fragment2);

        //Add this transaction to the back stack. This means that the transaction will be remembered
        //after it is committed, and will reverse its operation when later popped off the stack.
        //name: An optional name for this back stack state, or null.
        fragmentTransaction.addToBackStack("showfragment2");
        fragmentTransaction.commit();

        setTitle("Fragment 2");
    }

    private void showFragment1(float score) {
        FragmentManager fragmentManager = getFragmentManager();
        //Pop the top state off the back stack. This function is asynchronous
        fragmentManager.popBackStack();

        //Finds a fragment that was identified by the given tag either when inflated from XML or as supplied when added in a transaction. This first searches through fragments that are currently added to the manager's activity;
        //if no such fragment is found, then all fragments currently on the back stack are searched
        Fragment1 fragment1 = (Fragment1) getFragmentManager().findFragmentByTag("fragment1");
        fragment1.setScoreStudent(score);

        setTitle("Fragment 1");
    }


    @Override
    public void onNewNameStudent(String newName) {
        this.nameStudent = newName;
        showFragment2(newName);
    }

    @Override
    public void onNewScoreStudent(float newScore) {
        this.scoreStudent = newScore;
        showFragment1(newScore);
    }

}
