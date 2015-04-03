package howest.personen;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity
        implements ShowPersonFragment.EditPersonListener,EditPersonFragment.SavePersonListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*fragmentManager.beginTransaction()
                .add(R.id.container, new ShowPersonFragment())
                .commit();*/

        if(savedInstanceState == null) {

            /*Bundle arguments = new Bundle();
            arguments.putString("howest.ARG_NAME", "Nikita Lisabeth");
            arguments.putString("howest.ARG_EMAIL", "nikita.lisabet@student.howest.be");*/

            ShowPersonFragment spf = new ShowPersonFragment().newInstance(
                    "Nikita Lisabeth",
                    "nikita.lisabet@student.howest.be"
            );
            //spf.setArguments(arguments);


            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, spf);
            fragmentTransaction.commit();
        }
    }

    public void onEditPerson(){
        FragmentManager frmr = getFragmentManager();
        frmr.beginTransaction()
        .replace(R.id.container, EditPersonFragment.newInstance("Nikita Lisabeth", "nikitalisabethgard@hotmail.com"))
        .addToBackStack(null)
        .commit();

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

    @Override
    public void onSavePerson(String name, String email) {

    }
}
