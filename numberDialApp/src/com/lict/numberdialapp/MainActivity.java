package com.lict.numberdialapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText etNumberToDial;
	Button btnDial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeAllViewComponents();
		
		btnDial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialTheNumber();
			}
		});
	}

	protected void dialTheNumber() {
		
		if(!etNumberToDial.getText().toString().isEmpty())
		{
			try{
				startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + etNumberToDial.getText())));
				
				Toast.makeText(getApplicationContext(), "Finish Dialing", Toast.LENGTH_SHORT).show();
			}
			catch(Exception excp){
				Toast.makeText(getApplicationContext(), excp.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void initializeAllViewComponents() {
		etNumberToDial = (EditText)findViewById(R.id.numberToDialEditText);
		btnDial = (Button)findViewById(R.id.dialButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
