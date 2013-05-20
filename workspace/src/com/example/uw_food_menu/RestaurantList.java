package com.example.uw_food_menu;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RestaurantList extends ListActivity {

	private static final String[] restaurant_names = {"Bon Appetit", "Brubakers", "Festival Fare", "Liquid Assets", "REVelation", "Mudies"};
	static final String restaurant_id = "RestaurantName";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_list);
		
		PopulateListView();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_restaurant_list, menu);
		return true;
	}
	
	public void PopulateListView(){	
		List<String> list_restaurants = new ArrayList<String>();
		for(int i = 0; i < restaurant_names.length; i++){
			list_restaurants.add(restaurant_names[i]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_row, R.id.text, list_restaurants);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(this, MealOfDay.class);
		intent.putExtra(restaurant_id, restaurant_names[position]);
		startActivityForResult(intent, position);
	}
	
	

}
