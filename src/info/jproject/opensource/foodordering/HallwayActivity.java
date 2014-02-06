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

		// must call recycle() after done using obtainTypedArray
		//icon_food_products.recycle();

		// Get the login session id
		//Intent i = getIntent();
		//PHPSESSID = i.getStringExtra("Set-Cookie");

		// Binding Array to ListAdapter
		final ListView list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(hcalv);
		// listening to single list item on click
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				//Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();

				switch (position) {
				case 0:
					// Launching new Activity on selecting single List Item
					Intent univcenterInfo = new Intent(getApplicationContext(),
							UnivCenterInfoActivity.class);
					// univcenterInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// sending data to new activity
					univcenterInfo.putExtra("Set-Cookie", PHPSESSID);
					univcenterInfo.putExtra("prod", Integer.toString(position));
					//Log.e("Set-Cookie", PHPSESSID);
					startActivity(univcenterInfo);
					break;

				case 1:
					// Launching new Activity on selecting single List Item
					Intent subwayInfo = new Intent(getApplicationContext(),
							SubwayInfoActivity.class);
					// subwayInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// sending data to new activity
					subwayInfo.putExtra("Set-Cookie", PHPSESSID);
					subwayInfo.putExtra("prod", Integer.toString(position));
					//Log.e("Set-Cookie", PHPSESSID);
					startActivity(subwayInfo);
					break;

				case 2:
					// Launching new Activity on selecting single List Item
					Intent einsteinbrosInfo = new Intent(
							getApplicationContext(),
							EinsteinBrosInfoActivity.class);
					// einsteinbrosInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// sending data to new activity
					einsteinbrosInfo.putExtra("Set-Cookie", PHPSESSID);
					einsteinbrosInfo.putExtra("prod", Integer.toString(position));
					//Log.e("Set-Cookie", PHPSESSID);
					startActivity(einsteinbrosInfo);
					break;

				case 3:
					// Launching new Activity on selecting single List Item
					Intent ultimatebajaInfo = new Intent(
							getApplicationContext(),
							UltimateBajaInfoActivity.class);
					// ultimatebajaInfo.addFlags
					// (Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// sending data to new activity
					ultimatebajaInfo.putExtra("Set-Cookie", PHPSESSID);
					ultimatebajaInfo.putExtra("prod", Integer.toString(position));
					//Log.e("Set-Cookie", PHPSESSID);
					startActivity(ultimatebajaInfo);
					break;

				case 4:
					// Launching new Activity on selecting single List Item
					Intent simplytogoInfo = new Intent(getApplicationContext(),
							SimplyToGoInfoActivity.class);
					// simplytogoInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// sending data to new activity
					simplytogoInfo.putExtra("Set-Cookie", PHPSESSID);
					simplytogoInfo.putExtra("prod", Integer.toString(position));
					//Log.e("Set-Cookie", PHPSESSID);
					startActivity(simplytogoInfo);
					break;

				case 5:
					// Launching new Activity on selecting single List Item
					Intent starbucksInfo = new Intent(getApplicationContext(),
							StarbucksInfoActivity.class);
					// starbucksInfo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// sending data to new activity
					starbucksInfo.putExtra("Set-Cookie", PHPSESSID);
					starbucksInfo.putExtra("prod", Integer.toString(position));
					//Log.e("Set-Cookie", PHPSESSID);
					startActivity(starbucksInfo);
					break;

				default:
					break;
				}

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