package com.example.eclipsussos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {
    private lateinit var etPassword: EditText
    private lateinit var btnReset: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)



        etPassword = findViewById(R.id.rest_email)
        btnReset = findViewById(R.id.btnReset)

        auth = FirebaseAuth.getInstance()

        btnReset.setOnClickListener{
            val sPassword = etPassword.text.toString()
                auth.sendPasswordResetEmail(sPassword)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Please Check Your Email",Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                    }
        }
    }
}