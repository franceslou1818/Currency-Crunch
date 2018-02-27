package com.example.android.currencycrunch;


import android.app.Activity;
import android.app.ProgressDialog;
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


    private static Hashtable<String,String[]> countriesLangCurrDict = new Hashtable<String,String[]>(); // country:[langs, currs]
//    private static Hashtable<String,String> languagesCodes = new Hashtable<String,String>(); // <languageCode,languageName>
    private static Map<String, String> languagesCodes = new HashMap<String, String>();
//    private static Hashtable<String,String> currenciesCodes = new Hashtable<String,String>(); // <currencyCode, currencyName>
    private static Map<String, String> currenciesCodes = new HashMap<String, String>();

    private static String chosenFromCountry;
    private static String chosenToCountry;

    private static String chosenFromLanguage;
    private static String chosenToLanguage;

    private static String chosenFromCurrency;
    private static String chosenToCurrency;

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

    public HomeFragment() {
        // Required empty public constructor
//        chosenFromCountry = "UK";
//        chosenToCountry = "Philippines";
//
//        chosenFromLanguage = "English(en)";
//        chosenToLanguage = "Tagalog(tl)";
//
//        chosenFromCurrency = "Pound Sterling(gbp)";
//        chosenToCurrency = "Philippine Peso(php)";
//        populateDicts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        populateDicts(); //populating all dictionaries from string values xml files
//        System.out.println("test");

        spinnerFromCountry = (Spinner) getView().findViewById(R.id.spinnerFromCountry);
        adapterFromCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getAllCountries());
        adapterFromCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCountry.setAdapter(adapterFromCountry);
        spinnerFromCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                chosenFromCountry = selectedItemText;
                spinnerFromLanguage = (Spinner) getView().findViewById(R.id.spinnerFromLanguage);
                adapterFromLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getLanguagesOfCountry(selectedItemText));
                adapterFromLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFromLanguage.setAdapter(adapterFromLanguage);
                spinnerFromLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenFromLanguage = selectedItemText;

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


                spinnerFromCurrency = (Spinner) getView().findViewById(R.id.spinnerFromCurrency);
                adapterFromCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getCurrenciesOfCountry(selectedItemText));
                adapterFromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFromCurrency.setAdapter(adapterFromCurrency);
                spinnerFromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenFromCurrency = selectedItemText;

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
        adapterToCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getAllCountries());
        adapterToCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToCountry.setAdapter(adapterToCountry);
        spinnerToCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                chosenToCountry = selectedItemText;
                spinnerToLanguage = (Spinner) getView().findViewById(R.id.spinnerToLanguage);
                adapterToLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getLanguagesOfCountry(selectedItemText));
                adapterToLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerToLanguage.setAdapter(adapterToLanguage);
                spinnerToLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenToLanguage = selectedItemText;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });


                spinnerToCurrency = (Spinner) getView().findViewById(R.id.spinnerToCurrency);
                adapterToCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getCurrenciesOfCountry(selectedItemText));
                adapterToCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerToCurrency.setAdapter(adapterToCurrency);
                spinnerToCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        String selectedItemText = (String) adapterView.getItemAtPosition(position);
                        chosenToCurrency = selectedItemText;

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

    public void populateDicts() {


        String[] countryLangCurr = getActivity().getResources().getStringArray(R.array.countryLangCurr);
        for (String s : countryLangCurr) {
            String[] sSplit = s.split(":");
            countriesLangCurrDict.put(sSplit[0],Arrays.copyOfRange(sSplit, 1, sSplit.length));
        }

        for (String l : getActivity().getResources().getStringArray(R.array.languages)) {
            String[] lSplit = l.split(":");
            languagesCodes.put(lSplit[0], lSplit[1]);
        }

        for (String c : getActivity().getResources().getStringArray(R.array.currencies)) {
            String[] cSplit = c.split(":");
            currenciesCodes.put(cSplit[0], cSplit[1]);
        }

    }

    public String[] getAllCountries(){
        Set<String> countries = countriesLangCurrDict.keySet();
        String[] countriesArray = countries.toArray(new String[countries.size()]);
        return countriesArray;
    }


    public String[] getLanguagesOfCountry(String country) { return countriesLangCurrDict.get(country)[0].split("_"); }

    public String[] getCurrenciesOfCountry(String country) { return countriesLangCurrDict.get(country)[1].split("_"); }

    public String getChosenFromLanguage() {
        return chosenFromLanguage;
    }

    public String getChosenToLanguage() {
        return chosenToLanguage;
    }

    public String getChosenFromCurrency() {
        return chosenFromCurrency;
    }

    public String getChosenToCurrency() {
        return chosenToCurrency;
    }

    public String getChosenFromLanguageCode() { return languagesCodes.get(chosenFromLanguage); }

    public String getChosenToLanguageCode() { return languagesCodes.get(chosenToLanguage); }

    public String getChosenFromCurrencyCode() { return currenciesCodes.get(chosenFromCurrency); }

    public String getChosenToCurrencyCode() { return currenciesCodes.get(chosenToCurrency); }



    public String getAllChosen() {
        return "chosenFromCountry: " + chosenFromCountry + '\n' +
                "chosenToCountry: " + chosenToCountry + '\n' +
                "chosenFromLanguage: " + chosenFromLanguage + '\n' +
                "chosenToLanguage: " + chosenToLanguage + '\n' +
                "chosenFromCurrency: " + chosenFromCurrency + '\n' +
                "chosenToCurrency: " + chosenToCurrency + '\n' +
                "getChosenFromLanguageCode(): " + getChosenFromLanguageCode() + '\n' +
                "getChosenToLanguageCode(): " + getChosenToLanguageCode() + '\n' +
                "getChosenFromCurrencyCode(): " + getChosenFromCurrencyCode() + '\n' +
                "getChosenToCurrencyCode(): " + getChosenToCurrencyCode() + '\n';
    }
//    public static void main(String[] args) {
//
//        HomeFragment hf = new HomeFragment();
//
//    }
}
