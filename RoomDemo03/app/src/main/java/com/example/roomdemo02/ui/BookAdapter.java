package com.example.roomdemo02.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.roomdemo02.R;
import com.example.roomdemo02.entity.Book;
import java.util.List;

public class BookAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
	private List<Book> books;

	public BookAdapter(Context context, List<Book> books) {
		inflater = LayoutInflater.from(context);
		this.books = books;
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		return books.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = inflater.inflate(R.layout.item, null);
		Book book = books.get(position);

		TextView tv_like = view.findViewById(R.id.tv_title);
		tv_like.setText("title:"+book.getTitle());
		
		TextView tv_style = view.findViewById(R.id.tv_name);
		tv_style.setText("author:"+book.getAuthor());

		TextView tv_id = view.findViewById(R.id.tv_id);
		tv_id.setText("id: " + book.getId());
		
		return view;
	}
}
