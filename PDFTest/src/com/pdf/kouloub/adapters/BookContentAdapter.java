package com.pdf.kouloub.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pdftest.R;
import com.pdf.kouloub.externals.BookPart;

public class BookContentAdapter extends ArrayAdapter<BookPart> {

	Context mContext;
	int layoutResourceId;
	ArrayList<BookPart> data = null;
	LayoutInflater inflater;
	
	public BookContentAdapter(Context mContext, int layoutResourceId, ArrayList<BookPart> data) {

		super(mContext, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
		inflater = ((Activity) mContext).getLayoutInflater();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(layoutResourceId, parent, false);
			
			// get the elements in the layout
			holder.txv_title = (TextView) convertView.findViewById(R.id.txv_babTitle); 
			holder.img_title = (ImageView) convertView.findViewById(R.id.Img_babTitle);
			holder.txv_pageNb = (TextView) convertView.findViewById(R.id.txv_pageNb); 
			
			holder.img_title.setVisibility(View.GONE);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}

		/*
		 * Set the data for the list item. You can also set tags here if you
		 * want.
		 */
		BookPart part = data.get(position);

		holder.txv_title.setText(part.getTitle());
		holder.txv_pageNb.setText(""+part.getPageNb());

		return convertView;
	}

	class ViewHolder
	{
		ImageView img_title;
		TextView txv_title; 
		TextView txv_pageNb;
	}

}
