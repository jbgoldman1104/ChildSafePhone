package com.androidbook.childsafephone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ChildSafeDeleteContactActivity extends ChildSafeActivity {

	// Called when the activity is first created
	SharedPreferences contactInfo;
	static final int CONFIRM_DELETE_CONTACT1_DIALOG_ID = 0;
	static final int CONFIRM_DELETE_CONTACT2_DIALOG_ID = 1;
	static final int CONFIRM_DELETE_CONTACT3_DIALOG_ID = 2;
	static final int CONFIRM_DELETE_CONTACT4_DIALOG_ID = 3;
	static final int CONFIRM_DELETE_CONTACT5_DIALOG_ID = 4;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_contact);

		contactInfo = getSharedPreferences(CHILDSAFE_PREFERENCES,
				Context.MODE_PRIVATE);

		ListView lv = (ListView) findViewById(R.id.list_view);

		// create the grid item mapping
		String[] from = new String[] { "rowid", "col_1", "col_2", "col_3" };
		int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };

		// prepare the list of all records
		List<HashMap<String, Object>> fillMaps = new ArrayList<HashMap<String, Object>>();

		// Contact 1
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rowid", "1");
		map.put("col_1", contactInfo.getInt(
				"CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(
				CHILDSAFE_PREFERENCES_CONTACT1NAME, "No contact"));
		map.put("col_3",
				contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT1PHONE, ""));
		fillMaps.add(map);

		// Contact 2
		map = new HashMap<String, Object>();
		map.put("rowid", "2");
		map.put("col_1", contactInfo.getInt(
				"CHILDSAFE_PREFERENCES_CONTACT2ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(
				CHILDSAFE_PREFERENCES_CONTACT2NAME, "No contact"));
		map.put("col_3",
				contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT2PHONE, ""));
		fillMaps.add(map);

		// Contact 3
		map = new HashMap<String, Object>();
		map.put("rowid", "3");
		map.put("col_1", contactInfo.getInt(
				"CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(
				CHILDSAFE_PREFERENCES_CONTACT3NAME, "No contact"));
		map.put("col_3",
				contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT3PHONE, ""));
		fillMaps.add(map);

		// Contact 4
		map = new HashMap<String, Object>();
		map.put("rowid", "4");
		map.put("col_1", contactInfo.getInt(
				"CHILDSAFE_PREFERENCES_CONTACT4ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(
				CHILDSAFE_PREFERENCES_CONTACT4NAME, "No contact"));
		map.put("col_3",
				contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT4PHONE, ""));
		fillMaps.add(map);

		// Contact 5
		map = new HashMap<String, Object>();
		map.put("rowid", "5");
		map.put("col_1", contactInfo.getInt(
				"CHILDSAFE_PREFERENCES_CONTACT5ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(
				CHILDSAFE_PREFERENCES_CONTACT5NAME, "No contact"));
		map.put("col_3",
				contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT5PHONE, ""));
		fillMaps.add(map);

		// fill in the grid_item layout
		SimpleAdapter adapter = new SimpleAdapter(this, fillMaps,
				R.layout.grid_item, from, to);
		lv.setAdapter(adapter);

		// when you click on each contact, go to the dialer
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> child, View v, int position,
					long id) {
				switch (position) {
				case 0:
					// if contact is empty, cant delete, do nothing
					// if there is a contact, delete it
					String phone1 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT1PHONE, "");
					if (phone1 == "") {
						break;
					} else {
						showDialog(CONFIRM_DELETE_CONTACT1_DIALOG_ID);
						break;
					}
				case 1:
					// delete contact 2 if there is a contact
					String phone2 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT2PHONE, "");
					if (phone2 == "") {
						break;
					} else {
						showDialog(CONFIRM_DELETE_CONTACT2_DIALOG_ID);
						break;
					}
				case 2:
					// delete contact 3 if there is a contact
					String phone3 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT3PHONE, "");
					if (phone3 == "") {
						break;
					} else {
						showDialog(CONFIRM_DELETE_CONTACT3_DIALOG_ID);
						break;
					}
				case 3:
					// delete contact 4 if there is a contact
					String phone4 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT4PHONE, "");
					if (phone4 == "") {
						break;
					} else {
						showDialog(CONFIRM_DELETE_CONTACT4_DIALOG_ID);
						break;
					}
				case 4:
					// delete contact 5 if there is a contact
					String phone5 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT5PHONE, "");
					if (phone5 == "") {
						break;
					} else {
						showDialog(CONFIRM_DELETE_CONTACT5_DIALOG_ID);
						break;

					}
				}
			}
		});
	}
	
	// Dialog Creation for the Password Screen
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CONFIRM_DELETE_CONTACT1_DIALOG_ID:
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout1 = inflater1.inflate(R.layout.delete_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setView(layout1);
			builder1.setTitle(R.string.ConfirmDelete_title);
			
			// The Cancel Button
			builder1.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT1_DIALOG_ID);
						}
					});

			// The OK Button
			builder1.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Editor editor = contactInfo.edit();
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT1NAME);
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT1PHONE);
							editor.putInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00);
							
							
							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT1_DIALOG_ID);
							startActivity(new Intent(
									ChildSafeDeleteContactActivity.this,
									ChildSafeParentSettingActivity.class));
							editor.commit();
						}
					});

			// Create the AlertDialog and return it
			AlertDialog passwordDialog1 = builder1.create();
			return passwordDialog1;
			
		case CONFIRM_DELETE_CONTACT2_DIALOG_ID:
			LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout2 = inflater2.inflate(R.layout.delete_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setView(layout2);
			builder2.setTitle(R.string.ConfirmDelete_title);

			// The Cancel Button
			builder2.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT2_DIALOG_ID);
						}
					});

			// The OK Button
			builder2.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Editor editor = contactInfo.edit();
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT2NAME);
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT2PHONE);
							editor.putInt("CHILDSAFE_PREFERENCES_CONTACT2ICON", R.drawable.icon00);
							
							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT2_DIALOG_ID);
							startActivity(new Intent(
									ChildSafeDeleteContactActivity.this,
									ChildSafeParentSettingActivity.class));
							editor.commit();
						}
					});

			// Create the AlertDialog and return it
			AlertDialog passwordDialog2 = builder2.create();
			return passwordDialog2;
			
		case CONFIRM_DELETE_CONTACT3_DIALOG_ID:
			LayoutInflater inflater3 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout3 = inflater3.inflate(R.layout.delete_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
			builder3.setView(layout3);
			builder3.setTitle(R.string.ConfirmDelete_title);

			// The Cancel Button
			builder3.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT3_DIALOG_ID);
						}
					});

			// The OK Button
			builder3.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Editor editor = contactInfo.edit();
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT3NAME);
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT3PHONE);
							editor.putInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00);

							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT3_DIALOG_ID);
							startActivity(new Intent(
									ChildSafeDeleteContactActivity.this,
									ChildSafeParentSettingActivity.class));
							editor.commit();
						}
					});

			// Create the AlertDialog and return it
			AlertDialog passwordDialog3 = builder3.create();
			return passwordDialog3;
			
		case CONFIRM_DELETE_CONTACT4_DIALOG_ID:
			LayoutInflater inflater4 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout4 = inflater4.inflate(R.layout.delete_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
			builder4.setView(layout4);
			builder4.setTitle(R.string.ConfirmDelete_title);

			// The Cancel Button
			builder4.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT4_DIALOG_ID);
						}
					});

			// The OK Button
			builder4.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Editor editor = contactInfo.edit();
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT4NAME);
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT4PHONE);
							editor.putInt("CHILDSAFE_PREFERENCES_CONTACT4ICON", R.drawable.icon00);

							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT4_DIALOG_ID);
							startActivity(new Intent(
									ChildSafeDeleteContactActivity.this,
									ChildSafeParentSettingActivity.class));
							editor.commit();
						}
					});

			// Create the AlertDialog and return it
			AlertDialog passwordDialog4 = builder4.create();
			return passwordDialog4;
			
		case CONFIRM_DELETE_CONTACT5_DIALOG_ID:
			LayoutInflater inflater5 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout5 = inflater5.inflate(R.layout.delete_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
			builder5.setView(layout5);
			builder5.setTitle(R.string.ConfirmDelete_title);

			// The Cancel Button
			builder5.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT5_DIALOG_ID);
						}
					});

			// The OK Button
			builder5.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Editor editor = contactInfo.edit();
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT5NAME);
							editor.remove(CHILDSAFE_PREFERENCES_CONTACT5PHONE);
							editor.putInt("CHILDSAFE_PREFERENCES_CONTACT5ICON", R.drawable.icon00);

							ChildSafeDeleteContactActivity.this
									.removeDialog(CONFIRM_DELETE_CONTACT5_DIALOG_ID);
							startActivity(new Intent(
									ChildSafeDeleteContactActivity.this,
									ChildSafeParentSettingActivity.class));
							editor.commit();
						}
					});

			// Create the AlertDialog and return it
			AlertDialog passwordDialog5 = builder5.create();
			return passwordDialog5;
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