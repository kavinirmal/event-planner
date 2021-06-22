package com.dreams.hakunamatata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dreams.hakunamatata.R;
import com.dreams.hakunamatata.dto.OfferDto;

import java.util.List;


public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    Context mContext;
    List<OfferDto> mData;
    private OfferViewHolder.RecyclerViewClickListener clicklistener;

    public OfferAdapter(Context mContext, List<OfferDto> mData,OfferViewHolder.RecyclerViewClickListener listener){
        this.mContext = mContext;
        this.mData = mData;
        this.clicklistener =listener;

    }

    @NonNull
    @Override
    public OfferAdapter.OfferViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.offer_item,viewGroup,false);
        return new OfferViewHolder(layout,mContext,mData,clicklistener);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.OfferViewHolder holder, int position) {

        holder.tv_offer.setText(mData.get(position).getOffer_name());
        holder.tv_description.setText(mData.get(position).getOffer_description());

        boolean permission = mData.get(position).isPermission();
        if (permission){
            holder.tv_permission.setText("*Permission Required.");
        }else {
            holder.tv_permission.setTextColor(android.graphics.Color.parseColor("#00ff00"));
            holder.tv_permission.setText("*Free");
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_offer,tv_description,tv_permission;
        RecyclerViewClickListener mrecyclerViewClickListener;
        public OfferViewHolder(@NonNull View itemView, Context mContext, List<OfferDto> mData, RecyclerViewClickListener onClickListener) {
            super(itemView);
            tv_offer = itemView.findViewById(R.id.tv_offer);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_permission = itemView.findViewById(R.id.tv_permission);
            this.mrecyclerViewClickListener = onClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mrecyclerViewClickListener.onClickListener(getAdapterPosition());

        }
        public interface RecyclerViewClickListener {
            void onClickListener(int position);
        }
    }
}
