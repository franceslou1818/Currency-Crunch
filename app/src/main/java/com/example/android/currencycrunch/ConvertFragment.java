package com.example.android.currencycrunch;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConvertFragment extends Fragment {

    HomeFragment homeFragment = new HomeFragment();

    FixerConvert converter;
    EditText convertedittext;
    TextView convertabletext;


    public ConvertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_convert, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        convertedittext = (EditText) getView().findViewById(R.id.convertedittext);
        convertedittext.setHint(homeFragment.getChosenFromCurrency()+" - "+homeFragment.getChosenToCurrency());
        Button convertbutton = (Button) getView().findViewById(R.id.convertbutton);

        convertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Converting().execute();
            }
        });

    }

    public void converted(){
        String valueToConvert = convertedittext.getText().toString(); //get the value of text
        Double valueConverted = converter.convert(homeFragment.getChosenFromCurrencyCode(), homeFragment.getChosenToCurrencyCode(), Double.parseDouble(valueToConvert));
        convertabletext = (TextView) getView().findViewById(R.id.convertabletext);
        convertabletext.setText(valueConverted.toString());
    }
////////////////////////////////////////////////////////////////////////////////////////////////
    private class Converting extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                converter = new FixerConvert();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPreExecute() {
            //start the progress dialog
            progress = ProgressDialog.show(getActivity(), null, "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();

            super.onPostExecute(result);
            converted();
        }


    }


}
