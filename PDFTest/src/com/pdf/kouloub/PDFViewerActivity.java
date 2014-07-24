package com.pdf.kouloub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.pdftest.R;
import com.joanzapata.pdfview.PDFView;

public class PDFViewerActivity extends Activity
//implements OnLoadCompleteListener, OnPageChangeListener, OnDrawListener
{

	PDFView pdf ;
	public static final String SAMPLE_FILE = "sample.pdf";
    public static final String ABOUT_FILE = "about.pdf";
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_shower);
		
		pdf = (PDFView) findViewById(R.id.pdfView);
		
		Button btn = (Button) findViewById(R.id.button1);
		
		
		pdf.fromAsset("mhbbat-eklas-1.pdf")
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
//	@Override
//	public void loadComplete(int nbPages) {
//
//		Toast.makeText(getApplicationContext(), "PDF LOADED !", Toast.LENGTH_SHORT).show();
//		
//	}
//	@Override
//	public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight,
//			int displayedPage) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void onPageChanged(int page, int pageCount) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
