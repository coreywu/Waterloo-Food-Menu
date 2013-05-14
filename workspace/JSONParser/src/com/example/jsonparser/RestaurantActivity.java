package com.example.jsonparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantActivity extends Activity {
	
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
	
	public final static String ITEM_TITLE = "title";
	public final static String ITEM_CAPTION = "caption";
	
	// useless comment added for Shamak
	
	public Map<String,?> createItem(String title, String caption) {
		Map<String,String> item = new HashMap<String,String>();
		item.put(ITEM_TITLE, title);
		item.put(ITEM_CAPTION, caption);
		return item;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//List<String> list = new ArrayList<String>();
		
		List<String> list = new LinkedList<String>();  
		SeparatedListAdapter adapter = new SeparatedListAdapter(this);
		
		
		
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();
		
		// Getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);
		
		try {
			JSONObject response = json.getJSONObject(TAG_RESPONSE);
			JSONObject data = response.getJSONObject(TAG_DATA);
			JSONObject Restaurants = data.getJSONObject(TAG_RESTAURANTS);
			JSONObject BonAppetit = Restaurants.getJSONObject(TAG_BONAPPETIT);
			JSONObject Menu = BonAppetit.getJSONObject(TAG_MENU);
			
			JSONObject day;
			JSONObject Lunch;
			JSONObject Dinner;
			JSONObject Items;
			JSONArray result;
			
			for (int i = 0; i < Menu.length(); i ++) {
				day = Menu.getJSONObject(TAG_DAYS[i]);
				adapter.addSection(TAG_DAYS[i], new ArrayAdapter<String>(this, R.layout.list_item, new String[] {}));
				
				// Adding spaces fixes the problem of having multiple sections of the same title.
				String spaces = "";
				for (int j = 0; j < i; j++) {
					spaces += " ";
				}
				
				// Lunch
				if (day.has(TAG_LUNCH) == true) {
					Lunch = day.getJSONObject(TAG_LUNCH);
					Items = Lunch.getJSONObject(TAG_ITEMS);
					result = Items.getJSONArray(TAG_RESULT);
					list.add(result.getString(0)); 
					adapter.addSection("Lunch" + spaces, new ArrayAdapter<String>(this, R.layout.menu_list_item, new String[] {result.getString(0)}));
				}
				
				// Dinner
				if (day.has(TAG_DINNER) == true) {
					Dinner = day.getJSONObject(TAG_DINNER);
					Items = Dinner.getJSONObject(TAG_ITEMS);
					result = Items.getJSONArray(TAG_RESULT);
					list.add(result.getString(0));
					adapter.addSection("Dinner" + spaces, new ArrayAdapter<String>(this, R.layout.menu_list_item, new String[] {result.getString(0)}));
				}
			}
			//adapter.addSection("Lunch", new ArrayAdapter<String>(this, R.layout.list_item, list));
			ListView listView = new ListView(this);
			listView.setAdapter(adapter);
			this.setContentView(listView);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String s = (String) getListAdapter().getItem(position);
		Toast.makeText(this, "Aha: "+s, Toast.LENGTH_SHORT).show();
	}
	*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
