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

	public Course createCourse(String sName, float sID) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_SEM_NAME, sName);
		values.put(MySQLiteHelper.COLUMN_SEM_ID, sID);

		long insertId = database.insert(MySQLiteHelper.TABLE_SEMESTERS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SEMESTERS, allColumns,
				MySQLiteHelper.COLUMN_SEM_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Course newComment = cursorToCourse(cursor);
		cursor.close();
		return newComment;
	}
	
	 private Course cursorToCourse(Cursor cursor) {
		    Course course = new Course("", 0, 0,0);
		    course.setName(cursor.getString(0));
		    course.setID(cursor.getInt(1));
		    course.setSem2course(2);
		    
		    return course;
		  }

	  public void deleteCourse(Course course) {
		    long id = course.getID();
		    System.out.println("Course deleted with id: " + id);
		    database.delete(MySQLiteHelper.TABLE_COURSES, MySQLiteHelper.COLUMN_COURSE_ID
		        + " = " + id, null);
		  }
	 
	  public List<Course> getAllCourses() {
		    List<Course> list_of_courses = new ArrayList<Course>();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_COURSES,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Course c = cursorToCourse(cursor);
		      list_of_courses.add(c);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return list_of_courses;
		  }
	  
	  public int getNewID(){
		  
		  int newID;
		  String countQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_COURSES;
		  Cursor cursor = database.rawQuery(countQuery, null);
		  newID = cursor.getCount() + 1;
		  Log.d("newID", Integer.toString(newID));
		  
		  return newID;
	  }
	  
	  public List<Course> getCoursesfromSem (int semID){
		  
		  List<Course> course_list = new ArrayList<Course>();
		  
		  String query = "SELECT c.cNAME, c.cID, c.sID FROM " + MySQLiteHelper.TABLE_SEMESTERS + " s, " + MySQLiteHelper.TABLE_COURSES +
				  		" c" + " WHERE " + "s." + MySQLiteHelper.COLUMN_SEM_ID + " = " + " c." + MySQLiteHelper.COLUMN_SEM2COURSE_ID +
				  		" AND " + "s." + MySQLiteHelper.COLUMN_SEM_ID + " = " + semID;
		   
		  Cursor cursor = database.rawQuery(query, null);
		  
		  cursor.moveToFirst();
		  while(!cursor.isAfterLast()){
			  Course c = cursorToCourse(cursor);
			  course_list.add(c);
			  cursor.moveToNext();
		  }
		  cursor.close();
		  return course_list;
		  
	  }
	  
}
