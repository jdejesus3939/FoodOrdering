package info.jproject.opensource.foodordering;

import info.jproject.opensource.foodordering.library.UserFunctions;
import info.jproject.opensource.foodordering.library.VendorsConstants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends Activity {
	private Button next;
	private TextView txtVendor;
	private TextView txtInfoVendor;
	private ImageView iconVendor;
	private VendorsConstants vendConstants;
	int vendor = 0;
	String PHPSESSID = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.info);
        
        next = (Button) findViewById(R.id.btnInfoNext);
        txtVendor = (TextView) findViewById(R.id.vendor_label);
        txtInfoVendor = (TextView) findViewById(R.id.vendor_info);
        iconVendor = (ImageView) findViewById(R.id.vendor_icon);
        
        Intent i = getIntent();
        // getting attached intent data
        vendor = Integer.parseInt(i.getStringExtra("prod"));
        PHPSESSID = i.getStringExtra("Set-Cookie");
        // displaying selected product name
        txtVendor.setText(vendConstants.getVendorText(vendor));
        txtInfoVendor.setText(vendConstants.getVendorInfo(vendor));
        iconVendor.setImageResource(vendConstants.getVendorIcon(vendor));
        
        next.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				
				Intent i = null;
				
				switch (vendor) {
				case 0:
					i = new Intent(getApplicationContext(), UnivCenterMainActivity.class);
					//simMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("Set-Cookie", PHPSESSID);
					//Log.e("Set-Cookie",PHPSESSID);
					break;
				case 1:
					i = new Intent(getApplicationContext(), SubwayMainActivity.class);
					//simMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("Set-Cookie", PHPSESSID);
					//Log.e("Set-Cookie",PHPSESSID);
					break;
				case 2:
					i = new Intent(getApplicationContext(), EinsteinBrosMainActivity.class);
					//einMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("Set-Cookie", PHPSESSID);
					//Log.e("Set-Cookie",PHPSESSID);
					break;
				case 3:
					i = new Intent(getApplicationContext(), UltimateBajaMainActivity.class);
					//simMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("Set-Cookie", PHPSESSID);
					//Log.e("Set-Cookie",PHPSESSID);
					break;
				case 4:
					i = new Intent(getApplicationContext(), SimplyToGoMainActivity.class);
					//simMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("Set-Cookie", PHPSESSID);
					//Log.e("Set-Cookie",PHPSESSID);
					break;
				case 5:
					i = new Intent(getApplicationContext(), StarbucksMainActivity.class);
					//simMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("Set-Cookie", PHPSESSID);
					//Log.e("Set-Cookie",PHPSESSID);
					break;
				}
				
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
        // Closing dashboard screen
        finish();
        
		return true;
	}
}
