package com.lict.shakeditectapp.sensors.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

public class ShakeDitector implements SensorEventListener{
	// The gForce that is necessary to register as shake. Must be greater than 1G (one earth gravity unit)
    private static final float SHAKE_THRESHOLD_GRAVITY = 1.5F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
    
    private OnShakeListener mShakeListener;
    private long mShakeTimestamp;
    private int mShakeCount;
    
    public void setOnShakeListener(OnShakeListener listener) {
        mShakeListener = listener;
    }
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(mShakeListener != null){
			float xAxisVal = event.values[0];
            float yAxisVal = event.values[1];
            float zAxisVal = event.values[2];
            
            float xGForce = xAxisVal / SensorManager.GRAVITY_EARTH;
            float yGForce = yAxisVal / SensorManager.GRAVITY_EARTH;
            float zGForce = zAxisVal / SensorManager.GRAVITY_EARTH;
            
            // gForce will be close to 1 when there is no movement.
            float gForce = FloatMath.sqrt(xGForce * xGForce + yGForce * yGForce + zGForce * zGForce);
            if(gForce > SHAKE_THRESHOLD_GRAVITY){
            	final long timeNow = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > timeNow ) {
                    return;
                }
                // reset the shake count after 3 seconds of no shakes
                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < timeNow ) {
                    mShakeCount = 0;
                }
                mShakeTimestamp = timeNow;
                mShakeCount++;

                mShakeListener.onShake(mShakeCount);
            }
		}
	}

}
