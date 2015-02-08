package com.lict.proximityapp.sensors.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ProximityDitector implements SensorEventListener{
	static float proximityExisting = 0;
    private OnProximityChangeListener mProximityChangeListener;
    
    public void setOnShakeListener(OnProximityChangeListener listener) {
    	mProximityChangeListener = listener;
    }
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(mProximityChangeListener != null){
			float proximityVal = event.values[0];
			//float proximityDelta = Math.abs(proximityVal - proximityExisting);
			if(proximityVal != proximityExisting){
				proximityExisting = proximityVal;
				mProximityChangeListener.onChange(proximityVal);
			} 
		}
	}

}
