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
    public void onBindViewHolder(final CurrencyAdapter.CurrencyAdapterViewHolder holder, int position) {


        String currCode = Preferences.getChosenToCurrencyCode();
        StringBuilder imageName = new StringBuilder();
        imageName.append(currCode + "_" + String.valueOf(position));

        int imageId = context.getResources().getIdentifier(imageName.toString(), "drawable", context.getPackageName());

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), imageId);

        holder.coinPhrase.setText(GoogleTranslate.translate((Preferences.getCoinsList()[position]),"en",Preferences.getChosenToLanguageCode()));
        holder.coinImage.setImageBitmap(icon);

        ImageButton plusBtn = (ImageButton) holder.itemView.findViewById(R.id.plusIcon);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView coinSum = (TextView) holder.itemView.findViewById(R.id.coinSum);
                int currentText = Integer.parseInt(coinSum.getText().toString());
                coinSum.setText(Integer.toString(currentText+1));
//                Toast.makeText(context, "plus clicked!" + holder.coinPhrase.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton minusBtn = (ImageButton) holder.itemView.findViewById(R.id.minusIcon);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView coinSum = (TextView) holder.itemView.findViewById(R.id.coinSum);
                int currentText = Integer.parseInt(coinSum.getText().toString());
                coinSum.setText(Integer.toString(currentText-1));
//                Toast.makeText(context, "plus clicked!" + holder.coinPhrase.getText().toString(), Toast.LENGTH_SHORT).show();
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
