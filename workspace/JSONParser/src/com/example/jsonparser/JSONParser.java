package com.example.jsonparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	public JSONParser() {
	}
	
	/* 
	 * 
	 * 
	 * Method to get the JSON data from the API 
	 * 
	 * 
	 */

	public JSONObject getJSONFromUrl(String url) {
		try {
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			// Setting up a default client to get the data
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// HttpPost is a request to the web server
			HttpPost httpPost = new HttpPost(url);
			
			// Client executes the request
			HttpResponse httpResponse = httpClient.execute(httpPost);
			// The 'response' from the server feeds back data stored in the httpEntity
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// Assigning that string to the JSON Object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON object which carries the JSON data
		return jObj;

	}
}
