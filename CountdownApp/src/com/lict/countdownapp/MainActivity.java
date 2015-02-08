package com.lict.countdownapp;

import com.lict.countdownapp.utils.CounterClass;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
	Button btnStart;
	Button btnPause;
	TextView tvTimerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnStart = (Button)findViewById(R.id.startButton);
        btnPause = (Button)findViewById(R.id.pauseButton);
        tvTimerValue = (TextView)findViewById(R.id.timerValue);
        
        tvTimerValue.setText("00:03:00");
        
        final CounterClass timer = new CounterClass(180000,1000, tvTimerValue);
        btnStart.setOnClickListener(new OnClickListener() {  
            @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timer.start(); 
				
			}  
          });
        btnPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				timer.cancel();
				
			}
		});
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
