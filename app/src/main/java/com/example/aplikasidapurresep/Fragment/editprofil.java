package com.example.aplikasidapurresep.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.aplikasidapurresep.R;

public class editprofil extends AppCompatActivity {
    TextView nama,situsweb,biodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);
        nama = findViewById(R.id.nama);
        situsweb = findViewById(R.id.situsweb);
        biodata = findViewById(R.id.biodata);

    }
}