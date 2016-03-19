package com.example.findgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.findgo.Utils.AppUtils;

public class Dashboard_Activity extends Activity implements OnClickListener {
	Button btnFindAddress, btnManualSearch;
	Intent i;
	boolean doubleBackToExitPressedOnce = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		init_view();
	}

	private void init_view() {
		// TODO Auto-generated method stub
		btnFindAddress = (Button) findViewById(R.id.btnFindAddress);
		btnFindAddress.setOnClickListener(this);
		btnManualSearch = (Button) findViewById(R.id.btnManualSearch);
		btnManualSearch.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnFindAddress:
			if (AppUtils.isConnectingToInternet(this)) {
				i = new Intent(Dashboard_Activity.this, MainActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
			} else {
				AppUtils.internet_alertUser(Dashboard_Activity.this,
						"Check Internet Connection");
			}

			break;
		case R.id.btnManualSearch:

			if (AppUtils.isConnectingToInternet(this)) {
				i = new Intent(Dashboard_Activity.this,
						PlacePickerActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);
			} else {
				AppUtils.internet_alertUser(Dashboard_Activity.this,
						"Check Internet Connection");
			}

			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT)
				.show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);
	}

}
