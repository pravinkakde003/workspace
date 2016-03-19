package com.example.findgo.Utils;

import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class Getcoordinatebyname {
	public LatLng getLocationFromAddress(Context context, String strAddress) {

		Geocoder coder = new Geocoder(context);
		List<Address> address;
		LatLng p1 = null;

		try {
			address = coder.getFromLocationName(strAddress, 5);
			if (address == null || address.isEmpty()) {
				Log.e("HERE","HERE");
				return null;
			}
			Address location = address.get(0);
			location.getLatitude();
			location.getLongitude();
			Log.e("LAT",""+location.getLatitude());
			Log.e("LANG",""+location.getLongitude());

			p1 = new LatLng(location.getLatitude(), location.getLongitude());

		} catch (Exception ex) {
			p1 = null;
			ex.printStackTrace();

		}

		return p1;
	}

}