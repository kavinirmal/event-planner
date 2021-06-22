package com.dreams.hakunamatata.mainFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dreams.hakunamatata.R;
import com.dreams.hakunamatata.adapters.OfferAdapter;
import com.dreams.hakunamatata.dto.OfferDto;
import com.dreams.hakunamatata.ui.LessonsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
//        intent.putExtra("price","7000");
//                intent.putExtra("facilities","Birthday party, wedding , home comming and any ocassion.");
//                intent.putExtra("description","Birthday party, wedding , home comming and any ocassion.");
//                intent.putExtra("from","update");
public class HomeFragment extends Fragment implements OfferAdapter.OfferViewHolder.RecyclerViewClickListener {

    RecyclerView recyclerView;
    OfferAdapter offerAdapter;
    ArrayList<OfferDto> mData;
    private String m_Text = "";
    FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_offer);
        db = FirebaseFirestore.getInstance();
//        getData();
        LoadData();
        return view;
    }


    @Override
    public void onClickListener(int position) {
        boolean permission = mData.get(position).isPermission();
        String code = mData.get(position).getCode();
        String id = mData.get(position).getOffer_id();
        if (permission){
            DialogBox(code,id);

        }else {
            Intent intent = new Intent(getContext(), LessonsActivity.class);
            intent.putExtra("SubjectId",id);
            startActivity(intent);
        }
    }

    private void DialogBox(String s, String code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Permission");
        builder.setMessage("Please contact your teacher to get code.");
        final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (s.equals(m_Text)){
                    Intent intent = new Intent(getContext(), LessonsActivity.class);
                    intent.putExtra("SubjectId",code);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"Code is Incorrect!",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    private void LoadData() {
        db.collection("subject").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    mData = new ArrayList<>();
                    for (QueryDocumentSnapshot document: task.getResult()){
                        String offer_name = document.getData().get("topic").toString();
                        String offer_description = document.getData().get("description").toString();
                        boolean offer_permission =(boolean) document.getData().get("permission");
                        String offer_id = document.getId();
                        String offer_code = document.getData().get("code").toString();
                        mData.add(new OfferDto(offer_name,offer_description,offer_id, offer_permission,offer_code));
                    }
                    offerAdapter = new OfferAdapter(getActivity(),mData, HomeFragment.this::onClickListener);
                    recyclerView.setAdapter(offerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });
    }
}