package com.example.pr5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComixCardAdapterR extends RecyclerView.Adapter<ComixCardAdapterR.ViewHolder>
{
    interface OnCardClickListener
    {
        void onCardClick(ComixCard card, int position);
    }
    private final LayoutInflater inflater;
    private final List<ComixCard> cards;
    private final OnCardClickListener onCardClickListener;
    public ComixCardAdapterR(Context context, List<ComixCard> cards, OnCardClickListener onCardClickListener)
    {
        this.inflater = LayoutInflater.from(context);
        this.cards = cards;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.comix_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        ComixCard card = cards.get(position);
        holder.desc.setText(card.getDesc());
        holder.name.setText(card.getName());
        holder.logo.setImageResource(card.getLogo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onCardClickListener.onCardClick(card, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final ImageView logo;
        final TextView name, desc;
        public ViewHolder(View view)
        {
            super(view);
            logo = view.findViewById(R.id.ComixCardLogo);
            name = view.findViewById(R.id.ComicCardName);
            desc = view.findViewById(R.id.ComicCardDesc);
        }
    }
}
