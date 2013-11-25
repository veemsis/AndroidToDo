package com.project.androidtodo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void addNote() {
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
	        	Context context = getApplicationContext();
	        	CharSequence text = input.getText().toString();;
	        	int duration = Toast.LENGTH_SHORT;

	        	Toast toast = Toast.makeText(context, text, duration);
	        	toast.show();
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
}
