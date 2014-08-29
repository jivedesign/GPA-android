package com.calc.gpacalculator;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CourseDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_COURSE_NAME,
			MySQLiteHelper.COLUMN_COURSE_ID,
			MySQLiteHelper.COLUMN_SEM2COURSE_ID };

	public CourseDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Course createCourse(int ID, String cName, int s2c_ID) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COURSE_NAME, cName);
		values.put(MySQLiteHelper.COLUMN_COURSE_ID, ID);
		values.put(MySQLiteHelper.COLUMN_SEM2COURSE_ID, s2c_ID);

		long insertId = database.insert(MySQLiteHelper.TABLE_COURSES, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COURSES, allColumns,
				MySQLiteHelper.COLUMN_COURSE_ID + " = " + insertId, null, null, null,
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
		  newID = cursor.getCount();
		  if(newID == 0){
			  cursor.close();
			  return newID;
		  }else{
			 newID = newID + 1; 
		  }
		  
		  Log.d("database", "newID (course) = " +Integer.toString(newID));
		  cursor.close();
		  return newID;

	  }
	  
	  public List<Course> getCoursesfromSem (int semID){
		  
		  List<Course> course_list = new ArrayList<Course>();
		  
		  String query = "SELECT c.coursename, c.courseID, c.sem2courseID FROM " + MySQLiteHelper.TABLE_SEMESTERS + " s, " + MySQLiteHelper.TABLE_COURSES +
				  		" c" + " WHERE " + "s." + MySQLiteHelper.COLUMN_SEM_ID + " = " + " c." + MySQLiteHelper.COLUMN_SEM2COURSE_ID +
				  		" AND " + "s." + MySQLiteHelper.COLUMN_SEM_ID + " = " + semID;
		   
		  Log.d("database", "QUERY = " + query);
		  
		  
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
	  
	  
	  public Float getCourseGradefromTasks (int courseID) {
		  float course_grade = 0;
		  		  
		  String query = "SELECT " + MySQLiteHelper.COLUMN_ID + " FROM " + MySQLiteHelper.TABLE_TASKS
				  + " WHERE " + courseID + " = " + MySQLiteHelper.COLUMN_COURSE2TASK_ID;
		  
		  Cursor cursor = database.rawQuery(query, null);
		  
		  cursor.moveToFirst();
		  while(!cursor.isAfterLast()){
			  int taskID = cursor.getInt(0); 
					  
			  float weighted_course_grade = taskDataSource.getTaskGrade(taskID);
			  course_grade += weighted_course_grade;
			  cursor.moveToNext();
		  }
		  cursor.close();
		  
	  }  
	  
	 
}
