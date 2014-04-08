package org.alhan.alhan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListHymnsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_season);
        
        
        List<String> hymns = getHymns();
        ListAdapter adapter = new CustomFontArrayAdapter<String>(this, R.layout.activity_list_season, R.id.customFont, hymns);
        setListAdapter(adapter);
    }

	private List<String> getHymns() {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        
        SQLiteDatabase db = dbOpenHelper.getDB();
        
        String seasonId = getIntent().getStringExtra("season_id");

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("hymn join event on hymn_event_id_fk = event_id");
		Cursor cursor = qb.query(db, new String[]{"hymn_name", "event_name"}, "event_season_fk = " + seasonId , null, null,null, "event_name, hymn_name");

        List<String> hymns = new ArrayList<String>();
        Map<String, List<String>> events = new TreeMap<String, List<String>>();
        
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
        	do {
        		String hymnName = cursor.getString(cursor.getColumnIndex("hymn_name"));
        		String eventName = cursor.getString(cursor.getColumnIndex("event_name"));
        		//String hymnDesc = cursor.getString(cursor.getColumnIndex("hymn_desc"));
        		//hymns.add(eventName + "  --  " + hymnName);
        		addHymnToEvent(events, eventName, hymnName);
        		//hymns.add(eventName);
        		//hymns.add(hymnDesc);
        	} while (cursor.moveToNext());
        	
        }
        db.close();
        
        for(Entry<String, List<String>> entry : events.entrySet()) {
        	String eventName = entry.getKey();
        	hymns.add(eventName);
        	for (String hymnName : entry.getValue()) {
        		hymns.add("    " + hymnName);
        	}
        }
		return hymns;
	}
    
    private void addHymnToEvent(Map<String, List<String>> events, String eventName, String hymnName) {    	
    	if (!events.containsKey(eventName)) {
    		events.put(eventName, new ArrayList<String>());
    	}
   		events.get(eventName).add(hymnName);
	}

	@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO: Add the DetailHymnActivity here
    }

}