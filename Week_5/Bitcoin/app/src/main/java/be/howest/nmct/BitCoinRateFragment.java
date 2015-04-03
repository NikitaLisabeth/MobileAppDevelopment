package be.howest.nmct;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class BitCoinRateFragment extends Fragment {


    public BitCoinRateFragment() {
        // Required empty public constructor
    }
    static final String BITCOIN_RATE = "be.howest.nmct.NEW_BITCOIN_RATE";
    private OnChangeFragmentListener onFragment2Listener;

    public static BitCoinRateFragment newInstance(float bitcoinrate){
        BitCoinRateFragment fragment = new BitCoinRateFragment();
        Bundle args = new Bundle();
        args.putFloat(BITCOIN_RATE, bitcoinrate);
        fragment.setArguments(args);
        return fragment;
    }

    private Button btnWijzig;
    private EditText txtBedragInEuro;

    private float rate1BitcoinInEuros;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bit_coin_rate, container, false);
        btnWijzig = (Button) v.findViewById(R.id.btnWijzigen);
        txtBedragInEuro = (EditText) v.findViewById(R.id.txtAantalEuro);
        //getLatestBitcoin();
        if(rate1BitcoinInEuros !=0.0f){
            txtBedragInEuro.setText(""+rate1BitcoinInEuros);
        }
        return v;

    }

    public  void getLatestBitcoin(){
       //float nieuweWisselkoers = Float.parseFloat( txtBedragInEuro.getText().toString());
        //rate1BitcoinInEuros = nieuweWisselkoers;
        txtBedragInEuro.setText(rate1BitcoinInEuros+"");
    }
    public interface OnChangeFragmentListener {
        public void onNewBitCoinRate(float rate);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            rate1BitcoinInEuros = getArguments().getFloat(BITCOIN_RATE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            OnChangeFragmentListener onChangeFragmentListener = (OnChangeFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragment1Listener");
        }
    }
}
