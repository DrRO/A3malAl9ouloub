package com.pdf.kouloub;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.pdftest.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.pdf.kouloub.externals.AKManager;
import com.pdf.kouloub.utils.MySuperScaler;

import eu.janmuller.android.simplecropimage.CropImage;

public class PDFViewerActivity extends MySuperScaler implements OnLoadCompleteListener, OnPageChangeListener, OnSeekBarChangeListener {

	private PDFView pdf ;
	
	public static final int REQUEST_CODE_CROP_IMAGE   	= 0x1;
	public static final int REQUEST_CODE_SHARE   		= 0x2;
	
	private static final String PARTS_FRAGMENT = "parts_fragment";
	private static final String BOOKMARKS_FRAGMENT = "bookmark_fragment";

	private static final String TAG = null;
	private Fragment fragment;

	private int book_id;

	private ImageView preview1, preview2, preview3, preview4, preview5, 
	preview6, preview7, preview8, preview9, preview10 ;

	private Button back, add_bookmark, bookmark_list, crop, list_summary ;

	private int pdf_pages_number ;
	private SeekBar bar ;
	private Animation zoom_preview;
	
	private String filePath;
	private Uri resultUri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_shower);

		pdf = (PDFView) findViewById(R.id.pdfView);
		
		zoom_preview = AnimationUtils.loadAnimation(this, R.anim.zoom_preview);

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

		pdf.setDrawingCacheEnabled(true);
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




		preview1.setImageDrawable(new BitmapDrawable(getResources(), bm1));
		preview2.setImageDrawable(new BitmapDrawable(getResources(), bm2));
		preview3.setImageDrawable(new BitmapDrawable(getResources(), bm3));
		preview4.setImageDrawable(new BitmapDrawable(getResources(), bm4));
		preview5.setImageDrawable(new BitmapDrawable(getResources(), bm5));
		preview6.setImageDrawable(new BitmapDrawable(getResources(), bm6));
		preview7.setImageDrawable(new BitmapDrawable(getResources(), bm7));
		preview8.setImageDrawable(new BitmapDrawable(getResources(), bm8));
		preview9.setImageDrawable(new BitmapDrawable(getResources(), bm9));
		preview10.setImageDrawable(new BitmapDrawable(getResources(), bm10));


		bar = (SeekBar)findViewById(R.id.seekBar1); // make seekbar object
        
		bar.setRotation(180);
		bar.setOnSeekBarChangeListener(this);
		

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
		
		crop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				startCropImage();
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

		pdf_pages_number = pdf.getPageCount() ;
		
		bar.setMax(pdf_pages_number);
		Log.e("NUMBER OF PAGES", pdf_pages_number +"");

	}
	@Override
	public void onPageChanged(int page, int pageCount) {
		
		toggleBookMarkButton(pdfDB.isBookMarked(book_id, page), page);
		
		updatePreviews(page);
		
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
		pdf.jumpTo(pageTo);
	}

	private void gotoFragment(String fragmentTAG){

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.down_in, R.anim.down_out, R.anim.up_in, R.anim.up_out);

		fragment = getSupportFragmentManager().findFragmentByTag(fragmentTAG);

		if(fragment == null){
			Log.i("", "new instance of views fragment................");
			if(fragmentTAG.equals(PARTS_FRAGMENT)){
				fragment = new BookContentFragment(book_id);
				transaction.add(R.id.fragment_view, fragment, fragmentTAG);
			}
				
			else 
			{
				fragment = new BookMarkFragment(book_id);
				transaction.replace(R.id.fragment_view, fragment, fragmentTAG);
				scaled = false;
			}
			
			transaction.addToBackStack(fragmentTAG);
		}else{
			Log.i("", "show the same instance");
			transaction.attach(fragment);
		}

		
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
	@Override
	public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {

		updatePreviews(progress);
		pdf.jumpTo(progress);
		
		
	}
	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void updatePreviews(int progress)
	{
float preview_part = (float) ((double) progress / pdf_pages_number) ;
		
		preview1.clearAnimation();
		preview2.clearAnimation();
		preview3.clearAnimation();
		preview4.clearAnimation();
		preview5.clearAnimation();
		preview6.clearAnimation();
		preview7.clearAnimation();
		preview8.clearAnimation();
		preview9.clearAnimation();
		preview10.clearAnimation();
		
		
		
		if (preview_part < 0.1)
			{
			preview1.startAnimation(zoom_preview);
			preview1.bringToFront();
			}
		else if (preview_part >= 0.1 && preview_part < 0.2)
			{
			preview2.startAnimation(zoom_preview);
			preview2.bringToFront();
			}
		else if (preview_part >= 0.2 && preview_part < 0.3)
			{
			preview3.startAnimation(zoom_preview);
			preview3.bringToFront();
			}
		else if (preview_part >= 0.3 && preview_part < 0.4)
			{
			preview4.startAnimation(zoom_preview);
			preview4.bringToFront();
			}
		else if (preview_part >= 0.4 && preview_part < 0.5)
			{
			preview5.startAnimation(zoom_preview);
			preview5.bringToFront();
			}
		else if (preview_part >= 0.5 && preview_part < 0.6)
			{
			preview6.startAnimation(zoom_preview);
			preview6.bringToFront();
			}
		else if (preview_part >= 0.6 && preview_part < 0.7)
			{
			preview7.startAnimation(zoom_preview);
			preview7.bringToFront();
			}
		else if (preview_part >= 0.7 && preview_part < 0.8)
			{
			preview8.startAnimation(zoom_preview);
			preview8.bringToFront();
			}
		else if (preview_part >= 0.8 && preview_part < 0.9)
			{
			preview9.startAnimation(zoom_preview);
			preview9.bringToFront();
			}
		else if (preview_part >= 0.9 && preview_part < 1)
			{
			preview10.startAnimation(zoom_preview);
			preview10.bringToFront();
			}
	
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	        if (resultCode != RESULT_OK) {

	            return;
	        }

	        switch (requestCode) {
	            case REQUEST_CODE_CROP_IMAGE:

	                String path = data.getStringExtra(CropImage.IMAGE_PATH);
	                if (path == null) {

	                    return;
	                }

	                shareCroppedImage(filePath);
	                break;
	                
	            case REQUEST_CODE_SHARE:
	            	File f = new File(filePath);
	            	if(f.exists())
	            		f.delete();
	            	break;
	        }
	        super.onActivityResult(requestCode, resultCode, data);
	    }

	
	private void startCropImage() {
		pdf.buildDrawingCache();
		storeImage(pdf.getDrawingCache());
		pdf.destroyDrawingCache();

		Intent intent = new Intent(this, CropImage.class);
		intent.putExtra(CropImage.IMAGE_PATH, filePath);
		intent.putExtra(CropImage.SCALE, true);

		intent.putExtra(CropImage.ASPECT_X, 3);
		intent.putExtra(CropImage.ASPECT_Y, 2);

		startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
	}
	
	private boolean storeImage(Bitmap imageData) {
		//get path to external storage (SD card)
		
		String folder = getString(R.string.app_name);
		
		String paintsStoragePath = Environment.getExternalStorageDirectory() + File.separator + "Pictures" + File.separator + folder;
		
		//create storage directories, if they don't exist
		AKManager.dirChecker(paintsStoragePath);
		
		try {
			String filename = "ak_crop_" + System.currentTimeMillis();
			filePath = paintsStoragePath + File.separator + filename + ".jpg";
			FileOutputStream fileOutputStream = new FileOutputStream(filePath);

			BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

			//choose another format if PNG doesn't suit you
			imageData.compress(CompressFormat.JPEG, 100, bos);

			bos.flush();
			bos.close();
			
			ContentValues image = new ContentValues();
            image.put(Images.Media.TITLE, folder);
            image.put(Images.Media.DISPLAY_NAME, filename);
            image.put(Images.Media.DESCRIPTION, filePath);
            image.put(Images.Media.DATE_ADDED, System.currentTimeMillis());
            image.put(Images.Media.MIME_TYPE, "image/jpeg");
            image.put(Images.Media.ORIENTATION, 0);
            File fName = new File(filePath);
            File parent = fName.getParentFile();
            image.put(Images.ImageColumns.BUCKET_ID, parent.toString()
                    .toLowerCase().hashCode());
            image.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, parent.getName()
                    .toLowerCase());
            image.put(Images.Media.SIZE, fName.length());
            image.put(Images.Media.DATA, fName.getAbsolutePath());
            resultUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            {
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File f = new File("file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
                    Uri contentUri = Uri.fromFile(f);
                    mediaScanIntent.setData(contentUri);
                    this.sendBroadcast(mediaScanIntent);
            }
            else
            {
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
            }
            
		} catch (FileNotFoundException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			return false;
		} catch (IOException e) {
			Log.w("TAG", "Error saving image file: " + e.getMessage());
			return false;
		} catch (Exception e) {
			Log.w("TAG", "Error creating image file: " + e.getMessage());
			return false;
		}

		return true;
	}
	
	private void shareCroppedImage(String imagePath){

		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.setType("image/jpeg");
		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
		sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, resultUri);
		startActivityForResult(Intent.createChooser(sharingIntent, getString(R.string.share)), REQUEST_CODE_SHARE);

	}

}
