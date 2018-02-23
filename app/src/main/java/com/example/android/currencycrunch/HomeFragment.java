package com.example.android.currencycrunch;


import android.app.ProgressDialog;
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
import java.util.Hashtable;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static Hashtable<String,String[]> countriesLangCurrDict = new Hashtable<String,String[]>(); // country:[langs, currs]
    private static Hashtable<String,String> languagesCodes = new Hashtable<String,String>(); // <languageCode,languageName>
    private static Hashtable<String,String> currenciesCodes = new Hashtable<String,String>(); // <currencyCode, currencyName>

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        populateDicts(); //populating all dictionaries from straing values xml files

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

//        System.out.println("************getAllChosen: " + getAllChosen());
    }

    public void populateDicts() {
        for (String l : getResources().getStringArray(R.array.languages)) {
            String[] lSplit = l.split(":");
            languagesCodes.put(lSplit[0], lSplit[1]);
        }
        for (String c : getResources().getStringArray(R.array.currencies)) {
            String[] cSplit = c.split(":");
            currenciesCodes.put(cSplit[0], cSplit[1]);
        }
        String[] countryLangCurr = getResources().getStringArray(R.array.countryLangCurr);
        for (String s : countryLangCurr) {
            String[] sSplit = s.split(":");
            countriesLangCurrDict.put(sSplit[0],Arrays.copyOfRange(sSplit, 1, sSplit.length));
        }
    }

    public String[] getAllCountries(){
        String[] countries = new String[countriesLangCurrDict.size()];
        int i = 0;
        for (String key : countriesLangCurrDict.keySet()) {
            countries[i] = key;
            i++;
        }
        return countries;
    }

    public String[] getAllLanguages(){
        String[] languages = new String[languagesCodes.size()];
        int i = 0;
        for (String value : languagesCodes.values()) {
            languages[i] = value;
            i++;
        }
//        System.out.println("******************languages: " + Arrays.toString(languages));
        return languages;
    }

    public String[] getAllCurrencies(){
        String[] currencies = new String[currenciesCodes.size()];
        int i = 0;
        for (String value : currenciesCodes.values()) {
            currencies[i] = value;
            i++;
        }
//        System.out.println("******************Currencies: " + Arrays.toString(currencies));
        return currencies;
    }

    public String[] getLanguagesOfCountry(String country) {
        String[] langCodes = countriesLangCurrDict.get(country)[0].split(" ");
        String[] correspondingLangs = new String[langCodes.length];
        for (int i = 0; i < langCodes.length; i++) {
            correspondingLangs[i] = languagesCodes.get(langCodes[i]);
        }
//        System.out.println("******************getLanguagesOfCountry: " + Arrays.toString(correspondingLangs));
        return correspondingLangs;
    }

    public String[] getCurrenciesOfCountry(String country) {
        String[] currCodes = countriesLangCurrDict.get(country)[1].split(" ");
        String[] correspondingCurrs = new String[currCodes.length];
        for (int i = 0; i < currCodes.length; i++) {
            correspondingCurrs[i] = currenciesCodes.get(currCodes[i]);
        }
//        System.out.println("******************getCurrenciesOfCountry: " + Arrays.toString(correspondingCurrs));
        return correspondingCurrs;
    }

    public String getChosenFromLanguageCode() {
        for (String key : languagesCodes.keySet()) {
            if ( languagesCodes.get(key) == chosenFromLanguage)
                return key;
        }
        return null;
    }

    public String getChosenToLanguageCode() {
        for (String key : languagesCodes.keySet()) {
            if ( languagesCodes.get(key) == chosenToLanguage)
                return key;
        }
        return null;
    }

    public String getChosenFromLanguage() {
        return chosenFromLanguage;
    }

    public String getChosenToLanguage() {
        return chosenToLanguage;
    }

    public String getChosenFromCurrencyCode() {
        for (String key : currenciesCodes.keySet()) {
            if ( currenciesCodes.get(key) == chosenFromCurrency)
                return key;
        }
        return null;
    }

    public String getChosenToCurrencyCode() {
        for (String key : currenciesCodes.keySet()) {
            if ( currenciesCodes.get(key) == chosenToCurrency)
                return key;
        }
        return null;
    }

    public String getChosenFromCurrency() {
        return chosenFromCurrency;
    }

    public String getChosenToCurrency() {
        return chosenToCurrency;
    }

    public String getAllChosen() {
        return "chosenFromCountry: " + chosenFromCountry + '\n' +
                "chosenToCountry: " + chosenToCountry + '\n' +
                "chosenFromLanguage: " + chosenFromLanguage + '\n' +
                "chosenToLanguage: " + chosenToLanguage + '\n' +
                "chosenFromCurrency: " + chosenFromCurrency + '\n' +
                "chosenToCurrency: " + chosenToCurrency;
    }

}
