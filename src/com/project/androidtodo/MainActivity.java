package com.project.androidtodo;

import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ArrayAdapter;


public class MainActivity extends ListActivity  {
	private CommentsDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datasource = new CommentsDataSource(this);
	    datasource.open();

	    List<Comment> values = datasource.getAllComments();

	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
	   
	    
	    // Handle presses on the action bar items
	    
	    switch (item.getItemId()) {
	        case R.id.add_note:
	            addNote();
	            return true;
	        case R.id.action_settings:
	            return true;
	        case R.id.delete:
	        	@SuppressWarnings("unchecked")
	            ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
	            Comment comment = null;
	        	if (getListAdapter().getCount() > 0) {
	                comment = (Comment) getListAdapter().getItem(0);
	                datasource.deleteComment(comment);
	                adapter.remove(comment);
	              }
	              return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	  
	    
	}

	public void addNote() {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder noteBuilder = new AlertDialog.Builder(this);  
	       noteBuilder.setTitle("Add TODO note");  
	       final EditText input = new EditText(this);  
	       input.setSingleLine();  
	       noteBuilder.setView(input);
	       
	              
	      
		    //Save button  
	       noteBuilder.setPositiveButton("Save",  
	        new DialogInterface.OnClickListener() {  
	        public void onClick(DialogInterface dialog, int which) {  
	          //Save info here
	        	
	        	Comment comment = null;
	    		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
	    		String text = input.getText().toString();
	    		comment = datasource.createComment(text);
	    		adapter.add(comment);
	    		adapter.notifyDataSetChanged();
	            
	            
	        	
	        	
	        	

	        	
	        }  
	        });  
	      
	       //Cancel button  
	       noteBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
	       @Override  
	       public void onClick(DialogInterface dialog, int which) {  
	        // Do nothing, just close the dialog box  
	       }  
	       });  
	       // Remember, create doesn't show the dialog  
	       AlertDialog noteDialog = noteBuilder.create();  
	       noteDialog.show();
	}
	
	@Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }

}
