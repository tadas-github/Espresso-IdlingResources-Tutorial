package com.example.servicedemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	Button mStartService;
	Button mStopService;
	TextView titleText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Declare the start service button
		mStartService = (Button) findViewById(R.id.btn_start_service);

		// Declare the stop service button
		mStopService = (Button) findViewById(R.id.btn_stop_service);

		//Title Text
		titleText = (TextView) findViewById(R.id.textView1);

		// Add event listener to each button
		mStartService.setOnClickListener(this);
		mStopService.setOnClickListener(this);

		registerReceiver( mMessageReceiver, new IntentFilter("GETDATA"));

	}

	private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String str= intent.getStringExtra("DATA");
			titleText.setText(str);
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mMessageReceiver);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// User click start button
		if (v.getId() == R.id.btn_start_service) {
			// Display the Toast Message
			startService(new Intent(getBaseContext(), DemoService.class));
		} else { // User click stop button

			// Stop the service
			stopService(new Intent(getBaseContext(), DemoService.class));
		}
	}

}
