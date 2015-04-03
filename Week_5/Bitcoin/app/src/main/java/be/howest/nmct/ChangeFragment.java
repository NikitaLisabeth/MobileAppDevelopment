package be.howest.nmct;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeFragment extends Fragment {


    public ChangeFragment() {
        // Required empty public constructor
    }

    private Button btnToEuro;
    private Button btnToBitCoin;
    private EditText txtEuro;
    private EditText txtBitcoin;
    private Button btnWijzigKoers;
    private TextView tvWaardeBitcoin;

    private float currentRateBitcoinInEuro = 100;
    static final String BITCOIN_RATE = "be.howest.nmct.NEW_BITCOIN_RATE";
    private OnBitCoinFragmentListener onFragment1Listener;

    public static ChangeFragment newInstance(float bitcoinRate) {
        ChangeFragment fragment = new ChangeFragment();
        Bundle args = new Bundle();
        args.putFloat(BITCOIN_RATE, bitcoinRate);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bit_coin_rate, container, false);

        btnToBitCoin = (Button) v.findViewById(R.id.btnNaarBitcoin);
        txtBitcoin = (EditText) v.findViewById(R.id.txtBitcoin);
        btnToEuro = (Button) v.findViewById(R.id.btnNaarEuro);
        txtEuro = (EditText) v.findViewById(R.id.txtEuro);
        btnWijzigKoers = (Button) v.findViewById(R.id.btnWijzigKoers);
        tvWaardeBitcoin = (TextView) v.findViewById(R.id.tvWaardeBitcoin);

        if (currentRateBitcoinInEuro != 0.0f){
            String text = "1 bitcoin = "+ currentRateBitcoinInEuro + "€";
            tvWaardeBitcoin.setText(text);
        }

        btnToEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToEuro();
            }
        });
        btnToBitCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToBitcoin();
            }
        });
        btnWijzigKoers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verstuurDataNaarBitCoinRateFragment();
            }
        });
        return v;
    }


    private void changeToEuro(){
        float bitcoin = Float.parseFloat(txtBitcoin.getText().toString());
        float euro = bitcoin * currentRateBitcoinInEuro;
        txtEuro.setText(euro+"");
    }

    private void changeToBitcoin(){
        float euro = Float.parseFloat(txtEuro.getText().toString());
        float bitcoin = euro / currentRateBitcoinInEuro;
        txtBitcoin.setText(bitcoin+"");
    }

    private void verstuurDataNaarBitCoinRateFragment(){
        if (onFragment1Listener != null)
            onFragment1Listener.onBitCoinRate(currentRateBitcoinInEuro);
    }


    public interface OnBitCoinFragmentListener {
        public void onBitCoinRate (float rate);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onFragment1Listener = (OnBitCoinFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragment1Listener");
        }
    }
}
