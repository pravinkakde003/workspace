package com.example.spinner;

public class Spinner_Item {
	
	String categoryID,categoryTitle;

	public Spinner_Item(String categoryID, String categoryTitle) {
		super();
		this.categoryID = categoryID;
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	
	
	

}
