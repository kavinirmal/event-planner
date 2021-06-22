package com.dreams.hakunamatata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dreams.hakunamatata.R;
import com.dreams.hakunamatata.dto.LessonDto;

import java.util.List;


public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.OrderViewHolder> {
    Context mContext;
    List<LessonDto> mData;
    private OrderViewHolder.RecyclerViewClickListener clicklistener;

    public LessonAdapter(Context mContext, List<LessonDto> mData, LessonAdapter.OrderViewHolder.RecyclerViewClickListener listener){
        this.mContext = mContext;
        this.mData = mData;
        this.clicklistener =listener;
    }

    @NonNull
    @Override
    public LessonAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.lesson_item,viewGroup,false);
        return new OrderViewHolder(layout,mContext,mData,clicklistener);    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.OrderViewHolder holder, int position) {

        holder.tv_lesson.setText(mData.get(position).getLesson());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_lesson;
        LessonAdapter.OrderViewHolder.RecyclerViewClickListener mrecyclerViewClickListener;
        public OrderViewHolder(@NonNull View itemView, Context mContext, List<LessonDto> mData, RecyclerViewClickListener clicklistener) {
            super(itemView);
            tv_lesson = itemView.findViewById(R.id.textView10);
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
