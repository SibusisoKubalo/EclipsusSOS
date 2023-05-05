package com.example.eclipsussos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Sosbtn extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        TextView btn=findViewById(R.id.SOS_btn);
    }

    public void click (View v ) {
        Intent i = new Intent(Intent.ACTION_CALL);

        i.setData(Uri.parse("tel:0683036433"));

        startActivity(i);
    }
}
