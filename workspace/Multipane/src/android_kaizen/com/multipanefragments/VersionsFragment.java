package android_kaizen.com.multipanefragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Andy on 04-Jan-15.
 */
public class VersionsFragment extends Fragment {

    // Mandatory empty constructor for the fragment manager to instantiate the fragment
	 ListView plistview ;
	 my_adapter adapter;
	 private List<ItemPOJO> service_list;
	 String[] versionName;
    public VersionsFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        versionName = getResources().getStringArray(R.array.version_names);
        
        service_list = new ArrayList<ItemPOJO>();
        for (int i = 0; i < versionName.length; i++) {
        	service_list.add(new ItemPOJO(versionName[i]));
		}
        
        adapter = new my_adapter(getActivity(), R.layout.list_item, service_list);
        plistview.setAdapter(adapter);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	 View view = inflater.inflate(R.layout.fragment_version, container, false);

    	 plistview = (ListView) view.findViewById(R.id.plistview);
    	 plistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {  
             public void onItemClick(AdapterView<?> parent, View view, int position, long id)
             {  
            	 OnVersionNameSelectionChangeListener listener = (OnVersionNameSelectionChangeListener) getActivity();
            	 listener.OnSelectionChanged(position);
             
             
             }

          });
         return view;
    }
    
    
    
}
