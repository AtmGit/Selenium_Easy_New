package com.macys.mst.mcy.datasetup;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
//import org.jbehave.core.annotations.Aliases;
//import org.jbehave.core.annotations.Given;
//import org.jbehave.core.annotations.Named;
//import org.jbehave.core.annotations.Then;
import org.junit.Assert;

//import com.macys.wms.DataGeneration.DataGenerationValues;
import com.macys.mst.artemis.config.FileConfig;
import com.macys.mst.artemis.db.DBUtils;
//import com.macys.wms.db.TalosPickingSqls;
/*import com.macys.wms.db.dao.OrderDao;
 import com.macys.wms.db.dao.WorkHdrDao;*/
//import com.macys.wms.jbehave.reports.StepDetail;
//import com.macys.wms.systests.WMSASSteps;
import com.macys.mst.artemis.utils.Utils;
import com.macys.mst.mcy.datasetup.HttpOrderSimulator;
import com.macys.mst.mcy.datasetup.SoapCall;
import com.macys.mst.mcy.db.app.DBMethods;
import com.macys.mst.mcy.db.app.*;
import com.macys.mst.mcy.sqlconstants.Constants;

public class OrderWaveWorkCreation {
	/** The logger. */
	static Logger logger = Logger.getLogger(OrderWaveWorkCreation.class
			.getName());
	static String wmsEnv = FileConfig.getInstance().getStringConfigValue(
			"was.env.wms");
	public static String userName = FileConfig.getInstance()
			.getStringConfigValue("was.env.username");
	static String password = FileConfig.getInstance().getStringConfigValue(
			"was.env.password");
	static String orderPrefixManual = FileConfig.getInstance()
			.getStringConfigValue("testData.orderPrefix");
	static String facilityAliasId = FileConfig.getInstance()
			.getStringConfigValue("testData.facilityAliasId");
	static String prodStatInInv = FileConfig.getInstance()
			.getStringConfigValue("testData.prodStatInInv");
	long waitTime = Long.parseLong(FileConfig.getInstance()
			.getStringConfigValue("db.wmsuser.session"));
	public static String firstItemName;
	public static String firstResvLocnId;
	String orderNbrItemName = null;

	/* New order parameters */
	String noOfOrders;
	String prodLines;
	String orderNeedQty;
	String onHandQty;
	String orderCategory;
	String palletsBridged;
	String resvQty;
	String deliveryDateTime;
	String buyerCode;
	String pickupStartDttm;
	String pickupStartDays;
	String pickupEndDays;
	String pickupEndDttm;
	String orderType;
	String noteCode;
	String sameItem;
	String useExistingData;
	String activeLocnArea;
	String resLocnArea;
	String env;

	String unit[] = null;
	String noInInven[] = null;
	String noInReserve = null;
	String noOfLpn[] = null;
	String prodline[] = null;
	String prodStatsInInv[] = null;
	String maxallocation = null;
	public static String orderPrefix;
	public static List<String> newOrderListUpdated = new ArrayList<String>();
	static List<String> ordernumber = new ArrayList<String>();
	public static Map<String, String> newOrderListBuyerCode = new LinkedHashMap<String, String>();
	public static Map<String, String> newOrderListOrderCategory = new LinkedHashMap<String, String>();
	public static Map<String, String> itemsLocnsInOrders = new LinkedHashMap<String, String>();
	public static Map<String, String> itemsResvLocnsInOrders = new LinkedHashMap<String, String>();
	public static Map<String, String> itemsIDLocnsInOrders = new LinkedHashMap<String, String>();

	/* Wave parameters */
	String pickWaveNumber = null;
	String waveType = null;
	int row;
	public static String itemID;

	public static String getItemID() {
		return itemID;
	}

	public static void setItemID(String itemID) {
		OrderWaveWorkCreation.itemID = itemID;
	}

	private static String location_id = null;

	public static String getLocation_id() {
		return location_id;
	}

	public static void setLocation_id(String location_id) {
		OrderWaveWorkCreation.location_id = location_id;
	}

	// DataGenerationValues DataGenerationValues=new DataGenerationValues();

	// @Given("print statement $value")
	public void printStatement(String orderParameters, String value) {
		logger.info("orderParameters: " + orderParameters);
		logger.info("print statement: " + value);

	}

	public void setUpDataAndPostOrder(Map<String, Object> inputMap) {

		if (inputMap.containsKey("DT_NO_ORDERS")) {
			noOfOrders = inputMap.get("DT_NO_ORDERS").toString();
			logger.info(noOfOrders);
		}
		if (inputMap.containsKey("DT_PROD_LINES")) {
			prodLines = inputMap.get("DT_PROD_LINES").toString();
			logger.info(prodLines);

		}
		if (inputMap.containsKey("DT_ORDER_NEED_QTY")) {
			orderNeedQty = inputMap.get("DT_ORDER_NEED_QTY").toString();
			logger.info(orderNeedQty);

		}
		if (inputMap.containsKey("DT_ON_HAND_QTY")) {
			onHandQty = inputMap.get("DT_ON_HAND_QTY").toString();
			logger.info(onHandQty);

		}
		if (inputMap.containsKey("orderCategory")) {
			orderCategory = inputMap.get("orderCategory").toString();

		}
		if (inputMap.containsKey("palletsBridged")) {
			palletsBridged = inputMap.get("palletsBridged").toString();

		}
		if (inputMap.containsKey("resvQty")) {
			resvQty = inputMap.get("resvQty").toString();
			logger.info("resvQty: " + resvQty);

		}
		if (inputMap.containsKey("deliveryDateTime")) {
			deliveryDateTime = inputMap.get("deliveryDateTime").toString();
			logger.info("deliveryDateTime: " + deliveryDateTime);

		}
		if (inputMap.containsKey("buyerCode")) {
			buyerCode = inputMap.get("buyerCode").toString();
			logger.info("buyerCode: " + buyerCode);

		}
		if (inputMap.containsKey("orderType")) {
			orderType = inputMap.get("orderType").toString();
			logger.info("orderType: " + orderType);

		}
		if (inputMap.containsKey("DT_NOTE_CODE")) {
			noteCode = inputMap.get("DT_NOTE_CODE").toString();
			logger.info("noteCode: " + noteCode);

		}
		if (inputMap.containsKey("buyerCode")) {
			buyerCode = inputMap.get("buyerCode").toString();
			logger.info("buyerCode: " + buyerCode);

		}
		if (inputMap.containsKey("pickupStartDays")) {
			pickupStartDays = inputMap.get("pickupStartDays").toString();

			pickupStartDttm = dateStringInFutureByDaysCount(Integer
					.valueOf(pickupStartDays));
			logger.info("pickupStartDttm: " + pickupStartDttm);
		} else {

			pickupStartDttm = null;
		}

		if (inputMap.containsKey("pickupEndDays")) {
			pickupEndDays = inputMap.get("pickupEndDays").toString();
			logger.info("pickupEndDays: " + pickupEndDays);

			pickupEndDttm = dateStringInFutureByDaysCount(Integer
					.valueOf(pickupEndDays));
			logger.info("pickupEndDttm: " + pickupEndDttm);
		} else {
			pickupEndDttm = null;
		}
		if (inputMap.containsKey("useExistingData")) {
			useExistingData = inputMap.get("useExistingData").toString();
			logger.info("useExistingData: " + useExistingData);

		}
		if (inputMap.containsKey("activeLocnArea")) {
			activeLocnArea = inputMap.get("activeLocnArea").toString();
			logger.info("activeLocnArea: " + activeLocnArea);

		}
		if (inputMap.containsKey("resLocnArea")) {
			resLocnArea = inputMap.get("resLocnArea").toString();
			logger.info("resLocnArea: " + resLocnArea);

		}
		if (inputMap.containsKey("sameItem")) {
			sameItem = inputMap.get("sameItem").toString();

		}
		if (inputMap.containsKey("DT_ENV")) {
			env = inputMap.get("DT_ENV").toString();

		}
		if (inputMap.containsKey("maxAllocation")) {
			maxallocation = inputMap.get("maxAllocation").toString();

		}

		List<String> newOrderList = new ArrayList<String>();

		orderPrefix = orderPrefixManual;

		if (orderPrefix.isEmpty()) {
			orderPrefix = String.format("%s", RandomStringUtils
					.randomAlphanumeric(7).toUpperCase());
		}

		AppdataGenerationBean.setorderPrefix(orderPrefix);

		/* Get next alpha numeric order number which is new max tc order id */
		if (noOfOrders != null) {
			newOrderList = getNewOrderList(orderPrefix,
					Integer.valueOf(noOfOrders));
		} else {
			newOrderList = getNewOrderList(orderPrefix, 1);
		}

		setOrdernumber(newOrderList);

		newOrderListUpdated.addAll(newOrderList);
		logger.info("Test newOrderListUpdated : "
				+ newOrderListUpdated.toString());

		AppdataGenerationBean.setaggregateOrdernumbers(newOrderListUpdated);

		if (prodLines != null && prodLines.contains(",")) {
			prodline = prodLines.split(",");

		} else {
			prodline = new String[] { prodLines };
		}

		if (orderNeedQty != null && orderNeedQty.contains(",")) {
			unit = orderNeedQty.split(",");

		} else {
			unit = new String[] { orderNeedQty };
		}

		if (onHandQty != null && onHandQty.contains(",")) {
			noInInven = onHandQty.split(",");

		} else {
			noInInven = new String[] { onHandQty };
		}

		setPrevItem(sameItem);
		setbuyerCode(buyerCode);
		setorderType(orderType);
		setnoteCode(noteCode);
		setProdline(prodline);
		setUnit(unit);
		setNoInInven(noInInven);
		setNoInReserve(resvQty);
		setProdStatsInInv(prodStatsInInv);

		try {
			xmlOrderCreation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (env != null) {
			updateOrdersInStress(orderPrefix);
		}

	}

	public void xmlOrderCreation() throws SQLException {

		List<String> ordersList = null;
		int line_Item_No = 1;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YY hh:mm");
		DateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date now = new Date();
		Date then;
		if (deliveryDateTime != null
				&& deliveryDateTime.equalsIgnoreCase("FUTURE")) {
			then = new Date(now.getTime() + (10 * 24 * 3600000));

		} else if (deliveryDateTime != null) {
			then = new Date(now.getTime() + (10 * 24 * 3600000));

		} else {
			then = new Date(now.getTime() + (5 * 24 * 3600000));
		}

		if (pickupStartDttm == null) {
			pickupStartDttm = dateStringInFutureByDaysCount(1);
		}

		if (pickupEndDttm == null) {
			pickupEndDttm = pickupStartDttm;

		}
		/*
		 * if(pickupStartDttm!=null &&
		 * pickupStartDttm.equalsIgnoreCase("FUTURE")){ now =new
		 * Date(now.getTime() + (4*24*3600000));
		 * 
		 * }else if(pickupStartDttm!=null){ now =new Date(now.getTime() +
		 * (4*24*3600000));
		 * 
		 * }else { logger.info("TEST pickupstartDttm : "+pickupStartDttm); now
		 * =new Date(now.getTime() - (10*24*3600000));
		 * logger.info("TEST pickupstartDttm : "+now); }
		 */
		ordersList = getOrdernumber();
		logger.info("ordersList: " + ordersList);

		Iterator<String> orderIterator = ordersList.iterator();

		int noOfOrdersPosted = 0;

		while (orderIterator.hasNext()) {

			noOfOrdersPosted = noOfOrdersPosted + 1;

			StringBuffer xmlHeaderString = new StringBuffer();
			xmlHeaderString.append(Constants.OrderXmlStrings.xmlHeaderString);
			// xmlHeaderString.append(Constants.OrderXmlStrings.xmlOrderString);

			StringBuffer xmlOrderEndString = new StringBuffer();
			xmlOrderEndString = xmlOrderEndString
					.append("</DistributionOrder>");

			StringBuffer xmlEndString = new StringBuffer();
			xmlEndString = xmlEndString.append("</Message></tXML>");

			StringBuffer xmlOrderCollectionString = new StringBuffer();
			StringBuffer xml = new StringBuffer();
			StringBuffer xmlItemString = new StringBuffer();
			StringBuffer itemsInOrder = new StringBuffer();
			String orderNumber = orderIterator.next() + "";
			StringBuffer xmlOrderString = new StringBuffer();

			logger.info("the TC order ID is : " + orderNumber);

			if (orderType == null) {
				orderType = "CO";
			}
			if (noteCode == null) {
				noteCode = "NB";
			}
			if (buyerCode == null) {

				buyerCode = "KD";
			}
			if (orderCategory == null) {

				orderCategory = "NB";
			}
			if (palletsBridged == null) {

				palletsBridged = "1000";
			}

			newOrderListBuyerCode.put(orderNumber, buyerCode);
			AppdataGenerationBean.setOrderBuyerCode(newOrderListBuyerCode);

			newOrderListOrderCategory.put(orderNumber, orderCategory);
			AppdataGenerationBean.setOrderCategory(newOrderListOrderCategory);

			try {

				String xmlOrderStringConstant = Constants.OrderXmlStrings.xmlOrderString
						.replace("${orderNumber}", orderNumber);
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${dateFormatNow}", dateFormat.format(now));
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${palletsBridged}", palletsBridged);
				// xmlOrderStringConstant =
				// xmlOrderStringConstant.replace("${dateFormatThen}",dateFormat.format(then));
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${orderType}", orderType);
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${noteCode}", noteCode);
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${buyerCode}", buyerCode);
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${facilityAliasId}", facilityAliasId);
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${pickupStartDttm}", dateFormat
								.format((Date) inputDateFormat
										.parse(pickupStartDttm)));
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${pickupEndDttm}", dateFormat
								.format((Date) inputDateFormat
										.parse(pickupEndDttm)));
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${delStartDttm}", dateFormat
								.format((Date) inputDateFormat
										.parse(pickupStartDttm)));
				xmlOrderStringConstant = xmlOrderStringConstant.replace(
						"${delEndDttm}", dateFormat.format(then));

				if (orderCategory != null
						&& orderCategory.equalsIgnoreCase("SNG")) {

					xmlOrderStringConstant = xmlOrderStringConstant
							+ "<OriginalBudgetedCost>1</OriginalBudgetedCost>";

				} else {

					String orderNeed[] = getUnit();
					int totalOrderNeedQty = 0;
					for (String need : orderNeed) {
						totalOrderNeedQty = totalOrderNeedQty
								+ Integer.valueOf(need);
					}

					logger.info("TESAT orderNeedQty " + orderNeedQty);

					xmlOrderStringConstant = xmlOrderStringConstant
							+ "<OriginalBudgetedCost>" + totalOrderNeedQty
							+ "</OriginalBudgetedCost>";

				}

				xmlOrderString.append(xmlOrderStringConstant);

			} catch (Exception e) {

				logger.info("Exception in building xml .. " + e.getMessage());

			}

			/*
			 * XML Item String formation : Find an Item, Allocate Item, Create
			 * LPN
			 */

			String prodLine[] = getProdline();
			String units[] = getUnit();
			String noInInven[] = getNoInInven();
			String noInReserve = getNoInReserve();
			String activeLocnArea = getActiveLocnArea();
			String actLocations[] = null;
			if (activeLocnArea != null && activeLocnArea.contains(",")) {
				actLocations = activeLocnArea.split(",");

			} else {
				actLocations = new String[] { activeLocnArea };
			}

			String resvLocnArea = getResvLocnArea();
			String resvLocations[] = null;
			if (resvLocnArea != null && resvLocnArea.contains(",")) {
				resvLocations = resvLocnArea.split(",");

			} else {
				resvLocations = new String[] { resvLocnArea };
			}

			String qtyInresv[] = null;
			if (noInReserve != null && noInReserve.contains(",")) {
				qtyInresv = noInReserve.split(",");

			} else {
				qtyInresv = new String[] { noInReserve };
			}

			int sizeOfArray = prodLine.length;

			for (int i = 0; i <= sizeOfArray - 1; i++) {

				String prod = prodLine[i];
				String prodStat = prodStatInInv;
				String noInInvent = noInInven[i];
				if (noInReserve != null) {
					noInReserve = qtyInresv[i];
					resvLocnArea = resvLocations[i];
				}
				if (activeLocnArea != null) {
					activeLocnArea = actLocations[i];

				}
				String itemName = "";
				String resLocnId = "";
				if (sameItem == null
						|| (sameItem.equalsIgnoreCase("Y") && noOfOrdersPosted == 1)) {
					if (useExistingData != null
							&& useExistingData.equalsIgnoreCase("Y")) {

						logger.info("Getting item from wm_inventory ..");
						itemName = getItemWithInventory("", prod, prodStat,
								noInInvent);
						logger.info("Getting item  .. " + itemName);
						AppdataGenerationBean.setFirstItemName(itemName);
					} else {

						itemName = assignItemWithInventory("", prod, prodStat,
								noInInvent, activeLocnArea);
						AppdataGenerationBean.setFirstItemName(itemName);

					}
				} else if (sameItem.equalsIgnoreCase("Y")
						&& noOfOrdersPosted > 1) {
					if (useExistingData != null
							&& useExistingData.equalsIgnoreCase("Y")) {

						logger.info("Getting item from wm_inventory ..");
						itemName = getItemWithInventory("", prod, prodStat,
								noInInvent);
						logger.info("Getting item  .. " + itemName);
						AppdataGenerationBean.setFirstItemName(itemName);

					} else {
						itemName = AppdataGenerationBean.getFirstItemName();
						itemName = assignItemWithInventory(itemName, prod,
								prodStat, "0", activeLocnArea);

					}

				} else if (sameItem.equalsIgnoreCase("N")) {
					if (useExistingData != null
							&& useExistingData.equalsIgnoreCase("Y")) {

						logger.info("Getting item from wm_inventory ..");
						itemName = getItemWithInventory("", prod, prodStat,
								noInInvent);
						logger.info("Getting item  .. " + itemName);
						AppdataGenerationBean.setFirstItemName(itemName);
					} else {

						itemName = assignItemWithInventory("", prod, prodStat,
								noInInvent, activeLocnArea);
						AppdataGenerationBean.setFirstItemName(itemName);

					}

				}
				/* Create LPNs and adjust reserve location inventory */

				if (noInReserve != null
						&& (prod.contains("CSR") || prod.contains("BTR") || prod
								.contains("PLR"))) {
					if (useExistingData != null
							&& useExistingData.equalsIgnoreCase("Y")) {
						// String resvLocnArea= getResvLocnArea();
						resLocnId = createLPNS(prod, itemName, resvLocnArea,
								String.valueOf(noInReserve));
						AppdataGenerationBean.setResvLocnId(resLocnId);
					} else {

						logger.info("noInReserve: " + noInReserve);
						logger.info("resvLocnArea: " + resvLocnArea);

						resLocnId = createLPNS(prod, itemName, resvLocnArea,
								String.valueOf(noInReserve));
						AppdataGenerationBean.setResvLocnId(resLocnId);

					}
				}
				String xmlItemOrderString = Constants.OrderXmlStrings.xmlItemOrderString
						.replace("${lineItemNo}", String.valueOf(line_Item_No));
				xmlItemOrderString = xmlItemOrderString.replace("${itemName}",
						itemName);
				xmlItemOrderString = xmlItemOrderString.replace("${orderQty}",
						units[i]);
				xmlItemOrderString = xmlItemOrderString.replace("${prodStat}",
						prodStat);
				xmlItemString = xmlItemString.append(xmlItemOrderString);
				line_Item_No = line_Item_No + 1;

				itemsInOrder.append(itemName + " , ");

			}

			AppdataGenerationBean.setorderNbrItem("orderNumber: " + orderNumber
					+ ", itemName: " + itemsInOrder.toString());

			xmlOrderCollectionString = null;
			xmlOrderCollectionString = xmlOrderString.append(xmlItemString)
					.append(xmlOrderEndString);
			xml = null;
			xml = (xmlHeaderString).append(xmlOrderCollectionString).append(
					xmlEndString);
			logger.info("xml: " + xml.toString());
			OrderCreation(xml.toString());

		}

	}

	// @Then("get orders with buyer code")
	public void getOrdersWithBuyerCode() {
		logger.info(AppdataGenerationBean.getOrderWithCategory());

	}

	// @Given("wave is created in wm")
	public void runWaveInWm(Map<String, Object> inputMap) {

		/*
		 * Map<String, String> inputMap =
		 * Splitter.on("&").trimResults().withKeyValueSeparator
		 * (":").split(waveParameters); System.out.println(inputMap);
		 */

		String waveRule = null;
		String waveType = null;

		if (inputMap.containsKey("DT_WAVE_RULE")) {
			waveRule = inputMap.get("DT_WAVE_RULE").toString();
		}
		if (inputMap.containsKey("DT_WAVE_TYPE")) {
			waveType = inputMap.get("DT_WAVE_TYPE").toString();
		}
		String prefix = AppdataGenerationBean.getOrderPrefix();
		if (prefix == null) {
			// prefix=WMSASSteps.getOrderPrefix();
		}
		/* Check if wave rule exists */
		String waveRuleParmIdQuery = Constants.Select.WAVE_RULE_PARM_ID
				.replace("${waveDesc}", "'" + waveType + "'");
		waveRuleParmIdQuery = waveRuleParmIdQuery.replace("${ruleName}", "'"
				+ waveRule + "'");
		try {
			String waveRuleParmId = DBMethods
					.getDBValueInString(waveRuleParmIdQuery);
			if (waveRuleParmId == null) {
				/* Insert Rule */
				DBMethods.insertWaveRule(waveRule, waveType, prefix);
			} else {
				/* get order prefix .. update the order prefix */
				String getOrderPrefixQry = Constants.Select.RULE_CMPAR_VALUE
						.replace("${waveRuleParmId}", waveRuleParmId);
				String dbOrderPrefix = DBMethods
						.getDBValueInString(getOrderPrefixQry);
				if (!dbOrderPrefix.equalsIgnoreCase(prefix)) {
					logger.info("Changing prefix ... ");
					String updateOrderPrefixQry = Constants.Update.UPDATE_RULE_COMP_VALUE
							.replace("${waveRuleParmId}", waveRuleParmId);
					updateOrderPrefixQry = updateOrderPrefixQry.replace(
							"${ruleCompValue}", "'" + prefix + "%'");
					logger.info("TEST updateOrderPrefixQry : "
							+ updateOrderPrefixQry);
					AppDBMethods
							.deleteOrUpdateWMSDataBase(updateOrderPrefixQry);

				}
				String updateOperCode = Constants.Update.UPDATE_RULE_OPER_CODE
						.replace("${waveRuleParmId}", waveRuleParmId);
				AppDBMethods.deleteOrUpdateWMSDataBase(updateOperCode);
				logger.info("wave rule " + waveRule
						+ " exists for the wave type " + waveType
						+ " with wave rule parm id " + waveRuleParmId);
			}

		} catch (Exception e) {
			logger.info("Exception in  rules " + e.getMessage());

		}

		/* Make All rules for this wave type inactive */
		String updateWaveRulesQry = Constants.Update.UPDATE_WAVE_RULES.replace(
				"${wavedesc}", "'" + waveType + "'");
		AppDBMethods.deleteOrUpdateWMSDataBase(updateWaveRulesQry);

		/* Make Selected Rule active */
		String waveRuleParmId = DBMethods
				.getDBValueInString(waveRuleParmIdQuery);
		String updateSelWaveRule = Constants.Update.UPDATE_WAVE_SEL_RULE
				.replace("${waveRuleParmId}", waveRuleParmId);
		AppDBMethods.deleteOrUpdateWMSDataBase(updateSelWaveRule);

		/* Run a WAVE ................. */
		String wave_number = null;
		long startTime;
		long currentTime;
		long timeDiff;
		boolean flag = false;

		startTime = System.currentTimeMillis();
		try {
			String wave = SoapCall.runWave(wmsEnv, waveType).toString();

			String query = "select SHIP_WAVE_NBR from ORDER_LINE_ITEM where ORDER_ID in "
					+ " (select ORDER_ID from orders where tc_order_id like '"
					+ prefix + "%')";
			logger.info("the query is " + query);
			logger.info("Waiting for the wave to complete ....");
			startTime = System.currentTimeMillis();
			do {
				wave_number = getWaveFromDB(query);
				currentTime = System.currentTimeMillis();
				timeDiff = currentTime - startTime;

			} while (wave_number == null && timeDiff <= waitTime);

			if (wave_number == null) {
				flag = false;
			} else {
				flag = true;
				logger.info("the wave number is " + wave_number);
				// StepDetail.addDetail("WAVE NBR : "+wave_number, true);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			flag = false;
		}
		// setPickWaveNumber(wave_number);
		// setwaveType(waveType);
		AppdataGenerationBean.setPickWaveNumber(wave_number);
		AppdataGenerationBean.setwaveType(waveType);
		if (wave_number != null) {
			startTime = System.currentTimeMillis();
			String statc = null;
			do {

				String query = "select stat_code from alloc_invn_dtl where task_genrtn_ref_nbr='"
						+ wave_number + "001" + "'";
				statc = getWaveFromDB(query);
				currentTime = System.currentTimeMillis();
				timeDiff = currentTime - startTime;
			} while (statc == null && timeDiff <= waitTime);

		}

		if (flag) {

			row = row + 1;
		} else {
			row = row + 1;
		}
		assertTrue(flag);

	}

	public void releaseWaveAndCreateWork(Connection con, String typeofWave) {

		String waveNumber = AppdataGenerationBean.getPickWaveNumber();
		logger.info("waveNumber: " + waveNumber);
		// waveNumber = "201602031237";
		List<String> ordersCreated = new ArrayList<String>();
		ordersCreated = getOrdernumber();
		if (ordersCreated == null || ordersCreated.isEmpty()) {
			logger.info("Created order using new data generation ...");
			ordersCreated = AppdataGenerationBean.getaggregateOrdernumbers();
		}

		int ship_wave_parm_id = AppDBMethods.getship_wave_parm_id(waveNumber);
		String whse = AppDBMethods.getwhse();

		String miscFlag = "";
		String codeId = "";
		String chute_assign_type = "";

		if (typeofWave.equalsIgnoreCase("08 - MSL Home Ship Wave")) {
			miscFlag = "4 MSL HomC";
			codeId = "HOM 10";
			chute_assign_type = "HOM";

		} else if (typeofWave
				.equalsIgnoreCase("01 - Pick Wave = Pack Wave (Beumer)")) {
			miscFlag = "1 All Area";
			codeId = "NST 10";
			chute_assign_type = "NST";
		} else if (typeofWave
				.equalsIgnoreCase("12 - SIngles by Pick to Tote Process")) {
			miscFlag = "3 Singles";
			codeId = "SNG 01";
			chute_assign_type = "SNG";
		}

		logger.info("ordersCreated: " + ordersCreated + "userName: " + userName
				+ " facility id: " + 1 + ", ship_wave_parm_id: "
				+ ship_wave_parm_id + " ,wave_nbr: " + waveNumber
				+ ", codeId: " + codeId + " ,whse: " + whse
				+ " ,chute_assign_type: " + chute_assign_type + " ,miscFlag: "
				+ miscFlag);
		if (wmsEnv.equalsIgnoreCase("tst-app05-wms")) {
			codeId = "NST 14";
			chute_assign_type = "NST";
			miscFlag = "1 All Area";

		}

		AppDBMethods.releaseWaveProcedure(userName, 1, ship_wave_parm_id,
				waveNumber, codeId, whse, chute_assign_type, miscFlag);
		logger.info("pack wave number : " + waveNumber + "001");
		AppDBMethods.releaseWorkProcedure(waveNumber, userName);

		try {
			List<String> workId = getWorkIdUsingWaveNumber(con, waveNumber);
			logger.info("Work id:" + workId);
			AppdataGenerationBean.setWorkID(workId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Store Orders into database Y we need this ? Check and remove if
		 * needed
		 * 
		 * OrderUtils orderUtils=new OrderUtils();
		 * orderUtils.setOrderID(tc_order_id);
		 * orderUtils.setWaveNumber_1(waveNumber.equals("")?"NULL":waveNumber);
		 * orderUtils.setWaveNumber_2(waveNumber.equals("")?"NULL":waveNumber);
		 * orderUtils.setBeaumerWave(isBeaumerWave);
		 * TalosUtils.setWorkBatchNumber(waveNumber);
		 * orderUtils.setTestCaseName(TalosUtils.getTestcaseName());
		 * 
		 * OrderDao orderDao=new OrderDao(); try{
		 * orderDao.insertOrder(orderUtils); }catch(Exception e){ log.info(
		 * "Exception occured while inserting record in AUTOMATION_DATA table");
		 * }
		 * 
		 * 
		 * String dataCreationMode=configDataCreationMode; /*While creating
		 * data, scenarios will fail here if(dataCreationMode.contains("Y")){
		 * tc_order_id="@##"; waveNumber="@##"; waveNumber1="@##";
		 * setWaveNumber(waveNumber); setWaveNumber1(waveNumber1);
		 * TalosUtils.setWorkBatchNumber(waveNumber); Assert.assertTrue(false);
		 * }
		 */

	}

	/*
	 * public String getWorkID(){ WorkHdrDao workHdrDao = new WorkHdrDao();
	 * List<String> workIdList = new ArrayList<String>(); try {
	 * logger.info("Test waveNumber to get work : "+waveNumber); //waveNumber
	 * ="201602031237"; workIdList =
	 * workHdrDao.getWorkIdUsingWaveNumber(waveNumber);
	 * logger.info("Test indexWorkId : "+indexWorkId); if (indexWorkId == -1) {
	 * indexWorkId = workIdList.size(); indexWorkId--; } if (indexWorkId >= 0) {
	 * workId = workIdList.get(indexWorkId); indexWorkId--; }
	 * 
	 * logger.info("Test workId  : "+workId); }catch(Exception e){
	 * logger.info("Exception occured while getting work: "+e.getMessage());
	 * 
	 * } return workId; }
	 */
	public List<String> getWorkIdUsingWaveNumber(Connection con,
			String waveNumber) throws SQLException {

		if (!waveNumber.endsWith("001") || waveNumber.length() < 15) {
			waveNumber = waveNumber + "001";
		}
		List<String> workId = new ArrayList<String>();
		System.out.println("waveNumber: " + waveNumber);

		String sql = Constants.Select.GETWORKIDUSINGWAVENUMBER.replace(
				"#WAVENUMBER#", waveNumber);

		ResultSet rs = DBUtils.getresultset(con, sql);
		while (rs.next()) {

			workId.add(rs.getString("m_WORK_HDR_ID"));
		}

		// AppDBMethods.closeDBConnection();
		System.out.println("workId: " + workId);
		return workId;
	}

	public List<String> getNewOrderList(String orderPrefix, int Nooforders) {

		int prefixLength = orderPrefix.length();
		List<String> newOrderList = new ArrayList<String>();
		String maxOrderQuery = "select max (tc_order_id) from orders where tc_order_id like('"
				+ orderPrefix + "%')";
		String maxOrder = DBMethods.getDBValueInString(maxOrderQuery);

		if (maxOrder == null) {
			int suffix = 10 - prefixLength;
			StringBuffer newMaxOrder = new StringBuffer();
			newMaxOrder.append(orderPrefix);
			for (int i = 1; i < suffix + 1; i++) {
				newMaxOrder = newMaxOrder.append("0");
			}
			maxOrder = newMaxOrder.toString();

		}
		for (int i = 1; i <= Nooforders; i++) {
			String next_Order = Utils.getNextAlphaNumeric(maxOrder,
					prefixLength);
			newOrderList.add(next_Order);
			maxOrder = next_Order;
		}

		return newOrderList;
	}

	public String getItemWithInventory(String itemName, String prod,
			String prodStat, String noInInvent) {

		/* Find item in wm_inevtory */

		/* Item query with area */
		String area = null;
		if (prod.equalsIgnoreCase("CSR")) {
			area = "'D','E'";
		} else if (prod.equalsIgnoreCase("BTR")) {
			area = "'A'";
		} else if (prod.equalsIgnoreCase("HFG")) {
			area = "'H'";
		} else if (prod.equalsIgnoreCase("GOH")) {
			area = "'F'";
		} else if (prod.equalsIgnoreCase("SHO")) {
			area = "'C'";
		} else if (prod.equalsIgnoreCase("PLR")) {
			area = "'G'";
		}
		String findItemWithActiveLocnQry = "select ic.item_name from wm_inventory wm "
				+ " inner join item_cbo ic on wm.item_id=ic.item_id inner join locn_hdr lh on wm.location_id=lh.locn_id "
				+ "	where ic.item_id in (select item_id from item_wms) and wm.LOCN_CLASS = 'A' and lh.area in ( "
				+ area + ")";

		logger.info("findItemWithActiveLocnQry: " + findItemWithActiveLocnQry);

		itemName = DBMethods.getDBValueInString(findItemWithActiveLocnQry);
		logger.info("Getting item  .. " + itemName);
		Assert.assertTrue(itemName != null);
		String itemIdQuery = "select item_id from item_master where size_desc='"
				+ itemName + "'";
		String itemId = DBMethods.getDBValueInString(itemIdQuery);
		logger.info("Getting itemId  .. " + itemId);
		String zeroInventory = "update wm_inventory set on_hand_qty= '0' where locn_class= 'A' and item_id='"
				+ itemId + "' ";
		AppDBMethods.deleteOrUpdateWMSDataBase(zeroInventory);

		String queryInventory = "update wm_inventory set on_hand_qty='"
				+ noInInvent + "'where locn_class= 'A' and item_id='" + itemId
				+ "' ";
		AppDBMethods.deleteOrUpdateWMSDataBase(queryInventory);

		return itemName;

	}

	public String assignItemWithInventory(String itemName, String prod,
			String prodStat, String noInInvent, String activeArea) {
		logger.info("Finding right item ..");
		String locnQuery = null;
		String wrkGroupActive = null;
		String prodStatQry = null;
		String itemId = null;
		List<String> onHandQuantities = new ArrayList<String>();
		if (itemName.isEmpty()) {
			if (orderCategory != null && orderCategory.equalsIgnoreCase("VDR")) {
				String itemQuery = Constants.Select.singleVdrOrderItemQuery
						.replace("${prodPrefix}", prod.substring(0, 3));
				itemQuery = itemQuery.replace("${prodStat}", prodStat);
				itemQuery = itemQuery.replace("${sngVdr}", "Y");
				itemName = DBMethods.getDBValueInString(itemQuery);
				// logger.info("New Item Found : "+itemName);

			} else {
				String itemQuery = Constants.Select.orderItemQuery.replace(
						"${prodPrefix}", prod.substring(0, 3));
				itemQuery = itemQuery.replace("${prodStat}", prodStat);
				itemName = DBMethods.getDBValueInString(itemQuery);
				// logger.info("itemQuery "+itemQuery);
				logger.info("New Item Found : " + itemName);

			}
		}
		if (itemName == null) {
			Assert.assertTrue(false);
		}

		if (noInInvent.equals("0")) {
			logger.info("Not assigning to active location for this item ..... ");
			String itemIdQuery = "select item_id from item_master where size_desc='"
					+ itemName + "'";
			itemId = DBMethods.getDBValueInString(itemIdQuery);
			logger.info("Item_id is : " + itemId);
		} else if (noInInvent.contains("-")) {
			logger.info("noInInvent: " + noInInvent);
			onHandQuantities = Arrays.asList(noInInvent.split("-"));
		} else {
			onHandQuantities.add(noInInvent);

		}
		for (int i = 0; i < onHandQuantities.size(); i++) {
			String onHandQty = onHandQuantities.get(i);
			if (prod.contains("CSR")) {
				locnQuery = Constants.Select.activeLocationCSR;
				wrkGroupActive = "KAM";
				if (activeArea != null && activeArea.contains("CHE")) {
					locnQuery = Constants.Select.activeLocationCHESIRE;
				}
			} else if (prod.contains("GOH")) {
				locnQuery = Constants.Select.activeLocationGOH;
				wrkGroupActive = "KGH";
			} else if (prod.contains("BTR")) {
				locnQuery = Constants.Select.activeLocationBTR;
				wrkGroupActive = "KBTY";
				if (activeArea != null && activeArea.contains("CHE")) {
					locnQuery = Constants.Select.activeLocationCHESIRE;
				}
			} else if (prod.contains("HFG")) {
				locnQuery = Constants.Select.activeLocationHFG;
				wrkGroupActive = "KAM";
			} else if (prod.contains("PLR")) {
				locnQuery = Constants.Select.activeLocationPLR;
				wrkGroupActive = "KPLA";
			} else if (prod.contains("SHO")) {
				locnQuery = Constants.Select.activeLocationSHO;
			} else if (prod.contains("SEC")) {
				locnQuery = Constants.Select.activeLocationSEC;
				wrkGroupActive = "KSEC";
			} else if (prod.contains("HOM")) {
				locnQuery = Constants.Select.activeLocationHOM;
			}

			if (activeArea != null) {
				if (activeArea.length() > 4) {

					locnQuery = "select locn_id from locn_hdr where locn_brcd = '"
							+ activeArea + "'";

				} else if (activeArea.contains("CHE")) {
					activeArea = activeArea.substring(3);
					logger.info("activeArea: " + activeArea);

					locnQuery = locnQuery.replace("${wrkGroup}", "'"
							+ wrkGroupActive + "%'");
					locnQuery = locnQuery.replace("${area}", "'" + activeArea
							+ "%'");

				} else {
					locnQuery = locnQuery + " AND lhr.DSP_LOCN like '"
							+ activeArea + "%'";
					logger.info("locnQuery:  " + locnQuery);
				}

			}

			// logger.info("TEST locnQuery: "+locnQuery);
			String activeLocnID = DBMethods.getDBValueInString(locnQuery);
			Assert.assertTrue(activeLocnID != null);
			setLocation_id(activeLocnID);
			String itemIdQuery = "select item_id from item_master where size_desc='"
					+ itemName + "'";
			itemId = DBMethods.getDBValueInString(itemIdQuery);
			setItemID(itemId);

			// logger.info("TEST itemId: "+itemId);

			/* Item Allocation and adjust Inventory at active location */
			prodStatQry = "select MISC_ALPHA_3 from ITEM_FACILITY_MAPPING_WMS where ITEM_ID = '"
					+ itemId + "'";
			prodStat = DBMethods.getDBValueInString(prodStatQry);
			itemAllocation(activeLocnID, itemId, prodStat, "", "");

			String updateQuery1 = "update pick_locn_dtl  set trig_repl_for_sku='Y' where locn_id='"
					+ activeLocnID + "'";
			String updateQuery2 = "update pick_locn_hdr set REPL_FLAG='Y' where locn_id ='"
					+ activeLocnID + "'";

			if (maxallocation != null) {
				String updateQuery3 = "update pick_locn_dtl set max_invn_qty='"
						+ maxallocation + "' where locn_id='" + activeLocnID
						+ "'";
				AppDBMethods.deleteOrUpdateWMSDataBase(updateQuery3);
			}

			AppDBMethods.deleteOrUpdateWMSDataBase(updateQuery1);
			AppDBMethods.deleteOrUpdateWMSDataBase(updateQuery2);
			String queryInventory = "update wm_inventory set on_hand_qty='"
					+ onHandQty + "'where locn_class= 'A' and item_id='"
					+ itemId + "' and location_id='" + activeLocnID + "'";
			AppDBMethods.deleteOrUpdateWMSDataBase(queryInventory);

			itemsLocnsInOrders.put(itemName, activeLocnID);
			// setItemNames(itemsLocnsInOrders);
			AppdataGenerationBean.setItemNameLocn(itemsLocnsInOrders);
			itemsIDLocnsInOrders.put(itemId, activeLocnID);
			setItemNames(itemsLocnsInOrders);

			AppdataGenerationBean.setItemIDLocnID(itemsIDLocnsInOrders);
			AppdataGenerationBean.setLocnID(activeLocnID);
			logger.info("itemName : -- " + itemName + " Item id : -- " + itemId
					+ " activeLocnID: " + activeLocnID
					+ " with prod Stats In Inv:---" + prodStat);

		}
		// logger.info("itemName : -- "+itemName+" Item id : -- "+itemId+" activeLocnID: "+activeLocnID+" with prod Stats In Inv:---"+prodStat);

		return itemName;
	}

	public String createLPNS(String prod, String itemName, String reserveArea,
			String reserveQty) throws SQLException {
		List<String> resvAreas = new ArrayList<String>();
		logger.info("reserveQty: " + reserveQty);
		String resLocnQuery = null;
		String resLocnId = null;
		String resvArea = null;
		String itemIdQuery = "select item_id from item_master where size_desc='"
				+ itemName + "'";
		String itemId = DBMethods.getDBValueInString(itemIdQuery);

		List<String> noOfResvLocns = Arrays.asList(reserveQty.split(";"));

		for (int i = 0; i < noOfResvLocns.size(); i++) {
			reserveQty = noOfResvLocns.get(i);
			if (reserveArea != null) {
				resvAreas = Arrays.asList(reserveArea.split(";"));
				logger.info(resvAreas);
				resvArea = resvAreas.get(i);
				logger.info(resvArea);
			}

			if (prod.contains("CSR")) {
				resLocnQuery = Constants.Select.reserveLocationCSR;
			} else if (prod.contains("BTR")) {
				resLocnQuery = Constants.Select.reserveLocationBTR;
				// resLocnQuery= Constants.Select.reserveLocationCHESIRE;
			} else if (prod.contains("PLR")) {
				resLocnQuery = Constants.Select.reserveLocationPLR;
			}

			if (resvArea != null) {
				if (resvArea.contains("CHE")) {
					resvArea = resvArea.substring(3);
					logger.info("resvArea: " + resvArea);
					resLocnQuery = resLocnQuery + " and locn_brcd like '"
							+ resvArea + "%'";

				} else {
					resLocnQuery = resLocnQuery + " AND lhr.locn_brcd like '"
							+ resvArea + "%'";
					// logger.info("resLocnQuery:  "+resLocnQuery);
				}
			}
			logger.info("TEST resLocnQuery: " + resLocnQuery);
			resLocnId = DBMethods.getDBValueInString(resLocnQuery);

			logger.info("test resLocnId : " + resLocnId);
			Assert.assertTrue(resLocnId != null);

			int item_id = Integer.parseInt(itemId);

			logger.info("reserveQty " + reserveQty);
			if (reserveQty.contains("-")) {

				logger.info("I am in multiple LPNs ....");
				List<String> lpnQuantities = Arrays.asList(reserveQty
						.split("-"));

				for (int j = 0; j < lpnQuantities.size(); j++) {

					String qtyInLpn = lpnQuantities.get(j);

					logger.info("qtyInLpn " + qtyInLpn);
					logger.info("resLocnId " + resLocnId);

					AppDBMethods
							.deleteOrUpdateWMSDataBase("delete from M_PE_DATAPREP_ITEMS");

					String insertQuery = "insert into M_PE_DATAPREP_ITEMS (item_id,qty,no_of_lpns,locn_id) values ('"
							+ item_id
							+ "','"
							+ qtyInLpn
							+ "','1','"
							+ resLocnId + "')";
					AppDBMethods.deleteOrUpdateWMSDataBase(insertQuery);

					AppDBMethods.dataGenetaionStoredProcedure("0099");

					String updateResvLocnHdr = "update RESV_LOCN_HDR set curr_uom_qty= "
							+ (i + 1) + " where LOCN_ID = " + resLocnId + "";
					AppDBMethods.deleteOrUpdateWMSDataBase(updateResvLocnHdr);
				}

			} else {

				AppDBMethods
						.deleteOrUpdateWMSDataBase("delete from M_PE_DATAPREP_ITEMS");
				String insertQuery = "insert into M_PE_DATAPREP_ITEMS (item_id,qty,no_of_lpns,locn_id) values ('"
						+ item_id
						+ "','"
						+ reserveQty
						+ "','1','"
						+ resLocnId
						+ "')";
				AppDBMethods.deleteOrUpdateWMSDataBase(insertQuery);
				AppDBMethods.dataGenetaionStoredProcedure("0099");

				String updateResvLocnHdr = "update RESV_LOCN_HDR set curr_uom_qty= 1 where LOCN_ID = "
						+ resLocnId + "";
				AppDBMethods.deleteOrUpdateWMSDataBase(updateResvLocnHdr);
			}
			itemsResvLocnsInOrders.put(itemName, resLocnId);
			AppdataGenerationBean.setItemNameResvLocn(itemsResvLocnsInOrders);
			logger.info("Test created LPNS with resv location :" + resLocnId);

			// StepDetail.addDetail("itemName: "+itemName+", resLocnId: "+resLocnId,true);
		}
		return resLocnId;
	}

	public void OrderCreation(String xml) {
		try {
			// logger.info("xml "+xml);

			boolean flag = false;
			flag = HttpOrderSimulator.postOrderXML(xml, wmsEnv
					+ ".federated.fds:10500/");
			assertTrue(flag);
			// StepDetail.addDetail(DataGenerationValues.getorderNbrItem(),
			// true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Given("orders posted in AS by updating order do_type")
	public void updateOrdersInStress(String prefix) {
		// String prefix = getOrderPrefix();
		logger.info("prefix: " + prefix);
		if (prefix == null || prefix.isEmpty()) {

			prefix = AppdataGenerationBean.getOrderPrefix();
			logger.info("prefix: " + prefix);

		}
		String updateQry = "update orders set do_type=1 where tc_order_id like '"
				+ prefix + "%'";
		AppDBMethods.deleteOrUpdateWMSDataBase(updateQry);

		String facility = null;

		facility = "03";
		String updateQry2 = "declare out_result VARCHAR2(500);"
				+ " v_count number(9); " + " begin"
				+ " M_ORDERS_CUSTOM_UPDATES('88','" + facility
				+ "',out_result,v_count);" + " end; ";

		AppDBMethods.deleteOrUpdateWMSDataBase(updateQry2);
		logger.info("orders posted in AS by executing store proc ...");

	}

	public void itemAllocation(String locnID, String itemId, String prodStat,
			String maxinvQty, String maxLPNs) {
		if (maxinvQty.isEmpty()) {
			maxinvQty = "40";

		}
		if (maxLPNs.isEmpty()) {
			maxLPNs = "15";
		}
		AppDBMethods.assignItemStoredProcedure(locnID, itemId, maxinvQty,
				maxLPNs, prodStat);

	}

	public String getWaveFromDB(String query) {
		List result = new ArrayList();
		result = null;
		String wave = null;
		Iterator result_It = null;
		result = AppDBMethods.ReleaseAllList(query);
		if (result != null) {
			result_It = result.iterator();

			try {
				if (result != null) {
					while (result_It.hasNext()) {

						wave = result_It.next() + "";
						// logger.info("the wave number is"+wave);
					}
				}

			}

			catch (Exception e) {
				e.getLocalizedMessage();
			}
		} else {
			wave = null;
		}
		return wave;
	}

	public static String dateStringInFutureByDaysCount(int daysInFuture) {
		GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, daysInFuture);
		logger.info(daysInFuture + " Days In Future With Current Date:  "
				+ new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
		String date = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
		return date;
	}

	public static List<String> getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(List<String> ordernumber) {
		OrderWaveWorkCreation.ordernumber = ordernumber;
	}

	public String[] getProdline() {
		return prodline;
	}

	public void setProdline(String[] prodline) {
		this.prodline = prodline;
	}

	public void setbuyerCode(String buyerCode) {
		this.buyerCode = buyerCode;
	}

	public void setorderType(String orderType) {
		this.orderType = orderType;
	}

	public String getorderType() {
		return orderType;
	}

	public void setnoteCode(String noteCode) {
		this.noteCode = noteCode;
	}

	public String getnoteCode() {
		return noteCode;
	}

	public String[] getUnit() {
		return unit;
	}

	public void setUnit(String[] unit) {
		this.unit = unit;
	}

	public String[] getNoInInven() {
		return noInInven;
	}

	public void setNoInInven(String[] noInInven) {
		this.noInInven = noInInven;
	}

	public String[] getNoOfLpn() {
		return noOfLpn;
	}

	public void setNoOfLpn(String[] noOfLpn) {
		this.noOfLpn = noOfLpn;
	}

	public String getNoInReserve() {
		return noInReserve;
	}

	public void setNoInReserve(String noInReserve2) {
		this.noInReserve = noInReserve2;
	}

	public String getBuyerCode() {
		return buyerCode;
	}

	public String getActiveLocnArea() {
		return activeLocnArea;
	}

	public String getResvLocnArea() {
		return resLocnArea;
	}

	public String[] getProdStatsInInv() {
		return prodStatsInInv;
	}

	public void setProdStatsInInv(String[] prodStatsInInv) {
		this.prodStatsInInv = prodStatsInInv;
	}

	public static String getOrderPrefix() {
		return orderPrefix;
	}

	public static void setorderPrefix(String prefix) {
		orderPrefix = prefix;
	}

	public static String getFirstItemName() {
		return firstItemName;
	}

	public static void setFirstItemName(String itemName) {
		firstItemName = itemName;
	}

	public static String getresvLocnId() {
		return firstResvLocnId;
	}

	public static void setResvLocnId(String resvLocnId) {
		firstResvLocnId = resvLocnId;
	}

	public String getPrevItem() {
		return sameItem;
	}

	public void setPrevItem(String prevItem) {
		sameItem = prevItem;
	}

	public void setorderNbrItem(String orderNbrItem) {
		this.orderNbrItemName = orderNbrItem;
	}

	public String getorderNbrItem() {
		return orderNbrItemName;
	}

	public Map<String, String> getItemNames() {
		return itemsLocnsInOrders;
	}

	public void setItemNames(Map<String, String> itemsInOrders) {
		OrderWaveWorkCreation.itemsLocnsInOrders = itemsInOrders;
	}

	public String getPickWaveNumber() {
		return pickWaveNumber;
	}

	public void setPickWaveNumber(String pickWaveNumber) {
		this.pickWaveNumber = pickWaveNumber;
	}

	public void setwaveType(String waveType) {
		this.waveType = waveType;
	}

	public String getWaveType() {
		return waveType;
	}

}
