package com.example.android.currencycrunch;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Spinner spinnerFromCountry;
    ArrayAdapter<String> adapterFromCountry;

    Spinner spinnerToCountry;
    ArrayAdapter<String> adapterToCountry;

    Spinner spinnerFromLanguage;
    ArrayAdapter<String> adapterFromLanguage;

    Spinner spinnerToLanguage;
    ArrayAdapter<String> adapterToLanguage;

    Spinner spinnerFromCurrency;
    ArrayAdapter<String> adapterFromCurrency;

    Spinner spinnerToCurrency;
    ArrayAdapter<String> adapterToCurrency;

    public HomeFragment() { // Required empty public constructor
        System.out.println("*************Home fragment constructor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        spinnerFromCountry = (Spinner) getView().findViewById(R.id.spinnerFromCountry);
        adapterFromCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getAllCountries());
        adapterFromCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCountry.setAdapter(adapterFromCountry);
        spinnerFromCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                Preferences.setChosenFromCountry(selectedItemText);
                spinnerFromLanguage = (Spinner) getView().findViewById(R.id.spinnerFromLanguage);
                adapterFromLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getLanguagesOfCountry(selectedItemText));
                adapterFromLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFromLanguage.setAdapter(adapterFromLanguage);
                spinnerFromLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        Preferences.setChosenFromLanguage(selectedItemText);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


                spinnerFromCurrency = (Spinner) getView().findViewById(R.id.spinnerFromCurrency);
                adapterFromCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getCurrenciesOfCountry(selectedItemText));
                adapterFromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFromCurrency.setAdapter(adapterFromCurrency);
                spinnerFromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        Preferences.setChosenFromCurrency(selectedItemText);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerToCountry = (Spinner) getView().findViewById(R.id.spinnerToCountry);
        adapterToCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getAllCountries());
        adapterToCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToCountry.setAdapter(adapterToCountry);
        spinnerToCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                Preferences.setChosenToCountry(selectedItemText);
                spinnerToLanguage = (Spinner) getView().findViewById(R.id.spinnerToLanguage);
                adapterToLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getLanguagesOfCountry(selectedItemText));
                adapterToLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerToLanguage.setAdapter(adapterToLanguage);
                spinnerToLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        Preferences.setChosenToLanguage(selectedItemText);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


                spinnerToCurrency = (Spinner) getView().findViewById(R.id.spinnerToCurrency);
                adapterToCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getCurrenciesOfCountry(selectedItemText));
                adapterToCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerToCurrency.setAdapter(adapterToCurrency);
                spinnerToCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        Preferences.setChosenToCurrency(selectedItemText);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }


}
