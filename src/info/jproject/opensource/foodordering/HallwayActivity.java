package info.jproject.opensource.foodordering;

import info.jproject.opensource.foodordering.library.UserFunctions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HallwayActivity extends Activity {

	String PHPSESSID = null;
	int number = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hallway);

		// storing string resources into Array
		String[] food_products = getResources().getStringArray(
				R.array.food_products);
		TypedArray icon_food_products = getResources().obtainTypedArray(
				R.array.icon_food_products);

		HallwayCustomAdapterListView hcalv = new HallwayCustomAdapterListView(
				getApplicationContext(), food_products, icon_food_products);

		// must call recycle() after using obtainTypedArray
		// icon_food_products.recycle();

		// Get the login session id
		Intent i = getIntent();
		PHPSESSID = i.getStringExtra("Set-Cookie");

		// Binding Array to ListAdapter
		final ListView list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(hcalv);
		// listening to single list item on click
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),
						InfoActivity.class);
				// sending data to new activity
				i.putExtra("Set-Cookie", PHPSESSID);
				i.putExtra("prod", position);
				// Log.e("Set-Cookie", PHPSESSID);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		UserFunctions userFunctions = new UserFunctions();
		userFunctions.logoutUser(getApplicationContext());

		Intent login = new Intent(getApplicationContext(), LoginActivity.class);
		login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(login);
		// Closing this screen
		finish();

		return true;
	}

}