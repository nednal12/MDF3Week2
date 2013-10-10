package com.bmarohnic.externalstorage;

import android.content.BroadcastReceiver;
import android.os.Environment;
import android.util.Log;

public class DeviceStorage {

	
	BroadcastReceiver mExternalStorageReceiver;
	boolean _bolExternalStorageAvailable = false;
	boolean _bolExternalStorageWriteable = false;
	
	void updateExternalStorageState()
	{
		String externalStorageState = Environment.getExternalStorageState();
		
		if (Environment.MEDIA_MOUNTED.equals(externalStorageState))
		{
			_bolExternalStorageAvailable = _bolExternalStorageWriteable = true;
		}
		else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageState))
		{
			_bolExternalStorageAvailable = true;
			_bolExternalStorageWriteable = false;
		}
		else
		{
			_bolExternalStorageAvailable = _bolExternalStorageWriteable = false;
		}
		Log.i("onCreate - External Storage State: ", externalStorageState.toString());
		
		
	}
}
