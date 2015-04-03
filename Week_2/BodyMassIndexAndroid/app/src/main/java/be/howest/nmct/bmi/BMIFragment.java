package be.howest.nmct.bmi;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BMIFragment extends Fragment {

    private EditText editTextHeight;
    private EditText editTextMass;
    private Button buttonUpdate;
    private TextView textViewIndex;
    private TextView textViewCategory;
    private ImageView imageViewCategory;
    private BMIInfo bmi = null;
    private BMIInfo.Category category = BMIInfo.Category.NORMAL;
    public  static  final  String PREFS_NAME = "MyPrefsFile";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmi, container, false);

        this.editTextHeight = (EditText) v.findViewById(R.id.editTextYourHeight);
        this.editTextMass = (EditText) v.findViewById(R.id.editTextYourMass);
        this.buttonUpdate = (Button) v.findViewById(R.id.buttonUpdate);
        this.textViewIndex = (TextView) v.findViewById(R.id.textViewIndexResult);
        this.textViewCategory = (TextView) v.findViewById(R.id.textViewCategoryResult);
        this.imageViewCategory = (ImageView) v.findViewById(R.id.imageView);

        this.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateBMI();
            }
        });


        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String height = settings.getString("height","");
        String mass = settings.getString("mass","");
        editTextMass.setText(mass);
        editTextHeight.setText(height);

        return v;
    }


    @Override
    public void onStop(){
        super.onStop();

        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("height",this.editTextHeight.getText().toString());
        editor.putString("mass", this.editTextMass.getText().toString());
        editor.commit();
    }

    private void CalculateBMI(){

        if(!this.editTextHeight.getText().toString().isEmpty()
                && !this.editTextMass.getText().toString().isEmpty()
                && !this.editTextHeight.getText().toString().equals("0")
                && !this.editTextMass.getText().toString().equals("0")) {


            float height = Float.parseFloat(this.editTextHeight.getText().toString());
            int mass = Integer.parseInt(this.editTextMass.getText().toString());

            bmi = new BMIInfo(height, mass);
            float index = bmi.Recalculate();
            category = BMIInfo.Category.getCategory(index);

            this.textViewIndex.setText("" + index);
            this.textViewCategory.setText("" + category);
            this.imageViewCategory.setImageResource(getResourceId(category));
        }
    }

    private int getResourceId(BMIInfo.Category category){
        switch (category){
            case LARGE_UNDERWEIGHT:
                return R.drawable.silhouette_1;
            case MEDIUM_UNDERWEIGHT:
                return R.drawable.silhouette_2;
            case UNDERWEIGHT:
                return R.drawable.silhouette_3;
            case NORMAL:
                return R.drawable.silhouette_4;
            case OVERWEIGHT:
                return R.drawable.silhouette_5;
            case MODERATE_OVERWEIGHT:
                return R.drawable.silhouette_6;
            case MEDIUM_OVERWEIGHT:
                return R.drawable.silhouette_7;
            case LARGE_OVERWEIGHT:
                return R.drawable.silhouette_8;
        }
        return R.drawable.silhouette_4;
    }

    @Override
    public void onResume() {
        super.onResume();
        CalculateBMI();
    }
}
