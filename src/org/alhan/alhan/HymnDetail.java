package org.alhan.alhan;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HymnDetail extends Activity implements OnPreparedListener, MediaController.MediaPlayerControl{
	
	ToggleButton tb;
	TextView tx1;
	String copticText, englishText;

	private static final String TAG = "AudioPlayer";

	  public static final String AUDIO_FILE_NAME = "http://www.alhan.org/advent/mp3/apaheet.mp3";

	  private MediaPlayer mediaPlayer;
	  private MediaController mediaController;
	  private String audioFile;

	  private Handler handler = new Handler();
	  
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
		
		// Audio
		this.getIntent().putExtra(AUDIO_FILE_NAME,AUDIO_FILE_NAME); 	
		audioFile = this.getIntent().getStringExtra(AUDIO_FILE_NAME);
		 	
		    ((TextView)findViewById(R.id.now_playing_text)).setText(audioFile);

		    mediaPlayer = new MediaPlayer();
		    mediaPlayer.setOnPreparedListener(this);

		    mediaController = new MediaController(this);

		    try {
		      mediaPlayer.setDataSource(audioFile);
		      mediaPlayer.prepare();
		      mediaPlayer.start();
		    } catch (IOException e) {
		      Log.e(TAG, "Could not open file " + audioFile + " for playback.", e);
		    }

	}
	
	@Override
	  protected void onStop() {
	    super.onStop();
	    mediaController.hide();
	    mediaPlayer.stop();
	    mediaPlayer.release();
	  }

	  @Override
	  public boolean onTouchEvent(MotionEvent event) {
	    //the MediaController will hide after 3 seconds - tap the screen to make it appear again
	    mediaController.show();
	    return false;
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

	public void start() {
	    mediaPlayer.start();
	  }

	  public void pause() {
	    mediaPlayer.pause();
	  }

	  public int getDuration() {
	    return mediaPlayer.getDuration();
	  }

	  public int getCurrentPosition() {
	    return mediaPlayer.getCurrentPosition();
	  }

	  public void seekTo(int i) {
	    mediaPlayer.seekTo(i);
	  }

	  public boolean isPlaying() {
	    return mediaPlayer.isPlaying();
	  }

	  public int getBufferPercentage() {
	    return 0;
	  }

	  public boolean canPause() {
	    return true;
	  }

	  public boolean canSeekBackward() {
	    return true;
	  }

	  public boolean canSeekForward() {
	    return true;
	  }
	  public void onPrepared(MediaPlayer mediaPlayer) {
		    Log.d(TAG, "onPrepared");
		    mediaController.setMediaPlayer(this);
		    mediaController.setAnchorView(findViewById(R.id.SCROLLER_ID));

		    handler.post(new Runnable() {
		      public void run() {
		        mediaController.setEnabled(true);
		        mediaController.show();
		      }
		    });
		  }

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
