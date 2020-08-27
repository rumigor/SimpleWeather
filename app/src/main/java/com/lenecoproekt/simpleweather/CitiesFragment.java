package com.lenecoproekt.simpleweather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class CitiesFragment extends Fragment {


    private static final String CITY_NAME = "cityName";

    private String city;
    private Weather weather;


    public CitiesFragment() {
        // Required empty public constructor
    }


    public static CitiesFragment create(String city) {
        CitiesFragment fragment = new CitiesFragment();
        Bundle args = new Bundle();
        args.putString(CITY_NAME, city);
        fragment.setArguments(args);
        return fragment;
    }
    public String getCity() {
        city = getArguments().getString(CITY_NAME);
        return city;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        city = getCity();
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        final String[] cities = getResources().getStringArray(R.array.cities);
        final RadioButton [] radioButtons = new RadioButton[cities.length];
        for (int i = 0; i < cities.length; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(cities[i]);
            rb.setTextSize(30);
            radioButtons[i] = rb;
            layoutView.addView(radioButtons[i]);
        }
        Button change = new Button(getContext());
        change.setText(R.string.choose_city);
        change.setTextSize(18);
        layoutView.addView(change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = 0;
                for (int i = 0; i < radioButtons.length; i++) {
                    if (radioButtons[i].isChecked()){
                        index = i;
                        break;
                    }
                }
                if (index == 0){
                    weather = new Weather("+15.6", "1 m/s, E", "5:45", "20:16", "rain", 4);
                }
                if (index == 1){
                    weather = new Weather("+18", "2-5 m/s, SW", "6:05", "20:22", "no", 3);
                }
                if (index == 2){
                    weather = new Weather("+29", "3 m/s, S", "7:05", "20:12", "no", 2);
                }
                showWeather(cities[index], weather);
            }
        });
    }

    private void showWeather(String cityN, Weather weather) {
        WeatherFragment weatherFragment = (WeatherFragment) getFragmentManager().findFragmentById(R.id.currentWeather);
        if (!city.equals(cityN)){
            weatherFragment = WeatherFragment.create(weather, null, cityN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.currentWeather, weatherFragment);  // замена фрагмента
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }
}