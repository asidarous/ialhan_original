package org.alhan.alhan;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class SpecialArrayAdapter<T> extends ArrayAdapter<T>{
    public SpecialArrayAdapter(Context context,int resource, int textViewResourceId, List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View view = super.getView(position, convertView, parent);
    	TextView tv = (TextView) view.findViewById(R.id.customFont);
        if(position%2==0)
        {
        	tv.setBackgroundColor(Color.parseColor("#50FCFBE3"));
        }else
        {
        	tv.setBackgroundColor(Color.parseColor("#50FAFAF7"));
        }
        return view;
    }
 
}

