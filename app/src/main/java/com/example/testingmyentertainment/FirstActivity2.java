package com.example.testingmyentertainment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstActivity2 extends AppCompatActivity {

    Button login;
    Button reg;
    Button logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login = findViewById(R.id.loginbutton);
        reg = findViewById(R.id.regbutton);
        logout = findViewById(R.id.logoutid);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Log.d("ffirst", "logged in");
            String email = currentUser.getEmail();
            Log.d("ffirst", email);
        }




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity2.this, LoginActivity.class);
                startActivity(intent);
//                if(currentUser != null){
//                    Log.d("ffirst", "logged in click login");
//                    String email = currentUser.getEmail();
//                    Log.d("ffirst", email);
//                }
            }
        });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity2.this, RegistrationActivity.class);
//                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                if(currentUser != null){
                    Log.d("ffirst", "logged in click logout");
                    String email = currentUser.getEmail();
                    Log.d("ffirst", email);
                }
                else{
                    Log.d("ffirst", "logged out");
                }
            }
        });
    }
}