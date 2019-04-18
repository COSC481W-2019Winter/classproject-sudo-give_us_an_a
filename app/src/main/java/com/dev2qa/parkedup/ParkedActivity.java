package com.dev2qa.parkedup;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.List;


public class ParkedActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //latitude longitude of static parked position
    public double latitudeFirst, longitudeFirst;
    private boolean storedInstanceState = false;

    private Location locationFirst;

    private static final String TAG = "MyLog";

    private GoogleMap mMap;
    private SupportMapFragment mapFrag;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Marker currentUserLocationMarker;
    private boolean locationChanged = false;

    private LocationManager locMng = new LocationManager();
    private NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1");
    private SensorManager mSensorManager = null;

    public static final String CHANNEL_ID = "name";

    private static final int Request_User_Location_Code = 99;
    private static final int paddingZoom = 70; // offset from edges of the map in pixels. value may need to be altered
    private static final int overview = 0;
    private static final float ATM = SensorManager.PRESSURE_STANDARD_ATMOSPHERE;
    private static float P = 0;

    //string names of shared preferences
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LATITUDE_FLOAT = "latitudeFloat";
    public static final String LONGITUDE_FLOAT = "longitudeFloat";

    private float floatLat;
    private float floatLong;


    private Boolean freshStartFlag;


    Button button;
    Button button2;
    Button menuButton;
    TextView parkedCoord;
    TextView currCoord;
    TextView distance;
    TextView time;


    public void saveData(boolean saveData) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //if (freshStartFlag == true) {
            if (saveData == true) {
                //save preference values to these variables
                editor.putFloat(LATITUDE_FLOAT, (float) latitudeFirst);
                editor.putFloat(LONGITUDE_FLOAT, (float) longitudeFirst);
                editor.apply();

                Log.i(TAG, " ");
                Log.i(TAG, "saveData() write = save");
                Log.i(TAG, "LATITUDE_FLOAT: " + (float) latitudeFirst);
                Log.i(TAG, "LONGITUDE_FLOAT: " + (float) longitudeFirst);

                //Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
            } else {

                editor.clear();
                editor.apply();

                Log.i(TAG, " ");
                Log.i(TAG, "saveData() write = delete");
                Log.i(TAG, "LATITUDE_FLOAT: " + (float) latitudeFirst);
                Log.i(TAG, "LONGITUDE_FLOAT: " + (float) longitudeFirst);
                //Toast.makeText(this, "Data Deleted A", Toast.LENGTH_SHORT).show();
            }
//        } else {
//            editor.clear();
//            editor.apply();
//            //Toast.makeText(this, "Data Deleted B", Toast.LENGTH_SHORT).show();
//        }
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        floatLat = sharedPreferences.getFloat(LATITUDE_FLOAT, 0.0F);
        floatLong = sharedPreferences.getFloat(LONGITUDE_FLOAT, 0.0F);

        Log.i(TAG, "loadData()");
        Log.i(TAG, "floatLat: " + floatLat);
        Log.i(TAG, "floatLong: " + floatLong);
    }

    @Override
    public void finish() {
        super.finish();
        Log.i(TAG, "finish()");
        //stopService();//deleting this will allow you to keep the app running in background, even after exiting
        saveData(false);
        //perhaps save values to SharedPreferences or SQLlite database here
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parked);
        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Intent intentAborted = getIntent();
        freshStartFlag = intentAborted.getBooleanExtra("FRESH_START", false);

        Log.i(TAG, "");
        Log.i(TAG, "FRESH_START freshStartFlag: " + freshStartFlag);
        Log.i(TAG, "");


        //Log.i(TAG, "NEW public static void appAborted(boolean aborted) = " + isAborted);

        Log.i(TAG, "");
        Log.i(TAG, "onCreate() before loadData()");
        Log.i(TAG, "floatLat: " + floatLat);
        Log.i(TAG, "floatLong: " + floatLong);
        loadData();
        Log.i(TAG, "onCreate() after loadData()");
        Log.i(TAG, "floatLat: " + floatLat);
        Log.i(TAG, "floatLong: " + floatLong);
        Log.i(TAG, "");


        //createNotificationChannel();
        // set strings with updated data
        parkedCoord = findViewById(R.id.parkedCoord);
        currCoord = findViewById(R.id.currCoord);
        distance = findViewById(R.id.distance);
        time = findViewById(R.id.timeToCar);

        parkedCoord.append(" \t");
        currCoord.append(" \t");
        distance.append(" \t");

            Intent intent = new Intent(this, ParkedActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        builder.setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource( getResources(), R.mipmap.ic_launcher_foreground))
                    .setContentTitle("ParkedUp!")
                    .setContentText("Your parking spot has been saved!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true);

            final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            Log.i(TAG, "Nofity is  "+ MenuActivity.getNotify());
            if(MenuActivity.getNotify()) {
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(1, builder.build());
            }
        //Find your views
        button = findViewById(R.id.deleteButton);

        //Assign a listener to your button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = new Intent(ParkedActivity.this, BeginActivity.class);
                                startActivity(intent);
                                notificationManager.cancelAll();
                                //delete button
                                //clear saved parking position
                                saveData(false);

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ParkedActivity.this);
                builder.setMessage("Are you sure you want to delete? (This will be permanent)")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        //Find your views
        button2 = findViewById(R.id.exit);

        //Assign a listener to your button
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pop up for complete exit out off program
                DialogInterface.OnClickListener dialogClickListener2 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:

                                //exit button
                                //clear saved parked position
                                saveData(false);

                                finish();
                                finish();
                                Intent intent = new Intent(ParkedActivity.this, BeginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("EXIT", true);
                                startActivity(intent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ParkedActivity.this);
                builder2.setMessage("Are you sure you want to exit? (This will be delete your parked location)")
                        .setPositiveButton("Yes", dialogClickListener2)
                        .setNegativeButton("No", dialogClickListener2).show();
            }
        });

        menuButton = findViewById(R.id.menubutton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParkedActivity.this, MenuActivity.class);

                double[] coord = locMng.getCoordinates();
                intent.putExtra("Parked Coords",coord);

                startActivity(intent);
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*
        * Manipulates the map once available.
        * This callback is triggered when the map is ready to be used.
        * This is where we can add markers or lines, add listeners or move the camera. In this case,
        * we just add a marker near Sydney, Australia.
        * If Google Play services is not installed on the device, the user will be prompted to install
        * it inside the SupportMapFragment. This method will only be triggered once the user has
        * installed Google Play services and returned to the app.
        */
        mMap = googleMap;
        //Change type of map to hybrid ie satellite and roads
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //necessary add permissions check
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        } else {//new else statement. take out if buggy
            checkUserLocationPermission();
        }
    }

    //check if permission is granted or not
    public boolean checkUserLocationPermission(){
        //if the permission is not granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        }else{
            return true;
        }
    }

    //handle permission request response
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {//if permission is granted
            case Request_User_Location_Code:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if(googleApiClient == null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {//if permission is not granted
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    // we build google api client
    protected synchronized void buildGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();//connect the google API client
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {//called when device is connected
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);//1000ms = 1sec
        locationRequest.setFastestInterval(1000);//seems to be minimum for older devices to load map smoothly
        //locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //essential check before next lines of code are allowed
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

        // permissions ok, we get last location. new initial condition
        locationFirst = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        locMng.setParkCoord(locationFirst);
        parkedCoord.setText("\t\t\t " + locMng.displayParkCoord());




        //new code
        if(floatLat == 0.0F){
            //split up latitude/longitude into variables before creating LatLng object
            latitudeFirst = locationFirst.getLatitude();
            longitudeFirst = locationFirst.getLongitude();
            Log.i(TAG, "StoredPreferences == false");
            //should is send latitudeFirst & longitudeFirst as parameters to saveData()?
            saveData(true);
        } else {
            Log.i(TAG, "StoredPreferences == true");
            latitudeFirst = floatLat;
            longitudeFirst = floatLong;
            Log.i(TAG, "................latitudeFirst: " + latitudeFirst);
            Log.i(TAG, "................longitudeFirst: " + longitudeFirst);
        }



        //this makes sure only one marker is placed
        if(currentUserLocationMarker != null){
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(latitudeFirst, longitudeFirst);//instantiate lat/lng object

        //Elevation

        //ElevationResult result = getElevation(new com.google.maps.model.LatLng(latitudeFirst, longitudeFirst));
        //Log.i(TAG,"Google ElevationAPI: " + result.elevation);

        //changes things about the marker
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Your Parking Spot");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        // actually adds the marker
        currentUserLocationMarker = mMap.addMarker(markerOptions);
    }

    //new implemented methods for establishing our current location
    @Override
    public void onLocationChanged(Location location) {//called when location is changed

        locMng.setSpeed(location);

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //do this only the first time for initial location
        if(!locationChanged){
            CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
            CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(latLng, 19);//2.0 to 21.0 -higher double = more zoom
            mMap.moveCamera(center);//centers camera right above before zooming
            //mMap.animateCamera(zoom);//animated zoom in
            mMap.moveCamera(zoom);//non-animated zoom in
            locationChanged = true;
        }

        if (location != null) {
            locMng.setCurrCoord(location);
            float elevation = P;
            Log.i(TAG,"Elevation" + elevation);
            currCoord.setText("\t\t\t " + locMng.displayCoord());

            //Directions
            double[] parkingCoord = locMng.getParkingCoord();
            com.google.maps.model.LatLng origin = new com.google.maps.model.LatLng(parkingCoord[0], parkingCoord[1]);
            com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(location.getLatitude(), location.getLongitude());

            DirectionsResult results;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                results = getDirectionsDetails(origin, destination);
            else
                results = null;
            if ((results != null) && (results.routes.length > 0)) {
                addPolyline(results, latLng);
                distance.setText("Distance: " + locMng.getDistance(getDistanceFromResults(results)));
                time.setText("Time to Car: " + getTimeFromResults(results));
            } else {
                updateCamera(latLng);
                distance.setText("Distance: " + locMng.getDistance());
                time.setText("Time to Car: " + locMng.timeToCar());
            }
        }
    }
    private void updateCamera(LatLng latLng) {
        //moves camera to bounds of marker and current position
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        builder.include(currentUserLocationMarker.getPosition());//original marker position
        builder.include(latLng);//current location
        LatLngBounds bounds = builder.build();//set the bounds

        CameraUpdate updateCam = CameraUpdateFactory.newLatLngBounds(bounds, paddingZoom);
        //mMap.moveCamera(updateCam);//maybe better on battery life?
        mMap.animateCamera(updateCam);
    }

    private DirectionsResult getDirectionsDetails(com.google.maps.model.LatLng orig, com.google.maps.model.LatLng dest) {
        try {
            return DirectionsApi.newRequest(getGeoContext())
                    .mode(TravelMode.WALKING)
                    .origin(orig)
                    .destination(dest)
                    .departureTimeNow()
                    .await();
        } catch (ApiException e) {
            Log.i(TAG,"Direction ApiException" + e.toString());
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            Log.i(TAG,"Direction InterruptedException" + e.toString());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            Log.i(TAG,"Direction IOException" + e.toString());
            e.printStackTrace();
            return null;
        }
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext.Builder geoApiContext = new GeoApiContext.Builder();
        return geoApiContext.apiKey(getString(R.string.directionsApiKey)).build();
    }

    private void addPolyline(DirectionsResult results, LatLng currentPosition) {
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[overview].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().width(30).color(Color.BLUE).addAll(decodedPath));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(currentUserLocationMarker.getPosition());
        builder.include(currentPosition);
        for (LatLng latLng : decodedPath)
            builder.include(latLng);
        LatLngBounds bounds = builder.build();

        CameraUpdate updateCam = CameraUpdateFactory.newLatLngBounds(bounds, paddingZoom);
        //mMap.moveCamera(updateCam);//maybe better on battery life?
        mMap.animateCamera(updateCam);
    }

    private String getTimeFromResults(DirectionsResult results){
        return results.routes[overview].legs[overview].duration.humanReadable;
    }

    private long getDistanceFromResults(DirectionsResult results) {
        return results.routes[overview].legs[overview].distance.inMeters;
    }




//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            CharSequence name = getString(R.string.common_google_play_services_notification_channel_name);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
////            NotificationChannel channel = new NotificationChannel("1", name, importance);
//            NotificationChannel channel = new NotificationChannel("1", CHANNEL_ID, importance);
//            channel.setDescription("1");
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {//called when connection is severedExample Service Channel

    }

//    public void startService() {
//        Intent serviceIntent = new Intent(this, ForegroundService.class);
//        ContextCompat.startForegroundService(this, serviceIntent);
//        startService(serviceIntent);
//    }
//
//    public void stopService() {
//        Intent serviceIntent = new Intent(this, ForegroundService.class);
//        stopService(serviceIntent);
//    }

    @Override
    protected void onDestroy() {
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
        //saveData(false);
    }


//    Test code to determine which part of the activity lifecycle your in
//
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
        //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
   }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_NORMAL);
        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
        //Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        Log.i(TAG, "onPause()");
        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.i(TAG, "onStop()");
        //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
//    @Override
//    protected void onDestroy() {
//        Log.i(TAG, "onDestroy()");
//        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
//        super.onDestroy();
//    }

    private SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float pressure_value = 0.0f;
            float height = 0.0f;

            if ( Sensor.TYPE_PRESSURE == event.sensor.getType()){
                pressure_value = event.values[0];
                P = SensorManager.getAltitude(ATM, pressure_value);
                if(P == 0) {
                    locMng.setParkElevation(P);
                }else{
                    locMng.setElevation(P);
                }
                Log.i(TAG,"Elevation: " + P);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

}