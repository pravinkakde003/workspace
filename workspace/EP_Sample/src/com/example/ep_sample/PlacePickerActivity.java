package com.example.ep_sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlacePickerActivity extends FragmentActivity implements OnMapReadyCallback {
	private SupportMapFragment mMapView;
    private GoogleMap mMap;

   
 
    Button btn_autosearch;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	      
        setContentView(R.layout.activity_place);
        
        mMapView =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mMapView.getMapAsync(this);
        mMapView.onCreate(savedInstanceState);
       
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
				getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

				autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
				    @Override
				    public void onPlaceSelected(Place place) {
				        // TODO: Get info about the selected place.
				        Log.i("place", "Place: " + place.getName()+place.getLatLng());
				      
	                    mMap.addMarker(new MarkerOptions()
	                            .position(place.getLatLng())
	                            .title(""+place.getName()));
	                  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15));
				    }

				    @Override
				    public void onError(Status status) {
				        // TODO: Handle the error.
				        Log.i("status", "An error occurred: " + status);
				    }
				  });

    }
   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	getMenuInflater().inflate(R.menu.main, menu);
		return true;
    }
    
    @Override
    protected void onResume() {
            super.onResume();
            if (mMapView!=null)
            mMapView.onResume();

            }

    @Override
    protected void onPause() {
            if (mMapView!=null)
            mMapView.onPause();
            super.onPause();
            }
    @Override
    protected void onDestroy() {
            super.onDestroy();


            }
    @Override
    public void onLowMemory() {
            super.onLowMemory();
            if (mMapView!=null)
            mMapView.onLowMemory();
            }

            @Override
            public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;

                    // We will provide our own zoom controls.
                    mMap.getUiSettings().setZoomControlsEnabled(false);
                   double lat = Double.valueOf(19.090176500000002);
                    double longg = Double.valueOf(72.8687391);
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, longg))
                            .title("Chhatrapati Shivaji International Airport,Mumbai"));
                  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,longg), 15));

            }
            
            
            
            
    }

