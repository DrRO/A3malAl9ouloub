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
public class MainBookChoice extends MySuperScaler implements OnClickListener {

	private Button info ;
	private ImageView book_1, book_2, book_3, book_4 ,book_5 ,book_6 ,
						book_7,book_8 , book_9 , book_10 ,book_11, book_12 , new_back;
	

	public static final String book1 = "mhbbat-eklas-1.pdf";
	public static final String book2 = "mhbbat-twkl-2.pdf";
	public static final String book3 = "mhbbat-mhbh-3.pdf";
	public static final String book4 = "mhbbat-kuf-4.pdf";
	public static final String book5 = "mhbbat-rja-5.pdf";
	public static final String book6 = "mhbbat-t8wa-6.pdf";
	public static final String book7 = "mhbbat-rda-7.pdf";
	public static final String book8 = "mhbbat-shkr-8.pdf";
	public static final String book9 = "mhbbat-sabr-9.pdf";
	public static final String book10 = "mhbbat-war3-10.pdf";
	public static final String book11 = "mhbbat-tfkor-11.pdf";
	public static final String book12 = "mhbbat-mohasba-12.pdf";
	
	
	private RelativeLayout principal_layout ;
	
	public String book_number ;
	
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
		
		book_1.setTag(book1);
		book_2.setTag(book2);
		book_3.setTag(book3);
		book_4.setTag(book4);
		book_5.setTag(book5);
		book_6.setTag(book6);
		book_7.setTag(book7);
		book_8.setTag(book8);
		book_9.setTag(book9);
		book_10.setTag(book10);
		book_11.setTag(book11);
		book_12.setTag(book12);
		
		
//		book_1.setOnClickListener( new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				 Intent i = new Intent(MainBookChoice.this, PDFViewerActivity.class);
//                 Bundle b = null;
//                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                     Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
//                     b = ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, 0, 0).toBundle();
//                 }
//                 startActivity(i, b);
//				
//			}
//		});
		
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

			Intent i = new Intent(MainBookChoice.this, PDFViewerActivity.class) ;
			i.putExtra("book", book_number);
			startActivity(i);
			finish();

			super.handleMessage(msg);
		}
	};

	@Override
	public void onClick(final View v) {

		book_number = (String) v.getTag();
		
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
