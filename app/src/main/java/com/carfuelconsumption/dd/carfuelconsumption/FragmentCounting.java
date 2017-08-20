package com.carfuelconsumption.dd.carfuelconsumption;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.carfuelconsumption.dd.carfuelconsumption.R.string.zalito_benzina_v_bak;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCounting extends Fragment {


    private EditText et_Cena_litra_topliva;
    private EditText et_Zafiksirovannyj_probeg;
    private EditText et_Poslednij_probeg;
    private TextView tv_Itogo_projdennoe_rasstoyanie;
    private EditText et_oplacheno_za_polnyj_bak;
    private TextView et_zalito_benzina_v_bak;
    private TextView tv_rasxod_benzina_na_100_km;
    private Button btn_poschitat;

    public FragmentCounting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counting, null);

        et_Cena_litra_topliva = (EditText) view.findViewById(R.id.et_Cena_litra_topliva);
        et_Zafiksirovannyj_probeg = (EditText) view.findViewById(R.id.et_Zafiksirovannyj_probeg);
        et_Poslednij_probeg = (EditText) view.findViewById(R.id.et_Poslednij_probeg);
        tv_Itogo_projdennoe_rasstoyanie = (TextView) view.findViewById(R.id.tv_Itogo_projdennoe_rasstoyanie);
        et_oplacheno_za_polnyj_bak = (EditText) view.findViewById(R.id.et_oplacheno_za_polnyj_bak);
        et_zalito_benzina_v_bak = (TextView) view.findViewById(R.id.tv_zalito_benzina_v_bak);
        tv_rasxod_benzina_na_100_km = (TextView) view.findViewById(R.id.tv_rasxod_benzina_na_100_km);
        btn_poschitat = (Button) view.findViewById(R.id.btn_poschitat);

        //button press listener
        btn_poschitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if all the EditTexts all filled
                if (et_Cena_litra_topliva.getText().toString().isEmpty() ||
                        et_Zafiksirovannyj_probeg.getText().toString().isEmpty() ||
                        et_Poslednij_probeg.getText().toString().isEmpty() ||
                        et_oplacheno_za_polnyj_bak.getText().toString().isEmpty() ||
                        et_oplacheno_za_polnyj_bak.getText().toString().isEmpty()

                        ) {
                    Toast.makeText(v.getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                }

                //Check if et_Poslednij_probeg < et_Zafiksirovannyj_probeg
                else if (Integer.valueOf(et_Poslednij_probeg.getText().toString()) <=
                        Integer.valueOf(et_Zafiksirovannyj_probeg.getText().toString())
                        ) {
                    Toast.makeText(v.getContext(), "Пробег не может быть меньше зафиксированного ", Toast.LENGTH_SHORT).show();
                }

                //if all the parameters  filled correct, do the application
                else {

                    tv_rasxod_benzina_na_100_km(Itogo_projdennoe_rasstoyanie(), zalito_benzina_v_bak());

                }
                //setting result

            }
        });
        return view;
    }

    //result of the taken road
    private int Itogo_projdennoe_rasstoyanie() {
        //getting data from UI
        int Zafiksirovannyj_probeg = Integer.parseInt(et_Zafiksirovannyj_probeg.getText().toString());
        int Poslednij_probeg = Integer.parseInt(et_Poslednij_probeg.getText().toString());

        //counting difference between probeg
        int difference = Poslednij_probeg - Zafiksirovannyj_probeg;

        //setting difference to tv_Itogo_projdennoe_rasstoyanie
        tv_Itogo_projdennoe_rasstoyanie.setText(String.valueOf(difference)+ " км");
        return difference;
    }

    //couting how much gas was inserted into the car
    private int zalito_benzina_v_bak() {
        //getting data from UI
        int oplacheno_za_polnyj_bak = Integer.parseInt(et_oplacheno_za_polnyj_bak.getText().toString());
        int Cena_litra_topliva = Integer.parseInt(et_Cena_litra_topliva.getText().toString());

        //counting putted gas into the car
        int zalito_benzina_v_bak = oplacheno_za_polnyj_bak / Cena_litra_topliva;


        //setting to TextView the result
        et_zalito_benzina_v_bak.setText(String.valueOf(zalito_benzina_v_bak)+" литров");
        return zalito_benzina_v_bak;

    }

    //counting the result
    private void tv_rasxod_benzina_na_100_km(int Itogo_projdennoe_rasstoyanie, int zalito_benzina_v_bak) {

        //counting spent gas for 100 km
        int rasxod_benzina_na_100_km = zalito_benzina_v_bak * 100 / Itogo_projdennoe_rasstoyanie;

        //setting data to textview
        tv_rasxod_benzina_na_100_km.setText(String.valueOf(rasxod_benzina_na_100_km)+ " литров");
    }

}
