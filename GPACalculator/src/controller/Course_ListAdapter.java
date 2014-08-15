package controller;

import java.util.List;

import com.calc.gpacalculator.R;
import com.calc.gpacalculator.Task;

import controller.Gpa_ListAdapter.TaskListHolder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Course_ListAdapter extends ArrayAdapter<String> {

	private List<String> list;
	private int layoutResourceId;
	private Context context;
	
	public Course_ListAdapter(Context context, int resource, List<String> items) {
		super(context, resource, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.list = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);
		
		return row;
		
		
	}
	

}
