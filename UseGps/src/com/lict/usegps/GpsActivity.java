package com.lict.usegps;



import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class GpsActivity extends Activity {
	WebView wvposition;
	TextView tvLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener locListener = new MyLocationListener();
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,0,locListener);
		
		wvposition = (WebView) findViewById(R.id.webView);
		wvposition.getSettings().setJavaScriptEnabled(true);
		tvLocation = (TextView) findViewById(R.id.locationTextView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gps, menu);
		return true;
	}

	public class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			location.getLatitude();
			location.getLongitude();
			String locationText = "Latitud = "
					+ location.getLatitude() + " Longitude = "
					+ location.getLongitude();
			Toast.makeText(getApplicationContext(), locationText, Toast.LENGTH_SHORT)
					.show();
			Log.i("LOCATION : ", locationText);
			String url = "//www.google.com.bd/maps/@"
					+ location.getLatitude() + "," + location.getLongitude()
					+ ",15z?hl=en";
			wvposition.setWebViewClient(new WebViewClient());
			wvposition.loadUrl("www.google.com.bd");
			tvLocation.setText(locationText);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "GPS Disabled", Toast.LENGTH_SHORT).show();
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

	}
}
