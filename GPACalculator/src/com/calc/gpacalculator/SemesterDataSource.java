package com.calc.gpacalculator;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SemesterDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { 
			MySQLiteHelper.COLUMN_SEM_NAME,
			MySQLiteHelper.COLUMN_SEM_ID};
	
	public SemesterDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Semester createSemester(String sName, int sID) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_SEM_NAME, sName);
		values.put(MySQLiteHelper.COLUMN_SEM_ID, sID);

		long insertId = database.insert(MySQLiteHelper.TABLE_SEMESTERS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SEMESTERS, allColumns,
				MySQLiteHelper.COLUMN_SEM_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Semester newComment = cursorToSemester(cursor);
		cursor.close();
		return newComment;
	}
	
	 private Semester cursorToSemester(Cursor cursor) {
		    Semester semester = new Semester("", 0);
		    semester.setName(cursor.getString(0));
		    semester.setID(cursor.getInt(1));
		    
		    return semester;
		  }

	  public void deleteSemester(Semester semester) {
		    long id = semester.getID();
		    System.out.println("Semester deleted with id: " + id);
		    database.delete(MySQLiteHelper.TABLE_SEMESTERS, MySQLiteHelper.COLUMN_SEM_ID
		        + " = " + id, null);
		  }
	 
	  public List<Semester> getAllSemesters() {
		    List<Semester> list_of_semesters = new ArrayList<Semester>();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_SEMESTERS,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Semester c = cursorToSemester(cursor);
		      list_of_semesters.add(c);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return list_of_semesters;
		  }
	  
	  public int getNewID(){
		  
		  int newID;
		  String countQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_SEMESTERS;
		  Cursor cursor = database.rawQuery(countQuery, null);
		  newID = cursor.getCount() + 1;
		  Log.d("newID", Integer.toString(newID));
		  
		  return newID;
	  }
	  
	  
	  public Float getGPAfromCourses(int semId, CourseDataSource cds) {
		  float semester_grade = 0;
  		  
		  String query = "SELECT " + MySQLiteHelper.COLUMN_COURSE_GRADE + " FROM " + MySQLiteHelper.TABLE_COURSES
				  + " WHERE " + semId + " = " + MySQLiteHelper.COLUMN_SEM2COURSE_ID;
		  
		  Cursor cursor = database.rawQuery(query, null);
		  
		  cursor.moveToFirst();
		  while(!cursor.isAfterLast()){
			  semester_grade += cursor.getFloat(0);
			  cursor.moveToNext();
		  }
		  cursor.close();
		  
		  return semester_grade;
	  }
	
}
