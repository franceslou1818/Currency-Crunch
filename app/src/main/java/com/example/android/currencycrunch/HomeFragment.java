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

//    Preferences prefUser;

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

    String chosenFromCountry;
    String chosenToCountry;
    String chosenFromLang;
    String chosenToLang;
    String chosenFromCurr;
    String chosenToCurr;
    String fromSign;
    String toSign;
    
    Button changePrefBtn;

    public HomeFragment() { // Required empty public constructor

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
        spinnerFromCountry.setSelection(adapterFromCountry.getPosition(Preferences.getChosenFromCountry()));
        spinnerFromCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                chosenFromCountry = selectedItemText;
                spinnerFromLanguage = (Spinner) getView().findViewById(R.id.spinnerFromLanguage);
                adapterFromLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getLanguagesOfCountry(selectedItemText));
                adapterFromLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFromLanguage.setAdapter(adapterFromLanguage);
                spinnerFromLanguage.setSelection(adapterFromLanguage.getPosition(Preferences.getChosenFromLanguage()));
                spinnerFromLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenFromLang = selectedItemText;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


                spinnerFromCurrency = (Spinner) getView().findViewById(R.id.spinnerFromCurrency);
                adapterFromCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getCurrenciesOfCountry(selectedItemText));
                adapterFromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFromCurrency.setAdapter(adapterFromCurrency);
                spinnerFromCurrency.setSelection(adapterFromCurrency.getPosition(Preferences.getChosenFromCurrency()));
                spinnerFromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenFromCurr = selectedItemText;

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
        spinnerToCountry.setSelection(adapterToCountry.getPosition(Preferences.getChosenToCountry()));
        spinnerToCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                chosenToCountry = selectedItemText;
                spinnerToLanguage = (Spinner) getView().findViewById(R.id.spinnerToLanguage);
                adapterToLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getLanguagesOfCountry(selectedItemText));
                adapterToLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerToLanguage.setAdapter(adapterToLanguage);
                spinnerToLanguage.setSelection(adapterToLanguage.getPosition(Preferences.getChosenToLanguage()));
                spinnerToLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenToLang = selectedItemText;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


                spinnerToCurrency = (Spinner) getView().findViewById(R.id.spinnerToCurrency);
                adapterToCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Preferences.getCurrenciesOfCountry(selectedItemText));
                adapterToCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerToCurrency.setAdapter(adapterToCurrency);
                spinnerToCurrency.setSelection(adapterToCurrency.getPosition(Preferences.getChosenToCurrency()));
                spinnerToCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenToCurr = selectedItemText;

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

        changePrefBtn = (Button) getView().findViewById(R.id.buttonChangePref);
        changePrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Saving().execute();
            }
        });

    }

    public void saved(){
        String fromCode = Preferences.getCodeOfCurr(chosenFromCurr);
        String toCode = Preferences.getCodeOfCurr(chosenToCurr);
        String signs = Preferences.getCurrSigns(fromCode,toCode);
        String[] split = signs.split("&&");
        Preferences.saveSharedUserPref(chosenFromCountry,chosenToCountry,chosenFromLang,chosenToLang,chosenFromCurr,chosenToCurr,split[0],split[1]);
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////
    private class Saving extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(getActivity(), null, "Saving Preferences...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();

            super.onPostExecute(result);
            saved();
        }


    }
}
