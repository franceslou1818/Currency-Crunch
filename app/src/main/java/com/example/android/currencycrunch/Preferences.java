package com.example.android.currencycrunch;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.example.android.currencycrunch.R;


public class Preferences {

    private static Context context;

    private static SharedPreferences prefUser;
    private static SharedPreferences.Editor prefUserEditor;

    private static Hashtable<String,String[]> countriesLangCurrDict = new Hashtable<String,String[]>(); // {country:[langs, currs]}
    private static Map<String, String> languagesCodes = new HashMap<String, String>(); //{language:code}
    private static Map<String, String> currenciesCodes = new HashMap<String, String>(); // {currency:code}

    private static String[] phrasesList; // common phrases in english

    public Preferences(Context c) {
        context = c;
        populateDicts();
        phrasesList = context.getResources().getStringArray(R.array.phrases); // populate phrases list in english

        prefUser = context.getSharedPreferences("userPref", 0);
        prefUserEditor = prefUser.edit();

//        prefUserEditor.clear();
//        prefUserEditor.apply();
//        setPrefDefaults();

        Map<String,?> prefMap = Preferences.prefUser.getAll();

        if ( prefMap.values().contains(null) &&
                !prefMap.containsKey("chosenFromCountry") &&
                !prefMap.containsKey("chosenToCountry") &&
                !prefMap.containsKey("chosenFromLanguage") &&
                !prefMap.containsKey("chosenToLanguage") &&
                !prefMap.containsKey("chosenFromCurrency") &&
                !prefMap.containsKey("chosenToCurrency") &&
                !prefMap.containsKey("currencySigns") &&
                !prefMap.containsKey("coinsNames") &&
                !prefMap.containsKey("phrasesFrom") &&
                !prefMap.containsKey("phrasesTo") ) {

            System.out.println("********first if1: ");
            setPrefDefaults();

        } //else do nothing. shared preferences is filled

    }

    //defaults
    public void setPrefDefaults() {
        prefUserEditor.clear();
        prefUserEditor.putString("chosenFromCountry","UK");
        prefUserEditor.putString("chosenToCountry", "Philippines");
        prefUserEditor.putString("chosenFromLanguage", "English(en)");
        prefUserEditor.putString("chosenToLanguage","Tagalog(tl)");
        prefUserEditor.putString("chosenFromCurrency","Pound Sterling(gbp)");
        prefUserEditor.putString("chosenToCurrency","Philippine Peso(php)");
        prefUserEditor.putString("currencySigns", getCurrSigns("gbp","php"));
        prefUserEditor.putString("coinNames", getCoinsForCurr("tl","php"));
        prefUserEditor.putString("phrasesFrom", getPhrasesInLang("en"));
        prefUserEditor.putString("phrasesTo", getPhrasesInLang("tl"));
        prefUserEditor.apply();
    }

    // called when user clicks button to save preference in home fragment
    public static void saveSharedUserPref(String fromCountry,String toCountry,
                                            String fromLang,String toLang,
                                            String fromCurr,String toCurr,
                                            String fromSign,String toSign) {
        prefUserEditor.putString("chosenFromCountry",fromCountry);
        prefUserEditor.putString("chosenToCountry", toCountry);
        prefUserEditor.putString("chosenFromLanguage", fromLang);
        prefUserEditor.putString("chosenToLanguage",toLang);
        prefUserEditor.putString("chosenFromCurrency",fromCurr);
        prefUserEditor.putString("chosenToCurrency",toCurr);
//        prefUserEditor.putString("currencySigns", getCurrSigns(getChosenFromCurrencyCode(),getChosenToCurrencyCode()));
        prefUserEditor.putString("currencySigns", getCurrSigns(fromSign,toSign));
        prefUserEditor.putString("coinNames",getCoinsForCurr(languagesCodes.get(toLang),currenciesCodes.get(toCurr)));
        prefUserEditor.putString("phrasesFrom", getPhrasesInLang(languagesCodes.get(fromLang)));
        prefUserEditor.putString("phrasesTo", getPhrasesInLang(languagesCodes.get(toLang)));
        prefUserEditor.apply();
    }


    public static String getPhrasesInLang(String langCode) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<phrasesList.length; i++) {
            String translation = GoogleTranslate.translate(phrasesList[i], "en", langCode);
            sb.append(translation);
            if (i != (phrasesList.length-1))
                sb.append("&&"); // to split to array when retrieving phrases. array needed as it preserves order. set does'nt.
        }
        return sb.toString();
    }

/*    int getRes2 = getResources().getIdentifier(setRes, "array", getPackageName());
    String[] albums = getResources().getStringArray(getRes2);*/

    //    Get coin names for selected currency and language
    public static String getCoinsForCurr(String langCode, String currCode) {
        if (currCode.equals("try")) currCode = "trl";
        int resId = context.getResources().getIdentifier(currCode, "array", context.getPackageName());
        String[] coinList = context.getResources().getStringArray(resId);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coinList.length; i++){
            String translation = GoogleTranslate.translate(coinList[i], "en", langCode);
            sb.append(translation);
            if (i != (coinList.length-1)){
                sb.append("&&");
            }
        }
        return sb.toString();
    }

    public static String getCurrSigns(String fromCode, String toCode){
        String[] signs = context.getResources().getStringArray(R.array.signs);
        StringBuilder fromStr = new StringBuilder();
        StringBuilder toStr = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (String s : signs){
            if (s.contains(fromCode)){
                fromStr.append(s.substring(4));
            }
            if (s.contains(toCode)) {
                toStr.append(s.substring(4));
            }
        }
        sb.append(fromStr + "&&" + toStr);
        return sb.toString();
    }

//////////populate hash maps variables above
    public void populateDicts() {
        String[] countryLangCurr = context.getResources().getStringArray(R.array.countryLangCurr);
        for (String s : countryLangCurr) {
            String[] sSplit = s.split(":");
            countriesLangCurrDict.put(sSplit[0], Arrays.copyOfRange(sSplit, 1, sSplit.length));
        }
        for (String l : context.getResources().getStringArray(R.array.languages)) {
            String[] lSplit = l.split(":");
            languagesCodes.put(lSplit[0], lSplit[1]);
        }
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

    public static String getChosenFromCountry() { return prefUser.getString("chosenFromCountry","N/A"); }

    public static String getChosenToCountry() { return prefUser.getString("chosenToCountry","N/A"); }

    public static String getChosenFromLanguage() { return prefUser.getString("chosenFromLanguage","N/A"); }

    public static String getChosenToLanguage() { return prefUser.getString("chosenToLanguage","N/A"); }

    public static String getChosenFromCurrency() { return prefUser.getString("chosenFromCurrency","N/A"); }

    public static String getChosenToCurrency() { return prefUser.getString("chosenToCurrency","N/A"); }

    public static String getChosenCurrSigns() { return prefUser.getString("currencySigns", "N/A"); }

    public static String getChosenCoinNames() { return prefUser.getString("coinNames", "N/A"); }

    public static String getChosenFromLanguageCode() { return languagesCodes.get(getChosenFromLanguage()); }

    public static String getChosenToLanguageCode() {return languagesCodes.get(getChosenToLanguage());}

    public static String getChosenFromCurrencyCode() {return currenciesCodes.get(getChosenFromCurrency());}

    public static String getChosenToCurrencyCode() {return currenciesCodes.get(getChosenToCurrency());}

    public static String getCodeOfCurr(String s) {

        return currenciesCodes.get(s);
    }


/////////// getters of common phrases
    public static String[] getPhrasesList() { return phrasesList; }

    public static String[] getPhrasesFromList() {
        String s = prefUser.getString("phrasesFrom","N/A");
        String[] sArr = s.split("&&");
        return sArr;
    }

    public static String[] getPhrasesToList() {
        String s = prefUser.getString("phrasesTo","N/A");
        String[] sArr = s.split("&&");
        return sArr;
    }

//    Getters for coin names
    public static String[] getCoinsList() {
        String s = prefUser.getString("coinNames", "N/A");
        String[] sArr = s.split("&&");
        return sArr;
    }

    public static double[] getCoinsFloats(String code) {

        if(code.equals("eur"))
            return new double[]{0.01,0.02,0.05,0.1,0.2,0.5,1.0,2.0};
        else if (code.equals("gbp"))
            return new double[]{0.01,0.02,0.05,0.1,0.2,0.5,1.0,2.0};
        else if (code.equals("php"))
            return new double[]{0.01,0.05,0.1,0.25,1.0,5.0,10.0};
        else if (code.equals("try"))
            return new double[]{0.01,0.05,0.1,0.25,0.5,1.0};
        else if(code.equals("usd"))
            return new double[]{0.01, 0.05, 0.1, 0.25, 0.5, 1.0};
        else
            return null;


    }

//    public double[] getEurFloats() { return new double[]{0.01,0.02,0.05,0.1,0.2,0.5,1.0,2.0}; }
//    public double[] getGbpFloats() { return new double[]{0.01,0.02,0.05,0.1,0.2,0.5,1.0,2.0}; }
//    public double[] getPhpFloats() { return new double[]{0.01,0.05,0.1,0.25,1.0,5.0,10.0}; }
//    public double[] getTryFloats() { return new double[]{0.01,0.05,0.1,0.25,0.5,1.0}; }
//    public double[] getUsdFloats() { return new double[]{0.01,0.05,0.1,0.25,0.5,1.0}; }

}
