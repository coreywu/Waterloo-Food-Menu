// Created by Corey Wu, Johan Augustine & Shamak Dutta  

package com.example.jsonparser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<String> list = new ArrayList<String>();
		list.add("Bon Appetit");
		list.add("Brubakers");
		list.add("Festival Fare");
		list.add("Liquid Assets");
		list.add("Mudies");
		list.add("REVelation");
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.restaurant_list_item, list);
		setListAdapter(listAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String restaurant = (String) getListAdapter().getItem(position);
		Intent intent = new Intent(getApplicationContext(), RestaurantActivity.class);
		intent.putExtra("restaurant", restaurant);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
