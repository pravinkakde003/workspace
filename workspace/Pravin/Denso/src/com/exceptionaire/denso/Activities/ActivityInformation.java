package com.exceptionaire.denso.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exceptionaire.denso.R;

public class ActivityInformation extends Activity implements OnClickListener {
	ImageButton btn_back;
	WebView mwebview;
	TextView top_bar_title, footer_bar_title;
	RelativeLayout footer_bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amw);
		intView();
	}

	private void intView() {
		// TODO Auto-generated method stub
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		top_bar_title = (TextView) findViewById(R.id.top_bar_title);
		footer_bar_title = (TextView) findViewById(R.id.footer_bar_title);
		top_bar_title.setText("AMW");
		mwebview = (WebView) findViewById(R.id.mwebview);
		footer_bar_title.setText("Switch to SKW");
		mwebview.setWebViewClient(new WebViewClient());
		mwebview.loadUrl("http://www.google.com");
		footer_bar=(RelativeLayout) findViewById(R.id.footer_bar);
		footer_bar.setOnClickListener(this);
		btn_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.footer_bar:
			Intent intent = new Intent(ActivityInformation.this,
					ActivityLogin.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btn_back:
			finish();
			break;

		default:
			break;
		}
	}

}
