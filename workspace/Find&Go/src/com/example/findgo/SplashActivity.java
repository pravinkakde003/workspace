package com.example.findgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.findgo.Instruction.InstructionActivity;
import com.example.findgo.Service.LocationService;


public class SplashActivity extends Activity {
	
	 Animation anim_logo;
	 Animation anim_welcome;
	 TextView welcome;
	 LinearLayout logo_holder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_layout);
		init();
		Intent service= new Intent(getApplicationContext(), LocationService.class);
		startService(service);
		welcome.startAnimation(anim_welcome);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
					Intent intent = new Intent(SplashActivity.this,
							InstructionActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
					finish();
				
			}
		}, 3000);

	}
	
	private void init() {
		welcome=(TextView) findViewById(R.id.welcome);
		logo_holder = (LinearLayout) findViewById(R.id.logo_holder);
	    welcome.setVisibility(View.INVISIBLE);
	    logo_holder.setVisibility(View.INVISIBLE);  
	    anim_logo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_info_popup);
	    anim_welcome = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_info_popup);
	}

	protected void onResume() {
	    super.onResume();
	    welcome.startAnimation(anim_welcome);
	    
	    Handler handler = new Handler();
		Runnable startMain = new Runnable() {

			@Override
			public void run() {
				if (!isFinishing()){
					logo_holder.startAnimation(anim_logo);
				}
			}
		};
		
		handler.postDelayed(startMain, 200);
	    
	}

}
