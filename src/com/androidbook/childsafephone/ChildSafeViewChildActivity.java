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
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChildSafeViewChildActivity extends ChildSafeActivity {

	// Called when the activity is first created
	SharedPreferences contactInfo;
	static final int CONFIRM_CALL1_ID = 0;
	static final int CONFIRM_CALL2_ID = 1;
	static final int CONFIRM_CALL3_ID = 2;
	static final int CONFIRM_CALL4_ID = 3;
	static final int CONFIRM_CALL5_ID = 4;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child);

		contactInfo = getSharedPreferences(CHILDSAFE_PREFERENCES,
				Context.MODE_PRIVATE);

		// Finds the list view in the add_contact.xml
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

		// when you click on each contact, go to the dialer for
		// the corresponding contact, unless the contact is empty
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> child, View v, int position,
					long id) {
				switch (position) {
				case 0:
					// calls contact 1 if not empty
					String phone1 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT1PHONE, "");
					if (phone1 != "") {
						showDialog(CONFIRM_CALL1_ID);
					}
					break;
				case 1:
					// calls contact 2 if not empty
					String phone2 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT2PHONE, "");
					if (phone2 != "") {
						showDialog(CONFIRM_CALL2_ID);
					}
					break;
				case 2:
					// calls contact 3 if not empty
					String phone3 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT3PHONE, "");
					if (phone3 != "") {
						showDialog(CONFIRM_CALL3_ID);
					}
					break;
				case 3:
					// calls contact 4 if not empty
					String phone4 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT4PHONE, "");
					if (phone4 != "") {
						showDialog(CONFIRM_CALL4_ID);
					}
					break;
				case 4:
					// calls contact 5 if not empty
					String phone5 = contactInfo.getString(
							CHILDSAFE_PREFERENCES_CONTACT5PHONE, "");
					if (phone5 != "") {
						showDialog(CONFIRM_CALL5_ID);
					}
					break;
				default:
					assert (false);
				}
			}
		});
	}

	// Dialog creation for the confirm call screen
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CONFIRM_CALL1_ID:
			LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout1 = inflater1.inflate(R.layout.call_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setView(layout1);
			builder1.setTitle(R.string.confirm_call_title);

			// The cancel button
			builder1.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL1_ID);
						}
					});

			// When the OK Button is pressed, call the contact
			builder1.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							String phone1 = contactInfo.getString(
									CHILDSAFE_PREFERENCES_CONTACT1PHONE, "");
							startActivity(new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + phone1)));

							// We forcefully dismiss and remove the Dialog, so
							// it cannot be used again
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL1_ID);
						}
					});
			// Create the AlertDialog and return it
			AlertDialog callDialog1 = builder1.create();
			return callDialog1;

		case CONFIRM_CALL2_ID:
			LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout2 = inflater2.inflate(R.layout.call_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setView(layout2);
			builder2.setTitle(R.string.confirm_call_title);

			// The cancel button
			builder2.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL2_ID);
						}
					});

			// When the OK Button is pressed, call the contact
			builder2.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							String phone2 = contactInfo.getString(
									CHILDSAFE_PREFERENCES_CONTACT2PHONE, "");
							startActivity(new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + phone2)));

							// We forcefully dismiss and remove the Dialog, so
							// it cannot be used again
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL2_ID);
						}
					});
			// Create the AlertDialog and return it
			AlertDialog callDialog2 = builder2.create();
			return callDialog2;

		case CONFIRM_CALL3_ID:
			LayoutInflater inflater3 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout3 = inflater3.inflate(R.layout.call_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
			builder3.setView(layout3);
			builder3.setTitle(R.string.confirm_call_title);

			// The cancel button
			builder3.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL3_ID);
						}
					});

			// When the OK Button is pressed, call the contact
			builder3.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							String phone3 = contactInfo.getString(
									CHILDSAFE_PREFERENCES_CONTACT3PHONE, "");
							startActivity(new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + phone3)));

							// We forcefully dismiss and remove the Dialog, so
							// it cannot be used again
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL3_ID);
						}
					});
			// Create the AlertDialog and return it
			AlertDialog callDialog3 = builder3.create();
			return callDialog3;

		case CONFIRM_CALL4_ID:
			LayoutInflater inflater4 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout4 = inflater4.inflate(R.layout.call_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
			builder4.setView(layout4);
			builder4.setTitle(R.string.confirm_call_title);

			// The cancel button
			builder4.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL4_ID);
						}
					});

			// When the OK Button is pressed, call the contact
			builder4.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							String phone4 = contactInfo.getString(
									CHILDSAFE_PREFERENCES_CONTACT4PHONE, "");
							startActivity(new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + phone4)));

							// We forcefully dismiss and remove the Dialog, so
							// it cannot be used again
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL4_ID);
						}
					});
			// Create the AlertDialog and return it
			AlertDialog callDialog4 = builder4.create();
			return callDialog4;

		case CONFIRM_CALL5_ID:
			LayoutInflater inflater5 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout5 = inflater5.inflate(R.layout.call_dialog,
					(ViewGroup) findViewById(R.id.root));

			AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
			builder5.setView(layout5);
			builder5.setTitle(R.string.confirm_call_title);

			// The cancel button
			builder5.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL5_ID);
						}
					});

			// When the OK Button is pressed, call the contact
			builder5.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							String phone5 = contactInfo.getString(
									CHILDSAFE_PREFERENCES_CONTACT5PHONE, "");
							startActivity(new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + phone5)));

							// We forcefully dismiss and remove the Dialog, so
							// it cannot be used again
							ChildSafeViewChildActivity.this
									.removeDialog(CONFIRM_CALL5_ID);
						}
					});
			// Create the AlertDialog and return it
			AlertDialog callDialog5 = builder5.create();
			return callDialog5;
		}
		return null;
	}

}
