package com.androidbook.childsafephone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ChildSafeEditAContact3Activity extends ChildSafeActivity {
	
	SharedPreferences contactInfo;
	static final int BROWSE_DIALOG_ID = 0;
	int initIcon = CHILDSAFE_PREFERENCES_CONTACT3ICON;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_a_contact);
		
		contactInfo = getSharedPreferences(CHILDSAFE_PREFERENCES, Context.MODE_PRIVATE);
		
		//Gets the Id values from the Preferences so it can be edited
		final EditText editContactEditText = (EditText) findViewById(R.id.editTextName);
		final EditText editContactEditNumber = (EditText) findViewById(R.id.editTextPhone);
		final ImageView editIcon = (ImageView) findViewById(R.id.imageViewIcon);
		
		//Displays what is being edited (Name, Phone, Icon)
		editContactEditText.setText(contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT3NAME, ""));
		editContactEditNumber.setText(contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT3PHONE, ""));
		editIcon.setImageResource(contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00));
		
		initIcon= contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00);
		
		buttonOKEditScreen();
		buttonCancelEditScreen();
		IconChooser();
	}
	
	//The Ok Button Handling
	private void  buttonOKEditScreen(){
        // Handle the moving from one activity to another
        Button cancelEditAContactButton = (Button) findViewById(R.id.button_edit_a_contact_enter);
        cancelEditAContactButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get the values from the Contact Name and Number
				final EditText editContactEditText3 = (EditText) findViewById(R.id.editTextName);
				final EditText editContactEditNumber3 = (EditText) findViewById(R.id.editTextPhone);
               
				//Retrieves the Edit Text and puts it into a string variable
				String strEditContactName3 = editContactEditText3.getText().toString();
                String strEditContactNumber3 = editContactEditNumber3.getText().toString();
                    
                	//Commit the following changes when clicked ok
                	Editor editor = contactInfo.edit();
                    editor.putString(CHILDSAFE_PREFERENCES_CONTACT3NAME, strEditContactName3);
                    editor.commit();  
                    editor.putString(CHILDSAFE_PREFERENCES_CONTACT3PHONE, strEditContactNumber3);
                    editor.commit();
                    startActivity(new Intent(ChildSafeEditAContact3Activity.this,ChildSafeParentSettingActivity.class));
            }
        });
	}
	
	//The Cancel Button Handling
	private void  buttonCancelEditScreen(){
        // Handle the moving from one activity to another
        Button cancelEditAContactButton = (Button) findViewById(R.id.button_edit_a_contact_cancel);
        cancelEditAContactButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// sets the preferences of the icon to be the original icon from before
            	Editor editor = contactInfo.edit();
                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", initIcon);
                editor.commit();
            
               startActivity(new Intent(ChildSafeEditAContact3Activity.this, ChildSafeEditContactActivity.class));
            }
        });
	}
	
	private void IconChooser() {
		// Choose icon - handle picking icon dialog
		Button setIcon = (Button) findViewById(R.id.buttonBrowse);
		setIcon.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(BROWSE_DIALOG_ID);
			}
		});
	}
	
	protected Dialog onCreateDialog(int id) { 
		switch (id) {
		case BROWSE_DIALOG_ID: 
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout = inflater.inflate(R.layout.icon_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setView(layout);
			builder.setTitle(R.string.choose_icon_title);
			
			//------------------------------------------------------------------------
			//------------------------Creates onClicks for the Icons------------------
			//------------------------------------------------------------------------
			
			//--------Icon Sets from: 0 to 9------------
			for (int curIcon = 0; curIcon <= 9; curIcon++){
				//Using tags to find the value of the icon
				final int currentValue = curIcon;
				ImageView curImgView = (ImageView) layout.findViewWithTag("icon0" + curIcon);
				curImgView.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {			
						//sets the preferences of the icon being chosen
						Editor editor = contactInfo.edit();
		                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00 + currentValue);
		                editor.commit();
		               
		                //sets the image onto the activity
		                final ImageView editIcon = (ImageView) findViewById(R.id.imageViewIcon);
		                editIcon.setImageResource(contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00));
		                
		                //remove the dialog
		                ChildSafeEditAContact3Activity.this.removeDialog(BROWSE_DIALOG_ID);
					}
				});	
			}
			//--------Icon Sets from: 10 to 19------------
			for (int curIcon = 0; curIcon <= 9; curIcon++){
				final int currentValue1 = curIcon;
				ImageView curImgView = (ImageView) layout.findViewWithTag("icon1"+curIcon);
				curImgView.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						//sets the preferences of the icon being chosen
						Editor editor = contactInfo.edit();
		                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon10 + currentValue1);
		                editor.commit();
		               
		                //sets the image onto the activity
		                final ImageView editIcon = (ImageView) findViewById(R.id.imageViewIcon);
		                editIcon.setImageResource(contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00));
		                
		                //remove the dialog
		                ChildSafeEditAContact3Activity.this.removeDialog(BROWSE_DIALOG_ID);
					}
				});	
			}
			//--------Icon Sets 20------------
			ImageView curImgView2 = (ImageView) layout.findViewWithTag("icon20");
			curImgView2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					//sets the preferences of the icon being chosen
					Editor editor = contactInfo.edit();
	                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon20);
	                editor.commit();
	               
	                //sets the image onto the activity
	                final ImageView editIcon = (ImageView) findViewById(R.id.imageViewIcon);
	                editIcon.setImageResource(contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00));
	                
	                //remove the dialog
	                ChildSafeEditAContact3Activity.this.removeDialog(BROWSE_DIALOG_ID);
				}
			});	
			//-----------------------End of Icon Click Functions-----------------------
			
			// The cancel button
			builder.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeEditAContact3Activity.this
									.removeDialog(BROWSE_DIALOG_ID);
						}
					});
			// Create the AlertDialog and return it
			AlertDialog callDialog = builder.create();
			return callDialog;
		}
		return null;
	}
}