package com.pdf.kouloub;

import java.io.IOException;
import java.io.InputStream;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pdftest.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.pdf.kouloub.externals.AKManager;
import com.pdf.kouloub.utils.MySuperScaler;

public class PDFViewerActivity extends MySuperScaler implements OnLoadCompleteListener, OnPageChangeListener {

	private PDFView pdf ;

	private static final String PARTS_FRAGMENT = "parts_fragment";
	private static final String BOOKMARKS_FRAGMENT = "bookmark_fragment";
	private Fragment fragment;

	private int book_id;

	private ImageView preview1, preview2, preview3, preview4, preview5, 
	preview6, preview7, preview8, preview9, preview10 ;

	private Button back, add_bookmark, bookmark_list, crop, list_summary ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_shower);

		pdf = (PDFView) findViewById(R.id.pdfView);

		preview1 = (ImageView) findViewById(R.id.preview1);
		preview2 = (ImageView) findViewById(R.id.preview2);
		preview3 = (ImageView) findViewById(R.id.preview3);
		preview4 = (ImageView) findViewById(R.id.preview4);
		preview5 = (ImageView) findViewById(R.id.preview5);
		preview6 = (ImageView) findViewById(R.id.preview6);
		preview7 = (ImageView) findViewById(R.id.preview7);
		preview8 = (ImageView) findViewById(R.id.preview8);
		preview9 = (ImageView) findViewById(R.id.preview9);
		preview10 = (ImageView) findViewById(R.id.preview10);

		back = (Button) findViewById(R.id.pdf_back);
		add_bookmark = (Button) findViewById(R.id.pdf_bookmark);
		bookmark_list = (Button) findViewById(R.id.pdf_bookmark_list);
		crop = (Button) findViewById(R.id.pdf_crop);
		list_summary = (Button) findViewById(R.id.pdf_list);

		Bundle b = getIntent().getExtras();
		final String book_to_read = b.getString("book");
		book_id = b.getInt("book_id");


		pdf.fromAsset(book_to_read + ".pdf")
		.defaultPage(1)
		.showMinimap(false)
		.enableSwipe(true)
		.onLoad(this)
		.onPageChange(this)
		.load();

		Bitmap bm1 = AKManager.originalResolution(this, "previews/"+book_to_read+"/1.png", preview1.getWidth(), preview1.getHeight());
		Bitmap bm2 = AKManager.originalResolution(this, "previews/"+book_to_read+"/2.png", preview1.getWidth(), preview2.getHeight());
		Bitmap bm3 = AKManager.originalResolution(this, "previews/"+book_to_read+"/3.png", preview1.getWidth(), preview3.getHeight());
		Bitmap bm4 = AKManager.originalResolution(this, "previews/"+book_to_read+"/4.png", preview1.getWidth(), preview4.getHeight());
		Bitmap bm5 = AKManager.originalResolution(this, "previews/"+book_to_read+"/5.png", preview1.getWidth(), preview5.getHeight());
		Bitmap bm6 = AKManager.originalResolution(this, "previews/"+book_to_read+"/6.png", preview1.getWidth(), preview6.getHeight());
		Bitmap bm7 = AKManager.originalResolution(this, "previews/"+book_to_read+"/7.png", preview1.getWidth(), preview7.getHeight());
		Bitmap bm8 = AKManager.originalResolution(this, "previews/"+book_to_read+"/8.png", preview1.getWidth(), preview8.getHeight());
		Bitmap bm9 = AKManager.originalResolution(this, "previews/"+book_to_read+"/9.png", preview1.getWidth(), preview9.getHeight());
		Bitmap bm10 = AKManager.originalResolution(this, "previews/"+book_to_read+"/10.png", preview1.getWidth(), preview10.getHeight());


		//		Drawable d1 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d2 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d3 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d4 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d5 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d6 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d7 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d1 = new BitmapDrawable(getResources(), bm1);
		//		Drawable d1 = new BitmapDrawable(getResources(), bm1);


		preview1.setBackgroundDrawable(new BitmapDrawable(getResources(), bm1));
		preview2.setBackgroundDrawable(new BitmapDrawable(getResources(), bm2));
		preview3.setBackgroundDrawable(new BitmapDrawable(getResources(), bm3));
		preview4.setBackgroundDrawable(new BitmapDrawable(getResources(), bm4));
		preview5.setBackgroundDrawable(new BitmapDrawable(getResources(), bm5));
		preview6.setBackgroundDrawable(new BitmapDrawable(getResources(), bm6));
		preview7.setBackgroundDrawable(new BitmapDrawable(getResources(), bm7));
		preview8.setBackgroundDrawable(new BitmapDrawable(getResources(), bm8));
		preview9.setBackgroundDrawable(new BitmapDrawable(getResources(), bm9));
		preview10.setBackgroundDrawable(new BitmapDrawable(getResources(), bm10));

		//		preview1.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/1.png"));
		//		preview2.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/2.png"));
		//		preview3.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/3.png"));
		//		preview4.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/4.png"));
		//		preview5.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/5.png"));
		//		preview6.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/6.png"));
		//		preview7.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/7.png"));
		//		preview8.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/8.png"));
		//		preview9.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/9.png"));
		//		preview10.setImageBitmap(getBitmapFromAssets("previews/"+book_to_read+"/10.png"));


		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});

		list_summary.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				gotoFragment(PARTS_FRAGMENT);

			}
		});

		bookmark_list.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				gotoFragment(BOOKMARKS_FRAGMENT);

			}
		});

		add_bookmark.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				boolean isBookMarked = pdfDB.isBookMarked(book_id, pdf.getCurrentPage());
				if(isBookMarked){
					pdfDB.removeFromBookMarks(book_id, pdf.getCurrentPage());
				}else
					pdfDB.addToBookMarks(book_id, pdf.getCurrentPage());
				
				toggleBookMarkButton(!isBookMarked, pdf.getCurrentPage());

			}
		});


	}
	@Override
	public void loadComplete(int nbPages) {

		Toast.makeText(getApplicationContext(), "PDF LOADED !", Toast.LENGTH_SHORT).show();

	}
	@Override
	public void onPageChanged(int page, int pageCount) {
		
		toggleBookMarkButton(pdfDB.isBookMarked(book_id, page), page);
		
	}


	private void toggleBookMarkButton(boolean isBookMarked, int page) {
		if(isBookMarked)
			add_bookmark.setBackgroundResource(R.drawable.pdf_bookmark_added);
		else
			add_bookmark.setBackgroundResource(R.drawable.pdf_bookmark);
	}
	public Bitmap getBitmapFromAssets(String fileName) {

		AssetManager assetManager = getAssets();
		Bitmap bitmap = null;
		InputStream istr;
		try {
			istr = assetManager.open(fileName);
			bitmap = BitmapFactory.decodeStream(istr);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	@Override
	public void onBackPressed() {

		if(fragment == null)
			startActivity(new Intent( PDFViewerActivity.this, MainBookChoice.class));
		else{
			toggleEnabledViews(true);
			fragment = null;
		}
		super.onBackPressed();
	}

	public void onPageItemClicked(int pageTo){
		onBackPressed();
		pdf.jumpTo(pageTo + 1);
	}

	private void gotoFragment(String fragmentTAG){

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.down_in, R.anim.down_out, R.anim.up_in, R.anim.up_out);

		fragment = getSupportFragmentManager().findFragmentByTag(fragmentTAG);

		if(fragment == null){
			Log.i("", "new instance of views fragment................");
			if(fragmentTAG.equals(PARTS_FRAGMENT))
				fragment = new BookContentFragment(book_id);
			else 
				fragment = new BookMarkFragment(book_id);

			transaction.replace(R.id.fragment_view, fragment, fragmentTAG);
			transaction.addToBackStack(fragmentTAG);
		}else{
			Log.i("", "show the same instance");
			transaction.attach(fragment);
		}

		scaled = false;
		transaction.commit();

		toggleEnabledViews(false);
	}

	private void toggleEnabledViews(boolean enabled){
		pdf.setEnabled(enabled);
		back.setEnabled(enabled);
		add_bookmark.setEnabled(enabled);
		bookmark_list.setEnabled(enabled);
		crop.setEnabled(enabled);
		list_summary.setEnabled(enabled);
	}

}
