package com.example.uw_food_menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MenuData extends Activity {
	
	private static String url = "http://api.uwaterloo.ca/public/v1/?key=4aa5eb25c8cc979600724104ccfb70ea&service=FoodMenu&output=json";
	RestaurantDataParser dataParser = new RestaurantDataParser(url);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_data);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu_data, menu);
		return true;
	}

}
