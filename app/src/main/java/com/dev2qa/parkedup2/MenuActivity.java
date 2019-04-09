package com.dev2qa.parkedup2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MenuActivity extends FragmentActivity {
    static Boolean notify = true;
    static Boolean Miles = true;

    static double[] coords;
    private LocationManager locMng = new LocationManager();
    private static final String TAG = "MyLog";

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
        boolean MiKi = preferences.getBoolean("MiKi", true);  //default is true



        //https://stackoverflow.com/questions/43166650/save-state-of-togglebutton-android
        MIKI = findViewById(R.id.MIKItoggle);
        MIKI.setChecked(readState());
        MIKI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Miles = isChecked;
                saveState(isChecked);
            }
        });

        Notify = findViewById(R.id.notify);
        Notify.setChecked(readState());
        Notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notify = isChecked;
                saveState(isChecked);
            }
        });

        homeButton = findViewById(R.id.home);
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
                Log.i(TAG, "MENU coords are "+ coords);
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
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                Bundle extras = getIntent().getExtras();
                try {
                    coords = (double[]) extras.get("Parked Coords");
                    intent.putExtra("Parked Coords", coords);
                    Log.i(TAG, "MENU onclick coords are "+ coords);
                } catch(Exception NullPointerException){

                }
                startActivity(intent);
            }
        });
    }
    public static boolean getNotify(){
        return notify;
    }
    public static boolean getMiles(){
        return Miles;
    }
    private void saveState(boolean isFavourite) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sharedPreferences.edit().putBoolean("State", isFavourite).apply();
    }
    private boolean readState() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getBoolean("State", true);
    }

}
