package com.carfuelconsumption.dd.carfuelconsumption;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    android.app.FragmentTransaction fragmentTransaction;
    FragmentInstructions fragmentInstructions;
    FragmentCounting fragmentCounting;

    Button btnCounting;
    Button btnInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentInstructions = new FragmentInstructions();
        fragmentCounting = new FragmentCounting();

        btnCounting = (Button) findViewById(R.id.btnCounting);
        btnInstructions = (Button) findViewById(R.id.btnInstructions);

        fragment1();


        btnCounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentInstructions.isAdded()) {
                    fragment1();
                }
            }
        });

        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentCounting.isAdded()) {
                    fragment2();
                }
            }
        });


    }

    private void fragment1() {

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragmentInstructions);
        fragmentTransaction.add(R.id.frameLayout1, fragmentCounting);
        fragmentTransaction.commit();


    }

    private void fragment2() {

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragmentCounting);
        fragmentTransaction.add(R.id.frameLayout1, fragmentInstructions);
        fragmentTransaction.commit();

    }

}
