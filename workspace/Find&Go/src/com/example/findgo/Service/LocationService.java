package com.example.findgo.Service;

import java.text.DateFormat;
import java.util.Date;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.example.findgo.Utils.My_SharedPreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationService extends IntentService implements
		ConnectionCallbacks, OnConnectionFailedListener, LocationListener {
	protected static final String TAG = "location-updates-sample";
	/**
	 * The desired interval for location updates. Inexact. Updates may be more
	 * or less frequent.
	 */
	public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 300000;

	/**
	 * The fastest rate for active location updates. Exact. Updates will never
	 * be more frequent than this value.
	 */
	public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
	/**
	 * Provides the entry point to Google Play services.
	 */
	protected GoogleApiClient mGoogleApiClient;

	/**
	 * Stores parameters for requests to the FusedLocationProviderApi.
	 */
	protected LocationRequest mLocationRequest;

	/**
	 * Represents a geographical location.
	 */
	protected Location mCurrentLocation;

	// Labels.
	protected double mLatitude;
	protected double mLongitude;
	protected String mLastUpdateTimeLabel;

	/**
	 * Time when the location was updated represented as a String.
	 */
	protected String mLastUpdateTime;
	private My_SharedPreference preference;

	public LocationService() {
		super(LocationService.class.getName());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "In Oncreate()");
		preference = new My_SharedPreference(getApplicationContext());
		// TODO Auto-generated method stub
		super.onCreate();
		// Kick off the process of building a GoogleApiClient and requesting the
		// LocationServices
		// API.

		buildGoogleApiClient();

	}

	/**
	 * Builds a GoogleApiClient. Uses the {@code #addApi} method to request the
	 * LocationServices API.
	 */
	protected synchronized void buildGoogleApiClient() {
		Log.i(TAG, "In buildGoogleApiClient()");
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
		createLocationRequest();
	}

	/**
	 * Sets up the location request. Android has two location request settings:
	 * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These
	 * settings control the accuracy of the current location. This sample uses
	 * ACCESS_FINE_LOCATION, as defined in the AndroidManifest.xml.
	 * <p/>
	 * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast
	 * update interval (5 seconds), the Fused Location Provider API returns
	 * location updates that are accurate to within a few feet.
	 * <p/>
	 * These settings are appropriate for mapping applications that show
	 * real-time location updates.
	 */
	protected void createLocationRequest() {
		Log.i(TAG, "In createLocationRequest()");
		mLocationRequest = new LocationRequest();

		// Sets the desired interval for active location updates. This interval
		// is
		// inexact. You may not receive updates at all if no location sources
		// are available, or
		// you may receive them slower than requested. You may also receive
		// updates faster than
		// requested if other applications are requesting location at a faster
		// interval.
		mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

		// Sets the fastest rate for active location updates. This interval is
		// exact, and your
		// application will never receive updates faster than this value.
		mLocationRequest
				.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "In onHandleIntent()");
		mGoogleApiClient.connect();

	}

	/**
	 * Callback that fires when the location changes.
	 */
	@Override
	public void onLocationChanged(Location location) {
		Log.i(TAG, "In onLocationChanged()");
		mCurrentLocation = location;
		mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
		mLatitude = mCurrentLocation.getLatitude();
		mLongitude = mCurrentLocation.getLongitude();
		Log.e("Lat::", "" + mLatitude);
		Log.e("Long::", "" + mLongitude);
		preference.setlatitude("" + mLatitude);
		preference.setlongitude("" + mLongitude);
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// Refer to the javadoc for ConnectionResult to see what error codes
		// might be returned in
		// onConnectionFailed.
		Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
				+ result.getErrorCode());
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Log.i(TAG, "In onConnected()");
		startLocationUpdates();
	}

	/**
	 * Requests location updates from the FusedLocationApi.
	 */
	protected void startLocationUpdates() {
		Log.i(TAG, "In startLocationUpdates()");
		// The final argument to {@code requestLocationUpdates()} is a
		// LocationListener
		// (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
		LocationServices.FusedLocationApi.requestLocationUpdates(
				mGoogleApiClient, mLocationRequest, this);

	}

	/**
	 * Removes location updates from the FusedLocationApi.
	 */
	protected void stopLocationUpdates() {
		// It is a good practice to remove location requests when the activity
		// is in a paused or
		// stopped state. Doing so helps battery performance and is especially
		// recommended in applications that request frequent location updates.

		// The final argument to {@code requestLocationUpdates()} is a
		// LocationListener
		// (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
		LocationServices.FusedLocationApi.removeLocationUpdates(
				mGoogleApiClient, this);
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// The connection to Google Play services was lost for some reason. We
		// call connect() to
		// attempt to re-establish the connection.
		Log.i(TAG, "Connection suspended");
		mGoogleApiClient.connect();
	}

}
