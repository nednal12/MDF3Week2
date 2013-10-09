package com.bmarohnic.externalstorage;

import android.content.BroadcastReceiver;
import android.os.Environment;

public class DeviceStorage {

	
	BroadcastReceiver mExternalStorageReceiver;
	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWriteable = false;
	
	void updateExternalStorageState()
	{
		String state = Environment.getExternalStorageState();
		
		
	}
}
