package com.exceptionaire.denso.Fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Adapter.SubCategoryAdapter;
import com.exceptionaire.denso.Interface.OnVersionNameSelectionChangeListener;
import com.exceptionaire.denso.Model.Section;

public class SubCategoryDetailsVersionsFragment extends ListFragment {
	SubCategoryAdapter adapter;

	
	/**
	 * Interface reference
	 */
	OnVersionNameSelectionChangeListener listener;
	ISendLanguage iSendLanguage;
	public SubCategoryDetailsVersionsFragment() {

	}

	public interface FragmentCommunicator {
		public void onDataPass(String data);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		view.setBackgroundColor(Color.parseColor("#4DFF4081"));
		setListShown(true);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			listener = (OnVersionNameSelectionChangeListener) activity;
			iSendLanguage = (ISendLanguage) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Bundle bundle = this.getArguments();

		if (bundle != null) {
			ArrayList<Section> arraylist = bundle
					.getParcelableArrayList("SubCatList");
			adapter = new SubCategoryAdapter(getActivity(),
					R.layout.pan_list_item, arraylist);
			setListAdapter(adapter);
			listener.OnSelectionChanged(adapter.getItem(0));
			iSendLanguage.SendLanguageData(adapter.getItem(0));
		}

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		listener.OnSelectionChanged(adapter.getItem(position));
		iSendLanguage.SendLanguageData(adapter.getItem(position));
		
	}

	public void getNewData(ArrayList<Section> arraylist) {
		adapter = new SubCategoryAdapter(getActivity(),
				R.layout.pan_list_item, arraylist);
		setListAdapter(adapter);
		listener.OnSelectionChanged(adapter.getItem(0));
		iSendLanguage.SendLanguageData(adapter.getItem(0));
	}
	
	public interface ISendLanguage {
		public void SendLanguageData(Section section);
	}
}
