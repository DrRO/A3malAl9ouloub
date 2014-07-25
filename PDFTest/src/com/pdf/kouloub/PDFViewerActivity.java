package com.pdf.kouloub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.pdftest.R;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.pdf.kouloub.utils.MySuperScaler;

public class PDFViewerActivity extends MySuperScaler implements OnLoadCompleteListener, OnPageChangeListener {
    
	private PDFView pdf ;
	
	private PDFView preview1, preview2, preview3, preview4, preview5, 
					preview6, preview7, preview8, preview9, preview10 ;
	
	private Button back, add_bookmark, bookmark_list, crop, list_summary ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdf_shower);
		
		pdf = (PDFView) findViewById(R.id.pdfView);
		
		preview1 = (PDFView) findViewById(R.id.preview1);
		preview2 = (PDFView) findViewById(R.id.preview2);
		preview3 = (PDFView) findViewById(R.id.preview3);
		preview4 = (PDFView) findViewById(R.id.preview4);
		preview5 = (PDFView) findViewById(R.id.preview5);
		preview6 = (PDFView) findViewById(R.id.preview6);
		preview7 = (PDFView) findViewById(R.id.preview7);
		preview8 = (PDFView) findViewById(R.id.preview8);
		preview9 = (PDFView) findViewById(R.id.preview9);
		preview10 = (PDFView) findViewById(R.id.preview10);
		
		back = (Button) findViewById(R.id.pdf_back);
		add_bookmark = (Button) findViewById(R.id.pdf_bookmark);
		bookmark_list = (Button) findViewById(R.id.pdf_bookmark_list);
		crop = (Button) findViewById(R.id.pdf_crop);
		list_summary = (Button) findViewById(R.id.pdf_list);
		
		Bundle b = getIntent().getExtras();
		final String book_to_read = b.getString("book");
		
		
		pdf.fromAsset(book_to_read)
	    .defaultPage(1)
	    .showMinimap(false)
	    .enableSwipe(true)
	    .onLoad(this)
	    .onPageChange(this)
	    .load();
		
		
		preview1.fromAsset(book_to_read).pages(1).defaultPage(1).showMinimap(false).enableSwipe(false).load();
		preview2.fromAsset(book_to_read).pages(2).defaultPage(2).showMinimap(false).enableSwipe(false).load();
		preview3.fromAsset(book_to_read).pages(3).defaultPage(3).showMinimap(false).enableSwipe(false).load();
		preview4.fromAsset(book_to_read).pages(4).defaultPage(4).showMinimap(false).enableSwipe(false).load();
		preview5.fromAsset(book_to_read).pages(5).defaultPage(5).showMinimap(false).enableSwipe(false).load();
		preview6.fromAsset(book_to_read).pages(6).defaultPage(6).showMinimap(false).enableSwipe(false).load();
		preview7.fromAsset(book_to_read).pages(7).defaultPage(7).showMinimap(false).enableSwipe(false).load();
		preview8.fromAsset(book_to_read).pages(8).defaultPage(8).showMinimap(false).enableSwipe(false).load();
		preview9.fromAsset(book_to_read).pages(9).defaultPage(9).showMinimap(false).enableSwipe(false).load();
		preview10.fromAsset(book_to_read).pages(10).defaultPage(10).showMinimap(false).enableSwipe(false).load();
		
		
		
		
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		
		
	}
	@Override
	public void loadComplete(int nbPages) {

		Toast.makeText(getApplicationContext(), "PDF LOADED !", Toast.LENGTH_SHORT).show();
		
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
