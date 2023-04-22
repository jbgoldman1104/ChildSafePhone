package com.androidbook.childsafephone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ChildSafeAddContactActivity extends ChildSafeActivity {

	// Called when the activity is first created
	SharedPreferences contactInfo;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);
		
		contactInfo = getSharedPreferences(CHILDSAFE_PREFERENCES, Context.MODE_PRIVATE);
		
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
		map.put("col_1", contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT1ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT1NAME, "No contact"));
		map.put("col_3", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT1PHONE, ""));
		fillMaps.add(map);
		
		// Contact 2
		map = new HashMap<String, Object>();
		map.put("rowid", "2");
		map.put("col_1", contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT2ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT2NAME, "No contact"));
		map.put("col_3", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT2PHONE, ""));
		fillMaps.add(map);
		
		// Contact 3
		map = new HashMap<String, Object>();
		map.put("rowid", "3");
		map.put("col_1", contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT3ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT3NAME, "No contact"));
		map.put("col_3", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT3PHONE, ""));
		fillMaps.add(map);
		
		// Contact 4
		map = new HashMap<String, Object>();
		map.put("rowid", "4");
		map.put("col_1", contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT4ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT4NAME, "No contact"));
		map.put("col_3", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT4PHONE, ""));
		fillMaps.add(map);
		
		// Contact 5
		map = new HashMap<String, Object>();
		map.put("rowid", "5");
		map.put("col_1", contactInfo.getInt("CHILDSAFE_PREFERENCES_CONTACT5ICON", R.drawable.icon00));
		map.put("col_2", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT5NAME, "No contact"));
		map.put("col_3", contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT5PHONE, ""));
		fillMaps.add(map);

		// fill in the grid_item layout
		SimpleAdapter adapter = new SimpleAdapter(this, fillMaps,
				R.layout.grid_item, from, to);
		lv.setAdapter(adapter);
		
		// when you click on each contact, go to the add a contact activity for 
		// the corresponding contact, unless the contact is empty
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> child, View v, int position, long id) {
				switch (position) {
				case 0:	
					// add contact 1 if empty
					String phone1 = contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT1PHONE, "");
					if (phone1 == "") {
						startActivity(new Intent(ChildSafeAddContactActivity.this,
								ChildSafeAddAContact1Activity.class));
						break;
					}
					break;
				case 1:
					// add contact 2 if empty
					String phone2 = contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT2PHONE, "");
					if (phone2 == "") {
						startActivity(new Intent(ChildSafeAddContactActivity.this,
								ChildSafeAddAContact2Activity.class));
						break;
					}
					break;
				case 2: 
					// add contact 3 if empty
					String phone3 = contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT3PHONE, "");
					if (phone3 == "") {
						startActivity(new Intent(ChildSafeAddContactActivity.this,
								ChildSafeAddAContact3Activity.class));
						break;
					}
					break;
				case 3:
					// add contact 4 if empty
					String phone4 = contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT4PHONE, "");
					if (phone4 == "") {
						startActivity(new Intent(ChildSafeAddContactActivity.this,
								ChildSafeAddAContact4Activity.class));
						break;
					}
				case 4:
					// add contact 5 if empty
					String phone5 = contactInfo.getString(CHILDSAFE_PREFERENCES_CONTACT5PHONE, "");
					if (phone5 == "") {
						startActivity(new Intent(ChildSafeAddContactActivity.this,
								ChildSafeAddAContact5Activity.class));
						break;
					}
					break;
				}
			}
		});
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