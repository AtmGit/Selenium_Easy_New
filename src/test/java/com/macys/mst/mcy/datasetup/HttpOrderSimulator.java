package com.macys.mst.mcy.datasetup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.*;
import org.junit.Assert;

import com.macys.mst.artemis.config.FileConfig;
@SuppressWarnings("all")
public class HttpOrderSimulator {

	private static final Logger LOGGER = Logger.getLogger(HttpOrderSimulator.class);
	
	/**
	 * 
	 * @param fileName
	 * @param hostName
	 * @return
	 */
	public static boolean postOrder(String fileName, String hostName) {
		FileReader fileReader;
		try {
			fileReader = new FileReader(FileConfig.getInstance().getStringConfigValue("test.data.folder") + File.separator +  "orders" + File.separator + fileName);
			return postOrder(fileReader, hostName);
		} catch (FileNotFoundException fnfe) {
			LOGGER.error("Unable to post order", fnfe);
			return false;		
		}

	}
	
	
	public static boolean postOrderXML(String xml, String hostname) {
		Reader reader = new StringReader(xml);
		return postOrder(reader, hostname);
	}

	/**
	 * 
	 * @param reader
	 * @param hostName
	 * @return
	 */
	public static boolean postOrder(Reader reader, String hostName) {
		try {

			String urlString = "http://";
			String finalUrl = urlString + hostName + "ProcessWebRequests";
			URL url = new URL(finalUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");
	
			OutputStream os =  connection.getOutputStream();
	

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			StreamSource source = new StreamSource(reader);
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
			os.flush();
			LOGGER.info(connection.getResponseCode());
			Assert.assertTrue(201 == connection.getResponseCode());
			connection.disconnect();
			return true;
		} catch (RuntimeException re) {
			LOGGER.error("Unable to post order", re);
			return false;
		} catch (IOException e) {
			LOGGER.error("Unable to post order", e);
			return false;
		} catch (TransformerConfigurationException e) {
			LOGGER.error("Unable to post order", e);
			return false;
		} catch (TransformerException e) {
			LOGGER.error("Unable to post order", e);
			return false;
		}
	}

}
