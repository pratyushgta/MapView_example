/**
 * This class contains methods for handling map and markers
 * MAD-E9
 *
 * @author Pratyush Kumar (github.com/pratyushgta)
 */

package com.example.mad_e8_mapview;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_maps, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;
                // Pin Point current location
                LatLng currentLocation = new LatLng(-15.413, 49.538);
                MarkerOptions currentMarkerOptions = new MarkerOptions();
                currentMarkerOptions.position(currentLocation);
                currentMarkerOptions.title("Current Location");
                currentMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                googleMap.addMarker(currentMarkerOptions);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10)); // Animating to zoom the marker

                // Handle clicks on mapView
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions clickedMarkerOptions = new MarkerOptions();  // Initialize marker options
                        clickedMarkerOptions.position(latLng);  // Set position of marker
                        clickedMarkerOptions.title("Clicked on: " + latLng.latitude + " : " + latLng.longitude); // Set title of marker
                        googleMap.clear(); // Remove all marker
                        googleMap.addMarker(clickedMarkerOptions); // Add marker on map
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10)); // Animating to zoom the marker
                        googleMap.addMarker(currentMarkerOptions);
                    }
                });
            }
        });

        return root;
    }
}