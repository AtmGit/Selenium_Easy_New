package com.macys.mst.mcy.classes;

public class RowId {
	private static int currentId;
	
	public static int next()
	{
		currentId ++;
		return currentId;
	}
	
}
