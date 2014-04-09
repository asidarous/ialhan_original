package org.alhan.alhan;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HymnDetail extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coptic_hymn_text);
		String copticText = getCopticTextFromQuery();
		TextView tx1 = (TextView) findViewById(R.id.copticHymnView);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/CS_Avva_Shenouda.ttf");
		tx1.setTypeface(font);
		tx1.setText(copticText);

	}

	private String getCopticTextFromQuery() {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
		SQLiteDatabase db = dbOpenHelper.getDB();
		
        String hymnId = getIntent().getStringExtra("hymn_id");
        Cursor cursor = db.query("hymn", new String[]{"hymn_coptic","hymn_english"}, "hymn_id = "+Integer.parseInt(hymnId), null, null,null, null);
        cursor.moveToFirst();
        String copticText = cursor.getString(cursor.getColumnIndex("hymn_coptic"));
        return copticText;
	}

}
