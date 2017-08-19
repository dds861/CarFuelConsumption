package com.carfuelconsumption.dd.carfuelconsumption;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    android.app.FragmentTransaction fragmentTransaction;
    FragmentInstructions fragmentInstructions;
    FragmentCounting fragmentCounting;

    Button btnCounting;
    Button btnInstructions;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        fragmentInstructions = new FragmentInstructions();
        fragmentCounting = new FragmentCounting();

        btnCounting = (Button) findViewById(R.id.btnCounting);
        btnInstructions = (Button) findViewById(R.id.btnInstructions);

        btnInstructions.setVisibility(View.VISIBLE);
        btnCounting.setVisibility(View.GONE);

        fragment1();


        btnCounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentInstructions.isAdded()) {


                    btnInstructions.setVisibility(View.VISIBLE);
                    btnCounting.setVisibility(View.GONE);


                    fragment1();
                }
            }
        });

        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentCounting.isAdded()) {
                    btnInstructions.setVisibility(View.GONE);
                    btnCounting.setVisibility(View.VISIBLE);
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
