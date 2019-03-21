package com.dev2qa.parkedup2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MenuActivity extends FragmentActivity {
    static Boolean miles = true;
    static Boolean notify = true;

    private LocationManager locMng = new LocationManager();

    Button homeButton;
    Button mapButton;
    ToggleButton MIKI;
    ToggleButton Notify;

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MIKI = (ToggleButton) findViewById(R.id.MIKItoggle);

        MIKI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    miles = false;
                } else {
                    // The toggle is disabled
                }
            }
        });

        Notify = (ToggleButton) findViewById(R.id.notify);
        Notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notify = false;
                } else {
                    // The toggle is disabled
                }
            }
        });
        homeButton = (Button) findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, BeginActivity.class);
                startActivity(intent);
            }
        });
        mapButton = (Button) findViewById(R.id.mapsBack);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    locMng.getDistance();

                    Intent intent = new Intent(MenuActivity.this, ParkedActivity.class);
                    startActivity(intent);
                } catch(Exception ArrayIndexOutOfBoundsException) {
                }
            }
        });
    }
    public static boolean getMiles(){
        return miles;
    }
    public static boolean getNotify(){
        return notify;
    }
}
