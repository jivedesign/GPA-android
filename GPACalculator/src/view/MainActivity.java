package view;

import java.util.ArrayList;
import java.util.List;

import com.calc.gpacalculator.R;
import com.calc.gpacalculator.R.id;
import com.calc.gpacalculator.R.layout;
import com.calc.gpacalculator.R.menu;
import com.calc.gpacalculator.Task;
import com.calc.gpacalculator.TaskActivity;
import com.calc.gpacalculator.TaskDataSource;

import controller.Gpa_ListAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


import java.util.List;
import java.util.Random;

import android.app.Activity.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ActionBarActivity {

	//private TextView gpa_view;

	private ListView testView;
	public TaskDataSource tds;
	public List<Task> values;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//gpa_view = (TextView) findViewById(R.id.main_gpa);
		this.deleteDatabase("tasks.db");
		tds = new TaskDataSource(this);
		tds.open();
	
		tds.createTask(0, "exam1", 10, 10, "Cmput101", "Fall2013");
		//tds.createTask(1, "exam2", 5, 10, "Cmput102", "Fall2013");
		//tds.createTask(2, "exam3", 2, 10, "Cmput103", "Fall2013");
		
		values = tds.getAllTasks();

		List<Integer> ls = new ArrayList();
		int i = values.size();
		for (int j = 0; j<i;j++){
		
		ls.add(values.get(j).getID());
		}
		
		testView = (ListView) findViewById(R.id.abcdef);
		
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, ls);
		
		adapter.toString();
		
		
		testView.setAdapter(adapter);
		
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

		switch (item.getItemId()) {	  
	    case R.id.itemid_0:
	     // gpa_view.setText("4.0");
	      break;
	    case R.id.itemid_1:   	
	    	Intent intent = new Intent(this, TaskActivity.class);
	    	startActivity(intent);
		      break;
	    case R.id.itemid_2:
		     
		      break;	   
	  
	    default:
	      break;
	    }
		return true;
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	 
	   
	}
	
	public List<Task> getValues(){
		return values;
	}
	
}
