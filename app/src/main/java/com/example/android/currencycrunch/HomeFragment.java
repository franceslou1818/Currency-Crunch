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


    private static Hashtable<String,String[]> countriesLangCurrDict = new Hashtable<String,String[]>();
    private static Hashtable<String,String> languagesCodes = new Hashtable<String,String>(); // <languageCode,languageName>
    private static Hashtable<String,String> currenciesCodes = new Hashtable<String,String>(); // <currencyCode, currencyName>

    private static String chosenFromCountry;
    private static String chosenToCountry;

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

        spinnerToCountry = (Spinner) getView().findViewById(R.id.spinnerToCountry);
        adapterToCountry = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getAllCountries());
        adapterToCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToCountry.setAdapter(adapterToCountry);

        spinnerFromLanguage = (Spinner) getView().findViewById(R.id.spinnerFromLanguage);
        adapterFromLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getAllLanguages());
//        adapterFromLanguage = ArrayAdapter.createFromResource(getActivity(), R.array.languages, android.R.layout.simple_spinner_item);
        adapterFromLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromLanguage.setAdapter(adapterFromLanguage);

        spinnerToLanguage = (Spinner) getView().findViewById(R.id.spinnerToLanguage);
        adapterToLanguage = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getAllLanguages());
//        adapterToLanguage = ArrayAdapter.createFromResource(getActivity(), R.array.languages, android.R.layout.simple_spinner_item);
        adapterToLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToLanguage.setAdapter(adapterToLanguage);

        spinnerFromCurrency = (Spinner) getView().findViewById(R.id.spinnerFromCurrency);
        adapterFromCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getAllCurrencies());

//        adapterFromCurrency = ArrayAdapter.createFromResource(getActivity(), R.array.currencies, android.R.layout.simple_spinner_item);
        adapterFromCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCurrency.setAdapter(adapterFromCurrency);

        spinnerToCurrency = (Spinner) getView().findViewById(R.id.spinnerToCurrency);
        adapterToCurrency = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getAllCurrencies());
//        adapterToCurrency = ArrayAdapter.createFromResource(getActivity(), R.array.currencies, android.R.layout.simple_spinner_item);
        adapterToCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToCurrency.setAdapter(adapterToCurrency);

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

}
