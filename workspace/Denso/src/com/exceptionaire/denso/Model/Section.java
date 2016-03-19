package com.exceptionaire.denso.Model;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

public class Section implements Parcelable {
	private String section_name;
	private String pageData;
	private String[] language;
	private HashMap<String, String> data;

	public Section(String section_name, String pageData,String[] languages,HashMap<String, String> languagedata) {
		super();
		this.section_name = section_name;
		this.pageData = pageData;
		this.language = languages;
		this.data = languagedata;
	}

	public String getPageData() {
		return pageData;
	}

	public void setPageData(String pageData) {
		this.pageData = pageData;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	
	
	
	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	public String[] getLanguage() {
		return language;
	}

	public void setLanguage(String[] language) {
		this.language = language;
	}

	/**
	 * Storing the State_List_Inner_POJO data to Parcel object
	 **/
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(section_name);
		dest.writeString(pageData);
		
//		dest.writeMap(data);
		dest.writeInt(data.size());
		  for(Map.Entry<String, String> entry : data.entrySet()){
			  dest.writeString(entry.getKey());
			  dest.writeString(entry.getValue());
		  }
	}

	/**
	 * Retrieving State_List_Inner_POJO data from Parcel object This constructor
	 * is invoked by the method createFromParcel(Parcel source) of the object
	 * CREATOR
	 **/
	private Section(Parcel in) {
		this.section_name = in.readString();
		this.pageData = in.readString();
		
		int size = in.readInt();
		  for(int i = 0; i < size; i++){
		    String key = in.readString();
		    String value = in.readString();
		    this.data.put(key,value);
		  }
	}

	public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {

		@Override
		public Section createFromParcel(Parcel source) {
			return new Section(source);
		}

		@Override
		public Section[] newArray(int size) {
			return new Section[size];
		}
	};

}
