package info.jproject.opensource.foodordering;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HallwayCustomAdapterListView extends ArrayAdapter<Object> {
	
	private final Context context;
	private final String[] place;
	private final TypedArray icon;

	public HallwayCustomAdapterListView(Context context, String [] food_products,
			TypedArray icon_food_products) {
		super(context, R.layout.list_item, food_products);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.place = food_products;
		this.icon = icon_food_products;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.list_item, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.labellv);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.imagelv);
		textView.setText(place[position]);
		imageView.setImageResource(icon.getResourceId(position, -1));
		
		return rowView;
	}
	
	
	
}
