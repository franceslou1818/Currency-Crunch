package com.example.android.currencycrunch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        String currCode = Preferences.getChosenToCurrencyCode();
        StringBuilder imageName = new StringBuilder();
        //System.out.println(position);
        imageName.append(currCode + "_" + String.valueOf(position));
        //imageName.append(".png");
        //System.out.println(imageName.toString());

        int imageId = context.getResources().getIdentifier(imageName.toString(), "drawable", context.getPackageName());
        //Drawable coinDrawable = context.getResources().getDrawable(imageId);

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                imageId);

        holder.coinPhrase.setText(GoogleTranslate.translate((Preferences.getCoinsList()[position]),"en",Preferences.getChosenToLanguageCode()));
        holder.sum.setText("0");
        holder.coinImage.setImageBitmap(icon);
    }

    @Override
    public int getItemCount() {
        return Preferences.getCoinsList().length;
    }


//////////////////////////////////////////////////////////////////////////////////////////////
    public class CurrencyAdapterViewHolder extends RecyclerView.ViewHolder {


        TextView coinPhrase;
        TextView sum;
        ImageView coinImage;

        public CurrencyAdapterViewHolder(View itemView) {
            super(itemView);
            coinPhrase = (TextView) itemView.findViewById(R.id.coinPhrase);
            sum = (TextView) itemView.findViewById(R.id.coinSum);
            coinImage = (ImageView) itemView.findViewById(R.id.coinImage);

        }

    }
}
