//https://www.googleapis.com/language/translate/v2?key=AIzaSyDymF-_GTUelaiSJYO1ET6PZZrDbX9QqP8&q=How+are+you&target=tl&source=en

package com.example.android.currencycrunch;

import android.content.Context;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;


public class GoogleTranslate {

    private String key;
    private Context context;


//    public GoogleTranslate(String apiKey) {
//        key = apiKey;
//    }
    public GoogleTranslate(Context c) {
        context = c;
        key = context.getResources().getString(R.string.apiKey);
    }


    public String translate(String text, String from, String to) {
        StringBuilder result = new StringBuilder();
        try {
            String encodedText = URLEncoder.encode(text, "UTF-8");
            String urlStr = "https://www.googleapis.com/language/translate/v2?key=" + key +
                                "&q=" + encodedText +
                                "&target=" + to + "&source=" + from;

            URL url = new URL(urlStr);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            InputStream stream;

            if (conn.getResponseCode() == 200) { //success
                stream = conn.getInputStream();
            } else
                stream = conn.getErrorStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JsonParser parser = new JsonParser();

            JsonElement element = parser.parse(result.toString());

            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.get("error") == null) {
                    String translatedText = obj.get("data").getAsJsonObject().
                                                get("translations").getAsJsonArray().
                                                get(0).getAsJsonObject().
                                                get("translatedText").getAsString();
                    return translatedText;

                }
            }

            if (conn.getResponseCode() != 200) {
                System.err.println(result);
            }

        } catch (IOException | JsonSyntaxException ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

//    public static void main(String[] args) {
//
//        GoogleTranslate translator = new GoogleTranslate("AIzaSyDymF-_GTUelaiSJYO1ET6PZZrDbX9QqP8");
//
//        System.out.println("Hello World!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//
//    }

}