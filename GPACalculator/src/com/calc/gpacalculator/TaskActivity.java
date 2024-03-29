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
import android.graphics.Typeface;
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
import android.widget.EditText;
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
	
	private int c2t_ID;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.task_activity);
	    
	    //TextView myTextView=(TextView)findViewById(R.id.task_nameedit);
	    //Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Light.ttf");
	    //myTextView.setTypeface(typeFace);
	    
	    Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	        c2t_ID = extras.getInt("cID");
	        Log.d("onclick", "oncreate task id " + Integer.toString(c2t_ID));
	    }

		task_list = (ListView) findViewById(R.id.view_tasklist);
		setup_adapter();
		
	}

	private void setup_add() {

		LayoutInflater li = LayoutInflater.from(context);
		
		// Get XML file to view
		View promptsView = li.inflate(R.layout.add_task_dialog, null);
		
		final EditText taskname_edit = (EditText) promptsView.findViewById(R.id.taskname_dialog);
		final EditText average_edit = (EditText) promptsView.findViewById(R.id.average_dialog);
		final EditText total_edit = (EditText) promptsView.findViewById(R.id.total_dialog);
		final EditText task_weight = (EditText) promptsView.findViewById(R.id.weight_dialog);
		
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
		    	TaskDataSource tds1 = new TaskDataSource(getApplicationContext());
		    	tds1.open();
		    	int newID = tds1.getNewID();
		    
		    	// If a field is left empty show error message close dialog, otherwise add to DB
		    	if(!taskname_edit.getText().toString().isEmpty() & 
		    			!average_edit.getText().toString().isEmpty() & 
		    			!total_edit.getText().toString().isEmpty() & 
		    			!task_weight.getText().toString().isEmpty()){
		    		
		    		String new_taskName = taskname_edit.getText().toString();
			    	Float float_avg_edit = Float.parseFloat(average_edit.getText().toString());
			    	Float float_total_edit = Float.parseFloat(total_edit.getText().toString());
			    	Float float_weight = Float.parseFloat(task_weight.getText().toString());
			    	

		    		Log.d("weight", Float.toString(float_weight));
		    		tds1.createTask(newID, new_taskName, float_avg_edit, float_total_edit, c2t_ID, float_weight,-1);

		    	}else{
		    		showInValidInputMessage();	
		    	}
		    	tds1.close();	
		    	//Call to update the list view
		    	setup_adapter();
		    }
		  })
		.setNegativeButton("Cancel",
		  new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
		    	// Do nothing
		    	dialog.cancel();
		    }
		  });
		
		AlertDialog alertDialog = alertDialogBuilder.create();
		
		alertDialog.show();

	}

	private void setup_adapter(){
		
		TaskDataSource tds1 = new TaskDataSource(getApplicationContext());
		
		tds1.open();
		List<Task> tasks = tds1.getTasksfromCourse(c2t_ID);
		
		
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
	
	public void showInValidInputMessage() {
		Context context = getApplicationContext();
		CharSequence text = "Invalid Input";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		}
	
}
