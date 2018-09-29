package com.example.heshan.googlemapapi;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.akexorcist.googledirection.GoogleDirection;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView txt;
    GoogleDirection gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        txt=findViewById(R.id.txtPlace);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        PlaceAutocompleteFragment autocompleteFragment2 = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                txt.setText(place.getLatLng().toString());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getAddress().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 15.0f ) );
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

            }
        });

        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                txt.setText(place.getLatLng().toString());

                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getAddress().toString()));
                refreshMapPosition(place.getLatLng(),10);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

            }
        });
        gd = new GoogleDirection();

//        gd.setOnDirectionResponseListener(new GoogleDirection.OnDirectionResponseListener() {
//            public void onResponse(String status, Document doc, GoogleDirection gd) {
//                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
//
//                gd.animateDirection(mMap, gd.getDirection(doc), GoogleDirection.SPEED_FAST
//                        , true, true, true, false, null, false, true, new PolylineOptions().width(8).color(Color.RED));
//
//                mMap.addMarker(new MarkerOptions().position(start)
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markera)));
//
//                mMap.addMarker(new MarkerOptions().position(end)
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.markerb)));
//
//                String TotalDistance = gd.getTotalDistanceText(doc);
//                String TotalDuration = gd.getTotalDurationText(doc);
//            }
//        });
//
//        gd.request(start, end, GoogleDirection.MODE_DRIVING);
    }
    private void refreshMapPosition(LatLng pos, float angle) {
        CameraPosition.Builder positionBuilder = new CameraPosition.Builder();
        positionBuilder.target(pos);
        positionBuilder.zoom(15f);
        positionBuilder.bearing(angle);
        positionBuilder.tilt(60);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(positionBuilder.build()));
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

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

}
