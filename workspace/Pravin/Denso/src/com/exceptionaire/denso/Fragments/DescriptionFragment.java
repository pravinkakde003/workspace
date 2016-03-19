package com.exceptionaire.denso.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Model.Section;


public class DescriptionFragment extends Fragment {

	public final static String KEY_POSITION = "section";
	int mCurrentPosition = -1;

	String[] mVersionDescriptions;
	WebView mVersionDescriptionTextView;

	private String[] languages;
	private Section section;

	public DescriptionFragment() {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// mVersionDescriptions =
		// getResources().getStringArray(R.array.version_descriptions);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// If the Activity is recreated, the savedInstanceStare Bundle isn't
		// empty
		// we restore the previous version name selection set by the Bundle.
		// This is necessary when in two pane layout
		// if (savedInstanceState != null){
		// mCurrentPosition = savedInstanceState.getInt(KEY_POSITION);
		// }

		View view = inflater.inflate(R.layout.multipane_desc, container, false);

		mVersionDescriptionTextView = (WebView) view
				.findViewById(R.id.version_description);
		mVersionDescriptionTextView.setBackgroundColor(Color.TRANSPARENT);
		Bundle b = getArguments();
		if (b != null) {

			section = (Section) b
					.getParcelable(DescriptionFragment.KEY_POSITION);
			this.languages = section.getLanguage();
			
//			String text = "<html><head>"
//					+ "<style type=\"text/css\">body{color: #fff; background-color: none;}"
//					+ "</style></head>" + "<body>" + section.getData().get(languages[b.getInt("p")])
//					+ "</body></html>";
//
//			mVersionDescriptionTextView.setInitialScale(getScale());
//			mVersionDescriptionTextView.getSettings()
//					.setDefaultTextEncodingName("utf-8");
//			mVersionDescriptionTextView.getSettings().setBuiltInZoomControls(
//					true);
			mVersionDescriptionTextView.loadData("<font color='white'>"+section.getData().get(languages[b.getInt("p")])+"</font>",
					"text/html; charset=utf-8", null);
			
			
			
			
//			mVersionDescriptionTextView.loadData(section.getData().get(languages[b.getInt("p")]),
//					"text/html", "utf-8");
			

		}
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		// During the startup, we check if there are any arguments passed to the
		// fragment.
		// onStart() is a good place to do this because the layout has already
		// been
		// applied to the fragment at this point so we can safely call the
		// method below
		// that sets the description text
		// Bundle args = getArguments();
		// if (args != null){
		// // Set description based on argument passed in
		// setDescription(args.getInt(KEY_POSITION));
		// } else if(mCurrentPosition != -1){
		// // Set description based on savedInstanceState defined during
		// onCreateView()
		// setDescription(mCurrentPosition);
		// }
	}

	public void setSection(Section data) {

		this.section = data;
		
//		String text = "<html><head>"
//				+ "<style type=\"text/css\">body{color: #fff; background-color: none;}"
//				+ "</style></head>" + "<body>" + section.getPageData()
//				+ "</body></html>";
//
//		mVersionDescriptionTextView.setInitialScale(getScale());
//		mVersionDescriptionTextView.getSettings()
//				.setDefaultTextEncodingName("utf-8");
//		mVersionDescriptionTextView.getSettings().setBuiltInZoomControls(
//				true);
		mVersionDescriptionTextView.loadData("<font color='white'>"+section.getPageData()+"</font>",
				"text/html; charset=utf-8", null);
	
//		mVersionDescriptionTextView.loadData(section.getPageData(),
//				"text/html", "utf-8");
		languages = section.getLanguage();
	}



	
	
	
	public void setdata(String data){
//		String text = "<html><head>"
//				+ "<style type=\"text/css\">body{color: #fff; background-color: none;}"
//				+ "</style></head>" + "<body>" + data
//				+ "</body></html>";
//
//		mVersionDescriptionTextView.setInitialScale(getScale());
//		mVersionDescriptionTextView.getSettings()
//				.setDefaultTextEncodingName("utf-8");
//		mVersionDescriptionTextView.getSettings().setBuiltInZoomControls(
//				true);
		mVersionDescriptionTextView.loadData("<font color='white'>"+data+"</font>",
				"text/html; charset=utf-8", null);
		
//		mVersionDescriptionTextView.loadData(data,
//				"text/html", "utf-8");
		
		
	}

	// @Override
	// public void onSaveInstanceState(Bundle outState) {
	// super.onSaveInstanceState(outState);
	//
	// // Save the current description selection in case we need to recreate the
	// fragment
	// outState.putInt(KEY_POSITION,mCurrentPosition);
	// }
	
	private int getScale() {
		Display display = ((WindowManager) getActivity().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth();
		Double val = new Double(width) / new Double(width);
		val = val * 100d;
		return val.intValue();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}