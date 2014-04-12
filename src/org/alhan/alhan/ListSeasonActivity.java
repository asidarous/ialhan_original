package org.alhan.alhan;

import java.util.ArrayList;
import java.util.List;

import org.alhan.alhan.model.Season;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListSeasonActivity extends ListActivity {

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		int layoutResID = 0;
		if (android.os.Build.VERSION.SDK_INT >= 11){
			getActionBar().setTitle("Seasons");
			layoutResID = R.layout.activity_list_season;
		}else{
			layoutResID = R.layout.activity_list_season_old_os;
		}
		setContentView(layoutResID);

		List<Season> seasons = addListFromQuery();
		ListAdapter adapter = null;
		//ListAdapter adapter = new ArrayAdapter<Season>(this, R.layout.activity_list_season, R.id.customFont, seasons);
		adapter = new SpecialArrayAdapter<Season>(this, layoutResID, R.id.customFont, seasons);
			
		setListAdapter(adapter);
	}

	private List<Season> addListFromQuery() {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

		SQLiteDatabase db = dbOpenHelper.getDB();
		Cursor cursor = db.query("season", new String[]{"season", "season_id"}, null, null, null,null, "season_id");
		List<Season> seasons = new ArrayList<Season>();
		cursor.moveToFirst();
		do {
			String name = cursor.getString(cursor.getColumnIndex("season"));
			String id = cursor.getString(cursor.getColumnIndex("season_id"));
			seasons.add(new Season(name, id));
		} while (cursor.moveToNext());
		db.close();
		return seasons;
	}
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Season seasonClicked = (Season) l.getItemAtPosition(position);
    	Intent intent = new Intent(this, ListHymnsActivity.class);
    	intent.putExtra("season_id", seasonClicked.getSeasonId());
    	intent.putExtra("season_name", seasonClicked.toString());
    	Toast.makeText(this, "Season " + seasonClicked + ", id=" + seasonClicked.getSeasonId(), Toast.LENGTH_SHORT).show();
    	startActivity(intent);
    }

}
