package com.lict.vibrationapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	Vibrator vibrator;
	
	long[] vibratePattern = {100, 500, 700, 1200};
	
	Button btnVibrate, btnPatternVibrate;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        btnVibrate = (Button) findViewById(R.id.vibrateButton);
        btnPatternVibrate = (Button) findViewById(R.id.patternVibrateButton);
        btnVibrate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(vibrator.hasVibrator())
					vibrator.vibrate(500);
			}
		});
        btnPatternVibrate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(vibrator.hasVibrator())
					vibrator.vibrate(vibratePattern, -1);
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
