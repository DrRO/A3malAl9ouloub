package com.pdf.kouloub;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.example.pdftest.R;
import com.pdf.kouloub.utils.MySuperScaler;

public class AboutActivity extends MySuperScaler{

	Button more_apps, share ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		more_apps = (Button) findViewById(R.id.more_apps);
		share = (Button) findViewById(R.id.share);

		more_apps.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://alnour.ws/zadapp/")));  
				
			}
		});
		
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String shareBody = " *** TO DEFINE ***";
				Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
				startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));
				
			}
		});
//		more_apps.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				switch (event.getAction()) {
//				case MotionEvent.ACTION_DOWN: {
//					Button view = (Button) v;
//					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
//					v.invalidate();
//					break;
//				}
//				case MotionEvent.ACTION_UP: {
//					// Your action here on button click
//
//					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://alnour.ws/zadapp/")));  
//
//				}
//				case MotionEvent.ACTION_CANCEL: {
//					Button view = (Button) v;
//					view.getBackground().clearColorFilter();
//					view.invalidate();
//					break;
//				}
//				}
//				return true;
//			}
//		});
//
//
//
//		share.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				switch (event.getAction()) {
//				case MotionEvent.ACTION_DOWN: {
//					Button view = (Button) v;
//					view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
//					v.invalidate();
//					break;
//				}
//				case MotionEvent.ACTION_UP: {
//					// Your action here on button click
//					String shareBody = " *** TO DEFINE ***";
//					Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//					sharingIntent.setType("text/plain");
//					sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
//					sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//					startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));
//
//
//				}
//				case MotionEvent.ACTION_CANCEL: {
//					Button view = (Button) v;
//					view.getBackground().clearColorFilter();
//					view.invalidate();
//					break;
//				}
//				}
//				return true;
//			}
//		});

	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
