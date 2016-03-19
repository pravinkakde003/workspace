package com.exceptionaire.denso.Activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Fragments.DescriptionFragment;
import com.exceptionaire.denso.Fragments.VersionsFragment;
import com.exceptionaire.denso.Interface.OnVersionNameSelectionChangeListener;
import com.exceptionaire.denso.Model.Section;

public class SubCategoryActivity extends Activity implements
		OnVersionNameSelectionChangeListener, OnClickListener {
	ImageButton btn_back;
	TextView top_bar_title, footer_bar_title;
	RelativeLayout footer_bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subcategory_detail);

		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		top_bar_title = (TextView) findViewById(R.id.top_bar_title);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			top_bar_title.setText(extras.getString("SUB_CAT_NAME"));
		} else {
			top_bar_title.setText(getResources().getString(R.string.app_name));
		}
		footer_bar_title = (TextView) findViewById(R.id.footer_bar_title);
		footer_bar_title.setText("Switch to AMW");
		footer_bar = (RelativeLayout) findViewById(R.id.footer_bar);
		footer_bar.setOnClickListener(this);

		// Check whether the Activity is using the layout verison with the
		// fragment_container
		// FrameLayout and if so we must add the first fragment
		// if (findViewById(R.id.fragment_container) != null) {
		//
		// // However if we are being restored from a previous state, then we
		// // don't
		// // need to do anything and should return or we could end up with
		// // overlapping Fragments
		// if (savedInstanceState != null) {
		// return;
		// }
		//
		// // Create an Instance of Fragment
		// VersionsFragment versionsFragment = new VersionsFragment();
		// versionsFragment.setArguments(getIntent().getExtras());
		// getFragmentManager().beginTransaction()
		// .add(R.id.fragment_container, versionsFragment).commit();
		// }
	}

	@Override
	public void OnSelectionChanged(Section section) {
		DescriptionFragment descriptionFragment = (DescriptionFragment) getFragmentManager()
				.findFragmentById(R.id.description_fragment);

		if (descriptionFragment != null) {
			// If description is available, we are in two pane layout
			// so we call the method in DescriptionFragment to update its
			// content
			descriptionFragment.setSection(section);;
		} else {
			DescriptionFragment newDesriptionFragment = new DescriptionFragment();
			Bundle args = new Bundle();

			args.putParcelable(DescriptionFragment.KEY_POSITION, section);
			newDesriptionFragment.setArguments(args);
			FragmentTransaction fragmentTransaction = getFragmentManager()
					.beginTransaction();

			// Replace whatever is in the fragment_container view with this
			// fragment,
			// and add the transaction to the backStack so the User can navigate
			// back
			fragmentTransaction.replace(R.id.fragment_container,
					newDesriptionFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.footer_bar:
			Intent intent = new Intent(SubCategoryActivity.this,
					ActivityInformation.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}
}
