package com.example.gliderpager;

import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WalkthroughActivity extends Activity {

	public static ParallaxContainer parallaxContainer;
	public static CirclePageIndicator indicator;
	Button btn;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walkthrough);
		context = this;
		parallaxContainer = (ParallaxContainer) findViewById(R.id.parallaxcontainer);

		if (parallaxContainer != null) {

			parallaxContainer.setLooping(false);

			indicator = (CirclePageIndicator) findViewById(R.id.indicators);

			final ViewPager vp = parallaxContainer.setupChildren(
					getLayoutInflater(), R.raw.ny_blurred,
					R.layout.view_walkthrough1, R.layout.view_walkthrough2,
					R.layout.view_walkthrough3);

			indicator.setOnPageChangeListener(parallaxContainer);

			indicator.setViewPager(vp);

			btn = (Button) parallaxContainer
					.findViewById(R.id.getstarted);
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Toast.makeText(context, "Hello!", Toast.LENGTH_LONG).show();

				}
			});
		}

	}
}
