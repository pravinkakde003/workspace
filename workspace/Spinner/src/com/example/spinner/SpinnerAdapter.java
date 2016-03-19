package com.example.spinner;

import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends BaseAdapter {
	
	List<String> list;
	Activity activity;
	LayoutInflater inflater;
	TextView textView;
	public SpinnerAdapter(Activity activity,List<String> object) {
	
		this.activity = activity;
		this.list = object;
		this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.list.size();
		
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return this.list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		if (convertView==null) {
			convertView = inflater.inflate(R.layout.spinner_item, null);
			 textView= (TextView) convertView.findViewById(R.id.tvText);
			convertView.setTag(textView);
		}else {
			textView = (TextView) convertView.getTag();
		}
//		
		textView.setText(list.get(position));
		
		return convertView;
	}
	
	

}
