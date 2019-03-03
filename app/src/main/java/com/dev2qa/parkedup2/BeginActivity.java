package com.dev2qa.parkedup2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;

//public class BeginActivity extends AppCompatActivity implements
public class BeginActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //variables of current latitude/longitude
    double latitude, longitude;

    private GoogleMap mMap;
    private SupportMapFragment mapFrag;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;

    private FusedLocationProviderClient mFusedLocationProviderClient;//not used yet

    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        //Find your views
        button = (Button) findViewById(R.id.parkButton);

        //Assign a listener to your button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeginActivity.this, ParkedActivity.class);
                startActivity(intent);
            }
        });
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkUserLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        //Toast.makeText(this, "On Create()", Toast.LENGTH_SHORT).show();

        //new FusedLocationProviderClient not being used yet
        //mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
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
        //Toast.makeText(this, "On Map Ready()", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        //Change type of map to hybrid ie satalite and roads
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //necessary add permissions check
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //Location Permission already granted
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        } else {//new else statement. take out if buggy
            checkUserLocationPermission();
        }
    }

    //check if permission is granted or not
    public boolean checkUserLocationPermission(){
        //Toast.makeText(this, "Check User Location Permission()", Toast.LENGTH_SHORT).show();
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
        //Toast.makeText(this, "On Request PermissionsResult()", Toast.LENGTH_SHORT).show();
        switch (requestCode)
        {//if permission is granted
            case Request_User_Location_Code:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if(googleApiClient == null){
                            buildGoogleApiClient();
                        }
                        //less important I think. comment when debugging possibly
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else{//if permission is not granted
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
    // we build google api client
    protected synchronized void buildGoogleApiClient(){
        //Toast.makeText(this, "synchonized Build Google Api Client()", Toast.LENGTH_SHORT).show();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();//connect the google API client
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {//called when device is connected
        //Toast.makeText(this, "On Connected()", Toast.LENGTH_SHORT).show();
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1500);//1000ms = 1sec
        locationRequest.setFastestInterval(1000);
        //locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    //new implemented methods for establishing our current location
    @Override
    public void onLocationChanged(Location location) {//called when location is changed
        //Toast.makeText(this, "On Location Changed()", Toast.LENGTH_SHORT).show();
        lastLocation = location;
        if(currentUserLocationMarker != null){
            currentUserLocationMarker.remove();
        }
        //split up latitude/longitude into variables before creating LatLng object
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        //Toast.makeText(this, "latitude: " + latitude + "\nlongitude: " + longitude, Toast.LENGTH_LONG).show();

        LatLng latLng = new LatLng(latitude, longitude);//instantiate lat/lng object

        // fun target/camera/zoom/tilt methods that work
        // Construct a CameraPosition focusing on latLng and animate the camera to that position.
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(latLng)      // Sets the center of the map to latLng
//                .zoom(20)                   // Sets the zoom
//                .bearing(0)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //abruptly moves camera to this location and animates zoom in
        CameraUpdate center = CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.newLatLngZoom(latLng, 20);//2.0 to 21.0 -higher double = more zoom
        mMap.moveCamera(center);//centers camera right above before zooming
        mMap.animateCamera(zoom);//animated zoom in

        //start the location
        if(googleApiClient != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {//called when connection is severed

    }
}