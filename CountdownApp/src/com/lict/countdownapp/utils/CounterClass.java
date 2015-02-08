package com.lict.countdownapp.utils;

import java.util.concurrent.TimeUnit;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CounterClass extends CountDownTimer{
	TextView tvTimerValue;
	public CounterClass(long millisInFuture, long countDownInterval, TextView tv) {
		super(millisInFuture, countDownInterval);
		tvTimerValue = tv;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		long millis = millisUntilFinished;  
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),  
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),  
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));  
            System.out.println(hms);  
            tvTimerValue.setText(hms);  
		
	}

	@Override
	public void onFinish() {
		tvTimerValue.setText("Completed."); 
		
	}

}
