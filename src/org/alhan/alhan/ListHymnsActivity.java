package org.alhan.alhan;

import org.alhan.alhan.model.Event;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

public class ListHymnsActivity extends Activity {

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hymns);
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
        String seasonName = getIntent().getStringExtra("season_name");
        getActionBar().setTitle(seasonName); 
        }
        SparseArray<Event> events = getEvents();
        
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        EventHymnsExpandableAdapter adapter = new EventHymnsExpandableAdapter(this, events);
        listView.setAdapter(adapter);
    }

	private SparseArray<Event> getEvents() {
		
		SparseArray<Event> events = new SparseArray<Event>();
		
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        
        SQLiteDatabase db = dbOpenHelper.getDB();
        
        String seasonId = getIntent().getStringExtra("season_id");
        
        
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("hymn join event on hymn_event_id_fk = event_id");
		Cursor cursor = qb.query(db, new String[]{"hymn_name",  "hymn_id", "hymn_event_id_fk", "event_name", "hymn_desc"}, "event_season_fk = " + seasonId , null, null,null, "event_id, hymn_order");

        cursor.moveToFirst();
        int i = -1;
        String previousEventId = "";
        if (!cursor.isAfterLast()) {
        	do {
        		String hymnId = cursor.getString(cursor.getColumnIndex("hymn_id"));
        		String hymnName = cursor.getString(cursor.getColumnIndex("hymn_name"));
        		String eventId = cursor.getString(cursor.getColumnIndex("hymn_event_id_fk"));
        		String eventName = cursor.getString(cursor.getColumnIndex("event_name"));
        		String hymnDesc = cursor.getString(cursor.getColumnIndex("hymn_desc"));
        		if (!eventId.equals(previousEventId)) {
        			Event newEvent = new Event(eventId, eventName);
        			events.put(++i, newEvent);
        			previousEventId = eventId;        			
        		}
        		events.get(i).addHymn(hymnId, hymnName, hymnDesc);
        		
        	} while (cursor.moveToNext());
        	
        }
        db.close();

        return events;
	}

}