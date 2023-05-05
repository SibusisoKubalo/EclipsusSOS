package com.example.eclipsussos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

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