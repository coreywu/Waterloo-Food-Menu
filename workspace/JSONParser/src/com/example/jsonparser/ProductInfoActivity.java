package com.example.jsonparser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class ProductInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_info);
		
		Intent intent = getIntent();
		String TAG_RESTAURANT = intent.getStringExtra("item").replaceAll("\\s", "");
		Log.i(TAG_RESTAURANT, "stuff");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_info, menu);
		return true;
	}

}
