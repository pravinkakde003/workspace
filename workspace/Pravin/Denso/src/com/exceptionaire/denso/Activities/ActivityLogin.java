package com.exceptionaire.denso.Activities;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Utils.ActivityCallbackInterface;
import com.exceptionaire.denso.Utils.AppConstant;
import com.exceptionaire.denso.Utils.AppUtils;
import com.exceptionaire.denso.Utils.CommonAsyncTask;
import com.exceptionaire.denso.Utils.AppSharedPreference;

public class ActivityLogin extends Activity implements OnClickListener,
		ActivityCallbackInterface {
	EditText edt_txt_password, edt_txt_user_name;
	Button btn_login, btn_amw;
	ActivityCallbackInterface callbackInterface;
	private AppSharedPreference preference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		callbackInterface = this;
		preference= new AppSharedPreference(ActivityLogin.this);
		intView();
		
	}

	private void intView() {
		// TODO Auto-generated method stub
		edt_txt_user_name = (EditText) findViewById(R.id.edt_txt_user_name);
		edt_txt_password = (EditText) findViewById(R.id.edt_txt_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_amw = (Button) findViewById(R.id.btn_amw);
		btn_login.setOnClickListener(this);
		btn_amw.setOnClickListener(this);
		if (preference.getIsLoggedIn().equalsIgnoreCase("1")) {
			Intent intent = new Intent(ActivityLogin.this,
					ActivityCategory.class);
			startActivity(intent);
			finish();
		} 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			if (AppUtils.isEmpty(edt_txt_user_name)) {
				Log.e("Login Activity", "UserName Empty");
			} else if (AppUtils.isEmpty(edt_txt_password)) {
				Log.e("Login Activity", "Password Empty");
			} else if (AppUtils.isConnectingToInternet(this)) {
				new CommonAsyncTask(ActivityLogin.this,
						(ActivityCallbackInterface) this).execute("0",
										AppConstant.BASE_URL+AppConstant.LOGIN_API,
										edt_txt_user_name.getText().toString(),
										edt_txt_password.getText().toString());
								InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
								inputManager.hideSoftInputFromWindow(getCurrentFocus()
										.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			} else {
				AppUtils.internet_alertUser(ActivityLogin.this,
						"Check Internet Connection");
			}

			break;
		case R.id.btn_amw:
			Intent intent = new Intent(ActivityLogin.this, ActivityInformation.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public void getResultBack(String result) {
		if (result != null) {
			try {
				Log.d("RESULT", result);
				JSONObject jobject = new JSONObject(result);
				String status = jobject.getString("Status");
				if (status.equalsIgnoreCase("Success")) {
					JSONObject innerobject = jobject.getJSONObject("Login");
					String user_id = innerobject.getString("user_id");
					String email_address= innerobject.getString("email_address");
					preference.setIsLoggedIn("1");
					preference.setuser_id(user_id);
					preference.setUser_email_ID(email_address);	
					Intent mintent = new Intent(ActivityLogin.this,
					ActivityCategory.class);
					startActivity(mintent);
					finish();
				}else {
					Toast.makeText(ActivityLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
				}
	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

}
