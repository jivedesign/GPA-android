package controller;


import java.util.List;

import javax.xml.datatype.Duration;








import com.calc.gpacalculator.R;
import com.calc.gpacalculator.Task;


import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class Gpa_ListAdapter extends ArrayAdapter<Task>{
	
	private List<Task> list;
	private int layoutResourceId;
	private Context context;
	
	public Gpa_ListAdapter(Context context, int layoutResourceId, List<Task> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.list = items;
	}
	
	
	public static class TaskListHolder {
		Task task;
		TextView name;
		TextView mark_value;
		TextView total_value;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);
		
		TaskListHolder holder = new TaskListHolder();
		holder.task = list.get(position);
		
		holder.name = (TextView)row.findViewById(R.id.task_nameedit);
		setNameTextChangeListener(holder);
		holder.mark_value = (TextView)row.findViewById(R.id.your_markedit);
		//setValueTextListeners(holder);
		holder.total_value = (TextView)row.findViewById(R.id.total_edit);
		//setTotalTextListeners(holder);

		row.setTag(holder);

		setupItem(holder);
		return row;
		
		
	}
	
	private void setNameTextChangeListener(final TaskListHolder holder) {
		holder.name.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				holder.task.setName(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { 
				
				Toast.makeText(context, "CHANGED", Toast.LENGTH_SHORT).show();
				return;
				
			}
		});
	}

	private void setupItem(TaskListHolder holder) {
		holder.name.setText(holder.task.getName());
		holder.mark_value.setText(String.valueOf(holder.task.getAverage()));
		holder.total_value.setText(String.valueOf(holder.task.getTotal_marks()));
	}
	
	private void setValueTextListeners(final TaskListHolder holder) {
		holder.mark_value.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try{
					holder.task.setAverage((Float.parseFloat(s.toString())));
				}catch (NumberFormatException e) {
					//Log.e(LOG_TAG, "error reading double value: " + s.toString());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }
		});
	}
	
	private void setTotalTextListeners(final TaskListHolder holder) {
		holder.total_value.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try{
					holder.task.setTotal_marks((Float.parseFloat(s.toString())));
				}catch (NumberFormatException e) {
					//Log.e(LOG_TAG, "error reading double value: " + s.toString());
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

			@Override
			public void afterTextChanged(Editable s) { }
		});
	}

}
