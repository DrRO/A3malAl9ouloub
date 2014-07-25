package com.pdf.kouloub;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.pdftest.R;
import com.pdf.kouloub.externals.AKManager;
import com.pdf.kouloub.externals.Book;
import com.pdf.kouloub.utils.MySuperScaler;
import com.pdf.kouloub.utils.Utils;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint("HandlerLeak")
public class MainBookChoice extends MySuperScaler implements OnClickListener {

	private Button info ;
	private ImageView book_1, book_2, book_3, book_4 ,book_5 ,book_6 ,
						book_7,book_8 , book_9 , book_10 ,book_11, book_12 , new_back, img_cover;
	
	private RelativeLayout stage1, stage2, stage3, stage4, stage5, stage6;

	private ArrayList<Book> books;
	
	private RelativeLayout principal_layout ;
	
	public String pdfFile ;
	
	private AnimationSet animationSet;
	private Animation zoomin, alpha;
	private AKManager akManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_books_choice);
		
		akManager = AKManager.getInstance(this);
		books = akManager.getBooks();
		
		zoomin = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
		alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

		animationSet = new AnimationSet(true);
		animationSet.addAnimation(zoomin);
		animationSet.addAnimation(alpha);
		animationSet.setFillAfter(true);
		animationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {

				Message msg =  Message.obtain();
				msg.what = 1;
				splashHandler.sendMessageDelayed(msg, 2000);

			}
		});

		principal_layout = (RelativeLayout) findViewById(R.id.principal_layout);
		new_back = (ImageView) findViewById(R.id.new_back);
		img_cover = (ImageView) findViewById(R.id.img_cover);
		
		
		info = (Button) findViewById(R.id.info);
		
		stage1 = (RelativeLayout) findViewById(R.id.stage1);
		stage2 = (RelativeLayout) findViewById(R.id.stage2);
		stage3 = (RelativeLayout) findViewById(R.id.stage3);
		stage4 = (RelativeLayout) findViewById(R.id.stage4);
		stage5 = (RelativeLayout) findViewById(R.id.stage5); 
		stage6 = (RelativeLayout) findViewById(R.id.stage6);
		
		Bitmap bmStage = AKManager.originalResolution(this, "covers/stage.png", stage1.getWidth(), stage1.getHeight());
		Drawable dStage = new BitmapDrawable(getResources(), bmStage);
		stage1.setBackgroundDrawable(dStage);
		stage2.setBackgroundDrawable(akManager.createCopy(bmStage));
		stage3.setBackgroundDrawable(akManager.createCopy(bmStage));
		stage4.setBackgroundDrawable(akManager.createCopy(bmStage));
		stage5.setBackgroundDrawable(akManager.createCopy(bmStage));
		stage6.setBackgroundDrawable(akManager.createCopy(bmStage));
		
		
		
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
		

		int w = book_1.getWidth();
		int h = book_1.getHeight();
		
		for (int i = 0; i < books.size(); i++) {

			Book b = books.get(i);
			Bitmap bm = AKManager.originalResolution(this, b.getCover(), w, h);
			Drawable d = new BitmapDrawable(getResources(), bm);

			switch (i+1) {
			case 1:
				book_1.setTag(i);
				book_1.setBackgroundDrawable(d);
				break;
			case 2:
				book_2.setTag(i);
				book_2.setBackgroundDrawable(d);
				break;
			case 3:
				book_3.setTag(i);
				book_3.setBackgroundDrawable(d);
				break;
			case 4:
				book_4.setTag(i);
				book_4.setBackgroundDrawable(d);
				break;
			case 5:
				book_5.setTag(i);
				book_5.setBackgroundDrawable(d);
				break;
			case 6:
				book_6.setTag(i);
				book_6.setBackgroundDrawable(d);
				break;
			case 7:
				book_7.setTag(i);
				book_7.setBackgroundDrawable(d);
				break;
			case 8:
				book_8.setTag(i);
				book_8.setBackgroundDrawable(d);
				break;
			case 9:
				book_9.setTag(i);
				book_9.setBackgroundDrawable(d);
				break;
			case 10:
				book_10.setTag(i);
				book_10.setBackgroundDrawable(d);
				break;
			case 11:
				book_11.setTag(i);
				book_11.setBackgroundDrawable(d);
				break;
			case 12:
				book_12.setTag(i);
				book_12.setBackgroundDrawable(d);
				break;
			default :
				break;
			}
		}
		
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
			i.putExtra("book", pdfFile);
			startActivity(i);
			finish();

			super.handleMessage(msg);
		}
	};

	@Override
	public void onClick(final View v) {

		int selectedPosition = (Integer) v.getTag();
		pdfFile = books.get(selectedPosition).getPdfFile();

		img_cover.setBackgroundDrawable(v.getBackground());
		img_cover.startAnimation(animationSet);
		img_cover.bringToFront();

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		Utils.cleanViews(principal_layout);
		
		book_1 = null;
		book_2 = null;
		book_3 = null;
		book_4 = null;
		book_5 = null;
		book_6 = null;
		book_7 = null;
		book_8 = null;
		book_9 = null;
		book_10 = null;
		book_11 = null;
		book_12 = null;
		
		img_cover = null;
		new_back = null;
	}
}
