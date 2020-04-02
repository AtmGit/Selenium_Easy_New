package com.macys.mst.mcy.classes;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;

import com.macys.mst.mcy.db.app.MmgDBHelper;
import com.macys.mst.mcy.sqlconstants.SQLConstants;
import com.macys.mst.mcy.utilities.Logging;


public class ClassVariableDetailsHolder {
	
	
	//public static LinkedHashMap<String, String> Parameters;
	public static LinkedHashMap<String, String> Parameters = new LinkedHashMap();
	
	//////////////////////////////////
	
	// constructor
	public ClassVariableDetailsHolder() {
		//Parameters = new LinkedHashMap();
		Parameters = new LinkedHashMap<String, String>();
	}
	
	
	// Public Methods


	public static void Reset() {
		Parameters = new LinkedHashMap<String, String>();
	}
	
	public static void AddParameter(String key, String value) {
        if(!Parameters.containsKey(key.toUpperCase())) {
        	Parameters.put(key.toUpperCase(), value.toUpperCase());

        }
	}
	
	public static void UpdateParameter(String key, String value) {
        if(Parameters.containsKey(key.toUpperCase())) {
        	Parameters.put(key.toUpperCase(), value.toUpperCase());

        }
	}
	
	public static void PrintParameters()
	{
		for( String key : Parameters.keySet() ){
			  System.out.println(key + " --> " + Parameters.get(key));
			}
	}	
	
}