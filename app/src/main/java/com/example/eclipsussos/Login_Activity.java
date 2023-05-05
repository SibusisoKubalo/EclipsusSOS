package com.example.eclipsussos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    //Authentication
    EditText editEmail, editpassword;

    Button Login;
    Button SignUp;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //Authentication
        editEmail = findViewById(R.id.Email);
        editpassword = findViewById(R.id.Password);
        Login = findViewById(R.id.Loginbtn2);
        SignUp = findViewById(R.id.SignUpbtn);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email, Password;
                Email = String.valueOf(editEmail.getText());
                Password = String.valueOf(editpassword.getText());

                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(Login_Activity.this, "please input your Email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(Password)){
                    Toast.makeText(Login_Activity.this, "please input your password", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(Email , Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login_Activity.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(         Login_Activity .this,Emergency_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Login_Activity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}