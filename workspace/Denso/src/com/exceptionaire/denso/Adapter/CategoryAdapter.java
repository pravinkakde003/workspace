package com.exceptionaire.denso.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Model.Category;
/**
 * Adapter class to show Categories
 * @author PraWin_Android
 *
 */
public class CategoryAdapter extends ArrayAdapter<Category> {
	
	
	private Context mContext;
	
	private int layouID;
	
	private List<Category> service_grid_list;
	
	private LayoutInflater inflater;

	public CategoryAdapter(Context context, int resource,
			List<Category> objects) {
		
		super(context, resource, objects);
		
		this.mContext = context;
		
		this.layouID = resource;
		
		this.service_grid_list = objects;
		
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public Category getItem(int position) {
		return service_grid_list.get(position);
	}

	class ViewHolder {
		TextView txt_title;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(layouID, null);
			holder = new ViewHolder();
			holder.txt_title = (TextView) convertView
					.findViewById(R.id.txt_sub_category);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Category notes = service_grid_list.get(position);
		
		holder.txt_title.setText(notes.getCategory());
		
		return convertView;
	}

}
