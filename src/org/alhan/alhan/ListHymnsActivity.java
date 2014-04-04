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
        
        int eventId = (int) getIntent().getExtras().get("event_id");
        
        String seasonName = getIntent().getStringExtra("season_name");

		Cursor cursor = db.query("hymn", new String[]{"hymn_name"}, "hymn_event_id_fk = " + eventId , null, null,null, "hymn_name");
        List<String> hymns = new ArrayList<String>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
        	do {
        		String value = cursor.getString(cursor.getColumnIndex("hymn_name"));
        		hymns.add(value);
        	} while (cursor.moveToNext());
        	
        }
		return hymns;
	}
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO: Add the DetailHymnActivity here
    }

}