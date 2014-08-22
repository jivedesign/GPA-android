package com.calc.gpacalculator;

import java.util.List;

import controller.Course_ListAdapter;
import controller.Semester_ListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SemesterActivity extends ActionBarActivity {

	private String semester_name;
	private Semester_ListAdapter adapter;
	private ListView semester_listview;
	final Context context = this;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.semester_activity);
	    semester_listview = (ListView) findViewById(R.id.view_semesterlist);
	    setup_adapter();
	}
	
	private void setup_add() {

		LayoutInflater li = LayoutInflater.from(context);
		final SemesterDataSource sds = new SemesterDataSource(context);
				
				
		// Get XML file to view
		View promptsView = li.inflate(R.layout.add_semester_dialog, null);
		
		final EditText coursename_edit = (EditText) promptsView.findViewById(R.id.semestername_dialog);
		
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
		    
		    	// If a field is left empty show error message close dialog, otherwise add to DB
		    	if(!coursename_edit.getText().toString().isEmpty()){
		    	semester_name = coursename_edit.getText().toString();
		    	sds.open();
		    	int sID = sds.getNewID();
		    	
		    	sds.createSemester(semester_name, sID);
		    	
		    	sds.close();
		  
		    	}else{
		    		showInValidInputMessage();
		    	}
	
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

	
	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.semester_menu, menu);

        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()){
		
		 case R.id.add_semester_menu:
			 setup_add();
			 
		      break;	   
		 default:
	      break;
	    }
		return true;
	}
	
	public void showInValidInputMessage() {
		Context context = getApplicationContext();
		CharSequence text = "Invalid Input";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		}
	
	
	
private void setup_adapter(){
		
		SemesterDataSource sds = new SemesterDataSource(context);
		
		
		sds.open();
		List<Semester> semesters_fromDB = sds.getAllSemesters(); //Change to get courses from a semester?
			
		adapter = new Semester_ListAdapter(this, R.layout.semester_entity, semesters_fromDB);
		
		final ListView activity_taskview = semester_listview;
		
		activity_taskview.setAdapter(adapter);
		
		
		
		
		activity_taskview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//Log.d("onclick", "HERE");
				Semester s = (Semester) activity_taskview.getItemAtPosition(position);
				Log.d("onclick", "Name from list: " + s.getName());
				
				Intent i = new Intent(getApplicationContext(), CourseActivity.class);
				i.putExtra("sID",s.getID());
				startActivity(i);

				
			}

            

        });
		
		sds.close();
	}


}
