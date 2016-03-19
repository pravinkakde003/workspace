package com.exceptionaire.denso.Interface;

import com.exceptionaire.denso.Model.Section;

/**
 * Interface to send Section Page data nad languages to Description Fragment
 * 
 * @author PraWin_Android
 *
 */
public interface OnVersionNameSelectionChangeListener {

	/**
	 * Method for sending page description and language array
	 * 
	 * @param pagedata
	 */
	public void OnSelectionChanged(Section setion);
}