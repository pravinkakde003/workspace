package com.exceptionaire.denso.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Model.Section;

public class DescriptionFragment extends Fragment implements OnClickListener {

	public final static String KEY_POSITION = "section";
	int mCurrentPosition = -1;
	String[] mVersionDescriptions;
	WebView mVersionDescriptionTextView;
	private String[] languages;
	private Section section;
	ListView listViewResource;
	public DescriptionFragment() {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.multipane_desc, container, false);
		mVersionDescriptionTextView = (WebView) view
				.findViewById(R.id.version_description);
		listViewResource=(ListView) view.findViewById(R.id.listresource);
		WebSettings settings = mVersionDescriptionTextView.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		mVersionDescriptionTextView.setBackgroundColor(Color.TRANSPARENT);
		Bundle b = getArguments();
		if (b != null) {
			section = (Section) b
					.getParcelable(DescriptionFragment.KEY_POSITION);
			this.languages = section.getLanguage();
			mVersionDescriptionTextView.getSettings()
					.setDefaultTextEncodingName("utf-8");
			mVersionDescriptionTextView.getSettings().setBuiltInZoomControls(
					true);
			mVersionDescriptionTextView.loadData("<font color='white'>"
					+ section.getData().get(languages[b.getInt("p")])
					+ "</font>", "text/html; charset=utf-8", null);
		}
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	public void setSection(Section data) {
		this.section = data;
		mVersionDescriptionTextView.getSettings().setDefaultTextEncodingName(
				"utf-8");
		mVersionDescriptionTextView.getSettings().setBuiltInZoomControls(true);
		mVersionDescriptionTextView.loadData(
				"<font color='white'>" + section.getPageData() + "</font>",
				"text/html; charset=utf-8", null);
		languages = section.getLanguage();
	}

	public void setdata(String data) {
		mVersionDescriptionTextView.getSettings().setDefaultTextEncodingName(
				"utf-8");
		mVersionDescriptionTextView.getSettings().setBuiltInZoomControls(true);
		mVersionDescriptionTextView.loadData("<font color='white'>" + data
				+ "</font>", "text/html; charset=utf-8", null);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// case R.id.btn_language:
		// showDialog();
		// break;
		default:
			break;
		}

	}

}