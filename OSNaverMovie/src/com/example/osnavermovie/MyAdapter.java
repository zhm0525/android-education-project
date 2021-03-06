package com.example.osnavermovie;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter {
	List<MovieItem> mItems = new ArrayList<MovieItem>();
	String mKeyword;
	int totalCount;
	public void setKeyword(String keyword) {
		mKeyword = keyword;
	}
	
	public String getKeyword() {
		return mKeyword;
	}
	
	public void setTotal(int total) {
		totalCount = total;
	}
	
	public int getStart() {
		if (mItems.size() < totalCount) {
			return mItems.size() + 1;
		}
		return -1;
	}
	public void addAll(List<MovieItem> items) {
		mItems.addAll(items);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MovieItemView view;
		if (convertView == null) {
			view = new MovieItemView(parent.getContext());
		} else {
			view = (MovieItemView)convertView;
		}
		view.setMovieItem(mItems.get(position));
		return view;
	}

	public void clear() {
		mItems.clear();
		notifyDataSetChanged();
	}

}
