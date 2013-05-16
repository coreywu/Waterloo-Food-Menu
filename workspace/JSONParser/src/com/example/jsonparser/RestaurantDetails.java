package com.example.jsonparser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RestaurantDetails extends Activity {
	
	private static String url = "http://api.uwaterloo.ca/public/v1/?key=4aa5eb25c8cc979600724104ccfb70ea&service=FoodServices&output=json";
	private static final String TAG_RESPONSE = "response"; // Object
	private static final String TAG_DATA = "data"; // Object
	private static final String TAG_RESULT_MAIN = "result"; // Array
	
	private static final String TAG_NAME_OF_RESTAURANT = "Name";
	private static final String TAG_LOCATION = "Location";
	
	private static final String TAG_HOURS = "Hours";
	private static final String TAG_RESULT = "result";
	
	//test
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_details);
		
		Intent intent = getIntent();
		final String TAG_NAME = intent.getExtras().getString("Restaurant_Name");
		
		JSONParser parser = new JSONParser();
		JSONObject json = parser.getJSONFromUrl(url);
		
		try {
			JSONObject response = json.getJSONObject(TAG_RESPONSE);
			JSONObject data = response.getJSONObject(TAG_DATA);
			JSONArray result_main = data.getJSONArray(TAG_RESULT_MAIN);
			
			for(int i = 0; i < result_main.length(); i++){
				JSONObject restaurant_details = result_main.getJSONObject(i);
				
				String restaurant_name = (String) restaurant_details.get(TAG_NAME_OF_RESTAURANT);
				
				if(restaurant_name.equals(TAG_NAME)){
					// Populate activity with restaurant data
					
					JSONObject hours = restaurant_details.getJSONObject(TAG_HOURS);
					JSONArray result = hours.getJSONArray(TAG_RESULT);
					
					String location = restaurant_details.getString(TAG_LOCATION);
					
					List<String> list = new ArrayList<String>();
					
					list.add(TAG_NAME);
					list.add(location);
					for(int j = 0; j < result.length(); j++){
						String timings = result.getString(j);
						list.add(timings);
					}
					
					ListView lv = (ListView) findViewById(R.id.listView);
					ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
					lv.setAdapter(arrayAdapter);
					break;
				}

			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_restaurant_details, menu);
		return true;
	}

}
