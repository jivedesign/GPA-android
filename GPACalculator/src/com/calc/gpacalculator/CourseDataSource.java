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
			MySQLiteHelper.COLUMN_SEM2COURSE_ID,
			MySQLiteHelper.COLUMN_COURSE_GRADE};

	public CourseDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Course createCourse(int ID, String cName, int s2c_ID, float marks) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COURSE_NAME, cName);
		values.put(MySQLiteHelper.COLUMN_COURSE_ID, ID);
		values.put(MySQLiteHelper.COLUMN_SEM2COURSE_ID, s2c_ID);
		values.put(MySQLiteHelper.COLUMN_COURSE_GRADE, marks);

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
		    course.setSem2course(cursor.getInt(2));
		    course.setMark(cursor.getFloat(3));
		    
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
		  
		  Log.d("database", "newID (course) = " +Integer.toString(newID));
		  cursor.close();
		  return newID;

	  }
	  
	  /**
	   * 
	   * Change to account for grade column 
	   * 
	   * @param semID
	   * @return
	   */
	  
	  public List<Course> getCoursesfromSem (int semID){
		  
		  List<Course> course_list = new ArrayList<Course>();
		  
		  String query = "SELECT c.coursename, c.courseID, c.sem2courseID, c.coursegrade FROM " + MySQLiteHelper.TABLE_SEMESTERS + " s, " + MySQLiteHelper.TABLE_COURSES +
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
	  
	  public float getGradefromcID (int cID){
		  
		  float grade = 0;
		  
		  String query = "SELECT " + MySQLiteHelper.COLUMN_COURSE_GRADE + " FROM " + MySQLiteHelper.TABLE_COURSES 
				  		+ " WHERE " + cID + " = " + MySQLiteHelper.COLUMN_COURSE_ID ; 
		  
		  Cursor cursor = database.rawQuery(query, null);
		  
		  cursor.moveToFirst();
		  
		  grade = cursor.getFloat(0);
		  cursor.close();
		  return grade;
	  }
	  
	  
	  
	  public float getCourseGradefromTasks (int courseID, TaskDataSource tds) {
		  float course_grade = 0;
		  		  
		  String query = "SELECT " + MySQLiteHelper.COLUMN_ID + " FROM " + MySQLiteHelper.TABLE_TASKS
				  + " WHERE " + courseID + " = " + MySQLiteHelper.COLUMN_COURSE2TASK_ID;
		  
		  
		 List<Course> c = getAllCourses();
		 
		 int i = c.size();
		 
		 for (int j =0; j<i; j++ ){
			 
			 Log.d("update", " BEFORE cName: " + c.get(j).getName() + " cID: " + Integer.toString(c.get(j).getID() )
					 + " cGrade: " + Float.toString(c.get(j).getMark()));

		 }
		  
		  
		  
		  
		  
		  
		  
		  
		  Cursor cursor = database.rawQuery(query, null);
		  
		  cursor.moveToFirst();
		  while(!cursor.isAfterLast()){
			  int taskID = cursor.getInt(0); 					  
			  float weighted_course_grade = tds.getTaskGrade(taskID);
			  course_grade += weighted_course_grade;
			  cursor.moveToNext();
		  }
		  
		  Log.d("marks", "value of course_grade = " + Float.toString(course_grade));
		  
		  String updateCourseGrade = "UPDATE " + MySQLiteHelper.TABLE_COURSES
				  + " SET " + MySQLiteHelper.COLUMN_COURSE_GRADE + " = " + course_grade
				  + " WHERE " + MySQLiteHelper.COLUMN_COURSE_ID + " = " + courseID;
		  
	
			  
		  
		  
		  
		  
		  
		  
		  cursor = database.rawQuery(updateCourseGrade, null);
		  
	 List<Course> c1 = getAllCourses();
			 
			 int i1 = c.size();
			 
			 for (int j =0; j<i1; j++ ){
				 
				 Log.d("update", " AFTER cName: " + c1.get(j).getName() + " cID: " + Integer.toString(c1.get(j).getID() )
						 + " cGrade: " + Float.toString(c1.get(j).getMark()));

			 }
		  
		  
		  
		  String query1 = "SELECT " + MySQLiteHelper.COLUMN_ID + " FROM " + MySQLiteHelper.TABLE_TASKS
				  + " WHERE " + courseID + " = " + MySQLiteHelper.COLUMN_COURSE2TASK_ID;
		  
		  Cursor cursor1 = database.rawQuery(query1, null);
		  
		  
		//  Log.d("marks", "course_grade after update = " + Float.toString(cursor1.getFloat(0)));
		  
		  
		  
		  cursor.close();
		  
		  
		  return course_grade;
	  }  
	  
	 
}
