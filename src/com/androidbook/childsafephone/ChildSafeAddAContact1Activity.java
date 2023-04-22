package com.androidbook.childsafephone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ChildSafeAddAContact1Activity extends ChildSafeActivity {

	// Called when the activity is first created
	SharedPreferences contactInfo;
	static final int BROWSE_DIALOG_ID = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_a_contact);

		contactInfo = getSharedPreferences(CHILDSAFE_PREFERENCES,
				Context.MODE_PRIVATE);

		// Initialize the enter button
		initEnterButton();
		// Initialize the cancel button
		initCancelButton();
		// Start the icon chooser
		IconChooser();
	}

	private void NameEntry() {
		// Save name
		final EditText nameText = (EditText) findViewById(R.id.editTextName);
				String strName = nameText.getText().toString();
				Editor editorName = contactInfo.edit();
				editorName.putString(CHILDSAFE_PREFERENCES_CONTACT1NAME,
						strName);
				editorName.commit();
	}

	private void PhoneEntry() {
		// Save phone
		final EditText phoneText = (EditText) findViewById(R.id.editTextPhone);
		String strPhone = phoneText.getText().toString();
		Editor editorPhone = contactInfo.edit();
		editorPhone.putString(CHILDSAFE_PREFERENCES_CONTACT1PHONE,
				strPhone);
		editorPhone.commit();
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
		// icon dialog
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
		                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00 + currentValue);
		                editor.commit();
		               
		                //sets the image onto the activity
		                final ImageView editIcon = (ImageView) findViewById(R.id.imageViewIcon);
		                editIcon.setImageResource(contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00));
		                
		                //remove the dialog
		                ChildSafeAddAContact1Activity.this.removeDialog(BROWSE_DIALOG_ID);
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
		                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon10 + currentValue1);
		                editor.commit();
		               
		                //sets the image onto the activity
		                final ImageView editIcon = (ImageView) findViewById(R.id.imageViewIcon);
		                editIcon.setImageResource(contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00));
		                
		                //remove the dialog
		                ChildSafeAddAContact1Activity.this.removeDialog(BROWSE_DIALOG_ID);
					}
				});	
			}
			//--------Icon Sets 20------------
			ImageView curImgView2 = (ImageView) layout.findViewWithTag("icon20");
			curImgView2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					//sets the preferences of the icon being chosen
					Editor editor = contactInfo.edit();
	                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon20);
	                editor.commit();
	               
	                //sets the image onto the activity
	                final ImageView editIcon = (ImageView) findViewById(R.id.imageViewIcon);
	                editIcon.setImageResource(contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00));
	                
	                //remove the dialog
	                ChildSafeAddAContact1Activity.this.removeDialog(BROWSE_DIALOG_ID);
				}
			});	
			//-----------------------End of Icon Click Functions-----------------------
			
			
			// The cancel button
			builder.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeAddAContact1Activity.this
									.removeDialog(BROWSE_DIALOG_ID);
						}
					});

			// Create the AlertDialog and return it
			AlertDialog callDialog = builder.create();
			return callDialog;
		}
		return null;
	}
	
	private void initEnterButton() {
		// Handles enter
		Button enterButton = (Button) findViewById(R.id.buttonEnter);
		enterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Save name
				NameEntry();
				// Save phone
				PhoneEntry();
				// Save icon
				IconChooser();
				// goes back to the parent setting screen after saving
				startActivity(new Intent(ChildSafeAddAContact1Activity.this, ChildSafeParentSettingActivity.class));	
			}
		});
	}
	
	private void initCancelButton() {
		// Clear settings
		Button cancelButton = (Button) findViewById(R.id.buttonCancel);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// sets the preferences to the no icon
				Editor editor = contactInfo.edit();
                editor.putInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00);
                editor.commit();
                // goes back to the parent setting screen after clearing
				startActivity(new Intent(ChildSafeAddAContact1Activity.this, ChildSafeParentSettingActivity.class));	
			}
		});
}
	
	// For testing purposes, override the onDestroy() method 
	// to log all current settings whenever the settings
	// screen is destroyed.
	protected void onDestroy() {
		Log.d(DEBUG_TAG, "SHARED PREFERENCES");
		Log.d(DEBUG_TAG,
				"Name1 is: "
						+ contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT1NAME,
								"Not set"));
		Log.d(DEBUG_TAG,
				"Phone1 is: "
						+ contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT1PHONE,
								"Not set"));
		Log.d(DEBUG_TAG,
				"Icon1 is: "
						+ contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00));
		super.onDestroy();
	}
}