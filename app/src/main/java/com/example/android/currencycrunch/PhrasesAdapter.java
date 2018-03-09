package com.example.android.currencycrunch;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Keep;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by franzz1818 on 03/03/2018.
 */

public class PhrasesAdapter extends RecyclerView.Adapter<PhrasesAdapter.PhrasesAdapterViewHolder>{


    private Context context;


    public PhrasesAdapter(Context c) {
        this.context = c;
    }


    @Override
    public PhrasesAdapter.PhrasesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.phrases_list;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        PhrasesAdapterViewHolder viewHolder = new PhrasesAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhrasesAdapter.PhrasesAdapterViewHolder holder, int position) {

        holder.textViewFromPhrase.setText(Preferences.getPhrasesFromList()[position]);
        holder.textViewToPhrase.setText(Preferences.getPhrasesToList()[position]);
    }

    @Override
    public int getItemCount() {
        return Preferences.getPhrasesList().length;
    }


//////////////////////////////////////////////////////////////////////////////////////////////
    public class PhrasesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewFromPhrase;
        TextView textViewToPhrase;

        public PhrasesAdapterViewHolder(View itemView) {
            super(itemView);
            textViewFromPhrase = (TextView) itemView.findViewById(R.id.textViewFromPhrase);
            textViewToPhrase = (TextView) itemView.findViewById(R.id.textViewToPhrase);
            itemView.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,Pop.class);
        intent.putExtra("from", textViewFromPhrase.getText().toString());
        intent.putExtra("to", textViewToPhrase.getText().toString());
        context.startActivity(intent);

    }

    }
}
