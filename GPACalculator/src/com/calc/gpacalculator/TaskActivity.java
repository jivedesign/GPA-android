package com.calc.gpacalculator;

import java.util.ArrayList;
import java.util.List;

import view.MainActivity;
import controller.Gpa_ListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import controller.*;
import com.calc.gpacalculator.*;

public class TaskActivity extends ActionBarActivity {

	//private Button add_task;
	private ListView task_list;
	private Gpa_ListAdapter adapter;
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.task_activity);
	    
		//add_task = (Button) findViewById(R.id.add_taskButton);
		task_list = (ListView) findViewById(R.id.view_tasklist);
		
		setup_adapter();
	//	setup_button();
		
	}

	private void setup_adapter(){
		
		TaskDataSource tds1 = new TaskDataSource(getApplicationContext());
		
		tds1.open();
		List<Task> tasks = tds1.getAllTasks();
		
		
		adapter = new Gpa_ListAdapter(this, R.layout.task_entity, tasks);
		ListView activity_taskview = task_list;
		activity_taskview.setAdapter(adapter);
		tds1.close();
	}
	
//	private void setup_button(){
//	add_task.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				
//				int newID;
//				
//				newID = tds.getNewID();
//				
//				
//				
//				adapter.insert(tds.createTask(newID, "", 0, 0, "", ""), 0);
//			    String selectQuery = "SELECT * FROM " + MySQLiteHelper.TABLE_TASKS;
//			    
//				MySQLiteHelper dbHelper = new MySQLiteHelper(getBaseContext());
//				SQLiteDatabase db = dbHelper.getWritableDatabase();;
//			    Cursor cursor = db.rawQuery(selectQuery, null);
//
//			           if (cursor.moveToFirst())
//			    {
//			        do
//			        {   			  
//			        				  int i = cursor.getColumnCount();
//			        				  for (int j = 0; j<i;j++){
//			                          Log.d("database",cursor.getString(j));
//			                          
//			        				  }
//			                     }
//			              while (cursor.moveToNext());
//			    }
//			}
//			
//		});
//	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_menu, menu);

        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	
	}
	
	
	
	@Override
	public void onPause() {
	    super.onPause();  // Always call the superclass method first
	    
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	   
	   
	}
}
