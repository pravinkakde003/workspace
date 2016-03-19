package com.exceptionaire.denso.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Model.Section;

/**
 * 
 * @author Exceptionaire Technologies-Pr@WinK Adapter class for showing list of
 *         product categories.
 * 
 */
public class SubCategoryAdapter extends ArrayAdapter<Section> {
	/**
	 * Context of Activity
	 */
	private Context mContext;
	/**
	 * Inflater Layout resource ID
	 */
	private int layouID;
	/**
	 * List of Categories
	 */
	private List<Section> category_grid_list;
	/**
	 * {@link android.app.Activity#getLayoutInflater()}
	 */
	private LayoutInflater inflater;

	public SubCategoryAdapter(Context context, int resource,
			List<Section> objects) {
		super(context, resource, objects);
		this.mContext = context;
		this.layouID = resource;
		this.category_grid_list = objects;
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public Section getItem(int position) {
		return category_grid_list.get(position);
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
					.findViewById(R.id.textView1);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Section notes = category_grid_list.get(position);
		holder.txt_title.setText(notes.getSection_name());

		return convertView;
	}

}
