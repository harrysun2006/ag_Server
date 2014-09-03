
package com.agloco.web.service.model;

import java.util.ArrayList;

/**
 * 
 * @author Erick Kong
 * @see VBReqList.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class VBResList
{
	private int result;
	
	private ArrayList items;

	public ArrayList getItems()
	{
		return items;
	}

	public void addItem(Object obj)
	{
		this.items.add(obj);
	}
	
	public Object get(int index) {
		return getItems().get(index);
	}

	/**
	 * @param items
	 */
	public VBResList()
	{
		this.items = new ArrayList();
	}
	
	public int size()
	{
		return getItems().size();
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

}

