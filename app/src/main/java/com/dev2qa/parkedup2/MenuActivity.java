package com.dev2qa.parkedup2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MenuActivity extends FragmentActivity {
    static Boolean notify = true;
    static Boolean Miles = true;

    static double[] coords;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
//        if(MIKI.equals(null)){
//            boolean MiKi = preferences.getBoolean("MiKi", true);  //default is true
//        }
//
//        MIKI.setChecked(MiKi);

        MIKI = (ToggleButton) findViewById(R.id.MIKItoggle);
//        MIKI.setOnClickListener(new ToggleButton.OnClickListener() {
//
//            public void onClick(View v) {
//                SharedPreferences sharedPreferences = PreferenceManager
//                        .getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("toggleButton", MIKI.isChecked());
//                editor.commit();
//            }
//        });

        MIKI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((MIKI.isChecked())) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("Miles", true); // value to store
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("Kilometers", false); // value to store
                    editor.commit();
                }
                LocationManager lm = new LocationManager();
                lm.toggleUnits(Miles);
            }
        });

//        MIKI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    miles = false;
//                } else {
//                    // The toggle is disabled
//                }
//            }
//        });

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
                if(savedInstanceState == null) {
                    Bundle extras = getIntent().getExtras();
                    if (extras == null) {
                        coords = null;
                    } else {
                        coords = (double[]) extras.get("Parked Coords");
                    }
                } else {
                    coords = (double[]) savedInstanceState.getSerializable("Parked Coords");
                }
                // redirects user back to previous screen depending onn if a parked location is stored
                if (coords == null) {
                    Intent intent = new Intent(MenuActivity.this, BeginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent2 = new Intent(MenuActivity.this, ParkedActivity.class);
                    startActivity(intent2);
                }
            }
        });
        Button aboutButton = (Button) findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
    public static boolean getNotify(){
        return notify;
    }

}
