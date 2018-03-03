package com.example.android.currencycrunch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by franzz1818 on 03/03/2018.
 */

public class PhrasesAdapter extends RecyclerView.Adapter<PhrasesAdapter.PhrasesAdapterViewHolder>{

    private List<String> phrasesList;
    private Context context;

    public PhrasesAdapter(Context c) {
        this.context = c;
        String[] phrasesArray = context.getResources().getStringArray(R.array.phrases);
        phrasesList = Arrays.asList(phrasesArray);
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

        String phrase = phrasesList.get(position);

//        GoogleTranslate translator = new GoogleTranslate(getActivity());

        holder.textViewFromPhrase.setText(phrase);

        holder.textViewToPhrase.setText(phrase);
    }

    @Override
    public int getItemCount() {
        return phrasesList.size();
    }


//////////////////////////////////////////////////////////////////////////////////////////////
    public class PhrasesAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFromPhrase;
        TextView textViewToPhrase;

        public PhrasesAdapterViewHolder(View itemView) {
            super(itemView);
            textViewFromPhrase = (TextView) itemView.findViewById(R.id.textViewFromPhrase);
            textViewToPhrase = (TextView) itemView.findViewById(R.id.textViewToPhrase);;

        }

    }
}
