package org.alhan.alhan;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

		//Cursor cursor = db.query("hymn", new String[]{"hymn_name"}, "hymn_event_id_fk = " + seasonId , null, null,null, "hymn_name");
		//Cursor cursor = db.query("event", new String[]{"event_name"}, "event_season_fk = " + seasonId , null, null,null, "event_id");
        //Cursor cursor = db.rawQuery("Select h.hymn_name from hymn h where hymn_event_id_fk in (select event_id from event where event_season_fk="+seasonId+")",null);
        Cursor cursor = db.rawQuery("Select h.hymn_name, e.event_name, h.hymn_desc from hymn h inner join event e on e.event_id = h.hymn_event_id_fk where e.event_season_fk = "+seasonId+" order by hymn_order",null);
        List<String> hymns = new ArrayList<String>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
        	do {
        		String hymnName = cursor.getString(cursor.getColumnIndex("hymn_name"));
        		//String eventName = cursor.getString(cursor.getColumnIndex("event_name"));
        		//String hymnDesc = cursor.getString(cursor.getColumnIndex("hymn_desc"));
        		hymns.add(hymnName);
        		//hymns.add(eventName);
        		//hymns.add(hymnDesc);
        	} while (cursor.moveToNext());
        	
        }
        db.close();
		return hymns;
	}
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO: Add the DetailHymnActivity here
    }

}