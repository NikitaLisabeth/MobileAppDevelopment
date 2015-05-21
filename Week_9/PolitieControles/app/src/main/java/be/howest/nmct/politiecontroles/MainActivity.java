package be.howest.nmct.politiecontroles;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.widget.Spinner;
import android.widget.Toolbar;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import be.howest.nmct.politiecontroles.models.SnelheidsControle;


public class MainActivity extends Activity implements NavigationFragment.OnStartFragmentListener {

    public static String EXTRA_CONTROLE = "";
    public static String EXTRA_CONTROLES = null;

    private DrawerLayout mDrawerLayout=null;
    private ActionBarDrawerToggle mDrawerToggle=null;
    private ListView mDrawer=null;


    private Toolbar toolbar;
    private Spinner spinner;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner= (Spinner) findViewById(R.id.spinner);

        List<String> frags = new ArrayList<>();

        frags.add("All");

        for (int i=1;i<=12;i++){
            frags.add(convertToMonth(i + ""));

        }

        setActionBar(toolbar);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,frags);


        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.print("id"+id);
                List<SnelheidsControle> lijst = SnelheidsControle.Controles;
                List<SnelheidsControle> lijstInDeMaand = new ArrayList <SnelheidsControle>();
                if(lijst!=null) {

                    if(id==0){
                        changeElement(lijst);
                    }else {


                        for (int i = 0; i < lijst.size(); i++) {
                            String s = String.valueOf(id);
                            String maand = lijst.get(i).Maand;

                            if (maand == s) {
                                lijstInDeMaand.add(lijst.get(i));
                            }
                        }

                        changeElement(lijstInDeMaand);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.getAdapter();

            }
        });

        if (savedInstanceState == null) {
           // getFragmentManager().beginTransaction().add(R.id.container, new ControleMapFragment(), "NavigationFragment").commit();
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(Gravity.LEFT);


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
    public void changeElement(SnelheidsControle controle) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ControleMapFragment fragment = ControleMapFragment.newInstance(controle);
        fragmentTransaction
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void changeElement(List<SnelheidsControle> controle) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ControleMapFragment fragment = ControleMapFragment.newInstance(controle);
        fragmentTransaction
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();

    }
    private String convertToMonth(String month){
        switch (month){
            case "1":
                return "Januari";
            case "2":
                return "Februari";
            case "3":
                return "Maart";
            case "4":
                return "April";
            case "5":
                return "Mei";
            case "6":
                return "Juni";
            case "7":
                return "Juli";
            case "8":
                return "Augustus";
            case "9":
                return "September";
            case "10":
                return "Oktober";
            case "11":
                return "November";
            default:
                return "December";
        }
    }
}
