package com.lict.displaybrightnessapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	SeekBar sbBrightnessController;
	TextView tvBrightnessValue;
	
	int brightness;
	ContentResolver contentResolver;
	Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sbBrightnessController = (SeekBar) findViewById(R.id.brightnessControlSeekBar1);
        tvBrightnessValue = (TextView) findViewById(R.id.brightnessValueTextView);
        
      //Get the content resolver
        contentResolver = getContentResolver();
        //Get the current window
        window = getWindow();
        
        sbBrightnessController.setMax(100);
        sbBrightnessController.setKeyProgressIncrement(1);
        
        try{
        	brightness = System.getInt(contentResolver, System.SCREEN_BRIGHTNESS);
        }catch(Exception e)
        {
        	Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        //tvBrightnessValue.setText(brightness);
        sbBrightnessController.setProgress(brightness);
        sbBrightnessController.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				System.putInt(contentResolver, System.SCREEN_BRIGHTNESS, brightness);
				LayoutParams layoutparams = window.getAttributes();
				layoutparams.screenBrightness = brightness/(float)100;
				window.setAttributes(layoutparams);
				tvBrightnessValue.setText(String.valueOf(layoutparams.screenBrightness));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if(progress <= 20)
					brightness = 20;
				else
					brightness = progress;
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
