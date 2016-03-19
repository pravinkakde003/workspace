package com.exceptionaire.denso.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Utils.CustomProgressDialog;

public class TimeoutActivity extends Activity {
	RelativeLayout retry_layout;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taptoretry_layout);
		progressDialog = new CustomProgressDialog(TimeoutActivity.this,
				"Connecting Internet...");
		retry_layout = (RelativeLayout) findViewById(R.id.retry_layout);

		retry_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				progressDialog.setCancelable(false);
				progressDialog.show();
				Thread background = new Thread() {
					public void run() {
						try {
							sleep(5 * 1000);
							if (progressDialog != null
									&& progressDialog.isShowing()) {
								progressDialog.dismiss();
							}
//							Intent i = new Intent(getBaseContext(),
//									ActivityCategory.class);
//							startActivity(i);
							finish();

						} catch (Exception e) {

						}
					}
				};

				// start thread
				background.start();

			}
		});

	}
	
	@Override
	public void onBackPressed() {
	    // Do Here what ever you want do on back press;
	}

}
