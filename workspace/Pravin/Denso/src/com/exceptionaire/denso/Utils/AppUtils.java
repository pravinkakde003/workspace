package com.exceptionaire.denso.Utils;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;

import com.exceptionaire.denso.R;




public class AppUtils {
	public static AlertDialog deleteDialog;	
	
	/**
	 * Alertdialog for internet connectivity
	 *
	 */
	public static void internet_alertUser(final Context context, String msg) {
		LayoutInflater factory = LayoutInflater.from(context);
		final View deleteDialogView = factory.inflate(
				R.layout.internet_dialog_layout, null);

		deleteDialog = new AlertDialog.Builder(context).create();
		deleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		deleteDialog.setView(deleteDialogView);
	
		deleteDialogView.findViewById(R.id.btn_Setting).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						context.startActivity(new Intent(
								Settings.ACTION_SETTINGS));
					}
				});
		deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						deleteDialog.dismiss();
					}
				});
		
		
		

		deleteDialog.show();
	}
	
	
	
	
	/**
	 * To check internet connection
	 *
	 */
	public static boolean isConnectingToInternet(Context _context) {
		ConnectivityManager connectivity = (ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}
	
	
	public static boolean isEmpty(EditText edit_text_value) {
		if (edit_text_value.getText().toString().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}
	

}
