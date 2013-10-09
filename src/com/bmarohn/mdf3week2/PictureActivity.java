package com.bmarohn.mdf3week2;

import java.io.File;

import com.b2marohn.mdf3week2.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class PictureActivity extends Activity {

	public static final int MEDIA_TYPE_IMAGE = 1;
	
	private Uri pictureUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);
		
		String externalStorageState = Environment.getExternalStorageState();
		
		Log.i("onCreate - External Storage State: ", externalStorageState.toString());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.picture, menu);
		return true;
	}

	private static Uri getOutputMediaFileUri(int type)
	{
		return Uri.fromFile(getOutputMediaFile(type));
	}
	
	private static File getOutputMediaFile(int type)
	{
		File mediaFile = null;
		
		
		return mediaFile;
	}
	
	public void takePictureButton(View view){
		
		// Create an intent to take a picture and then return control to this application
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		pictureUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
	}
}
