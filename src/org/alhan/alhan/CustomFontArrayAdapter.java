package org.alhan.alhan;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomFontArrayAdapter<T> extends ArrayAdapter<T> {

	private Context context;
	
	public CustomFontArrayAdapter(Context context, int resource, int textViewResourceId, List<T> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.activity_list_season, parent, false);
		TextView text = (TextView) rowView.findViewById(R.id.customFont);
		Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/CS_Avva_Shenouda.ttf");
		text.setTypeface(font);
		T item = getItem(position);
		if (item instanceof CharSequence) {
			text.setText((CharSequence)item);
		} else {
			text.setText(item.toString());
		}
		return rowView;

	}
}
