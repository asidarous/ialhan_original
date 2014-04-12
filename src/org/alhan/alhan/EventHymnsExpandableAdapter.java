package org.alhan.alhan;

import org.alhan.alhan.model.Event;
import org.alhan.alhan.model.Hymn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class EventHymnsExpandableAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private SparseArray<Event> events;
	private LayoutInflater inflater;

	public EventHymnsExpandableAdapter(Activity act, SparseArray<Event> events) {
		this.activity = act;
		this.events = events;
		inflater = act.getLayoutInflater();
	}

	@Override
	public int getGroupCount() {
		return events.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return events.get(groupPosition).getHymns().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return events.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return events.get(groupPosition).getHymns().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
	    if (convertView == null) {
	        convertView = inflater.inflate(R.layout.event_row_group, null);
	      }
	    Event event = (Event) getGroup(groupPosition);
	      ((CheckedTextView) convertView).setText(event.getName());
	      ((CheckedTextView) convertView).setChecked(isExpanded);
	      
	      if(groupPosition%2==0)
	        {
	    	  ((CheckedTextView) convertView).setBackgroundColor(Color.parseColor("#50FCFBE3"));
	        }else
	        {
	        	((CheckedTextView) convertView).setBackgroundColor(Color.parseColor("#50FAFAF7"));
	        }
	      return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final Hymn hymn = (Hymn) getChild(groupPosition, childPosition);
	    TextView text = null;
	    TextView descText = null;
	    if (convertView == null) {
	      convertView = inflater.inflate(R.layout.hymn_row_detail, null);
	    }
	    text = (TextView) convertView.findViewById(R.id.textView1);
		Typeface font = Typeface.createFromAsset(activity.getAssets(), "fonts/CS_Avva_Shenouda.ttf");
		text.setTypeface(font);
	    text.setText(hymn.getName());
	    descText = (TextView) convertView.findViewById(R.id.textView2);
		Typeface englishFont = Typeface.DEFAULT;
		descText.setTypeface(englishFont);
		descText.setText(hymn.getDesc());
	    convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, hymn.getId() + "  " + hymn.getName(),
			            Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(activity, HymnDetail.class);
				intent.putExtra("hymn_id", hymn.getId());
				intent.putExtra("hymn_name", hymn.getName());
				activity.startActivity(intent);


			}
		});
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
