package com.example.findgo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMap_Activity extends Activity implements OnClickListener {

	private GoogleMap map;
	double latitude, longitude;
	LatLng DAVAO;
	private String placeName;
	Button btn_normal, btn_satellite, btn_hybrid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_map);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		btn_normal = (Button) findViewById(R.id.btn_normal);
		btn_satellite = (Button) findViewById(R.id.btn_satellite);
		btn_hybrid = (Button) findViewById(R.id.btn_hybrid);
		btn_normal.setOnClickListener(this);
		btn_satellite.setOnClickListener(this);
		btn_hybrid.setOnClickListener(this);

		Intent intent = getIntent();
		if (intent != null) {
			placeName = intent.getStringExtra("PLACE_NAME");
			latitude = intent.getDoubleExtra("LATITUDE", 0.0);
			longitude = intent.getDoubleExtra("LONGITUDE", 0.0);
			DAVAO = new LatLng(latitude, longitude);
		} else {
			Log.e("NULL", "NULLLLLLLLLLLL");
			latitude = 40.7127;
			longitude = 74.0059;
		}

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.pmap))
				.getMap();

		Marker davao = map.addMarker(new MarkerOptions().position(DAVAO).title(
				placeName));

		// zoom in the camera to Davao city
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(DAVAO, 15));

		// animate the zoom process
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 1500, null);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; goto parent activity.
			this.finish();
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_right);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_normal:
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.btn_satellite:
			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.btn_hybrid:
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;

		default:
			break;
		}
	}
}
