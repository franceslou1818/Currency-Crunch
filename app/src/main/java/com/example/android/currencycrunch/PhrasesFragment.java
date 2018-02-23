package com.example.android.currencycrunch;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class PhrasesFragment extends Fragment {

    HomeFragment homeFragment = new HomeFragment();
    GoogleTranslate translator;
    EditText translateedittext;
    TextView translatabletext;


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phrases, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        translateedittext = (EditText) getView().findViewById(R.id.translateedittext);
        translateedittext.setHint(homeFragment.getChosenFromLanguage() + " - " + homeFragment.getChosenToLanguage());
        Button translatebutton = (Button) getView().findViewById(R.id.translatebutton);

        translatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Translating().execute();

            }
        });

//        System.out.println("************in phrases: " + homeFragment.getChosenFromLanguageCode());

    }

    public void translated(){

        String toTranslate = translateedittext.getText().toString();//get the value of text
//        String translated = translator.translate(toTranslate, "en", "tl");
        String translated = translator.translate(toTranslate, homeFragment.getChosenFromLanguageCode(), homeFragment.getChosenToLanguageCode());
        translatabletext = (TextView) getView().findViewById(R.id.translatabletext);
        translatabletext.setText(translated);

    }
////////////////////////////////////////////////////////////////////////////////////////////
    private class Translating extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                translator = new GoogleTranslate(getString(R.string.apiKey));
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
            translated();
        }


    }

}
