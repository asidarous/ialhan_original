package org.alhan.alhan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HymnDetail extends Activity {
	
	ToggleButton tb;
	TextView tx1;
	String copticText, englishText;


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hymn_detail_view);

		// Change title background and text to be Coptic hymn name
		int titleId = getResources().getIdentifier("action_bar_title", "id","android");
		TextView titleTextView = (TextView) findViewById(titleId);
		Typeface font = Typeface.createFromAsset(getAssets(),"fonts/CS_Avva_Shenouda.ttf");
		titleTextView.setTextColor(Color.YELLOW);
		titleTextView.setTypeface(font);
		String hymnName = getIntent().getStringExtra("hymn_name");
		ColorDrawable colorDrawable = new ColorDrawable();
		colorDrawable.setColor(Color.argb(255, 153, 0, 0));
		getActionBar().setBackgroundDrawable(colorDrawable);
		getActionBar().setTitle(hymnName);
		
		getCopticTextFromQuery();
		tx1 = (TextView) findViewById(R.id.hymnView);
		
		setCopticText();

		tb = (ToggleButton) findViewById(R.id.languageButton);
		tb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 if (tb.getText().toString().equalsIgnoreCase("English")) {
					 setCopticText();
				 } else {
					 Typeface font = Typeface.DEFAULT;
					 tx1.setTypeface(font);
					 tx1.setText(englishText);
				 }
			}
		});
	}
	


	private void setCopticText() {
		Typeface font = Typeface.createFromAsset(getAssets(),"fonts/CS_Avva_Shenouda.ttf");
		 tx1.setTypeface(font);
		 tx1.setText(copticText);
	}

	private void getCopticTextFromQuery() {
		DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
		SQLiteDatabase db = dbOpenHelper.getDB();

		String hymnId = getIntent().getStringExtra("hymn_id");
		Cursor cursor = db.query("hymn", new String[] { "hymn_coptic, hymn_english",
				"hymn_english" }, "hymn_id = " + Integer.parseInt(hymnId),
				null, null, null, null);
		cursor.moveToFirst();
		copticText = cursor.getString(cursor.getColumnIndex("hymn_coptic"));
		englishText = cursor.getString(cursor.getColumnIndex("hymn_english"));
		
	}

}
