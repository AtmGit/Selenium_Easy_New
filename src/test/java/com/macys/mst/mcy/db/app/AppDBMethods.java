package com.macys.mst.mcy.db.app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.macys.mst.artemis.db.DBConnectionBean;
import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.mcy.db.app.AppDBMethods;

/**
 * 
 * This class is used for reusable application specific database methods
 */

public class AppDBMethods {

	private static Logger logger = Logger.getLogger(AppDBMethods.class.getName());
	public static Connection connection = DBConnections.getinstance("db", "appuser").dbConnection();

	public static void getTestResultSet() throws Exception {

		//Connection connection = DBConnectionBean.getAppDBConnection();

		if (!DBConnections.isDBConnectionOpen(connection)) {
			connection =  DBConnections.getinstance("db", "mstuser").dbConnection();
		}

		String sql = "select * from M_GENERIC_CONFIG where CONFIG_CODE = 'SOP/ASSIGN'";
		ResultSet rs = DBUtils.getresultset(connection, sql);
		try {
			if (rs != null) {
				while (rs.next()) {
					logger.info(rs.getString("CONFIG_CODE") + ":  "
							+ rs.getString("UI_DISP_TXT"));
				}
			}

		} catch (Exception e) {
			logger.error("Exception in getPackageShipDtlByLpnAndOrders:", e);
			;
		}
	}

	public static ResultSet mstDashBoardResultSet(String query)
			throws Exception {
		ResultSet resultSet = null;
		try {
			Connection connection = DBConnections.getinstance("db", "mstuser").dbConnection();
			// logger.info("Query: "+query);
			PreparedStatement sqlStatement = connection.prepareStatement(query);
			resultSet = sqlStatement.executeQuery();
		} catch (Exception exception) {
			logger.info("Exception in result set :" + exception.getMessage());
			exception.printStackTrace();
			if (resultSet != null) {
				resultSet.close();
			}
		}

		return resultSet;
	}

	public static ResultSet dashBoardResultSet(String query) throws Exception {
		ResultSet resultSet = null;
		PreparedStatement sqlStatement = null;
		try {
			// Connection connection= AppDbConnection();
			sqlStatement = connection.prepareStatement(query);
			resultSet = sqlStatement.executeQuery();
		} catch (Exception exception) {
			logger.info("Exception in result set :" + exception.getMessage());
			exception.printStackTrace();
			if (resultSet != null) {
				resultSet.close();
			}
		} finally {
			/*
			 * if(sqlStatement!=null){ sqlStatement.close(); }
			 */
		}

		return resultSet;
	}

	public static ResultSet ProteusValues(String query) throws Exception {
		ResultSet resultSet = null;
		PreparedStatement sqlStatement = null;
		try {
			// Connection connection=AppDbConnection();
			sqlStatement = connection.prepareStatement(query);
			resultSet = sqlStatement.executeQuery();

		} catch (Exception exception) {
			logger.info(exception.getMessage());
			resultSet.close();
		} finally {
			if (sqlStatement != null) {
				sqlStatement.close();
			}
		}

		return resultSet;
	}

	public static ResultSet proteusResultSet(String query) throws Exception {
		ResultSet resultSet = null;
		// Connection connection = null;
		PreparedStatement sqlStatement = null;
		try {
			// connection = AppDbConnection();
			sqlStatement = connection.prepareStatement(query);
			resultSet = sqlStatement.executeQuery();
		} catch (Exception exception) {
			logger.info(exception.getMessage());
			exception.printStackTrace();
			if (resultSet != null) {
				resultSet.close();
			}
		} finally {
			if (sqlStatement != null) {
				sqlStatement.close();
			}
		}

		return resultSet;
	}

	public static void dataGenetaionStoredProcedure(String prefix)
			throws SQLException {

		PreparedStatement sqlStatement = null;
		try {
			// Connection connection=AppDbConnection();
			String SQL = "{call M_WO_RPL_WAVE_DATA_PREP (?)}";
			sqlStatement = connection.prepareCall(SQL);
			sqlStatement.setString(1, prefix);
			sqlStatement.execute();
		} catch (SQLException e) {
			logger.info("Exception in executing SP " + e.getMessage());
		} finally {
			if (sqlStatement != null) {
				sqlStatement.close();
			}
		}

	}

	public static List ReleaseAllList(String query) {
		List result = null;
		ResultSet resultSet = null;
		PreparedStatement sqlStatement = null;
		try {
			// connection=AppDbConnection();
			sqlStatement = connection.prepareStatement(query);
			resultSet = sqlStatement.executeQuery();
			result = new ArrayList();
			// System.out.println("the result set size is "+resultSet);
			while (resultSet.next()) {
				String res = resultSet.getString(1);
				if (res == null) {
					// System.out.println("the result is null in release");

					result = null;
				} else {

					result.add(res);
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			// logger.info(exception.getMessage());

		} finally {
			try {
				resultSet.close();
				sqlStatement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static List ProteusList(String query) {
		List result = null;
		ResultSet resultSet = null;
		PreparedStatement sqlStatement = null;

		try {
			// connection=AppDbConnection();

			sqlStatement = connection.prepareStatement(query);
			resultSet = sqlStatement.executeQuery();
			result = new ArrayList();
			// System.out.println("the result set size is "+resultSet);
			while (resultSet.next()) {

				String res = resultSet.getString(1);
				if (res == null) {
					// System.out.println("the result is null in release");

					result = null;
				} else {

					result.add(res);
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			// logger.info(exception.getMessage());

		} finally {
			try {
				resultSet.close();
				sqlStatement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void deleteOrUpdateWMSDataBase(String query) {

		PreparedStatement sqlStatement = null;
		try {
			// connection= AppDbConnection();
			if (connection == null) {
				System.out.println("NULL");
			}
			sqlStatement = connection.prepareStatement(query);
			// logger.info("query to update : "+query);
			sqlStatement.executeUpdate();
			connection.commit();
		} catch (Exception exception) {
			logger.info("Exception while updatign values : "
					+ exception.getMessage());
		} finally {
			try {
				sqlStatement.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* Get ship wave parm id */
	public static int getship_wave_parm_id(String pick_wave_nbr) {
		int ship_wave_parm_id = -1;
		try {
			ResultSet rs = AppDBMethods
					.dashBoardResultSet("select stat_code,ship_wave_parm_id from ship_wave_parm where pick_wave_nbr='"
							+ pick_wave_nbr + "'");
			while (rs.next()) {
				ship_wave_parm_id = rs.getInt("ship_wave_parm_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// AppDBMethods.closeDBConnection();
		return ship_wave_parm_id;
	}

	/* get chute assign type for a wave */

	static long startTime;
	static long currentTime;
	static long timeDiff;
	static long waitTime = 1000000;
	static Boolean flag = false;

	public static String getchute_assign_type(String pick_wave_nbr)
			throws InterruptedException {
		String chute_assign_type = null;
		String sql = null;
		try {
			sql = "select chute_assign_type from pack_wave_parm_dtl inner join pack_wave_parm_hdr on pack_wave_parm_dtl.pack_wave_parm_id = pack_wave_parm_hdr.pack_wave_parm_id "
					+ "where pack_wave_parm_dtl.pack_wave_nbr like '"
					+ pick_wave_nbr + "%'";
			ResultSet rs = AppDBMethods.dashBoardResultSet(sql);
			System.out
					.println("select chute_assign_type from pack_wave_parm_dtl inner join pack_wave_parm_hdr on pack_wave_parm_dtl.pack_wave_parm_id = pack_wave_parm_hdr.pack_wave_parm_id "
							+ " where pack_wave_parm_dtl.pack_wave_nbr like '"
							+ pick_wave_nbr + "%'");
			startTime = System.currentTimeMillis();
			do {
				rs = AppDBMethods.dashBoardResultSet(sql);
				currentTime = System.currentTimeMillis();
				timeDiff = currentTime - startTime;
			} while (!rs.isBeforeFirst() && timeDiff <= waitTime);

			if (rs.next()) {
				chute_assign_type = rs.getString("chute_assign_type");
				System.out.println("inside loop: " + chute_assign_type);
				flag = true;

			} else {
				logger.info("No Result Obtained from Query :" + sql);
				flag = false;
				Assert.assertTrue(flag);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// AppDBMethods.closeDBConnection();
		logger.info(chute_assign_type);
		return chute_assign_type;
	}

	/* get WHSE */

	public static String getwhse() {
		String whse = null;
		try {
			ResultSet rs = AppDBMethods
					.dashBoardResultSet("select whse From whse_master");

			while (rs.next()) {
				whse = rs.getString("whse");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// AppDBMethods.closeDBConnection();
		return whse;
	}

	/* release a wave from proteus */

	public static void releaseWaveProteusProcedure(String userId,
			String packWaveNbr, String p_logical_group) {

		packWaveNbr = packWaveNbr + "001";
		CallableStatement cs = null;
		try {
			// Connection connection = AppDbConnection();
			cs = connection
					.prepareCall("{call M_WAVE_RELEASE_PROC(?,?,?,?,?,?,?)}");

			cs.registerOutParameter(1, java.sql.Types.VARCHAR);
			cs.registerOutParameter(2, java.sql.Types.NUMERIC);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			logger.info(userId);
			cs.setString(4, userId);
			cs.setString(5, packWaveNbr);
			cs.setString(6, p_logical_group);
			cs.setInt(7, -1);

			cs.execute();

			int status = cs.getInt(2);
			String p_outResult = cs.getString(1);
			String p_released_task = cs.getString(3);

			logger.info("Pack wave stat code: " + status);
			logger.info("Pack wave status : " + p_outResult);
			logger.info("No of tasks created : " + p_released_task);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* release a wave */

	public static void releaseWaveProcedure(String userId, int facilityId,
			int shipWaveParmId, String packWaveNbr, String codeId, String whse,
			String chuteType, String miscFlag) {
		packWaveNbr = packWaveNbr + "001";
		CallableStatement cs = null;
		try {
			// Connection connection = AppDbConnection();

			cs = connection
					.prepareCall("{call CUST_WAVE_RELEASE_UPDATE(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, userId); // -- pass it as automation user
			cs.setInt(2, facilityId); // -- 1
			cs.setInt(3, shipWaveParmId); // -- take from 1st query
			cs.setString(4, packWaveNbr); // -- concatenation of wave_nbr and
											// "001"
			cs.setString(5, codeId); // -- code id depends on wave type (for
										// Pick=Pack this is "1 All Area")
			cs.setString(6, whse); // -- take from 3rd query
			cs.setFloat(7, 10.0F);
			cs.setFloat(8, 1.0F);
			cs.setString(9, chuteType); // --take from query 2
			cs.setString(10, miscFlag); // -- same as code id
			cs.registerOutParameter(11, 2);
			cs.execute();
			int status = cs.getInt(11);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Release work */

	public static void releaseWorkProcedure(String packWaveNbr, String userId) {
		packWaveNbr = packWaveNbr + "001";
		CallableStatement cs = null;
		try {
			// Connection connection = AppDbConnection();
			String SQL = "{call M_INS_WORK (?,?, ?)}";

			cs = connection.prepareCall(SQL);

			cs.setString(2, packWaveNbr); // -- concatenation of wave_nbr and
											// "001"
			cs.setString(3, userId); // -- pass it as automation user
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
			logger.info("cs.getInt(1) :" + cs.getInt(1));

			// ResultSet rs = null;
			/*
			 * if(cs.execute()) { rs = cs.getResultSet(); }
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void assignItemStoredProcedure(String locnid, String item_id,
			String maxQuantity, String actInv, String prod_Status) {
		CallableStatement csmt = null;
		try {

			// Connection connection=AppDbConnection();
			String SQL = "{call M_INS_INVN_WITH_LOCKING (?,?,?,?,?,?,?)}";
			csmt = connection.prepareCall(SQL);
			// csmt.setInt(1, Integer.parseInt(lpn_id));
			csmt.setInt(1, 99);
			csmt.setString(2, locnid);
			csmt.setInt(3, Integer.parseInt(item_id));
			csmt.setInt(4, Integer.parseInt(maxQuantity));
			csmt.setInt(5, Integer.parseInt(actInv));
			csmt.setString(6, "wmdev");
			csmt.setString(7, prod_Status);
			csmt.execute();

			logger.info("Assigning item Successful");
		} catch (Exception e) {
			logger.info("Exception " + e.getMessage());
			e.printStackTrace();
		}

	}

	public static void deleteOrUpdateProteusDataBase(String query) {
		// Connection connection= AppDbConnection();
		PreparedStatement sqlStatement = null;
		try {

			sqlStatement = connection.prepareStatement(query);
			sqlStatement.executeUpdate();
			connection.commit();

		} catch (Exception exception) {
			exception.printStackTrace();
			logger.info(exception.getMessage());
		} finally {
			try {
				sqlStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public static void deleteOrUpdateDataBase(String query,String schema) 
	{
		PreparedStatement sqlStatement = null;
		Connection con = null;
		try{
			logger.info(query);
			con = DBConnections.getinstance("db", schema).dbConnection();
			sqlStatement=con.prepareStatement(query);
			sqlStatement.executeUpdate();
			con.commit();

		}
		catch(Exception exception)
		{
			logger.info("2nd time : "+query);
			logger.info(exception.getMessage());
			logger.error(exception);
			StepDetail.addDetail("Exception in DB : "+exception, false);
			Assert.assertTrue(false);
		}

		finally{
			//finally block used to close resources
			try{
				if(sqlStatement !=null)
					sqlStatement.close();
			}catch(SQLException se2){
				logger.error(se2);
				Assert.assertTrue(false);

			}// nothing we can do
			 try{
		         if(con!=null)
		        	 con.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
	}

}
