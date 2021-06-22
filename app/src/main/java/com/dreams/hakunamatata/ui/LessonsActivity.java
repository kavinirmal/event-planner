package com.dreams.hakunamatata.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.dreams.hakunamatata.R;
import com.dreams.hakunamatata.adapters.OrderAdapter;
import com.dreams.hakunamatata.dto.OrderDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LessonsActivity extends AppCompatActivity implements OrderAdapter.OrderViewHolder.RecyclerViewClickListener {
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    List<OrderDto> mData;
    FirebaseFirestore db;
    String subject_id,lesson_name,description,lessonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView =findViewById(R.id.rv_order);
        subject_id = getIntent().getExtras().getString("SubjectId","defaultKey");
        db = FirebaseFirestore.getInstance();
        LoadData(subject_id);

    }
    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(this, PdfListActivity.class);
        String name = mData.get(position).getOrder_name();
        String id = mData.get(position).getLesson_id();
        intent.putExtra("LessonName",name);
        intent.putExtra("LessonId",id);
        startActivity(intent);
    }

    private void LoadData(String subject_id) {
        db.collection("lessons").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    mData = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        String subject_code = documentSnapshot.getData().get("subjectCode").toString();
                        if (subject_code.equals(subject_id)){
                            lesson_name = documentSnapshot.getData().get("LessonName").toString();
                            description = documentSnapshot.getData().get("description").toString();
                            String order = documentSnapshot.getData().get("Order").toString();
                            lessonId = documentSnapshot.getId();
                            mData.add(new OrderDto(lesson_name,description,lessonId,order));
                        }
                    }
                    SortData(mData);
                    callAdapter(mData);
                }
            }
        });
    }

    private void SortData(List<OrderDto> mData) {
        Collections.sort(mData, new Comparator<OrderDto>() {
            @Override
            public int compare(OrderDto orderDto, OrderDto t1) {
                return orderDto.getOrder().compareTo(t1.getOrder());

            }
        });
    }

    private void callAdapter(List<OrderDto> mData) {
        orderAdapter = new OrderAdapter(this,mData,this::onClickListener);
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}