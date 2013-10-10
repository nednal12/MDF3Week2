package com.bmarohn.mdf3week2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.b2marohn.mdf3week2.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class PictureActivity extends Activity {

	public static final int MEDIA_TYPE_IMAGE = 1;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	ImageView imageView;
	
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

	// Create a file Uri for saving the picture
	private static Uri getOutputMediaFileUri(int type)
	{
		return Uri.fromFile(getOutputMediaFile(type));
	}
	  
	// Create a file for saving the picture
	private static File getOutputMediaFile(int type)
	{	
		
		File mediaStorageDirectory = new File(Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES), "MDF3PictureApp");
		
		if (! mediaStorageDirectory.exists())
		{
			if (! mediaStorageDirectory.mkdirs())
			{
				Log.i("getOutputMediaFile", "Unable to create directory");
				return null;
			}
		}
		
		
		// Create the picture file
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
		
		File mediaFile = new File(mediaStorageDirectory.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		
		Log.i("getOutputMediaFile", "Directory created");
		
		return mediaFile;
	}
	
	public void takePictureButton(View view){
		
		// Create an intent to take a picture and then return control to this application
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		pictureUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
		String stringUri = pictureUri.toString();
		Log.i("stringUri: ", stringUri);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
		intent.putExtra("uriName", pictureUri);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.i("onActivityResult", "This is being called");
		if (resultCode == RESULT_OK)
		{
			Log.i("onActivityResult", "This is being called");
			imageView = (ImageView) this.findViewById(R.id.imageView1);
			imageView.setImageURI(pictureUri);
			
			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Notification notification = new Notification();
			
			NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Image Saved to External Storage")
				.setContentText(pictureUri.toString());
			
			notification = notificationBuilder.build();
			
			notification.tickerText = "Image Saved to External Storage";
			nm.notify(5, notification);
			
		}
	}
	
	

}
