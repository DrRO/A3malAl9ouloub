package com.pdf.kouloub;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.pdftest.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

public class PDFViewerActivity extends Activity
implements OnLoadCompleteListener, OnPageChangeListener, OnDrawListener
{
	
    
    
	private PDFView pdf ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_shower);
		
		pdf = (PDFView) findViewById(R.id.pdfView);
		
		Button btn = (Button) findViewById(R.id.button1);
		
		
		Bundle b = getIntent().getExtras();
		final String book_to_read = b.getString("book");
		
		
		pdf.fromAsset(book_to_read)
	    .defaultPage(1)
	    .showMinimap(true)
	    .enableSwipe(true)
	    .load();
		
//		pdf.fromAsset("mhbbat-eklas-1.pdf")
//		.pages(1,2,3,4,5,6)
//		.defaultPage(1)
//	    .showMinimap(false)
//	    .enableSwipe(true)
////	    .onDraw(this)
////	    .onLoad(this)
////	    .onPageChange(this)
//	    .load();
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pdf.jumpTo(4);
			}
		});
		
		
	}
	@Override
	public void loadComplete(int nbPages) {

		Toast.makeText(getApplicationContext(), "PDF LOADED !", Toast.LENGTH_SHORT).show();
		
	}
	@Override
	public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight,
			int displayedPage) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageChanged(int page, int pageCount) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onBackPressed() {

		startActivity(new Intent( PDFViewerActivity.this, MainBookChoice.class));
		finish();
		
	}
	
}
