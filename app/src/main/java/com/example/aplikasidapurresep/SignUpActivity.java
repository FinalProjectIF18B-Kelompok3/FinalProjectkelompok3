package com.example.aplikasidapurresep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    TextView txt_back, txt_log_in;

    //Log In dengan Google
    GoogleSignInClient mGoogleSignInClient;
    SignInButton bt_sign_in_google;
    private FirebaseAuth mAuth;
    private static final String TAG = "Error" ;
    private static final int RC_SIGN_IN = 10005;
    DatabaseReference reference;

    // Sign Up menggunakan Email
    EditText sign_up_email, sign_up_password, sign_up_username;
    Button bt_create_account;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txt_back = findViewById(R.id.txt_back);
        txt_log_in = findViewById(R.id.txt_log_in);

        // Sign Up Menggunakan Email
        sign_up_email = findViewById(R.id.sign_up_email);
        sign_up_password = findViewById(R.id.sign_up_password);
        sign_up_username = findViewById(R.id.sign_up_username);
        bt_create_account = findViewById(R.id.bt_create_account);

        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, IntroActivity.class));
            }
        });

        txt_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        //Log In dengan Google

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("13230024458-n7h3akdmfn5lblqenak7i4fhk6hdrrgf.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set the dimensions of the sign-in button.
        bt_sign_in_google = findViewById(R.id.bt_sign_in_google);
        bt_sign_in_google.setSize(SignInButton.SIZE_STANDARD);


        bt_sign_in_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Sign Up menggunakan email

        bt_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(SignUpActivity.this);
                pd.setMessage("Please Wait ...");
                pd.show();

                String email = sign_up_email.getText().toString();
                String password = sign_up_password.getText().toString();
                String username = sign_up_username.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(username)){
                    Toast.makeText(SignUpActivity.this, "All field are required", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6 ) {
                    Toast.makeText(SignUpActivity.this, "Password must have 6 character", Toast.LENGTH_SHORT).show();
                } else {
                    register(email, password, username);
                }
            }
        });

    }

    //Register dengan Email
    private void register(String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();


                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username.toLowerCase());
                            hashMap.put("image_url", "gs://aplikasi-dapur-resep.appspot.com/placeholder.png");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        pd.dismiss();
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }else {
                            pd.dismiss();
                            Toast.makeText(SignUpActivity.this, "You can't not register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    //Login dengan Google

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                e.printStackTrace();
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(getApplicationContext(), "Your Google Account is Connected to our app", Toast.LENGTH_SHORT).show();
                        // ...

                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                            String userid = firebaseUser.getUid();
                            String username = firebaseUser.getDisplayName();
                            String image_profile = firebaseUser.getPhotoUrl().toString();


                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username.toLowerCase());
                            hashMap.put("image_url", image_profile);


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SignUpActivity.this, "You can't not register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}