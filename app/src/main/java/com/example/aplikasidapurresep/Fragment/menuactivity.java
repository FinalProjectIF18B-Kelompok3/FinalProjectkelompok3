package com.example.aplikasidapurresep.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasidapurresep.R;

public class menuactivity extends AppCompatActivity {
    TextView setting,Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuactivity);
        setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(menuactivity.this, "tombol setting telah ditekan", Toast.LENGTH_SHORT).show();
            }
        });
        Logout = findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(menuactivity.this, "Logout berhasil ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}