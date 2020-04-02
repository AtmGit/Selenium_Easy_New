package com.macys.mst.mcy.datasetup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AppdataGenerationBean {

	private static List<String> ordernumber = new ArrayList<String>();
	private static Map<String, String> newOrderListBuyerCode = new LinkedHashMap<String, String>();
	private static Map<String, String> newOrderListOrderCategory = new LinkedHashMap<String, String>();
	private static Map<String, String> itemsLocnsInOrders = new LinkedHashMap<String, String>();
	private static Map<String, String> itemsResvLocnsInOrders = new LinkedHashMap<String, String>();
	public static Map<String, String> itemsIDLocnsInOrders = new LinkedHashMap<String, String>();
	public static String LocInOrders = null;

	private static String orderPrefix;
	private static String firstResvLocnId;
	private static String orderNbrItemName;
	private static String pickWaveNumber;
	private static String waveType;
	private static String firstItemName;
	private static List<String> workid;

	public static String getOrderPrefix() {
		return orderPrefix;
	}

	public static void setorderPrefix(String prefix) {
		orderPrefix = prefix;
	}

	public static List<String> getaggregateOrdernumbers() {
		return ordernumber;
	}

	public static void setaggregateOrdernumbers(List<String> ordernumberList) {
		ordernumber = ordernumberList;
	}

	public static void setOrderBuyerCode(Map<String, String> ordersBuyerCodeMap) {
		newOrderListBuyerCode = ordersBuyerCodeMap;
	}

	public static Map<String, String> getOrderWithBuyerCode() {
		return newOrderListBuyerCode;
	}

	public static void setOrderCategory(Map<String, String> ordersCategoryMap) {
		newOrderListOrderCategory = ordersCategoryMap;
	}

	public static Map<String, String> getOrderWithCategory() {
		return newOrderListOrderCategory;
	}

	public static void setItemNameLocn(Map<String, String> itemsInOrders) {
		itemsLocnsInOrders = itemsInOrders;
	}

	public static Map<String, String> getItemNameLocn() {
		return itemsLocnsInOrders;
	}

	public static void setItemNameResvLocn(Map<String, String> itemsInOrders) {
		itemsResvLocnsInOrders = itemsInOrders;
	}

	public static Map<String, String> getItemNameResvLocn() {
		return itemsResvLocnsInOrders;
	}

	public static String getresvLocnId() {
		return firstResvLocnId;
	}

	public static void setResvLocnId(String resvLocnId) {
		firstResvLocnId = resvLocnId;
	}

	public static void setorderNbrItem(String orderNbrItem) {
		orderNbrItemName = orderNbrItem;
	}

	public static String getorderNbrItem() {
		return orderNbrItemName;
	}

	public static String getPickWaveNumber() {
		return pickWaveNumber;
	}

	public static void setPickWaveNumber(String pickWaveNumbers) {
		pickWaveNumber = pickWaveNumbers;
	}

	public static void setwaveType(String waveTypes) {
		waveType = waveTypes;
	}

	public static String getWaveType() {
		return waveType;
	}

	public static String getFirstItemName() {
		return firstItemName;
	}

	public static void setFirstItemName(String itemName) {
		firstItemName = itemName;
	}

	public static Map getItemIdLocnID() {
		return itemsIDLocnsInOrders;
	}

	public static void setItemIDLocnID(Map itemsInOrders) {
		itemsIDLocnsInOrders = itemsInOrders;
	}

	public static void setWorkID(List<String> workId) {
		workid = workId;
	}

	public static List<String> getWorkID() {
		return workid;
	}

	public static String getLocnID() {
		return LocInOrders;
	}

	public static void setLocnID(String LocID) {
		LocInOrders = LocID;
	}

}
