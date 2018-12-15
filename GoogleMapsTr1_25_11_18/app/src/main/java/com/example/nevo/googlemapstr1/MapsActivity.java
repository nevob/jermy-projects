package com.example.nevo.googlemapstr1;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private static final LatLng ORT = new LatLng(31.883277,34.8111137);
    private static final LatLng MODIIN = new LatLng(31.9091192,35.0112168);

    private static Marker mModiin;
    private static Marker mOrt;

    private TextView tvDis;
    private Button btnCal, btnShow;
    private EditText edLoc;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        tvDis = findViewById(R.id.tvDis);
        btnCal = findViewById(R.id.btnCal);
        btnShow = findViewById(R.id.btnShow);
        edLoc = findViewById(R.id.edLoc);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float[] res = new float[1];
                double lat1 = mModiin.getPosition().latitude;
                double lng1 = mModiin.getPosition().longitude;
                double lat2 = mOrt.getPosition().latitude;
                double lng2 = mOrt.getPosition().longitude;
                Location.distanceBetween(lat1,lng1,lat2,lng2,res);
                tvDis.setText(res[0]+"");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = edLoc.getText().toString();

                Geocoder gc = new Geocoder(MapsActivity.this);
                if(gc.isPresent()){
                    List<Address> list = null;
                    try {
                        list = gc.getFromLocationName(addr, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(list.size() != 0) {
                        Address address = list.get(0);
                        double lat = address.getLatitude();
                        double lng = address.getLongitude();

                        mOrt.setPosition(new LatLng(lat, lng));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mOrt.getPosition(), 12));
                    }
                    else
                        Toast.makeText(MapsActivity.this, "not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.6
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerDragListener(this);
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        MarkerOptions markerOptions = new MarkerOptions();

        mModiin = mMap.addMarker(markerOptions.position(MODIIN)
                .draggable(true)
                .title("Marker in Modiin"));

        mOrt = mMap.addMarker(markerOptions.position(ORT)
                .draggable(true)
                .title("Marker in Ort"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mModiin.getPosition(), 12));
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        /*mMap.clear();
        if(marker.getTitle().equals(mModiin.getTitle())) {
            mMap.addMarker(mModiin.position(marker.getPosition()));
            mMap.addMarker(mOrt);
        }
        else {
            mMap.addMarker(mModiin);
            mMap.addMarker(mOrt.position(marker.getPosition()));
        }*/
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 12));
    }
}
