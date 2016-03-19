package com.exceptionaire.denso.Activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Fragments.DescriptionFragment;
import com.exceptionaire.denso.Fragments.VersionsFragment;
import com.exceptionaire.denso.Interface.OnVersionNameSelectionChangeListener;
import com.exceptionaire.denso.Model.Category;
import com.exceptionaire.denso.Model.SubCategories;
import com.exceptionaire.denso.Model.Section;
import com.exceptionaire.denso.Utils.ActivityCallbackInterface;
import com.exceptionaire.denso.Utils.AppConstant;
import com.exceptionaire.denso.Utils.AppUtils;
import com.exceptionaire.denso.Utils.GetCommonAsyncTask;
import com.exceptionaire.denso.Utils.HorizontalListView;
import com.exceptionaire.denso.Utils.AppSharedPreference;

public class CategoryDetailsActivity extends Activity implements
		OnVersionNameSelectionChangeListener, OnClickListener,
		ActivityCallbackInterface,VersionsFragment.ISendLanguage {
	HorizontalListView mhorizontallistview;
	ImageButton btn_back;
	TextView top_bar_title, footer_bar_title;
	RelativeLayout footer_bar;
	ActivityCallbackInterface callbackInterface;
	String cat_id;
	ArrayList<Section> subcategory_list;
	List<SubCategories> horizontal_list;
	AppSharedPreference prefernce;
	Button btn_language;
	String[] languages = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_category_activity);
		callbackInterface = this;
		prefernce = new AppSharedPreference(CategoryDetailsActivity.this);
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		top_bar_title = (TextView) findViewById(R.id.top_bar_title);
		btn_language = (Button) findViewById(R.id.btn_language);
		btn_language.setOnClickListener(this);
		btn_language.setVisibility(View.VISIBLE);
		footer_bar_title = (TextView) findViewById(R.id.footer_bar_title);
		footer_bar_title.setText("Switch to AMW");
		footer_bar = (RelativeLayout) findViewById(R.id.footer_bar);
		footer_bar.setOnClickListener(this);

		mhorizontallistview = (HorizontalListView) findViewById(R.id.listview);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			top_bar_title.setText(extras.getString("CAT_NAME"));
			cat_id = extras.getString("CAT_ID");
		} else {
			top_bar_title.setText(getResources().getString(R.string.app_name));
		}
		subcategory_list = new ArrayList<Section>();
		horizontal_list = new ArrayList<SubCategories>();
		
		
//		getResultBack(Activity_Category.ReadFromfile("cc", this));
		if (AppUtils.isConnectingToInternet(this)) {
			new GetCommonAsyncTask(CategoryDetailsActivity.this,
					(ActivityCallbackInterface) this).execute("0",
							 AppConstant.BASE_URL +
							 AppConstant.GET_SUBCATEGORY+cat_id+"&user_id="+prefernce.getuser_id());
							
//							AppConstant.BASE_URL + AppConstant.GET_SUBCATEGORY + cat_id
//							+ "&user_id=" + "1");

		} else {
			AppUtils.internet_alertUser(CategoryDetailsActivity.this,
					"Check Internet Connection");
		}

		mhorizontallistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.e("Click", "Clicked on:" + position);
				SubCategories notes = horizontal_list.get(position);
				Intent intent = new Intent(CategoryDetailsActivity.this,
						SubCategoryActivity.class);
				intent.putExtra("SUB_CAT_NAME", top_bar_title.getText() + "-"
						+ "Subcategory:" + notes.getSub_category());
				startActivity(intent);
			}
		});

	}

	private BaseAdapter mAdapter = new BaseAdapter() {

		@Override
		public int getCount() {
			return horizontal_list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View retval = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.horizontal_list_item, null);
			TextView title = (TextView) retval
					.findViewById(R.id.txt_sub_category);
			SubCategories notes = horizontal_list.get(position);
			title.setText(notes.getSub_category());

			return retval;
		}

	};

	@Override
	public void OnSelectionChanged(Section section) {
		DescriptionFragment descriptionFragment = (DescriptionFragment) getFragmentManager()
				.findFragmentById(R.id.description_fragment);

		if (descriptionFragment != null) {
			descriptionFragment.setSection(section);;
		} else {
			DescriptionFragment newDesriptionFragment = new DescriptionFragment();
			Bundle args = new Bundle();

			args.putParcelable(DescriptionFragment.KEY_POSITION, section);
			newDesriptionFragment.setArguments(args);
			FragmentTransaction fragmentTransaction = getFragmentManager()
					.beginTransaction();
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
			Intent intent = new Intent(CategoryDetailsActivity.this,
					ActivityInformation.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btn_language:
			
			showDialog();

			break;

		default:
			break;
		}
	}
	
	
	public void showDialog() {

		final Section section = (Section) btn_language.getTag();
		languages = section.getLanguage();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Your Language");
		builder.setSingleChoiceItems(section.getLanguage(), 0,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						// Logic for language change
						DescriptionFragment descriptionFragment = (DescriptionFragment) getFragmentManager()
								.findFragmentById(R.id.description_fragment);
								
								if (descriptionFragment!=null) {
									
									descriptionFragment.setdata(section.getData().get(languages[item]));
								}else {
									descriptionFragment = new DescriptionFragment();
									// versionsFragment.setArguments(getIntent().getExtras());
									Bundle bundle = new Bundle();
									bundle.putInt("p", item);
									bundle.putParcelable(DescriptionFragment.KEY_POSITION, section);
									descriptionFragment.setArguments(bundle);
									getFragmentManager().beginTransaction()
											.add(R.id.fragment_container, descriptionFragment)
											.commit();

								}
						dialog.dismiss();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	@Override
	public void getResultBack(String result) {
		if (result != null) {
			try {

				JSONObject jobject = new JSONObject(result);
				String status = jobject.getString("Status");
				if (status.equalsIgnoreCase("Success")) {
					JSONArray j_array = jobject.getJSONArray("Sections");
					for (int i = 0; i < j_array.length(); i++) {
						JSONObject obj = j_array.getJSONObject(i);
						String section_name = obj.getString("section_name");
						String page_id = obj.getString("page_id");
						String lang_id = obj.getString("lang_id");
						String category = obj.getString("category");

						String sub_category = obj.getString("sub_category");
						String page_data = obj.getString("page_data");
						String page_description = obj
								.getString("page_description");
						String resources_id = obj.getString("resources_id");

						String lang_code = obj.getString("lang_code");
						String v_cat_id = obj.getString("v_cat_id");
						String background_img = obj.getString("background_img");

						HashMap<String, String> languageMap = new HashMap<String, String>();
						
						
						if (obj.has("versions")) {
							JSONArray versionArray = obj
									.getJSONArray("versions");
							for (int j = 0; j < versionArray.length(); j++) {
								
								JSONObject vobj = versionArray.getJSONObject(j);
								String inner_page_id = vobj
										.getString("page_id");
								String inner_page_description = vobj
										.getString("page_description");
								String language = vobj.getString("language");
								
								languageMap.put(language, inner_page_description);
							}
						}
						
						languageMap.put("English", page_data);

					
						if (obj.has("language")) {
							JSONArray versionArray = obj
									.getJSONArray("language");
							languages = new String[versionArray.length()];
							for (int j = 0; j < versionArray.length(); j++) {

								languages[j] = versionArray.getString(j);
							}
						}

						subcategory_list.add(new Section(section_name,
								page_data, languages,languageMap));
					}

					if (jobject.has("Sub_category_list")) {
						JSONArray subCat_array = jobject
								.getJSONArray("Sub_category_list");
						for (int i = 0; i < subCat_array.length(); i++) {
							JSONObject obj = subCat_array.getJSONObject(i);
							String v_sub_cat_id = obj.getString("v_sub_cat_id");
							String sub_category = obj.getString("sub_category");
							Log.e("v_sub_cat_id::", v_sub_cat_id);
							Log.e("sub_category::", sub_category);
							horizontal_list.add(new SubCategories(
									v_sub_cat_id, sub_category));
						}
					} else {
						Log.e("", "No valuse-Sub_category_list");
						mhorizontallistview.setVisibility(View.GONE);
					}

					mhorizontallistview.setAdapter(mAdapter);

					VersionsFragment versionsFragment = (VersionsFragment) getFragmentManager()
							.findFragmentById(R.id.names_fragment);

					if (versionsFragment != null) {
						versionsFragment.getNewData(subcategory_list);
					} else {

						versionsFragment = new VersionsFragment();
						// versionsFragment.setArguments(getIntent().getExtras());
						Bundle bundle = new Bundle();
						bundle.putParcelableArrayList("SubCatList",
								subcategory_list);
						versionsFragment.setArguments(bundle);
						getFragmentManager().beginTransaction()
								.add(R.id.fragment_container, versionsFragment)
								.commit();

					}

				} else if (status.equalsIgnoreCase("Failed")) {
					String msg = jobject.getString("msg");
					Toast.makeText(CategoryDetailsActivity.this, msg,
							Toast.LENGTH_SHORT).show();
					finish();
				} else {
					Toast.makeText(CategoryDetailsActivity.this,
							"Network Congestion", Toast.LENGTH_SHORT).show();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void SendLanguageData(Section section) {

		if (btn_language!=null) {
			btn_language.setTag(section);
		}
	}
}
