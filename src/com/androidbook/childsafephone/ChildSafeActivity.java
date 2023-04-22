package com.androidbook.childsafephone;

import android.app.Activity;

public class ChildSafeActivity extends Activity {
	//Preferences used for storing key values for Shared Preferences
	public static final String DEBUG_TAG = "ChildApp"; 
	public static final String CHILDSAFE_PREFERENCES = "ChildPrefs";

	// Preference Setting for the Parent Password, see ChildSafeParentSetting for 
	// Its implementation
	public static final String CHILDSAFE_PREFERENCES_PASSWORD = "Password";

	// Preference Settings for each contact's name, Icon and Phone Number
	public static final String CHILDSAFE_PREFERENCES_CONTACT1NAME = "Contact1";
	public static final int CHILDSAFE_PREFERENCES_CONTACT1ICON = R.drawable.icon00;
	public static final String CHILDSAFE_PREFERENCES_CONTACT1PHONE = "Phone1";
	
	public static final String CHILDSAFE_PREFERENCES_CONTACT2NAME = "Contact2";
	public static final int CHILDSAFE_PREFERENCES_CONTACT2ICON = R.drawable.icon00;
	public static final String CHILDSAFE_PREFERENCES_CONTACT2PHONE = "Phone2";
	
	public static final String CHILDSAFE_PREFERENCES_CONTACT3NAME = "Contact3";
	public static final int CHILDSAFE_PREFERENCES_CONTACT3ICON = R.drawable.icon00;
	public static final String CHILDSAFE_PREFERENCES_CONTACT3PHONE = "Phone3";
	
	public static final String CHILDSAFE_PREFERENCES_CONTACT4NAME = "Contact4";
	public static final int CHILDSAFE_PREFERENCES_CONTACT4ICON = R.drawable.icon00;
	public static final String CHILDSAFE_PREFERENCES_CONTACT4PHONE = "Phone4";
	
	public static final String CHILDSAFE_PREFERENCES_CONTACT5NAME = "Contact5";
	public static final int CHILDSAFE_PREFERENCES_CONTACT5ICON = R.drawable.icon00;
	public static final String CHILDSAFE_PREFERENCES_CONTACT5PHONE = "Phone5";
}