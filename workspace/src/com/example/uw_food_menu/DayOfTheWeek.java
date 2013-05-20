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

public class DayOfTheWeek extends ListActivity {
	
	private static final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private static String Restaurant_name;
	private static String Meal;
	static String days_ID = "days";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day_of_the_week);
		
		Intent daysList_intent = getIntent();
		Restaurant_name = daysList_intent.getStringExtra(RestaurantList.restaurant_id);
		Meal = daysList_intent.getStringExtra(MealOfDay.Meal);
		PopulateListView();
	}

	public void PopulateListView() {
		List<String> list_days = new ArrayList<String>();
		for(int i = 0; i < days.length; i++){
			list_days.add(days[i]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_row, R.id.text, list_days);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_day_of_the_week, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, 	int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(this, MenuData.class);
		intent.putExtra(RestaurantList.restaurant_id, Restaurant_name);
		intent.putExtra(MealOfDay.Meal, Meal);
		intent.putExtra(days_ID, days[position]);
		startActivityForResult(intent, position);
	}
}
