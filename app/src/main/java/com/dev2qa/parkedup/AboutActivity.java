package com.dev2qa.parkedup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends FragmentActivity {
    Button backButton;
    double[] coords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Bundle extras = getIntent().getExtras();
        try {
            coords = (double[]) extras.get("Parked Coords");
        } catch(Exception NullPointerException){

        }

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, MenuActivity.class);
                intent.putExtra("Parked Coords",coords);
                startActivity(intent);
            }
        });
    }
}
