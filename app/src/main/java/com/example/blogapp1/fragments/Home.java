package com.example.blogapp1.fragments;

import android.annotation.SuppressLint;
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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blogapp1.R;
import com.example.blogapp1.adapter.HomeAdapter;
import com.example.blogapp1.model.HomeModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    HomeAdapter adapter;
    private FirebaseUser user;
    private List<HomeModel> list;



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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    init(view);

                    list = new ArrayList<>();
                    adapter = new HomeAdapter(list, getContext());
                    recyclerView.setAdapter(adapter);

                    loadDataFromFirestore();
                }
            });
        }
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

    @SuppressLint("NotifyDataSetChanged")
    private void loadDataFromFirestore() {

        CollectionReference reference = FirebaseFirestore.getInstance().collection("Users")
                .document(user.getUid())
                .collection("Post Images");

        /*reference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Error: ", error.getMessage());
                    return;
                }

                /*if (value == null) return;

                list.clear(); // Xóa danh sách để tránh trùng lặp
                for (QueryDocumentSnapshot snapshot : value) {
                    HomeModel model = snapshot.toObject(HomeModel.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();*/
               /* assert  value != null;
                for (QueryDocumentSnapshot snapshot : value) {
                    list.add(new HomeModel(snapshot.get("userName").toString(),
                                    snapshot.get("timstamp").toString(),
                                    snapshot.get("profileImage").toString(),
                                    snapshot.get("postImage").toString(),
                                    snapshot.get("uid").toString(),
                                    snapshot.get("comments").toString(),
                                    snapshot.get("description").toString(),
                                    snapshot.get("id").toString(),
                                    Integer.parseInt(snapshot.get("likeCount").toString())
                            ));
                    adapter.notifyDataSetChanged();
                }
                
            }
        });*/
        reference.addSnapshotListener((value, error) -> {
            if (error != null ) {
                Log.e("Error: ", error.getMessage());
                return;
            }
            if(value == null)
                return;

            for (QueryDocumentSnapshot snapshot : value) {
                if (!snapshot.exists())
                    return;;
                HomeModel model = snapshot.toObject(HomeModel.class);
                list.add(new HomeModel(
                        model.getUserName(),
                        model.getProfileImage(),
                        model.getImageUrl(),
                        model.getUid(),
                        model.getComments(),
                        model.getDescription(),
                        model.getId(),
                        model.getTimestamp(),
                        model.getLikeCount()

                        ));

            }
            adapter.notifyDataSetChanged();
        });
    }
}