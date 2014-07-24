package com.pdf.kouloub;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.pdftest.R;
import com.pdf.kouloub.utils.MySuperScaler;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint("HandlerLeak")
public class MainBookChoice extends MySuperScaler implements OnClickListener{

	private Button info ;
	private ImageView book_1, book_2, book_3, book_4 ,book_5 ,book_6 ,
						book_7,book_8 , book_9 , book_10 ,book_11, book_12 , new_back;
	
	private RelativeLayout principal_layout ;
	
	private Animation zoomin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_books_choice);
		
		zoomin = AnimationUtils.loadAnimation(this, R.anim.zoom_in);

		principal_layout = (RelativeLayout) findViewById(R.id.principal_layout);
		new_back = (ImageView) findViewById(R.id.new_back);
		
		
		info = (Button) findViewById(R.id.info);
		
		book_1 = (ImageView) findViewById(R.id.book_1);
		book_2 = (ImageView) findViewById(R.id.book_2);
		book_3 = (ImageView) findViewById(R.id.book_3);
		book_4 = (ImageView) findViewById(R.id.book_4);
		book_5 = (ImageView) findViewById(R.id.book_5);
		book_6 = (ImageView) findViewById(R.id.book_6);
		book_7 = (ImageView) findViewById(R.id.book_7);
		book_8 = (ImageView) findViewById(R.id.book_8);
		book_9 = (ImageView) findViewById(R.id.book_9);
		book_10 = (ImageView) findViewById(R.id.book_10);
		book_11 = (ImageView) findViewById(R.id.book_11);
		book_12 = (ImageView) findViewById(R.id.book_12);
		
		
		book_1.setOnClickListener(this);
		book_2.setOnClickListener(this);
		book_3.setOnClickListener(this);
		book_4.setOnClickListener(this);
		book_5.setOnClickListener(this);
		book_6.setOnClickListener(this);
		book_7.setOnClickListener(this);
		book_8.setOnClickListener(this);
		book_9.setOnClickListener(this);
		book_10.setOnClickListener(this);
		book_11.setOnClickListener(this);
		book_12.setOnClickListener(this);

		
		info.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				startActivity(new Intent(MainBookChoice.this, AboutActivity.class));
			}
		});
		
		
		
	}
	public static void setMargins (View v, int myNewX, int myNewY) {
		RelativeLayout.LayoutParams absParams = 
				(RelativeLayout.LayoutParams)v.getLayoutParams();
		absParams.leftMargin = 	myNewX ;
		absParams.topMargin = myNewY ;
		v.setLayoutParams(absParams);
	}

	private Handler splashHandler = new Handler() {
		
		public void handleMessage(Message msg) {

			startActivity(new Intent(MainBookChoice.this, PDFViewerActivity.class));
			finish();

			super.handleMessage(msg);
		}
	};

	@Override
	public void onClick(final View v) {

		int left = v.getLeft();
		int top = v.getTop();
		
		View parent = (View) v.getParent();
		((ViewGroup) parent).removeView(v);
		
		principal_layout.addView(v);
		setMargins(v, top, left);
		
		v.bringToFront();
		v.startAnimation(zoomin);
		zoomin.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {

				new_back.setBackground(v.getBackground());
				new_back.bringToFront();
				
				Message msg =  Message.obtain();
				msg.what = 1;
				splashHandler.sendMessageDelayed(msg, 2000);
						
				
			}
		});
		
		
	}
	
}
