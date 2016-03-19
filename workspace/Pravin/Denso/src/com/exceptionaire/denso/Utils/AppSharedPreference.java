package com.exceptionaire.denso.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class AppSharedPreference {
	Editor editor;
	SharedPreferences preferences;
	String user_id, isLoggedIn, user_email_ID;

	public AppSharedPreference(Context context) {
		/*
		 * CREATE DEFAULT SHARED PREFERENCE INSTANCE TO STORE AND RETRIVE DATA
		 * IN SHARED PREFERENCCES
		 */
		this.preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		this.editor = preferences.edit();
	}

	public String getuser_id() {
		return this.preferences.getString("user_id", "user_id");
	}

	public void setuser_id(String user_id) {
		this.user_id = user_id;
		this.editor.putString("user_id", user_id);
		this.editor.commit();
	}

	public String getUser_email_ID() {
		return this.preferences.getString("user_email_ID", null);
	}

	public void setUser_email_ID(String user_email_ID) {
		this.user_email_ID = user_email_ID;
		this.editor.putString("email_ID", user_email_ID);
		this.editor.commit();
	}

	public String getIsLoggedIn() {
		return this.preferences.getString("isLoggedIn", "0");
	}

	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
		this.editor.putString("isLoggedIn", isLoggedIn);
		this.editor.commit();
	}

}
