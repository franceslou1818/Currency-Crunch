package com.example.android.currencycrunch;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class PhrasesFragment extends Fragment {//implements PhrasesAdapter.PhrasesAdapterOnClickHandler {

//    HomeFragment homeFragment = new HomeFragment();
    private GoogleTranslate translator;
    private EditText translateedittext;
    private TextView translatabletext;

    private PhrasesAdapter phrasesAdapter;
    private RecyclerView phrasesRecyclerView;



    public PhrasesFragment() {// Required empty public constructor
//        translator = new GoogleTranslate(getActivity());
    }

//    @Override
//    public void onClick() {
//        Intent i = new Intent(getActivity(), Pop.class);
//        startActivity(i);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phrases, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        translateedittext = (EditText) getView().findViewById(R.id.translateedittext);
        translateedittext.setHint(Preferences.getChosenFromLanguage() + " - " + Preferences.getChosenToLanguage());
        Button translatebutton = (Button) getView().findViewById(R.id.translatebutton);

        translatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (translateedittext.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "Please specify text to process", Toast.LENGTH_LONG).show();
                }
                else {
                    new Translating().execute();
                }
            }
        });

        //for the common phrases list list
        phrasesRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview_phrases);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        phrasesRecyclerView.setLayoutManager(layoutManager);
        phrasesRecyclerView.setHasFixedSize(true);
        phrasesAdapter = new PhrasesAdapter(getActivity());
        phrasesRecyclerView.setAdapter(phrasesAdapter);
    }

    public void translated(){ // translate button

        String toTranslate = translateedittext.getText().toString();//get the value of text
        String translated = translator.translate(toTranslate, Preferences.getChosenFromLanguageCode(), Preferences.getChosenToLanguageCode());

        translatabletext = (TextView) getView().findViewById(R.id.translatabletext);
        translatabletext.setText(translated);

    }
////////////////////////////////////////////////////////////////////////////////////////////
    private class Translating extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                translator = new GoogleTranslate();

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
