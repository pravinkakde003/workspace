package com.example.findgo;

import com.example.findgo.Utils.AppUtils;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	public void onCreate() {
		// Setup handler for uncaught exceptions.
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread, Throwable e) {
				handleUncaughtException(thread, e);
			}
		});
	}

	public void handleUncaughtException(Thread thread, Throwable e) {
		e.printStackTrace(); // not all Android versions will print the stack
								// trace automatically
		AppUtils.alertDialogShow(getApplicationContext(),
				"Internal Error Occured");
		System.exit(1); // kill off the crashed app
	}
}
