package com.example.findgo.Utils;

import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;

import com.example.findgo.R;
import com.example.findgo.TimeoutActivity;



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
		deleteDialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));

		deleteDialog.setView(deleteDialogView);
		deleteDialog.setCancelable(false);
		deleteDialogView.findViewById(R.id.btn_Setting).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						deleteDialog.dismiss();
						context.startActivity(new Intent(
								Settings.ACTION_SETTINGS));
					}
				});

		deleteDialogView.findViewById(R.id.btn_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						deleteDialog.dismiss();
						Intent intent = new Intent(context, TimeoutActivity.class);
						context.startActivity(intent);
					
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

	public static boolean isValidEmail(String email) {
		Pattern pattern = Patterns.EMAIL_ADDRESS;
		return pattern.matcher(email).matches();
	}

	public static void alertDialogShow(Context context, String message)
    {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Find & Go");
        alertDialog.setMessage(message);
        
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface dialog, int which) 
            {
                alertDialog.dismiss();
          } 
        }); 
        alertDialog.show();
    }

}
