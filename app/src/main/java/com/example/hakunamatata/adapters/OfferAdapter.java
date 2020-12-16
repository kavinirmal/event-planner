package com.example.hakunamatata.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hakunamatata.R;
import com.example.hakunamatata.dto.OfferDto;

import java.util.List;


public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    Context mContext;
    List<OfferDto> mData;

    public OfferAdapter(Context mContext, List<OfferDto> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public OfferAdapter.OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.offer_item,viewGroup,false);
        return new OfferViewHolder(layout);    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.OfferViewHolder holder, int position) {

        holder.tv_offer.setText(mData.get(position).getOffer_name());
        holder.tv_description.setText(mData.get(position).getOffer_description());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView tv_offer,tv_description;
        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_offer = itemView.findViewById(R.id.tv_offer);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
}
