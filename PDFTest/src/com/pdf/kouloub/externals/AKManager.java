package com.pdf.kouloub.externals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

public class AKManager {

	static final String TAG = "AKManager";
	
	private static AKManager mInstance = null;
	private static SharedPreferences settings;
	private SharedPreferences.Editor editor;
	private Context mContext;
	
	private ArrayList<Book> books = new ArrayList<Book>();
	
	
	public AKManager(Context context) {
		
		mContext = context;
		settings = PreferenceManager.getDefaultSharedPreferences(context);
		editor = settings.edit();
		
		books.add(new Book(1, "covers/book1.png", "mhbbat-eklas-1"));
		books.add(new Book(2, "covers/book2.png", "mhbbat-twkl-2"));
		books.add(new Book(3, "covers/book3.png", "mhbbat-mhbh-3"));
		books.add(new Book(4, "covers/book4.png", "mhbbat-kuf-4"));
		books.add(new Book(5, "covers/book5.png", "mhbbat-rja-5"));
		books.add(new Book(6, "covers/book6.png", "mhbbat-t8wa-6"));
		books.add(new Book(7, "covers/book7.png", "mhbbat-rda-7"));
		books.add(new Book(8, "covers/book8.png", "mhbbat-shkr-8"));
		books.add(new Book(9, "covers/book9.png", "mhbbat-sabr-9"));
		books.add(new Book(10, "covers/book10.png", "mhbbat-war3-10"));
		books.add(new Book(11, "covers/book11.png", "mhbbat-tfkor-11"));
		books.add(new Book(12, "covers/book12.png", "mhbbat-mohasba-12"));
		
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public synchronized static AKManager getInstance(Context context) {
		if (mInstance == null)
			mInstance = new AKManager(context);

		return mInstance;
	}
	
	
	public static Bitmap originalResolution(Context context, String path, int width, int height)   {
		Bitmap bm = null;
		BitmapFactory.Options bfOptions=new BitmapFactory.Options();
		bfOptions.inDither=false;                     //Disable Dithering mode
		bfOptions.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
		bfOptions.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
		bfOptions.inTempStorage=new byte[32 * 1024]; 

		// Calculate inSampleSize
		bfOptions.inSampleSize = calculateInSampleSize(bfOptions, width, height);

		// Decode bitmap with inSampleSize set
		bfOptions.inJustDecodeBounds = false;    

		InputStream in = null;
		try {
			in = context.getAssets().open(path);
			bm=BitmapFactory.decodeStream(in, null, bfOptions);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bm;

	}
	
	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public Drawable createCopy(Bitmap b){
		Config config = Config.ARGB_8888;
		Bitmap bm = b.copy(config, false);
		return new BitmapDrawable(mContext.getResources(), bm);
	}
	
	public static void dirChecker(String dir) {
		File f = new File(dir);
		if (!f.isDirectory()) {
			f.mkdirs();
		}
	}
}
