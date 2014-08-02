package com.calc.gpacalculator;

import java.util.ArrayList;

import controller.Gpa_ListAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

	// MOVE TO TASKACTIVITY.JAVA //
	
	private Button add_task;
	private ListView task_list;
	
	private Gpa_ListAdapter adapter;
	
	// END OF MOVE //
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// TO TEST CODE BELOW, CHANGE setContentView(R.layout.activity_main); TO setContentView(R.layout.task_activity);
		
//		/// MOVE TO TASKACTIVITY.JAVA ///
//		
//		add_task = (Button) findViewById(R.id.add_taskButton);
//		task_list = (ListView) findViewById(R.id.view_tasklist);
//		
//		
//			// CREATE IN NEW SETUP ADAPTER METHOD //
//		adapter = new Gpa_ListAdapter(this, R.layout.task_entity, new ArrayList<Task>());
//		ListView activity_taskview = task_list;
//		activity_taskview.setAdapter(adapter);
//			//	END OF NEW METHOD //
//		
//			//	CREATE IN NEW SETUP BUTTON METHOD //
//		add_task.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				
//				adapter.insert(new Task("", 0, 0), 0);
//				
//			}
//			
//		});
//		
//			//	END OF NEW METHOD	//
//		
//		
//		//// END OF MOVE ////
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
		switch (item.getItemId()) {
	  
	    case R.id.itemid_0:
	      Toast.makeText(this, "Item1 selected", Toast.LENGTH_SHORT)
	          .show();
	      break;
	    case R.id.itemid_1:
		      Toast.makeText(this, "Item1 selected", Toast.LENGTH_SHORT)
		          .show();
		      break;
	    case R.id.itemid_2:
		      Toast.makeText(this, "Item1 selected", Toast.LENGTH_SHORT)
		          .show();
		      break;
	   
	  
	    default:
	      break;
	    }
		return true;
	}
}
