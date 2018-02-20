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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Spinner spinnerFromCountry;
    ArrayAdapter<CharSequence> adapterFromCountry;

    Spinner spinnerToCountry;
    ArrayAdapter<CharSequence> adapterToCountry;


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

        spinnerFromCountry = (Spinner) getView().findViewById(R.id.spinnerFromCountry);
        adapterFromCountry = ArrayAdapter.createFromResource(getActivity(), R.array.countries, android.R.layout.simple_spinner_item);
        adapterFromCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCountry.setAdapter(adapterFromCountry);

        spinnerToCountry = (Spinner) getView().findViewById(R.id.spinnerToCountry);
        adapterToCountry = ArrayAdapter.createFromResource(getActivity(), R.array.countries, android.R.layout.simple_spinner_item);
        adapterToCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerToCountry.setAdapter(adapterToCountry);


    }

}
