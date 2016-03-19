package com.exceptionaire.denso.Activities;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Utils.ActivityCallbackInterface;
import com.exceptionaire.denso.Utils.AppConstant;
import com.exceptionaire.denso.Utils.AppUtils;
import com.exceptionaire.denso.Utils.CommonAsyncTask;
import com.exceptionaire.denso.Utils.AppSharedPreference;
import com.exceptionaire.denso.Utils.GetCommonAsyncTask;

public class ActivityLogin extends Activity implements OnClickListener,
		ActivityCallbackInterface {
	EditText edt_txt_password, edt_txt_user_name;
	Button btn_login, btn_amw;
	ActivityCallbackInterface callbackInterface;
	private AppSharedPreference preference;
	TextView txt_forget_password;
	public static AlertDialog deleteDialog;
	int OPERATION_CODE;
	private long mLastClickTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		callbackInterface = this;
		preference = new AppSharedPreference(ActivityLogin.this);
		intView();

	}

	private void intView() {
		// TODO Auto-generated method stub
		edt_txt_user_name = (EditText) findViewById(R.id.edt_txt_user_name);
		edt_txt_password = (EditText) findViewById(R.id.edt_txt_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		txt_forget_password = (TextView) findViewById(R.id.txt_forget_password);
		txt_forget_password.setOnClickListener(this);
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
				Log.e("Login Activity", "Enter UserName Name");
				Toast.makeText(ActivityLogin.this, "Enter UserName/Email ID",
						Toast.LENGTH_SHORT).show();
			} else if (AppUtils.isEmpty(edt_txt_password)) {
				Log.e("Login Activity", "Password Empty");
		
				if (AppUtils.isValidEmail(edt_txt_user_name.getText()
					.toString())) {
					Log.e("","Valid Email");
				}else {
					Toast.makeText(ActivityLogin.this,
							"Enter Vaild Email ID", Toast.LENGTH_SHORT)
							.show();
				}
				Toast.makeText(ActivityLogin.this, "Enter Password",
						Toast.LENGTH_SHORT).show();
			} else if (AppUtils.isValidEmail(edt_txt_user_name.getText()
					.toString())) {
				if (AppUtils.isConnectingToInternet(this)) {
					OPERATION_CODE = 1;
					new CommonAsyncTask(ActivityLogin.this,
							(ActivityCallbackInterface) this).execute("0",
							AppConstant.BASE_URL + AppConstant.LOGIN_API,
							edt_txt_user_name.getText().toString(),
							edt_txt_password.getText().toString());
					InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					inputManager.hideSoftInputFromWindow(getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
				} else {
					AppUtils.internet_alertUser(ActivityLogin.this,
							"Check Internet Connection");
				}
			} else {
				edt_txt_user_name.setText("");
				edt_txt_password.setText("");
				Toast.makeText(ActivityLogin.this,
						"Enter Vaild Email ID", Toast.LENGTH_SHORT)
						.show();
			}

			break;
		case R.id.btn_amw:
			if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
				return;
			}
			Intent intent = new Intent(ActivityLogin.this,
					ActivityCategory.class);
			intent.putExtra("USER_TYPE", "GENERAL");
			startActivity(intent);
			break;

		case R.id.txt_forget_password:
			if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
				return;
			}
			forgetPassword();
			break;

		default:
			break;
		}
	}

	private void forgetPassword() {
		final EditText forget_email;
		LayoutInflater factory = LayoutInflater.from(ActivityLogin.this);
		final View deleteDialogView = factory.inflate(
				R.layout.forget_password_dialog, null);
		deleteDialog = new AlertDialog.Builder(ActivityLogin.this).create();
		deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		deleteDialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));

		deleteDialog.setView(deleteDialogView);
		deleteDialog.setCancelable(false);
		forget_email = (EditText) deleteDialogView
				.findViewById(R.id.edt_txt_email);
		deleteDialogView.findViewById(R.id.btn_Submit).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (AppUtils.isEmpty(forget_email)) {
							Toast.makeText(ActivityLogin.this,
									"Enter Email Address", Toast.LENGTH_SHORT)
									.show();
							Log.e("Login Activity", "UserName Empty");
						} else if (AppUtils.isValidEmail(forget_email.getText()
								.toString())) {
							if (AppUtils
									.isConnectingToInternet(ActivityLogin.this)) {
								OPERATION_CODE = 2;
								InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
								inputManager.hideSoftInputFromWindow(
										getCurrentFocus().getWindowToken(),
										InputMethodManager.HIDE_NOT_ALWAYS);
								new GetCommonAsyncTask(
										ActivityLogin.this,
										(ActivityCallbackInterface) ActivityLogin.this)
										.execute("0", AppConstant.BASE_URL
												+ AppConstant.FORGET_PASSWORD
												+ forget_email.getText()
														.toString());

								deleteDialog.dismiss();
							} else {
								AppUtils.internet_alertUser(ActivityLogin.this,
										"Check Internet Connection");
								deleteDialog.dismiss();
							}
						} else {
							forget_email.setText("");
							Toast.makeText(ActivityLogin.this,
									"Enter Valid Email ID",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						inputManager.hideSoftInputFromWindow(getCurrentFocus()
								.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
						deleteDialog.dismiss();
					}
				});

		deleteDialog.show();

	}

	@Override
	public void getResultBack(String result) {
		if (result != null) {
			switch (OPERATION_CODE) {
			case 1:
				try {
					Log.d("RESULT", result);
					JSONObject jobject = new JSONObject(result);
					String status = jobject.getString("Status");
					if (status.equalsIgnoreCase("Success")) {
						JSONObject innerobject = jobject.getJSONObject("Login");
						String user_id = innerobject.getString("user_id");
						String email_address = innerobject
								.getString("email_address");
						preference.setIsLoggedIn("1");
						preference.setuser_id(user_id);
						preference.setUser_email_ID(email_address);
						Intent mintent = new Intent(ActivityLogin.this,
								ActivityCategory.class);
						startActivity(mintent);
						finish();
					} else {
						Toast.makeText(ActivityLogin.this,
								"Invalid Credentials", Toast.LENGTH_SHORT)
								.show();
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
			case 2:
				Log.e("RESULT", result);
				try {
					JSONObject jobject = new JSONObject(result);
					String status = jobject.getString("status");
					String msg = jobject.getString("msg");
					if (status.equalsIgnoreCase("Success")) {
						Toast.makeText(ActivityLogin.this, msg,
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(ActivityLogin.this, msg,
								Toast.LENGTH_SHORT).show();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			default:
				break;
			}

		} else {
			edt_txt_user_name.setText("");
			edt_txt_password.setText("");
			Toast.makeText(ActivityLogin.this, "Invalid Credentials",
					Toast.LENGTH_SHORT).show();
		}

	}
	
	@Override
	public void onBackPressed() {
		LayoutInflater factory = LayoutInflater.from(ActivityLogin.this);
		final View deleteDialogView = factory.inflate(R.layout.exit_app_dialog,
				null);
		deleteDialog = new AlertDialog.Builder(ActivityLogin.this).create();
		deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		deleteDialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));

		deleteDialog.setView(deleteDialogView);
		deleteDialog.setCancelable(false);
		deleteDialogView.findViewById(R.id.btn_yes).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						deleteDialog.dismiss();
						finish();

					}
				});

		deleteDialogView.findViewById(R.id.btn_no).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						deleteDialog.dismiss();
					}
				});

		deleteDialog.show();

	}


}
