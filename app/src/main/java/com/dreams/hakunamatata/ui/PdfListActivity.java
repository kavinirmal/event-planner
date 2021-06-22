package com.dreams.hakunamatata.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.dreams.hakunamatata.R;
import com.dreams.hakunamatata.adapters.LessonAdapter;
import com.dreams.hakunamatata.adapters.OrderAdapter;
import com.dreams.hakunamatata.dto.LessonDto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PdfListActivity extends AppCompatActivity implements OrderAdapter.OrderViewHolder.RecyclerViewClickListener {
    RecyclerView recyclerView;
    LessonAdapter lessonAdapter;
    List<LessonDto> mData;
    TextView tv_topic;
    String subject_name,lesson_id;
    FirebaseFirestore db;
    Snackbar snackbar;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.rv_lesson);
        tv_topic = findViewById(R.id.textView4);
        constraintLayout = findViewById(R.id.const_layout);
        db = FirebaseFirestore.getInstance();
        subject_name = getIntent().getExtras().getString("LessonName","defaultKey");
        lesson_id = getIntent().getExtras().getString("LessonId","defaultKey");
        tv_topic.setText(subject_name);
        LoadData(lesson_id);

    }

    private void LoadData(String lesson_id) {
        db.collection("pdfList").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    mData = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        String lessonId = documentSnapshot.getData().get("LessonId").toString();
                        if (lessonId.equals(lesson_id)){
                            String topic = documentSnapshot.getData().get("Topic").toString();
                            String pUrl = documentSnapshot.getData().get("PdfLink").toString();
                            String aUrl = documentSnapshot.getData().get("AudioLink").toString();
                            String order = documentSnapshot.getData().get("Order").toString();
                            mData.add(new LessonDto(topic,pUrl,aUrl,order));
                        }
                    }
                    SortData(mData);
                    callAdapter(mData);
                }
            }
        });
    }

    private void SortData(List<LessonDto> mData) {
        Collections.sort(mData, new Comparator<LessonDto>() {
            @Override
            public int compare(LessonDto lessonDto, LessonDto t1) {
                return lessonDto.getOrder().compareTo(t1.getOrder());
            }
        });
    }

    private void callAdapter(List<LessonDto> mData) {
        lessonAdapter = new LessonAdapter(this,mData,this::onClickListener);
        recyclerView.setAdapter(lessonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClickListener(int position) {
        snackbar = Snackbar.make(constraintLayout,"Files Loading...",Snackbar.LENGTH_LONG)
                .setAction("Dissmiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.setActionTextColor(Color.CYAN);
        snackbar.show();

        Intent intent = new Intent(PdfListActivity.this,PdfViewActivity.class);
        String aLink = mData.get(position).getAudio_link();
        String pLink = mData.get(position).getPdf_link();
        intent.putExtra("pdfLink",pLink);
        intent.putExtra("audioLink",aLink);
        startActivity(intent);

    }
}