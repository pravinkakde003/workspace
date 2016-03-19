package com.exceptionaire.denso.Model;

public class Category {
	String v_cat_id, category;

	public Category(String v_cat_id, String category) {
		super();
		this.v_cat_id = v_cat_id;
		this.category = category;
	}

	public String getV_cat_id() {
		return v_cat_id;
	}

	public void setV_cat_id(String v_cat_id) {
		this.v_cat_id = v_cat_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
