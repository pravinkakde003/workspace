package com.example.findgo.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findgo.R;






public class CustomProgressDialog extends ProgressDialog {

	private Animation animRotate;
	private ImageView ivProgress;
	private static TextView tvProgress;
	private String message;

	public CustomProgressDialog(Context context, String message) {
		super(context);
		this.message = message;

		animRotate = AnimationUtils.loadAnimation(context,
				R.anim.custom_progress_dialog);
		setIndeterminate(true);
		setCancelable(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_progress_dialog);
		ivProgress = (ImageView) findViewById(R.id.ivProgress);
		tvProgress = (TextView) findViewById(R.id.tvProgress);
		tvProgress.setText(message);
	}

	@Override
	public void show() {
		super.show();
		ivProgress.startAnimation(animRotate);
		
	}

	@Override
	public void dismiss() {
		super.dismiss();
		animRotate.cancel();
	}
	
	
	public static void setText(String text)
	{
		tvProgress.setText(text);
	}
	
	
}
