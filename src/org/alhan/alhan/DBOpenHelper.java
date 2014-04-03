/**
 * 
 */
package org.alhan.alhan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author schintada
 *
 */
public class DBOpenHelper extends SQLiteOpenHelper {

	private static final String SOURCE_DB_NAME = "AlhanSQL.sqlite";
	private static final String TARGET_DB_NAME = "AlhanSQL.db";
	private SQLiteDatabase db;
	private Context dbContext;
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @throws  
	 */
	public DBOpenHelper(Context context) {
		super(context, TARGET_DB_NAME, null, 1);
		
		dbContext = context;
		if (!dbFileExists()) {
			getReadableDatabase();
			copyDatabaseFromAssets();			
		}
		db = openDatabase();
	}


	private boolean dbFileExists() {
		return (dbContext.getDatabasePath(TARGET_DB_NAME).isFile() && dbContext.getDatabasePath(TARGET_DB_NAME).exists());
	}


	private SQLiteDatabase openDatabase() {
		SQLiteDatabase db = null;
		
		try {
			db = SQLiteDatabase.openDatabase(dbContext.getDatabasePath(TARGET_DB_NAME).getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
		} catch (Exception e) {
		}
		return db;
	}


	private void copyDatabaseFromAssets() {
		InputStream inputStream = null;
		OutputStream fos = null;
		try {
			inputStream = dbContext.getAssets().open(SOURCE_DB_NAME);
			fos = new FileOutputStream(dbContext.getDatabasePath(TARGET_DB_NAME));
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.flush();
					fos.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				// DO NOTHING
			}
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


	public SQLiteDatabase getDB() {
		return db;
	}

}
