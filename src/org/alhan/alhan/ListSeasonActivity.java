package org.alhan.alhan;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListSeasonActivity extends ListActivity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        
        SQLiteDatabase db = dbOpenHelper.getDB();
        Cursor cursor = db.query("season", new String[]{"season"}, null, null, null,null, "season");
        List<String> seasons = new ArrayList<String>();
        cursor.moveToFirst();
        do {
        	String value = cursor.getString(cursor.getColumnIndex("season"));
        	seasons.add(value);
        } while (cursor.moveToNext());
        
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seasons);
        setListAdapter(adapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent intent = new Intent(this, ListHymnsActivity.class);
    	intent.putExtra("event_id", position);
    	startActivity(intent);
    }

}