package com.example.hakunamatata.mainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hakunamatata.R;
import com.example.hakunamatata.adapters.OfferAdapter;
import com.example.hakunamatata.adapters.OrderAdapter;
import com.example.hakunamatata.dto.OfferDto;
import com.example.hakunamatata.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    List<OrderDto> mData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
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
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.rv_order);
        mData = new ArrayList<>();
        mData.add(new OrderDto("Photography","2020/12/25"));
        mData.add(new OrderDto("Food order","2020/12/25"));
        mData.add(new OrderDto("Food order","2020/12/25"));
        mData.add(new OrderDto("Photography","2020/12/25"));
        mData.add(new OrderDto("Food order","2020/12/25"));
        mData.add(new OrderDto("Photography","2020/12/25"));
        mData.add(new OrderDto("Photography","2020/12/25"));
        mData.add(new OrderDto("Food order","2020/12/25"));
        mData.add(new OrderDto("Photography","2020/12/25"));

        orderAdapter = new OrderAdapter(getActivity(),mData);
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}