package com.lenecoproekt.simpleweather;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class WeatherFragment extends Fragment {

    private static final String WEATHER = "Weather_Parameters";
    private static final String SETTINGS = "App_Settings";
    private static final String CITY_NAME = "CityName";

    private Weather weather;
    private AppSettings settings;
    private String city;

    private boolean landOrientation;

    private TextView cityName;
    private TextView temperature;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView precipitation;
    private ImageView weatherCondition;
    private Button changeCity;
    private Button settingsBtn;



    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment create(Weather weather, AppSettings settings, String city) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable(WEATHER, weather);
        args.putSerializable(SETTINGS, settings);
        args.putString(CITY_NAME, city);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weather = (Weather) getArguments().getSerializable(WEATHER);
            settings = (AppSettings) getArguments().getSerializable(SETTINGS);
            city = getArguments().getString(CITY_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        temperature = view.findViewById(R.id.temperature);
        wind = view.findViewById(R.id.currentWind);
        sunrise = view.findViewById(R.id.sunriseTime);
        sunset = view.findViewById(R.id.sunsetTime);
        precipitation = view.findViewById(R.id.precepitationData);
        cityName = view.findViewById(R.id.city);
        weatherCondition = view.findViewById(R.id.weatherImg);
        if (city != null){
            cityName.setText(city);
        }
        if (weather != null){
            temperature.setText(weather.getTemperature());
            wind.setText(weather.getWind());
            sunrise.setText(weather.getSunrise());
            sunset.setText(weather.getSunset());
            precipitation.setText(weather.getPrecipitation());
            setPic(weather.getwCond());
        }
        else setPic(4);
        if (settings != null) {
        }
        changeCity = view.findViewById(R.id.cityChoose);
        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CitiesActivity.class);
                intent.putExtra(CITY_NAME, city);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        landOrientation = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            if (savedInstanceState.getSerializable(WEATHER) != null) {
                weather = (Weather) savedInstanceState.getSerializable(WEATHER);
            }
            if (savedInstanceState.getSerializable(SETTINGS) != null) {
                settings = (AppSettings) savedInstanceState.getSerializable(SETTINGS);
            }
            if (savedInstanceState.getString(CITY_NAME) != null){
                city = savedInstanceState.getString(CITY_NAME);
            }
            else city = getString(R.string.saint_petersburg);
        }
        else city = getString(R.string.saint_petersburg);
        if (landOrientation){
            showCities(city);
        }
    }

    private void showCities(String city) {
        if (landOrientation){
            CitiesFragment citiesFragment = (CitiesFragment) getFragmentManager().findFragmentById(R.id.cities);
            if (citiesFragment == null || !citiesFragment.getCity().equals(city)) {
                // Создаем новый фрагмент с текущей позицией для вывода герба
                citiesFragment = CitiesFragment.create(city);

                // Выполняем транзакцию по замене фрагмента
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.cities, citiesFragment);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            // Если нельзя вывести герб рядом, откроем вторую activity
            Intent intent = new Intent();
            intent.setClass(getActivity(), CitiesActivity.class);
            //+ и передадим туда Parcel
            intent.putExtra(CITY_NAME, city);
            startActivity(intent);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(WEATHER, weather);
        outState.putSerializable(SETTINGS, settings);
        outState.putString(CITY_NAME, city);
        super.onSaveInstanceState(outState);
    }

    private void setPic(int weather){
        switch (weather){
            case 1:
                weatherCondition.setImageResource(R.drawable.sun2);
                break;
            case 2:
                weatherCondition.setImageResource(R.drawable.party_cloudy);
                break;
            case 3:
                weatherCondition.setImageResource(R.drawable.cloudy);
                break;
            case 4:
                weatherCondition.setImageResource(R.drawable.rainy);
                break;
            case 5:
                weatherCondition.setImageResource(R.drawable.thunderstorm);
                break;
        }
    }
}