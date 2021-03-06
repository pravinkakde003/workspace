package android_kaizen.com.multipanefragments;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class my_adapter extends ArrayAdapter<ItemPOJO> {
	


	private Context mContext;
	
	private int layouID;
	
	private List<ItemPOJO> service_grid_list;
	
	private LayoutInflater inflater; 

	
	
	public my_adapter(Context context, int resource, List<ItemPOJO> objects) {
		super(context, resource, objects);

		
		this.mContext = context;
		
		this.layouID = resource;
		
		this.service_grid_list = objects;
		
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public ItemPOJO getItem(int position) {
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
					.findViewById(R.id.textView1);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		ItemPOJO notes = service_grid_list.get(position);
		
		holder.txt_title.setText(notes.getTitle());
		
		return convertView;
	}
	
	
	
	
	
	
	
}
