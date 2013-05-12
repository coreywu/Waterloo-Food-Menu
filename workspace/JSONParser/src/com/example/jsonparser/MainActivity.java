package com.example.jsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static String url = "http://api.uwaterloo.ca/public/v1/?key=98bbbd30b3e4f621d9cb544a790086d6&service=FoodMenu&output=json";
	private static final String TAG_RESPONSE = "response";
	private static final String TAG_DATA = "data";
	private static final String TAG_RESTAURANTS = "Restaurants";
	private static final String TAG_BONAPPETIT = "BonAppetit";
	private static final String TAG_MENU = "Menu";
	
	private static final String[] TAG_DAYS = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	
	private static final String TAG_LUNCH = "Lunch";
	private static final String TAG_DINNER = "Dinner";
	private static final String TAG_ITEMS = "Items";
	private static final String TAG_RESULT = "result";
	
	JSONArray response = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();
		
		// Getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);
		Log.d("blah","blah");
		try {
			// Getting Array of responses
			//response = json.getJSONArray(TAG_RESPONSE);
			//JSONArray responseArray = response.names();
			//String response0 = responseArray.getString(0);
			//Log.d("blah", response0);
			JSONObject response = json.getJSONObject(TAG_RESPONSE);
			JSONObject data = response.getJSONObject(TAG_DATA);
			Log.d("blah","blah");
			JSONObject Restaurants = data.getJSONObject(TAG_RESTAURANTS);
			Log.d("blah","blah");
			JSONObject BonAppetit = Restaurants.getJSONObject(TAG_BONAPPETIT);
			Log.d("blah","blah");
			JSONObject Menu = BonAppetit.getJSONObject(TAG_MENU);
			String output = "";
			
			for (int i = 0; i < Menu.length(); i ++) {
				output += TAG_DAYS[i] + ": \n";
				JSONObject day = Menu.getJSONObject(TAG_DAYS[i]);
				
				// Lunch
				JSONObject Lunch = day.getJSONObject(TAG_LUNCH);
				JSONObject Items = Lunch.getJSONObject(TAG_ITEMS);
				Log.d("blah","blah5");
				output += "Lunch:";
				JSONArray result = Items.getJSONArray(TAG_RESULT);
				output += result.getString(0);
				output += "\n";
				
				// Dinner
				if (day.has(TAG_DINNER) == true) {
					JSONObject Dinner = day.getJSONObject(TAG_DINNER);
					Items = Dinner.getJSONObject(TAG_ITEMS);

					output += "Dinner:";
					result = Items.getJSONArray(TAG_RESULT);
					output += result.getString(0);
					output += "\n";
				}
			}
			Log.d("blah",output);
			TextView tv = (TextView) findViewById(R.id.text);
			tv.setText(output);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
