package com.example.android.currencycrunch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created by franzz1818 on 08/03/2018.
 */

public class Pop extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

//        getWindow().setLayout((int)(width*.8),(int)(height*.6)).setBackgroundDrawable(new BitmapDrawable());
//        getWindow().setLayout((int)(width*.8),(int)(height*.6)).setOutsideTouchable(true);

        Intent in = getIntent();
        String inFrom = in.getStringExtra("from");
        String inTo = in.getStringExtra("to");



        TextView popTextViewFrom = (TextView) findViewById(R.id.textViewPopFrom);
        TextView popTextViewTo = (TextView) findViewById(R.id.textViewPopTo);

//        popTextViewFrom.setText(localeToEmoji( Locale.TAIWAN ));
        popTextViewFrom.setText(inFrom);
        popTextViewTo.setText(inTo);

    }

    private String localeToEmoji(Locale locale) {
        String countryCode = locale.getCountry();
        int firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6;
        int secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }
}
