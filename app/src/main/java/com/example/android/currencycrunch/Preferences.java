package com.example.android.currencycrunch;


import android.content.Context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.example.android.currencycrunch.R;


public class Preferences {

    Context context;

    private static Hashtable<String,String[]> countriesLangCurrDict = new Hashtable<String,String[]>(); // country:[langs, currs]
    private static Map<String, String> languagesCodes = new HashMap<String, String>();
    private static Map<String, String> currenciesCodes = new HashMap<String, String>();

    private static String chosenFromCountry;
    private static String chosenToCountry;

    private static String chosenFromLanguage;
    private static String chosenToLanguage;

    private static String chosenFromCurrency;
    private static String chosenToCurrency;

    public Preferences(Context c) {
        context = c;
        populateDicts();

        chosenFromCountry = "UK";
        chosenToCountry = "Philippines";

        chosenFromLanguage = "English(en)";
        chosenToLanguage = "Tagalog(tl)";

        chosenFromCurrency = "Pound Sterling(gbp)";
        chosenToCurrency = "Philippine Peso(php)";

    }

    public void populateDicts() {

//        String[] countryLangCurr = getActivity().getResources().getStringArray(R.array.countryLangCurr);
        String[] countryLangCurr = context.getResources().getStringArray(R.array.countryLangCurr);
        for (String s : countryLangCurr) {
            String[] sSplit = s.split(":");
            countriesLangCurrDict.put(sSplit[0], Arrays.copyOfRange(sSplit, 1, sSplit.length));
        }

//        for (String l : getActivity().getResources().getStringArray(R.array.languages)) {
        for (String l : context.getResources().getStringArray(R.array.languages)) {
            String[] lSplit = l.split(":");
            languagesCodes.put(lSplit[0], lSplit[1]);
        }

//        for (String c : getActivity().getResources().getStringArray(R.array.currencies)) {
        for (String c : context.getResources().getStringArray(R.array.currencies)) {
            String[] cSplit = c.split(":");
            currenciesCodes.put(cSplit[0], cSplit[1]);
        }

    }

    public static String[] getAllCountries(){
        Set<String> countries = countriesLangCurrDict.keySet();
        String[] countriesArray = countries.toArray(new String[countries.size()]);
        return countriesArray;
    }


    public static String[] getLanguagesOfCountry(String country) { return countriesLangCurrDict.get(country)[0].split("_"); }

    public static String[] getCurrenciesOfCountry(String country) { return countriesLangCurrDict.get(country)[1].split("_"); }

    public static String getChosenFromLanguage() { return chosenFromLanguage; }

    public static String getChosenToLanguage() {
        return chosenToLanguage;
    }

    public static String getChosenFromCurrency() {
        return chosenFromCurrency;
    }

    public static String getChosenToCurrency() {
        return chosenToCurrency;
    }

    public static String getChosenFromLanguageCode() { return languagesCodes.get(chosenFromLanguage); }

    public static String getChosenToLanguageCode() { return languagesCodes.get(chosenToLanguage); }

    public static String getChosenFromCurrencyCode() { return currenciesCodes.get(chosenFromCurrency); }

    public static String getChosenToCurrencyCode() { return currenciesCodes.get(chosenToCurrency); }

    ////
    public static void setChosenFromCountry(String s) { chosenFromCountry = s; }

    public static void setChosenToCountry(String s) {
        chosenToCountry = s;
    }

    public static void setChosenFromLanguage(String s) { chosenFromLanguage = s; }

    public static void setChosenToLanguage(String s) {
        chosenToLanguage = s;
    }

    public static void setChosenFromCurrency(String s) {
        chosenFromCurrency = s;
    }

    public static void setChosenToCurrency(String s) {
        chosenToCurrency = s;
    }




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

}
