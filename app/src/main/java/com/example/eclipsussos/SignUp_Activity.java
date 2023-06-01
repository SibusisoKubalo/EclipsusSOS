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

public class SignUp_Activity extends AppCompatActivity {

    //Authentication
    EditText editEmail, editpassword;

    Button SignUp;
    Button Login1;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Authentication
        editEmail = findViewById(R.id.email);
        editpassword = findViewById(R.id.inputPassword2);
        SignUp = findViewById(R.id.SignUpbtn2);



        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp_Activity.this,Login_Activity.class));
            }
        });



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email, Password;
                Email = String.valueOf(editEmail.getText());
                Password = String.valueOf(editpassword.getText());

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(SignUp_Activity.this, "please input your Email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(SignUp_Activity.this, "please input your password", Toast.LENGTH_SHORT).show();
                }



                firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(SignUp_Activity.this, "Sign up Successsfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUp_Activity.this, Emergency_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else {
                                    Toast.makeText(SignUp_Activity.this, "Sign up Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
};

