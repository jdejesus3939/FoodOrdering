package info.jproject.opensource.foodordering;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import info.jproject.opensource.foodordering.library.CheckoutFunctions;
import info.jproject.opensource.foodordering.library.InfoRetrieved;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SubwayMainActivity extends Activity {
	
	private RadioGroup radioBreadGroup,
	                   radioFlavorGroup,
	                   radioCheeseGroup,
	                   radioSauceGroup;
	                  
	private RadioButton radioBreadButton,
	                    radioFlavorButton,
	                    radioCheeseButton,
	                    radioSauceButton;
	                   
	
	private CheckBox check6inch,
					 check12inch,
	                 checkLettuce,
	                 checkTomatoes,
	                 checkBellPepper,
	                 checkRedOnion,
	                 checkCucumber,
	                 checkSpinach,
	                 checkYes;
	
	private TextView price6inch,
					 price12inch,
					 priceYes,
					 subwayOrderHistory;
		
	private Button btnSubwayProceed;
	
	private static String SUCCESS = "success";
	private static String ERROR = "error";
	
	int[] subwayPrice = new int[3];
	String [] subwayString = new String [12];
	String subwaytvhistory;
	String [] subwayHistoryString = new String[12];
	String PHPSESSID = null;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subwaymain);
		
		Intent myIntent = getIntent();
		PHPSESSID = myIntent.getStringExtra("Set-Cookie");
		//Log.e("Set-Cookie",PHPSESSID);

		InfoRetrieved price = new InfoRetrieved();
	    JSONObject json = price.pricelist("subway", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    // check for success process
	    try {
	        if (json.getString(SUCCESS) != null) {
	            String res = json.getString(SUCCESS);
	            Log.e("check# res subway main is ", res);
	            if(Integer.parseInt(res) == 1){
	            	subwayPrice[0] = Integer.parseInt(json.getString("6inch"));
	            	subwayPrice[1] = Integer.parseInt(json.getString("12inch"));
	            	subwayPrice[2] = Integer.parseInt(json.getString("meal"));
	            }
	        }
	        
	        if (json.getString(ERROR) != null) {
                String res1 = json.getString(ERROR);
                Log.e("check# res1 on pricing is ", res1);
                if (Integer.parseInt(res1) == 2){
                	Toast toast = Toast.makeText(getApplicationContext(), "Insufficient balance, please check sanddollar office", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (Integer.parseInt(res1) == 3){
                	Toast toast = Toast.makeText(getApplicationContext(), "User is not found, please check sanddollar office", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (Integer.parseInt(res1) == 4){
                	Log.e("check# error_msg is 4", json.toString());
                }
                if (Integer.parseInt(res1) == 5) {
                	Toast toast = Toast.makeText(getApplicationContext(), "Your session is expired.\nPlease re-login.", Toast.LENGTH_LONG);
                    toast.show();
                    // call main activity
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                if (Integer.parseInt(res1) == 6) {
                	Toast toast = Toast.makeText(getApplicationContext(), "You are not allowed\nto make a double order\nPlease re-login.", Toast.LENGTH_LONG);
                    toast.show();
                    //call main activity
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }

	    InfoRetrieved history = new InfoRetrieved();
	    JSONObject jsonhist = history.orderhistorylist("subway", PHPSESSID, getApplicationContext());
	    //Log.e("check# here @checkoutActivity", subjson.toString());
	    subwaytvhistory = "Your last order with this vendor was:\n";
	    // check for success process
	    try {
	        if (jsonhist.getString(SUCCESS) != null) {
	            String reshist = jsonhist.getString(SUCCESS);
	            Log.e("check# reshist subway main is ", reshist);
	            if(Integer.parseInt(reshist) == 1){
	            	subwayHistoryString[0] = jsonhist.getString("foot");
	            	subwayHistoryString[1] = jsonhist.getString("bread");
	            	subwayHistoryString[2] = jsonhist.getString("flavor");
	            	subwayHistoryString[3] = jsonhist.getString("cheese");
	            	subwayHistoryString[4] = jsonhist.getString("sauce");
	            	subwayHistoryString[5] = jsonhist.getString("meal");
	            	subwayHistoryString[6] = jsonhist.getString("veg_1");
	            	subwayHistoryString[7] = jsonhist.getString("veg_2");
	            	subwayHistoryString[8] = jsonhist.getString("veg_3");
	            	subwayHistoryString[9] = jsonhist.getString("veg_4");
	            	subwayHistoryString[10] = jsonhist.getString("veg_5");
	            	subwayHistoryString[11] = jsonhist.getString("veg_6");
	            
		            
		            for(int i=0 ; i<subwayHistoryString.length; i++)
		            {
		            	if(!subwayHistoryString[i].equals("No"))
		            	{
		            		subwaytvhistory = subwaytvhistory + subwayHistoryString[i] + "\n";
		            	}
		            }
	            }
	            
	            if(Integer.parseInt(reshist) == 0){
	            	Log.e("check@subway fail-hist-retrived", reshist);
	            	subwaytvhistory = subwaytvhistory + "You have not made any purchase with this vendor\n";
	            }
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }

	    subwayOrderHistory = (TextView) findViewById(R.id.subwayOrderHistory);
	    
		price6inch = (TextView) findViewById(R.id.tvPrice6inch);
		price12inch = (TextView) findViewById(R.id.tvPrice12inch);
		priceYes = (TextView) findViewById(R.id.tvPriceYes);
		
		radioBreadGroup = (RadioGroup) findViewById(R.id.radioBread);
		radioFlavorGroup = (RadioGroup) findViewById(R.id.radioFlavor);
		radioCheeseGroup = (RadioGroup) findViewById(R.id.radioCheese);
		radioSauceGroup = (RadioGroup) findViewById(R.id.radioSauce);
		
		check6inch = (CheckBox) findViewById(R.id.check6inch);
		check12inch = (CheckBox) findViewById(R.id.check12inch);
		checkLettuce = (CheckBox) findViewById(R.id.checkLettuce);
        checkTomatoes = (CheckBox) findViewById(R.id.checkTomatoes);
        checkBellPepper = (CheckBox) findViewById(R.id.checkBellPepper);
        checkRedOnion = (CheckBox) findViewById(R.id.checkRedOnion);
        checkCucumber = (CheckBox) findViewById(R.id.checkCucumber);
        checkSpinach = (CheckBox) findViewById(R.id.checkSpinach);
        checkYes = (CheckBox) findViewById(R.id.checkYes);
		
        subwayOrderHistory.setText(subwaytvhistory);
        
        price6inch.setText("$ " + subwayPrice[0]);
    	price12inch.setText("$ " + subwayPrice[1]);
    	priceYes.setText("$ " + subwayPrice[2]);
        
		btnSubwayProceed = (Button) findViewById(R.id.btnSubwayProceed);
		
		btnSubwayProceed.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//get the id of selected radio button from radio group
				int selectedBread = radioBreadGroup.getCheckedRadioButtonId();
				int selectedFlavor = radioFlavorGroup.getCheckedRadioButtonId();
				int selectedCheese = radioCheeseGroup.getCheckedRadioButtonId();
				int selectedSauce = radioSauceGroup.getCheckedRadioButtonId();
				
				
				//set the id to the radio button
				radioBreadButton = (RadioButton) findViewById(selectedBread);
				radioFlavorButton = (RadioButton) findViewById(selectedFlavor);
				radioCheeseButton = (RadioButton) findViewById(selectedCheese);
				radioSauceButton = (RadioButton) findViewById(selectedSauce);
				
				
				//assign all selected radio buttons to string
				subwayString[1] = radioBreadButton.getText().toString();
				subwayString[2] = radioFlavorButton.getText().toString();
				subwayString[3] = radioCheeseButton.getText().toString();
				subwayString[4] = radioSauceButton.getText().toString();
				
				//check box, if checked set the string
				if(check6inch.isChecked())
					subwayString[0] = check6inch.getText().toString();
				if(check12inch.isChecked())
					subwayString[0] = check12inch.getText().toString();
				if(checkYes.isChecked())
					subwayString[5] = checkYes.getText().toString();

				if(checkLettuce.isChecked())
					subwayString[6] = "lettuce";
				else subwayString[6] = "No";
				if(checkTomatoes.isChecked())
					subwayString[7] = "tomatoes";
				else subwayString[7] = "No";
				if(checkBellPepper.isChecked())
					subwayString[8] = "bellpepper";
				else subwayString[8] = "No";
				if(checkRedOnion.isChecked())
					subwayString[9] = "redonion";
				else subwayString[9] = "No";
				if(checkCucumber.isChecked())
					subwayString[10] = "cucumber";
				else subwayString[10] = "No";
				if(checkSpinach.isChecked())
					subwayString[11] = "spinach";
				else subwayString[11] = "No";
				
				//indicator for checkout activity
				String intentID = "subway";
				
				Intent subwayCheckout = new Intent(getApplicationContext(), CheckoutActivity.class);
				//subwayCheckout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Bundle extras = new Bundle();
				//putExtra to the Bundle
				extras.putString("intentKey", intentID);
				extras.putString("Set-Cookie", PHPSESSID);
				extras.putStringArray("activityKey", subwayString);
				extras.putIntArray("price", subwayPrice);
				subwayCheckout.putExtras(extras);
				startActivity(subwayCheckout);
				
				
			}
			
		});
	}
}
