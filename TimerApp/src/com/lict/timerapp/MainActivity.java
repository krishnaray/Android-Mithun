package com.lict.timerapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity{
	
	Button btnStart;
	Button btnPause;
	TextView tvTimerValue;
	
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnStart = (Button)findViewById(R.id.startButton);
        btnPause = (Button)findViewById(R.id.pauseButton);
        tvTimerValue = (TextView)findViewById(R.id.timerValue);
        
        btnStart.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(updateTimerThread, 0);
			}
		});
        btnPause.setOnClickListener(new View.OnClickListener() {
    		
			public void onClick(View view) {
				timeSwapBuff += timeInMilliseconds;
				customHandler.removeCallbacks(updateTimerThread);
			}
		});
    }
    

	protected Runnable updateTimerThread = new Runnable() {
		@Override
		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			int milliseconds = (int) (updatedTime % 1000);
			tvTimerValue.setText("" + mins + ":"
									+ String.format("%02d", secs) + ":"
										+ String.format("%03d", milliseconds));
			customHandler.postDelayed(this, 0);
		}
	};

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
