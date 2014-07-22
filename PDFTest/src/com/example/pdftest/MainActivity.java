package com.example.pdftest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.joanzapata.pdfview.PDFView;

public class MainActivity extends Activity {

	PDFView pdf ;
	public static final String SAMPLE_FILE = "sample.pdf";

    public static final String ABOUT_FILE = "about.pdf";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pdf = (PDFView) findViewById(R.id.pdfView);
		
		Button btn = (Button) findViewById(R.id.button1);
		
	//	pdf.fromAsset(SAMPLE_FILE).;
		
		pdf.fromAsset(SAMPLE_FILE)
	    .defaultPage(1)
	    .showMinimap(true)
	    .enableSwipe(true)
	    .load();
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pdf.jumpTo(4);
			}
		});
		
		
	}
	
}
