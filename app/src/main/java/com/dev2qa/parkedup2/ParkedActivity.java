package com.dev2qa.parkedup2;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParkedActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //latitude longitude of static parked position
    public double latitudeFirst, longitudeFirst;
    //latitude longitude of current updating position
    //public double latitudeCurrent, longitudeCurrent;

    private Location location;
    private Location locationFirst;
    private Location locationCurrent;

    private static final String TAG = "MyLog";

    private GoogleMap mMap;
    private SupportMapFragment mapFrag;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;

    private LocationManager locMng = new LocationManager();

    private static final int Request_User_Location_Code = 99;

    public static boolean forceExit = false;
    TextView text;
    Button button;
    Button button2;
    TextView parkedCoord;
    TextView currCoord;
    TextView distance;
    TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parked);

        // set strings with updated data
        parkedCoord = findViewById(R.id.parkedCoord);
        currCoord = findViewById(R.id.currCoord);
        distance = findViewById(R.id.distance);
        time = findViewById(R.id.timeToCar);

        parkedCoord.append(" Update");
        currCoord.append(" Update");
        distance.append(" Update");

        //Find your views
        button = (Button) findViewById(R.id.deleteButton);

        //Assign a listener to your button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choice) {
                        switch (choice) {
                            case DialogInterface.BUTTON_POSITIVE:
                                finish();
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
        button2 = (Button) findViewById(R.id.exit);

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

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
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
        locationRequest.setInterval(1500);//1000ms = 1sec
        locationRequest.setFastestInterval(1000);
        //locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //essential check before next lines of code are allowed
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

        // permissions ok, we get last location. new initial condition
        locationFirst = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        locMng.setParkCoord(locationFirst);
        parkedCoord.setText("GPS Coordinates: " + locMng.displayParkCoord());

        //split up latitude/longitude into variables before creating LatLng object
        latitudeFirst = locationFirst.getLatitude();
        longitudeFirst = locationFirst.getLongitude();

        //this makes sure only one marker is placed
        if(currentUserLocationMarker != null){
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(latitudeFirst, longitudeFirst);//instantiate lat/lng object

        //changes things about the marker
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Your Parking Spot");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        // actually adds the marker
        currentUserLocationMarker = mMap.addMarker(markerOptions);
    }

    //new implemented methods for establishing our current location
    @Override
    public void onLocationChanged(Location location) {//called when location is changed

        lastLocation = location;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        // Need to move this to onCreate later
        //moves camera to this location
        CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(latLng, 19);//2.0 to 21.0 -higher double = more zoom
        mMap.moveCamera(center);//centers camera right above before zooming
        //mMap.animateCamera(zoom);//animated zoom in
        mMap.moveCamera(zoom);//non-animated zoom in


        if (location != null){
            locMng.setCurrCoord(location);
            currCoord.setText("GPS Coordinates: " + locMng.displayCoord());
            distance.setText("Distance: " + locMng.getDistance());
            time.setText("Time to Car: " + locMng.timeToCar());
        }
        //this was stopping location updates
//        if(googleApiClient != null){
//            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
//        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {//called when connection is severed

    }
}