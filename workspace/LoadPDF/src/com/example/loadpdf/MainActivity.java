package com.example.loadpdf;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;

public class MainActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 getWindow().setFlags(LayoutParams.FLAG_SECURE,
                 LayoutParams.FLAG_SECURE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		String pdf = "https://www.google.co.in/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwi5zIOMi8DLAhXCB44KHb_yCccQFggbMAA&url=http%3A%2F%2Fwww.tutorialspoint.com%2Fandroid%2Fandroid_tutorial.pdf&usg=AFQjCNF9pjrm8zcuuZ0iTF1Nc5X8M7zwWA&sig2=GHW-_5e7QfGZYve-Nyu1vw&bvm=bv.116573086,d.c2E";
		webview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="
				+ pdf);

		
	}

}
