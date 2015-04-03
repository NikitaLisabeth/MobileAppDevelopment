package howest.personen;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowPersonFragment extends Fragment {

    public interface EditPersonListener{
        void onEditPerson();
    }

    private  String mName;
    private  String nEmail;
    private TextView mTvName;
    private TextView mTvEmail;
    private EditPersonListener mActivity;

    public ShowPersonFragment() {
        // Required empty public constructor
        /*mName = name;
        nEmail = email;*/
    }

   public static ShowPersonFragment newInstance(String name, String email){
       Bundle arguments = new Bundle();
       arguments.putString("howest.ARG_NAME", name);
       arguments.putString("howest.ARG_EMAIL", email);

       ShowPersonFragment spf = new ShowPersonFragment();
       spf.setArguments(arguments);

       return spf;
   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        if(argument != null){
            mName = argument.getString("howest.ARG_NAME");
            nEmail = argument.getString("howest.ARG_EMAIL");
        }

        setHasOptionsMenu(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mn_edit_person){
            mActivity.onEditPerson();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAttach(Activity activity) {
        mActivity = (EditPersonListener) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_show_person, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_show_person2, container, false);
        mTvName = (TextView) v.findViewById(R.id.tvName);
        mTvName.setText(mName);
        mTvEmail = (TextView) v.findViewById(R.id.tvEmail);
        mTvEmail.setText(nEmail);
        return v;
    }


}
