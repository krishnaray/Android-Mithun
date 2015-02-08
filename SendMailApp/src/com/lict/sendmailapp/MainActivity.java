package com.lict.sendmailapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	EditText etSendAddress;
	EditText etSendCCAddress;
	EditText etSendSubject;
	EditText etSendMsg;
	Button btnSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeAllViewComponents();
        btnSendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(etSendAddress.getText().toString().isEmpty())
				{
					Toast.makeText(getApplicationContext(), "Where to send?", Toast.LENGTH_SHORT).show();
					return;
				}
				if(etSendMsg.getText().toString().isEmpty())
				{
					Toast.makeText(getApplicationContext(), "What to send?", Toast.LENGTH_SHORT).show();
					return;
				}
				sendEmail();
			}
		});
        
    }
    
    private void initializeAllViewComponents() {
    	etSendAddress = (EditText)findViewById(R.id.editTextSendTo);
    	etSendCCAddress = (EditText)findViewById(R.id.editTextSendCC);
    	etSendSubject = (EditText)findViewById(R.id.sendSubject); 
    	etSendMsg = (EditText)findViewById(R.id.editTextSendMsg);
    	btnSendButton = (Button)findViewById(R.id.sendButton);
	}
    
    private void sendEmail() {
    	String sendToAddress = etSendAddress.getText().toString();
    	String sendCCAddress = etSendCCAddress.getText().toString();
    	String sendSubject = etSendSubject.getText().toString();
    	String sendMessage = etSendMsg.getText().toString();
    	
    	Intent emailIntent = new Intent(Intent.ACTION_SEND);
    	
    	//emailIntent.setData(Uri.parse("mailto:" + sendToAddress));
        emailIntent.setType("text/plain");
    	
    	emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{sendToAddress});
    	emailIntent.putExtra(Intent.EXTRA_CC, new String[] {sendCCAddress});
    	emailIntent.putExtra(Intent.EXTRA_SUBJECT, sendSubject);
    	emailIntent.putExtra(Intent.EXTRA_TEXT, sendMessage);
    	emailIntent.setType("message/rfc822");
    	
    	try{
        	startActivity(Intent.createChooser(emailIntent, "Send Mail"));
        	finish();
        	Toast.makeText(getApplicationContext(), "Finish sending mail", Toast.LENGTH_SHORT).show();
    	}
    	catch(ActivityNotFoundException exc){
    		Toast.makeText(getApplicationContext(), "No mail client found", Toast.LENGTH_SHORT).show();
    	}
    	
		
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
