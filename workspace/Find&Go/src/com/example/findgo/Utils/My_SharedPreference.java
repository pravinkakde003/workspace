package com.example.findgo.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class My_SharedPreference {
	Editor editor;
	SharedPreferences preferences;
	String latitude, longitude;

	public My_SharedPreference(Context context) {
		/*
		 * CREATE DEFAULT SHARED PREFERENCE INSTANCE TO STORE AND RETRIVE DATA
		 * IN SHARED PREFERENCCES
		 */
		this.preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		this.editor = preferences.edit();
	}

	public String getlatitude() {
		return this.preferences.getString("latitude", "latitude");
	}

	public void setlatitude(String latitude) {
		this.latitude = latitude;
		this.editor.putString("latitude", latitude);
		this.editor.commit();
	}

	public String getlongitude() {
		return this.preferences.getString("longitude", "longitude");
	}

	public void setlongitude(String longitude) {
		this.longitude = longitude;
		this.editor.putString("longitude", longitude);
		this.editor.commit();
	}

}
