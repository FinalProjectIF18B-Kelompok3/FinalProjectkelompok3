package com.example.aplikasidapurresep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasidapurresep.Fragment.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class StartActivity extends AppCompatActivity {

    Button bt_start_cooking;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        bt_start_cooking = findViewById(R.id.bt_start_cooking);

        bt_start_cooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, IntroActivity.class));
            }
        });


        //Login dengan Google
        // cek user sudah pernah login atau belum
        final GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null){
            startActivity(new Intent(StartActivity.this, MainActivity.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //redirect if user is not null
        if (firebaseUser !=null){
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }
    }
}