package com.exceptionaire.denso.Model;

public class SubCategories {
	String v_sub_cat_id, sub_category;

	public SubCategories(String v_sub_cat_id, String sub_category) {
		super();
		this.v_sub_cat_id = v_sub_cat_id;
		this.sub_category = sub_category;
	}

	public String getV_sub_cat_id() {
		return v_sub_cat_id;
	}

	public void setV_sub_cat_id(String v_sub_cat_id) {
		this.v_sub_cat_id = v_sub_cat_id;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	
}
