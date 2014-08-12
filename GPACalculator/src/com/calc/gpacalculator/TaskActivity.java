package com.calc.gpacalculator;

import java.util.ArrayList;
import java.util.List;

import view.MainActivity;
import controller.Gpa_ListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
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
	final Context context = this;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.task_activity);
	    
		task_list = (ListView) findViewById(R.id.view_tasklist);
		setup_adapter();
		
	}

	private void setup_add() {
		LayoutInflater li = LayoutInflater.from(context);
		
		// Get XML file to view
		View promptsView = li.inflate(R.layout.add_task_dialog, null);
		
		//Create a new AlertDialog
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		
		//Link the alertdialog to the XML 
		alertDialogBuilder.setView(promptsView);
		
		//Create the buttons
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("Save",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
			
		    	//Add new task to the DB
		    	
		    	
		    }
		  })
		.setNegativeButton("Cancel",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
			dialog.cancel();
		    }
		  });
		
		AlertDialog alertDialog = alertDialogBuilder.create();
		
		alertDialog.show();

		
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
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.task_menu, menu);

        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()){
		
		 case R.id.addtask_menu:
			 setup_add();
			 
		      break;	   
		 default:
	      break;
	    }
		return true;
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
