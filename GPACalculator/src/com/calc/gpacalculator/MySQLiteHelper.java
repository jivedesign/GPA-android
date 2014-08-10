package com.calc.gpacalculator;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_TASKS = "tasks";
	   public static final String COLUMN_ID = "_id";
	   public static final String COLUMN_NAME = "coursename";
	   public static final String COLUMN_AVG = "average";
	   public static final String COLUMN_TOTAL = "total";
	   public static final String COLUMN_COURSE = "course";
	   public static final String COLUMN_SEM = "sem";

	   private static final String DATABASE_NAME = "tasks.db";
	   private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
		       + TABLE_TASKS + "(" + COLUMN_ID
		       + " integer primary key autoincrement, " + COLUMN_NAME
		       + " TEXT," + COLUMN_AVG + " FLOAT," + COLUMN_TOTAL + " FLOAT," + COLUMN_COURSE + " TEXT," + COLUMN_SEM + " TEXT" + ")";

	  
	  
	  
	  
	  
	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
		    onCreate(db);	
	}

}
