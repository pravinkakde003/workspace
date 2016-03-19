package com.exceptionaire.denso.Activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exceptionaire.denso.R;
import com.exceptionaire.denso.Fragments.SubCategoryDetailsDescriptionFragment;
import com.exceptionaire.denso.Fragments.SubCategoryDetailsVersionsFragment;
import com.exceptionaire.denso.Interface.OnVersionNameSelectionChangeListener;
import com.exceptionaire.denso.Model.Section;
import com.exceptionaire.denso.Utils.ActivityCallbackInterface;
import com.exceptionaire.denso.Utils.AppConstant;
import com.exceptionaire.denso.Utils.AppSharedPreference;
import com.exceptionaire.denso.Utils.AppUtils;
import com.exceptionaire.denso.Utils.GetCommonAsyncTask;

public class SubCategoryDetailsActivity extends Activity implements
		OnClickListener, ActivityCallbackInterface,
		OnVersionNameSelectionChangeListener,
		SubCategoryDetailsVersionsFragment.ISendLanguage {
	ImageButton btn_back;
	TextView top_bar_title, footer_bar_title;
	RelativeLayout footer_bar;
	ArrayList<Section> subcategory_list;
	String subcat_id;
	AppSharedPreference prefernce;
	Button btn_language;
	String[] languages = null;
	int selectedradioposition = 0;
	private long mLastClickTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subcategory_detail);
		prefernce = new AppSharedPreference(SubCategoryDetailsActivity.this);
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		top_bar_title = (TextView) findViewById(R.id.top_bar_title);
		btn_language = (Button) findViewById(R.id.btn_language);
		btn_language.setOnClickListener(this);
		btn_language.setVisibility(View.VISIBLE);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			top_bar_title.setText(extras.getString("SUB_CAT_NAME"));
			subcat_id = extras.getString("SUB_CAT_ID");
		} else {
			top_bar_title.setText(getResources().getString(R.string.app_name));
		}
		footer_bar_title = (TextView) findViewById(R.id.footer_bar_title);
		footer_bar_title.setText("Switch To After Market Web");
		footer_bar = (RelativeLayout) findViewById(R.id.footer_bar);
		footer_bar.setOnClickListener(this);

		if (AppUtils.isConnectingToInternet(this)) {
			new GetCommonAsyncTask(SubCategoryDetailsActivity.this,
					(ActivityCallbackInterface) this).execute("0",
					AppConstant.BASE_URL
							+ AppConstant.GET_SECTION_BY_SUB_CAT_ID + subcat_id
							+ "&user_id=" + prefernce.getuser_id());
		} else {
			AppUtils.internet_alertUser(SubCategoryDetailsActivity.this,
					"Check Internet Connection");
		}
		subcategory_list = new ArrayList<Section>();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
				return;
			}
			finish();
			break;
		case R.id.footer_bar:
			if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
				return;
			}
			Intent intent = new Intent(SubCategoryDetailsActivity.this,
					ActivityInformation.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btn_language:
			if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
				return;
			}
			showDialog();
			break;
		default:
			break;
		}
	}

	private void showDialog() {
		final Section section = (Section) btn_language.getTag();
		languages = section.getLanguage();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Your Language");
		builder.setSingleChoiceItems(section.getLanguage(),
				selectedradioposition, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						selectedradioposition = item;
						SubCategoryDetailsDescriptionFragment descriptionFragment = (SubCategoryDetailsDescriptionFragment) getFragmentManager()
								.findFragmentById(R.id.description_fragment);

						if (descriptionFragment != null) {
							descriptionFragment.setdata(section.getData().get(
									languages[item]));
						} else {
							descriptionFragment = new SubCategoryDetailsDescriptionFragment();
							Bundle bundle = new Bundle();
							bundle.putInt("p", item);
							bundle.putParcelable(
									SubCategoryDetailsDescriptionFragment.KEY_POSITION,
									section);
							descriptionFragment.setArguments(bundle);
							getFragmentManager()
									.beginTransaction()
									.add(R.id.fragment_container,
											descriptionFragment).commit();

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
				Log.e("RESULT::", result);
				if (result.equalsIgnoreCase("SocketTimeoutException")
						|| result.equalsIgnoreCase("ConnectTimeoutException")) {
					Toast.makeText(SubCategoryDetailsActivity.this,
							"Server did not respond", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(getBaseContext(),
							TimeoutActivity.class);
					startActivity(i);
					finish();
				} else {
					JSONObject jobject = new JSONObject(result);
					String status = jobject.getString("Status");
					if (status.equalsIgnoreCase("Success")) {
						JSONArray j_array = jobject.getJSONArray("Sections");
						for (int i = 0; i < j_array.length(); i++) {
							JSONObject obj = j_array.getJSONObject(i);
							String section_name = obj.getString("section_name");
							String page_data = obj.getString("page_data");
							String background_img = obj
									.getString("background_img");

							HashMap<String, String> languageMap = new HashMap<String, String>();

							if (obj.has("versions")) {
								JSONArray versionArray = obj
										.getJSONArray("versions");
								for (int j = 0; j < versionArray.length(); j++) {

									JSONObject vobj = versionArray
											.getJSONObject(j);
									String inner_page_id = vobj
											.getString("page_id");
									String inner_page_description = vobj
											.getString("page_description");
									String language = vobj
											.getString("language");

									languageMap.put(language,
											inner_page_description);
								}
							}
							languageMap.put("English", page_data);

							String[] languages = null;
							if (obj.has("language")) {
								JSONArray versionArray = obj
										.getJSONArray("language");
								languages = new String[versionArray.length()];
								for (int j = 0; j < versionArray.length(); j++) {

									languages[j] = versionArray.getString(j);
								}
							}

							subcategory_list.add(new Section(section_name,
									page_data, languages, languageMap));
						}

						SubCategoryDetailsVersionsFragment versionsFragment = (SubCategoryDetailsVersionsFragment) getFragmentManager()
								.findFragmentById(R.id.names_fragment);

						if (versionsFragment != null) {
							versionsFragment.getNewData(subcategory_list);
						} else {

							versionsFragment = new SubCategoryDetailsVersionsFragment();
							// versionsFragment.setArguments(getIntent().getExtras());
							Bundle bundle = new Bundle();
							bundle.putParcelableArrayList("SubCatList",
									subcategory_list);
							versionsFragment.setArguments(bundle);
							getFragmentManager()
									.beginTransaction()
									.add(R.id.fragment_container,
											versionsFragment).commit();

						}

					} else if (status.equalsIgnoreCase("Failed")) {
						String msg = jobject.getString("msg");
						Toast.makeText(SubCategoryDetailsActivity.this, msg,
								Toast.LENGTH_SHORT).show();
						finish();
					} else {
						Toast.makeText(SubCategoryDetailsActivity.this,
								"Network Congestion", Toast.LENGTH_SHORT)
								.show();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void OnSelectionChanged(Section section) {
		SubCategoryDetailsDescriptionFragment descriptionFragment = (SubCategoryDetailsDescriptionFragment) getFragmentManager()
				.findFragmentById(R.id.description_fragment);

		if (descriptionFragment != null) {
			descriptionFragment.setSection(section);
			selectedradioposition = 0;
		} else {
			selectedradioposition = 0;
			SubCategoryDetailsDescriptionFragment newDesriptionFragment = new SubCategoryDetailsDescriptionFragment();
			Bundle args = new Bundle();

			args.putParcelable(
					SubCategoryDetailsDescriptionFragment.KEY_POSITION, section);
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
	public void SendLanguageData(Section section) {

		if (btn_language != null) {
			btn_language.setTag(section);
		}
	}
}
