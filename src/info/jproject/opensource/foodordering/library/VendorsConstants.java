package info.jproject.opensource.foodordering.library;

import info.jproject.opensource.foodordering.R;

public class VendorsConstants {
	
	private String error = "-1";
	
	private String VendA = "The University Center";
	private String VendB = "Mary and Jeff Bell Library";
	private String VendC = "Island Hall";
	private String VendD = "Center for Instruction Learning";
	private String VendE = "Bay Hall";
	private String VendF = "Faculty Hall";
	
	private String VendAInfo = "Stop by The Commons Market, located on the first floor of the University Center and choose from a wide variety of fresh food designed to satisfy everyone's appetite with food choices to rival your favorite restaurants.";
	private String VendBInfo = "Our menu includes hot and cold made-to-order sub sandwiches and is ideal for  those on the run. All our sandwiches are made with low-fat deli meats and freshly baked breads. Bottled drinks and chips are also available.";
	private String VendCInfo = "Einstein Bros®, known for their bagels, cream cheese schmears and coffee heritage, will introduce an exceptional morning alternative that’s fast, hot and packed with flavor. But it doesn’t stop there. Einstein Bros. bistro salads, soups and sandwiches will be a great lunch attraction, while oversized brownies, krispie treats, cookies, pound cake and muffins will make for delicious anytime snacking.";
	private String VendDInfo = "It’s food with an attitude! It’s our newest Serving delicious, authentic, Mexican cuisine at a great value, it&apos;s a fiesta of tantalizing tastes and aromas that are sure to bring you back for more. Try a delicious loco nachos, chicken Quesadilla, or steak taco with some of our exceptional lime cilantro rice and simmering black beans. You will only find these delicious combinations at Ultimate Baja.";
	private String VendEInfo = "“Simply To Go” is a comprehensive convenient meal solutions program targeted at customers that need a food fix in a hurry. The grab and go menu consists of sandwiches, salads desserts and hot microwaveable meals.";
	private String VendFInfo = "Starbuck&apos;s has Faculty Hall as it&apos;s new home, boasting a beautiful patio area for studying or lounging with friends. We offer the full line of coffees, expresso drinks, teas and pastries for your enjoyment.";

	private int VendAIcon = R.drawable.universitycenter;
	private int VendBIcon = R.drawable.subway;
	private int VendCIcon = R.drawable.einsteinbros;
	private int VendDIcon = R.drawable.ultimatebaja;
	private int VendEIcon = R.drawable.simplytogo;
	private int VendFIcon = R.drawable.starbucks;
	
	public String getVendorText(int id) {
		
		switch (id) {
		case 0: return VendA;
		case 1: return VendB;
		case 2: return VendC;
		case 3: return VendD;
		case 4: return VendE;
		case 5: return VendF;
		default: return error;
		}
	}
	
	public String getVendorInfo(int id) {
		
		switch (id) {
		case 0: return VendAInfo;
		case 1: return VendBInfo;
		case 2: return VendCInfo;
		case 3: return VendDInfo;
		case 4: return VendEInfo;
		case 5: return VendFInfo;
		default: return error;
		}
	}
	
	public int getVendorIcon(int id) {
		
		switch (id) {
		case 0: return VendAIcon;
		case 1: return VendBIcon;
		case 2: return VendCIcon;
		case 3: return VendDIcon;
		case 4: return VendEIcon;
		case 5: return VendFIcon;
		default: return Integer.parseInt(error);
		}
	}
	
}
