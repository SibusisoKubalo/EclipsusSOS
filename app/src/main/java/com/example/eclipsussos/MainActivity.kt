package com.example.eclipsussos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btnNext = findViewById<Button>(R.id.Loginbtn)
        btnNext.setOnClickListener {
            val intent = Intent ( this,Login_Activity::class.java)
            startActivity(intent)
            finish()
        }


    }
}