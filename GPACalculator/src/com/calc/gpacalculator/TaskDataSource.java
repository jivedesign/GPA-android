package com.calc.gpacalculator;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TaskDataSource {

	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { 
			  MySQLiteHelper.COLUMN_ID,
			  MySQLiteHelper.COLUMN_NAME, 
			  MySQLiteHelper.COLUMN_AVG, 
			  MySQLiteHelper.COLUMN_TOTAL, 
			  MySQLiteHelper.COLUMN_COURSE,
			  MySQLiteHelper.COLUMN_SEM, };
	  
	  public TaskDataSource(Context context) {
		    dbHelper = new MySQLiteHelper(context);
		  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }
		  
	  public Task createTask(int ID, String taskname, float average, float total, String course, String sem) {
		    ContentValues values = new ContentValues();
		    values.put(MySQLiteHelper.COLUMN_ID, ID);
		    values.put(MySQLiteHelper.COLUMN_NAME, taskname);
		    values.put(MySQLiteHelper.COLUMN_AVG, average);
		    values.put(MySQLiteHelper.COLUMN_TOTAL, total);
		    values.put(MySQLiteHelper.COLUMN_COURSE, course);
		    values.put(MySQLiteHelper.COLUMN_SEM, sem);
		    
		    long insertId = database.insert(MySQLiteHelper.TABLE_TASKS, null,
		        values);
		    Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS,
		        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Task newComment = cursorToTask(cursor);
		    cursor.close();
		    return newComment;
		  }
	  
	  public void deleteTask(Task task) {
		    long id = Task.getID();
		    System.out.println("Task deleted with id: " + id);
		    database.delete(MySQLiteHelper.TABLE_TASKS, MySQLiteHelper.COLUMN_ID
		        + " = " + id, null);
		  }
	  public List<Task> getAllTasks() {
		    List<Task> list_of_tasks = new ArrayList<Task>();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_TASKS,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Task task = cursorToTask(cursor);
		      list_of_tasks.add(task);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return list_of_tasks;
		  }
	  private Task cursorToTask(Cursor cursor) {
		    Task task = new Task(0, "", 0, 0, "", "");
		    task.setID(cursor.getInt(0));
		    task.setName(cursor.getString(1));
		    task.setAverage(cursor.getFloat(2));
		    task.setTotal_marks(cursor.getFloat(3));
		    task.setClass_name(cursor.getString(4));
		    task.setSemester(cursor.getString(5));
		    return task;
		  }
	  
	  public int getNewID(){
		  
		  int newID;
		  String countQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_TASKS;
		  Cursor cursor = database.rawQuery(countQuery, null);
		  newID = cursor.getCount() + 1;
		  Log.d("newID", Integer.toString(newID));
		  
		  return newID;
	  }
	  
}
