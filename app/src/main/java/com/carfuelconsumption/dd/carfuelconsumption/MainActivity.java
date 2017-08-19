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

    //Создем пустые компоненты
    android.app.FragmentTransaction fragmentTransaction;
    FragmentInstructions fragmentInstructions;
    FragmentCounting fragmentCounting;

    //Создем пустые компоненты
    Button btnCounting;
    Button btnInstructions;

    //Код рекламы AdMod
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Код рекламы AdMod
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Инициализируем Фрагменты
        fragmentInstructions = new FragmentInstructions();
        fragmentCounting = new FragmentCounting();

        //Инициализируем кнопки
        btnCounting = (Button) findViewById(R.id.btnCounting);
        btnInstructions = (Button) findViewById(R.id.btnInstructions);

        //Определяем видимость кнопок
        btnInstructions.setVisibility(View.VISIBLE);
        btnCounting.setVisibility(View.GONE);

        //Запускаем Фрагмент
        fragment1();

        //Событие при нажатии кнопки btnCounting
        btnCounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentInstructions.isAdded()) {

                    ////Определяем видимость кнопок
                    btnInstructions.setVisibility(View.VISIBLE);
                    btnCounting.setVisibility(View.GONE);

                    //Запускаем Фрагмент
                    fragment1();
                }
            }
        });

        //Событие при нажатии кнопки btnInstructions
        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentCounting.isAdded()) {

                    //Определяем видимость кнопок
                    btnInstructions.setVisibility(View.GONE);
                    btnCounting.setVisibility(View.VISIBLE);

                    //Запускаем Фрагмент
                    fragment2();
                }
            }
        });


    }

    //Запуск Фрагмента
    private void fragment1() {

        //Начинаем транаакцию фрагмента
        fragmentTransaction = getFragmentManager().beginTransaction();

        //убираем фрагмент fragmentInstructions
        fragmentTransaction.remove(fragmentInstructions);

        //добавляем layout фрагмента
        fragmentTransaction.add(R.id.frameLayout1, fragmentCounting);

        //Вводим в действие изменения
        fragmentTransaction.commit();

    }

    private void fragment2() {

        //Начинаем транаакцию фрагмента
        fragmentTransaction = getFragmentManager().beginTransaction();

        //убираем фрагмент fragmentCounting
        fragmentTransaction.remove(fragmentCounting);

        //добавляем layout фрагмента
        fragmentTransaction.add(R.id.frameLayout1, fragmentInstructions);

        //Вводим в действие изменения
        fragmentTransaction.commit();

    }

}
