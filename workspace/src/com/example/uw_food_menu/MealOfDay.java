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

public class MealOfDay extends ListActivity {
	
	private static final String[] meals = { "Lunch", "Dinner" };
	private static String Restaurant_name;
	static String Meal = "Meal_ID";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meal_of_day);
		
		Intent restaurantList_intent = getIntent();
		Restaurant_name = restaurantList_intent.getStringExtra(RestaurantList.restaurant_id);
		PopulateListView();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_meal_of_day, menu);
		return true;
	}
	
	public void PopulateListView(){
		List<String> list_meals = new ArrayList<String>();
		for(int i = 0; i < meals.length; i++){
			list_meals.add(meals[i]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_row, R.id.text, list_meals);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, 	int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(this, DayOfTheWeek.class);
		intent.putExtra(RestaurantList.restaurant_id, Restaurant_name);
		intent.putExtra(Meal, meals[position]);
		startActivityForResult(intent, position);
	}

}
