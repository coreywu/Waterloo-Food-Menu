// Created by Corey Wu, Johan Augustine & Shamak Dutta  

package com.example.jsonparser;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	String mode = "menu";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final List<String> listMenu = new ArrayList<String>();
		listMenu.add("Bon Appetit");
		listMenu.add("Brubakers");
		listMenu.add("Festival Fare");
		listMenu.add("Liquid Assets");
		listMenu.add("Mudies");
		listMenu.add("REVelation");
		final ArrayAdapter<String> listAdapterMenu = new ArrayAdapter<String>(this, R.layout.restaurant_list_item, listMenu);
		setListAdapter(listAdapterMenu);
		
		final List<String> listDetails = new ArrayList<String>();
		listDetails.add("Bon Appetit");
		listDetails.add("Browsers Cafe");
		listDetails.add("Brubakers");
		listDetails.add("CEIT Cafe");
		listDetails.add("Eye Opener Cafe");
		listDetails.add("Festival Fare");
		listDetails.add("LA Cafe");
		listDetails.add("ML's Coffee Shop");
		listDetails.add("Mudie's");
		listDetails.add("PAS Lounge");
		listDetails.add("Pastry Plus Needles Hall"); // These are not implemented yet
		listDetails.add("Pastry Plus Tatham Centre");
		listDetails.add("REVelation");
		listDetails.add("Subway");
		listDetails.add("University Club");
		listDetails.add("Williams Fresh Cafe");
		final ArrayAdapter<String> listAdapterDetails = new ArrayAdapter<String>(this, R.layout.restaurant_list_item, listDetails);
		
		Button back = (Button) findViewById(R.id.back);
		final Button menu = (Button) findViewById(R.id.menu);
		final Button details = (Button) findViewById(R.id.details);
		
		menu.setClickable(false);
		
		details.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mode == "menu") {
					setListAdapter(listAdapterDetails);
					mode = "details";
					menu.setClickable(true);
					details.setClickable(false);
				}
			}
		});
		
		menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mode == "details") {
					setListAdapter(listAdapterMenu);
					mode = "menu";
					details.setClickable(true);
					menu.setClickable(false);
				}
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String restaurant = (String) getListAdapter().getItem(position);
		Intent intent;
		
		if (mode == "menu") {
			intent = new Intent(getApplicationContext(), RestaurantActivity.class);
		}
		else {
			intent = new Intent(getApplicationContext(), RestaurantDetails.class);	
		}
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
