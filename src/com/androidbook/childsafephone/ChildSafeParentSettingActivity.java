package com.androidbook.childsafephone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChildSafeParentSettingActivity extends ChildSafeActivity {
	
	private static final String DEBUG_TAG = "DEBUG_TAG";
    static final int PASSWORD_DIALOG_ID = 0;
	
  //Called when the activity is first created.
  SharedPreferences childSettings;
    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.parentsettings);
        
        childSettings = getSharedPreferences(CHILDSAFE_PREFERENCES, Context.MODE_PRIVATE);
        
        buttonAddContactScreen();
        buttonDeleteContactScreen();
        buttonEditContactScreen();
        buttonViewContactScreen();
        buttonPasswordScreen();
        buttonHelpScreen();
      
    }
  //@Override 
    public void onBackPressed(){
    	startActivity(new Intent(ChildSafeParentSettingActivity.this, ChildSafeMenuActivity.class));
    }
   
    //Add a Contact Button
	private void buttonAddContactScreen() {
		Button addContactButton = (Button) findViewById(R.id.Button_Add_Contact);
		addContactButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(ChildSafeParentSettingActivity.this, ChildSafeAddContactActivity.class));
			}
		});
		
	}
    
    //Delete a Contact Button
    private void buttonDeleteContactScreen() {
		Button deleteContactButton = (Button) findViewById(R.id.Button_Delete_Contact);
		deleteContactButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ChildSafeParentSettingActivity.this, ChildSafeDeleteContactActivity.class));
			}
		});
		
	}
    
    //Edit Contact Button
    private void buttonEditContactScreen() {
		Button editContactButton = (Button) findViewById(R.id.Button_Edit_Contact);
		editContactButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(ChildSafeParentSettingActivity.this, ChildSafeEditContactActivity.class));
			}
		});
	}

    // button function for the view contact screen
    private void buttonViewContactScreen() {
    	Button viewContactButton = (Button) findViewById(R.id.Button_View_Contact);
    	viewContactButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ChildSafeParentSettingActivity.this, ChildSafeViewChildActivity.class));
			}
		});
	}
    
	//Button Function for the Password Dialog
    private void buttonPasswordScreen(){
    	// Set password info
        TextView passwordInfo = (TextView) findViewById(R.id.Password_condition);
        if (childSettings.contains(CHILDSAFE_PREFERENCES_PASSWORD)) {
            passwordInfo.setText(R.string.parent_setting_pwd_set);
        } else {
            passwordInfo.setText(R.string.parent_setting_pwd_not_set);
        }
    	
    	Button passwordButton = (Button) findViewById(R.id.Button_Change_Pwd);
    	passwordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(PASSWORD_DIALOG_ID);
            }
        });
    }
    
  //-------Button for Going to the help screen--------------
	private void  buttonHelpScreen(){
        // Handle the moving from one activity to another
        Button helpButton = (Button) findViewById(R.id.Button_Help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ChildSafeParentSettingActivity.this,ChildSafeHelpActivity.class));
            }
        });
	}

	//Dialog Creation for the Password Screen
	protected Dialog onCreateDialog(int id){
		switch (id){
			case PASSWORD_DIALOG_ID:
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View layout = inflater.inflate(R.layout.password_dialog, (ViewGroup) findViewById(R.id.root));
				final EditText p1 = (EditText) layout.findViewById(R.id.EditText_PwdSet);
				final EditText p2 = (EditText) layout.findViewById(R.id.EditText_PwdConfirm);
				final TextView error = (TextView) layout.findViewById(R.id.TextView_PwdProblem);
				
				p2.addTextChangedListener(new TextWatcher() {
					@Override
					public void afterTextChanged(Editable s) {
						String strPass1 = p1.getText().toString();
						String strPass2 = p2.getText().toString();
						if (strPass1.equals(strPass2)) {
							error.setText(R.string.parent_settings_pwd_equal);
						} else {
							error.setText(R.string.parent_settings_pwd_not_equal);
						}
					}
                // ... other required overrides do nothing
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(layout);
            builder.setTitle(R.string.parent_setting_button_changepwd);
           
            //The Cancel Button
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    ChildSafeParentSettingActivity.this
                    	.removeDialog(PASSWORD_DIALOG_ID);
                }
            });
            
            
            //The OK Button
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    TextView passwordInfo = (TextView) findViewById(R.id.Password_condition);
                    String strPassword1 = p1.getText().toString();
                    String strPassword2 = p2.getText().toString();
                    
                    if (strPassword1.equals(strPassword2)) {
                        Editor editor = childSettings.edit();
                        editor.putString(CHILDSAFE_PREFERENCES_PASSWORD, strPassword1);
                        editor.commit();
                        passwordInfo.setText(R.string.parent_setting_pwd_set);
                    } else {
                        Log.d(DEBUG_TAG, "Passwords do not match. Not saving. Keeping old password (if set).");
                    }
                    // Remove the Dialog
                    ChildSafeParentSettingActivity.this.removeDialog(PASSWORD_DIALOG_ID);
                }
            });
            // Create the AlertDialog and return it
            AlertDialog passwordDialog = builder.create();
            return passwordDialog;
			}
		return null;
	}
	
	// Opens the menu screen for easy navigation
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.parentoptions, menu);
		menu.findItem(R.id.main_item).setIntent(
				new Intent(this, ChildSafeMenuActivity.class));
		menu.findItem(R.id.parent_item).setIntent(
				new Intent(this, ChildSafeParentSettingActivity.class));
		menu.findItem(R.id.contact_item).setIntent(
				new Intent(this, ChildSafeViewChildActivity.class));
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		startActivity(item.getIntent());
		return true;
	}
	
	
}