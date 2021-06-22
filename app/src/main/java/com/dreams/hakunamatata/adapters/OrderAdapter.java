package com.dreams.hakunamatata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dreams.hakunamatata.R;
import com.dreams.hakunamatata.dto.OrderDto;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context mContext;
    List<OrderDto> mData;
    private OrderViewHolder.RecyclerViewClickListener clicklistener;

    public OrderAdapter(Context mContext, List<OrderDto> mData, OrderAdapter.OrderViewHolder.RecyclerViewClickListener listener){
        this.mContext = mContext;
        this.mData = mData;
        this.clicklistener =listener;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.order_item,viewGroup,false);
        return new OrderViewHolder(layout,mContext,mData,clicklistener);    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

        holder.tv_order.setText(mData.get(position).getOrder_name());
        holder.tv_date.setText(mData.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_order,tv_date;
        OrderAdapter.OrderViewHolder.RecyclerViewClickListener mrecyclerViewClickListener;
        public OrderViewHolder(@NonNull View itemView, Context mContext, List<OrderDto> mData, RecyclerViewClickListener clicklistener) {
            super(itemView);
            tv_order = itemView.findViewById(R.id.tv_order);
            tv_date = itemView.findViewById(R.id.tv_date);
            this.mrecyclerViewClickListener = clicklistener;
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
