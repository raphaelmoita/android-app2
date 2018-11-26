package com.momo.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");

        // Capture the layout's TextView and set the string as its text
        TextView nameTextView = findViewById(R.id.name);
        nameTextView.setText(name);

        TextView emailTextView = findViewById(R.id.email);
        emailTextView.setText(email);
    }
}
