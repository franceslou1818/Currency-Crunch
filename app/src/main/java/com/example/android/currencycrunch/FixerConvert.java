//https://api.fixer.io/latest
//https://api.fixer.io/latest?base=USD
//https://api.fixer.io/latest?base=USD&symbols=GBP



package com.example.android.currencycrunch;


import android.os.StrictMode;

import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class FixerConvert {


    private static String convertApiUrl = "https://api.fixer.io/latest";

        public  FixerConvert() {

    }


    public double convert(String fromCurr, String toCurr, double fromCurrAmount) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String fromCurrUpper = "";
        fromCurrUpper = fromCurr.toUpperCase();
        String toCurrUpper = "";
        toCurrUpper = toCurr.toUpperCase();

            double toCurrAmount =0.0; // to return

        try {
            String urlBaseSpecified = convertApiUrl+"?base="+fromCurrUpper+"&symbols="+toCurrUpper;
            HttpURLConnection conn = (HttpURLConnection) (new URL(urlBaseSpecified)).openConnection();
            InputStream stream;
            if (conn.getResponseCode() == 200) { //success
                stream = conn.getInputStream();
            } else {
                stream = conn.getErrorStream();
            }

            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter(",|\"rates\":|\\{|\\}");

            while (scanner.hasNext()) {
                String line = scanner.next();
                String[] lineArr = line.split(":");

                if (lineArr.length != 2)
                    continue;

                String key = lineArr[0].substring(1,lineArr[0].length()-1);

                if (key.equals("date"))
                    continue;
                if (key.equals("base"))
                    continue;
                if (key.equals(toCurrUpper))
                    toCurrAmount = fromCurrAmount * Double.parseDouble(lineArr[1]);

                return toCurrAmount;

            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }


        return 0.0;
    }



}
