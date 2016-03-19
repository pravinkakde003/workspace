package com.example.spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends Activity implements ActivityCallbackInterface {
	Spinner spinner1;
	List<Spinner_Item> categoryNames;
	SpinnerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		categoryNames = new ArrayList<Spinner_Item>();

		new CommonAsyncTask(MainActivity.this, (ActivityCallbackInterface) this)
				.execute("0", "http://api.androidhive.info/json/movies.json");

		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner_Item notes = categoryNames.get(position);
				Log.e("ID",""+notes.getCategoryID());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

	}

	@Override
	public void getResultBack(String result) {
		if (result != null) {
			try {

				JSONArray jsonArray = new JSONArray(result);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String title = jsonObject.getString("title");
					String id = "" + jsonObject.getInt("releaseYear");
					categoryNames.add(new Spinner_Item(id, title));
				}
				CategoryAdapter adapter = new CategoryAdapter(
						MainActivity.this, R.layout.spinner_item, categoryNames);

				spinner1.setAdapter(adapter);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
