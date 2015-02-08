package com.lict.gyroapp;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
	TextView tvXAxis;
	TextView tvYAxis;
	TextView tvZAxis;
	
	SensorManager sensorManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		tvXAxis = (TextView) findViewById(R.id.xTextView);
		tvYAxis = (TextView) findViewById(R.id.yTextView);
		tvZAxis = (TextView) findViewById(R.id.zTextView);
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void onResume(){
		super.onResume();
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), 
																		sensorManager.SENSOR_DELAY_NORMAL);
		
	}
	@Override
	protected void onStop() {
		super.onStop();
		 sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
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

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			switch (event.sensor.getType()) {
				case Sensor.TYPE_GYROSCOPE:
					tvXAxis.setText("x:"+Float.toString(event.values[0]));
					tvYAxis.setText("y:"+Float.toString(event.values[1]));
					tvZAxis.setText("z:"+Float.toString(event.values[2]));
					break;
				default:
					break;
			}
		}
		
	}
}
