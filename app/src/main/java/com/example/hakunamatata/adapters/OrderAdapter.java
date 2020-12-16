package com.example.hakunamatata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hakunamatata.R;
import com.example.hakunamatata.dto.OfferDto;
import com.example.hakunamatata.dto.OrderDto;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context mContext;
    List<OrderDto> mData;

    public OrderAdapter(Context mContext, List<OrderDto> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.order_item,viewGroup,false);
        return new OrderViewHolder(layout);    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

        holder.tv_order.setText(mData.get(position).getOrder_name());
        holder.tv_date.setText(mData.get(position).getDate());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tv_order,tv_date;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_order = itemView.findViewById(R.id.tv_order);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
