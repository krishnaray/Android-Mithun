package com.lict.volumecntrolapp;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class MainActivity extends Activity {

	SeekBar sbVolumeController;
	TextView tvVolumeValue;
	
	int volume;
	
	private AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sbVolumeController = (SeekBar) findViewById(R.id.volumeControlSeekBar1);
        tvVolumeValue = (TextView) findViewById(R.id.volumeValueTextView);
        
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        
        sbVolumeController.setMax(100);
        sbVolumeController.setKeyProgressIncrement(1);
        
        sbVolumeController.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_SHOW_UI);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
				if(!(progress >= maxVolume))
					volume = progress;
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
