package com.example.countriescitieshouses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button ok;
    private Spinner countries;
    private Spinner cities;
    private Spinner house;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inIt();
    }

    private void inIt() {
        ok = findViewById(R.id.okBtn);
        countries = findViewById(R.id.chooseCountry);
        cities = findViewById(R.id.chooseCity);
        house = findViewById(R.id.chooseHouse);

        spinnerCountries();
        spinnerHouses();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        countries.getSelectedItem().toString() + " "
                + cities.getSelectedItem().toString() + " "
                + house.getSelectedItem().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void spinnerCountries() {
        ArrayAdapter adapterCountries = ArrayAdapter.createFromResource(
                this, R.array.Countries, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countries.setAdapter(adapterCountries);

        countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] countriesArray = getResources().getStringArray(R.array.Countries);
                spinnerCities(countriesArray[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void spinnerCities(String country) {
        ArrayAdapter adapterCities = null;
        switch (country) {
            case "Россия":
                adapterCities = ArrayAdapter.createFromResource(this, R.array.RussianCities,
                        android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapterCities = ArrayAdapter.createFromResource(this, R.array.UkrainianCities,
                        android.R.layout.simple_spinner_item);
                break;
            case "Беларусь":
                adapterCities = ArrayAdapter.createFromResource(this, R.array.BelorussianCities,
                        android.R.layout.simple_spinner_item);
                break;
        }
        if(adapterCities != null) {
            adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cities.setAdapter(adapterCities);
        }
    }

    private void spinnerHouses() {
        Integer[] houseNumbers = new Integer[50];
        for(int i = 1; i < 51; i++) {
            houseNumbers[i - 1] = i;
        }

        ArrayAdapter<Integer> adapterHouses = new ArrayAdapter<>(
                        this, android.R.layout.simple_spinner_item, houseNumbers);
        house.setAdapter(adapterHouses);
    }
}