package com.example.android.currencycrunch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by matthew on 06/03/2018.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyAdapterViewHolder>{

    private ConvertFragment cf = new ConvertFragment();

    private Context context;

//    public CurrencyAdapter(Context c) {
//        this.context = c;
//    }
//customAdapter = new CustomAdapter(myContext, android.R.layout.simple_list_item_1, getList, HomeFragment.this);

    public CurrencyAdapter(Context c, ConvertFragment f) {
        this.context = c;
        this.cf = f;
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
    public void onBindViewHolder(final CurrencyAdapter.CurrencyAdapterViewHolder holder, int position) {


        String currCode = Preferences.getChosenToCurrencyCode();
        StringBuilder imageName = new StringBuilder();
        imageName.append(currCode + "_" + String.valueOf(position));

        int imageId = context.getResources().getIdentifier(imageName.toString(), "drawable", context.getPackageName());

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), imageId);

        String[] coinNames = Preferences.getCoinsList();

        holder.coinPhrase.setText(coinNames[position]);
        holder.coinImage.setImageBitmap(icon);



        ImageButton plusBtn = (ImageButton) holder.itemView.findViewById(R.id.plusIcon);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView coinSum = (TextView) holder.itemView.findViewById(R.id.coinSum);
                int currentText = Integer.parseInt(coinSum.getText().toString());
                int newC = currentText+1;
                coinSum.setText(Integer.toString(newC));

//                System.out.println("**********plus" + holder.getAdapterPosition());
                cf.setTotalTo(holder.getAdapterPosition(), newC);

            }
        });

        ImageButton minusBtn = (ImageButton) holder.itemView.findViewById(R.id.minusIcon);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView coinSum = (TextView) holder.itemView.findViewById(R.id.coinSum);
                int currentText = Integer.parseInt(coinSum.getText().toString());

                if (currentText != 0) {
                    int newC = currentText-1;
                    coinSum.setText(Integer.toString(newC));
                    cf.setTotalTo(holder.getAdapterPosition(), newC);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return Preferences.getCoinsList().length;
    }



//////////////////////////////////////////////////////////////////////////////////////////////
    public class CurrencyAdapterViewHolder extends RecyclerView.ViewHolder {


        TextView coinPhrase;
        TextView coinSum;
        ImageView coinImage;

        public CurrencyAdapterViewHolder(View itemView) {
            super(itemView);
            coinPhrase = (TextView) itemView.findViewById(R.id.coinPhrase);
            coinSum = (TextView) itemView.findViewById(R.id.coinSum);
            coinImage = (ImageView) itemView.findViewById(R.id.coinImage);



        }

    }



}
