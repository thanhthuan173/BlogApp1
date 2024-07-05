package com.example.blogapp1.fragments;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blogapp1.R;
import com.example.blogapp1.adapter.HomeAdapter;
import com.example.blogapp1.model.HomeModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    HomeAdapter adapter;
    private FirebaseUser user;
    private List<HomeModel> list;

    DocumentReference reference;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        init(view);

        reference = FirebaseFirestore.getInstance().collection("Posts").document(user.getUid());

        list = new ArrayList<>();
        adapter = new HomeAdapter(list,getContext());
        recyclerView.setAdapter(adapter);

        loadDataFromFirestore();

    }

    private void init(View view){

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if(getActivity()!=null)
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


    }

    private void loadDataFromFirestore(){

        list.add(new HomeModel("Thuan","05/07/2024","","","123456",12));
        list.add(new HomeModel("Thuan","04/07/2024","","","321654",20));
        list.add(new HomeModel("Thuan","05/07/2024","","","452165",11));
        list.add(new HomeModel("Thuan","05/07/2024","","","888811",5));

        adapter.notifyDataSetChanged();
    }


}