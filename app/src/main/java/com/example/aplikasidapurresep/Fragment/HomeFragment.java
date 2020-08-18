package com.example.aplikasidapurresep.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasidapurresep.Adapter.FoodAdapter;
import com.example.aplikasidapurresep.Model.FoodData;
import com.example.aplikasidapurresep.PostDetail;
import com.example.aplikasidapurresep.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        myFoodList = new ArrayList<>();

        mFoodData = new FoodData("Ayam Tangkap","Ini Ayam Tangkap",R.drawable.imagetester);
        myFoodList.add(mFoodData);

        FoodAdapter foodAdapter = new FoodAdapter(getContext(),myFoodList);
        mRecyclerView.setAdapter(foodAdapter);

        return view;
    }
}
