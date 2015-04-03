package be.howest.nmct;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements ChangeFragment.OnBitCoinFragmentListener , BitCoinRateFragment.OnChangeFragmentListener {
    private float rate = 0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //Initial start
            //To manage the fragments in your activity, you need to use FragmentManager
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
            ChangeFragment fragment1 = ChangeFragment.newInstance(rate);

            //parameters:
            //1: ID container
            //2: fragment
            //3: Optional tag name for the fragment, to later retrieve the fragment with FragmentManager.findFragmentByTag(String).
            fragmentTransaction.add(R.id.container, fragment1, "ChangeFragment");
            fragmentTransaction.commit();

            //setTitle("Fragment 1");
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


    private void showFragment2(float rate) {
        Fragment newFragment = new BitCoinRateFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

        BitCoinRateFragment fragment2 = BitCoinRateFragment.newInstance(rate);
        transaction.replace(R.id.container, fragment2);

        // Commit the transaction
        transaction.commit();
    }

    private void showFragment1(float score) {
        // Create new fragment and transaction
        Fragment newFragment = new ChangeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onBitCoinRate(float rate) {
        this.rate = rate;
        showFragment2(rate);
    }

    @Override
    public void onNewBitCoinRate(float rate) {

    }
}
