package view;

import java.util.ArrayList;

import com.calc.gpacalculator.R;
import com.calc.gpacalculator.R.id;
import com.calc.gpacalculator.R.layout;
import com.calc.gpacalculator.R.menu;
import com.calc.gpacalculator.TaskActivity;

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

public class MainActivity extends ActionBarActivity {


	
	private TextView gpa_view;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gpa_view = (TextView) findViewById(R.id.main_gpa);

		
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
	      gpa_view.setText("4.0");
	      break;
	    case R.id.itemid_1:   	
	    	Intent intent = new Intent(this, TaskActivity.class);
	    	startActivity(intent);
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
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	    Toast.makeText(this, "resumed", Toast.LENGTH_SHORT)
        .show();
	   
	}
	
}
