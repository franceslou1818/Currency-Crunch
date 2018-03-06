package com.example.android.currencycrunch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by matthew on 06/03/2018.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyAdapterViewHolder>{


    private Context context;

    public CurrencyAdapter(Context c) {
        this.context = c;
    }

    @Override
    public CurrencyAdapter.CurrencyAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.currency_list;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        CurrencyAdapterViewHolder viewHolder = new CurrencyAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CurrencyAdapter.CurrencyAdapterViewHolder holder, int position) {

/*
        String phrase = Preferences.getPhrasesList()[position];
//        System.out.println("********phrases adapter: " + Arrays.toString(Preferences.getPhrasesFromList()));
        holder.textViewFromPhrase.setText(Preferences.getPhrasesFromList()[position]);
        holder.textViewToPhrase.setText(Preferences.getPhrasesToList()[position]);*/



        holder.coinPhrase.setText(GoogleTranslate.translate((Preferences.getCoinsList()[position]),"en",Preferences.getChosenToLanguageCode()));
        holder.sum.setText("0");
    }

    @Override
    public int getItemCount() {
        return Preferences.getCoinsList().length;
    }


//////////////////////////////////////////////////////////////////////////////////////////////
    public class CurrencyAdapterViewHolder extends RecyclerView.ViewHolder {


        TextView coinPhrase;
        TextView sum;

        public CurrencyAdapterViewHolder(View itemView) {
            super(itemView);
            coinPhrase = (TextView) itemView.findViewById(R.id.coinPhrase);
            sum = (TextView) itemView.findViewById(R.id.coinSum);

        }

    }
}
