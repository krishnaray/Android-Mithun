package com.lict.sendsmsapp;


import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText etSendnumber;
	EditText etSendMessage;
	Button btnSendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initializeAllViewComponents();
		
		btnSendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendSms();
			}
		});
	}

	protected void sendSms() {
		String sendNumber = etSendnumber.getText().toString();
		String sendMessage = etSendMessage.getText().toString();
		if(sendNumber != null && sendMessage != null)
		{
			try{
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(sendNumber, null, sendMessage, null, null);
				Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
			}
			catch(Exception exc){
				Toast.makeText(getApplicationContext(), "Send Failed", Toast.LENGTH_SHORT).show();
			}
		}
		
	}

	private void initializeAllViewComponents() { 
		etSendnumber = (EditText)findViewById(R.id.editTextSendToNumber);
		etSendMessage = (EditText)findViewById(R.id.editTextSendMsg);
    	btnSendButton = (Button)findViewById(R.id.sendButton);
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
