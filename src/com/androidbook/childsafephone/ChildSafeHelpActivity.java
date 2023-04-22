package com.androidbook.childsafephone;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ChildSafeHelpActivity extends ChildSafeActivity {
	
	final static String debug = "Debugging the file";
	
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        
        //Reads the Raw text file
        InputStream iFile = getResources().openRawResource(R.raw.childsafehelp);
		try {
			//Reads each line word by word
			TextView helpText = (TextView) findViewById(R.id.TextView_HelpText);
			String strFile = inputStreamToString(iFile);
			helpText.setText(strFile);
		} catch (IOException e) {
			Log.e(debug, "InputStreamToString failure", e);
		}
    }
     
    //Returns String values of the txt File
    public String inputStreamToString(InputStream is) throws IOException {
        StringBuffer sBuffer = new StringBuffer();
        DataInputStream dataIO = new DataInputStream(is);
        String strLine = null;
        while ((strLine = dataIO.readLine()) != null) {
            sBuffer.append(strLine + "\n");
        }
        dataIO.close();
        is.close();
        return sBuffer.toString();
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