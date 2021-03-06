package com.exceptionaire.denso.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetCommonAsyncTask extends AsyncTask<String, Void, String> {

	Context mContaxt;
	ActivityCallbackInterface activityCallback;
	private final ProgressDialog progressDialog;

	public GetCommonAsyncTask(Context c, ActivityCallbackInterface callback) {
		this.mContaxt = c;
		this.activityCallback = callback;
		progressDialog = new CustomProgressDialog(c, "Loading...");
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		progressDialog.show();
	}

	@Override
	protected String doInBackground(String... params) {

		return GetMethodServerSocket.getInstance().GET(params);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}

			activityCallback.getResultBack(result);
		
		
	}

}
