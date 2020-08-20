package com.example.aplikasidapurresep.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;


import com.example.aplikasidapurresep.R;


public class ProfileFragment extends Fragment {
    Button menu;
    Button editprofil;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        menu = view.findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),menuactivity.class));
            }
        });

        editprofil = view.findViewById(R.id.editprofil);
        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),editprofil.class));
            }
        });
        return view;
    }
}
