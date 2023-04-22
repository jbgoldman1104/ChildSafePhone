package com.androidbook.childsafephone;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChildSafeMenuActivity extends ChildSafeActivity {
	
	private static final String DEBUG_TAG = "DEBUG_TAG";
	static final int PASSWORD_DIALOG_ID = 0;
	//Called when the activity is first created.
	SharedPreferences childSettings;
	
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.mainmenu);
	        
	        childSettings = getSharedPreferences(CHILDSAFE_PREFERENCES, Context.MODE_PRIVATE);
	        
	        gotoChildSettings();
	        gotoParentSettings();
	 }
	 
	//-----------------------------------------------------------------------------------
	//------DEFAULT PASSWORD RIGHT NOW IS MARIO or whatever you set it-------------------
	//-----------------------------------------------------------------------------------
	 
	//------Handles going to the Parent Screen from Main Menu--------------
	//Some notes:
	//If the password is not set or the Shared Preferences does not contain a value,
	//it will go to the parent screen
	//If the Password is set or Shared Preferences contains a password value, then a login
	//screen will pop up.
	private void gotoParentSettings() {
		ImageView ParentImage = (ImageView) findViewById(R.id.parent);
		ParentImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//If there is a password in SharedPreferences Go to Login Screen
				if (childSettings.contains(CHILDSAFE_PREFERENCES_PASSWORD)) {
					showDialog(PASSWORD_DIALOG_ID);
		        } else {
		        	//Go to Parent Screen if there is no password set
		        	startActivity(new Intent(ChildSafeMenuActivity.this,ChildSafeParentSettingActivity.class));	
		        }	
			}
		});
	}
	//------Handles going to the Child Screen from Main Menu--------------
	private void gotoChildSettings() {
		ImageView ChildImage = (ImageView) findViewById(R.id.child);
		ChildImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ChildSafeMenuActivity.this,ChildSafeChildActivity.class));	
			}
		});	
	}
	 
	//Dialog Creation for the Password Login Screen
	protected Dialog onCreateDialog(int id){
		switch (id){
			case PASSWORD_DIALOG_ID:
				
				//Gets the Layout and the IDs for the Login Screen
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View layout = inflater.inflate(R.layout.password_login, (ViewGroup) findViewById(R.id.root));
				final EditText p1 = (EditText) layout.findViewById(R.id.EditText_PwdLogin);
				final TextView errorPwd = (TextView) layout.findViewById(R.id.TextView_PwdWrong);
				
				p1.addTextChangedListener(new TextWatcher() {
					@Override
					public void afterTextChanged(Editable s) {
						String strPasswordLogin = p1.getText().toString();
		                String strPasswordLoginValue = childSettings.getString(CHILDSAFE_PREFERENCES_PASSWORD, CHILDSAFE_PREFERENCES_PASSWORD);
						if (strPasswordLogin.equals(strPasswordLoginValue)) {
							errorPwd.setText(R.string.menu_pwd_ok);
						} else {
							errorPwd.setText(R.string.menu_pwd_not_ok);
						}
					}
                //required overrides these do nothing
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setView(layout);
				builder.setTitle(R.string.input_pwd);
           
            //The Cancel Button handling
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    ChildSafeMenuActivity.this
                    	.removeDialog(PASSWORD_DIALOG_ID);
                }
            });
            
            //The OK Button handling
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                   String strPasswordLogin = p1.getText().toString();
                   String strPasswordLoginValue = childSettings.getString(CHILDSAFE_PREFERENCES_PASSWORD, CHILDSAFE_PREFERENCES_PASSWORD);
                   
                   //if Login value is = to the SharedPreference Password Value
                   //go to the Parent Settings Screen
                   if (strPasswordLogin.equals(strPasswordLoginValue)) {
                    	startActivity(new Intent(ChildSafeMenuActivity.this,ChildSafeParentSettingActivity.class));
                    } else {
                        Log.d(DEBUG_TAG, "Passwords do not match");
                    }
                    // remove the Dialog
                    ChildSafeMenuActivity.this.removeDialog(PASSWORD_DIALOG_ID);
                }
            });
            // Create the AlertDialog and return it
            AlertDialog passwordDialog = builder.create();
            return passwordDialog;
			}
		return null;
	}
    
}