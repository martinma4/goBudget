package com.martarian.gobudget;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ReviewBudgetListFragment extends ListFragment {
	private ArrayList<Category> mCategories;
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.category_title);
		mCategories = CategoryLab.get(getActivity()).getCategories();
		CategoryAdapter adapter = new CategoryAdapter(mCategories);
		setListAdapter(adapter);
	}
	
	public void onListItemClick(ListView l,View v, int position, long id) {
		Category c = ((CategoryAdapter)getListAdapter()).getItem(position);
		Intent i = new Intent(getActivity(), ReviewCategoryPagerActivity.class);
		i.putExtra(ReviewCategoryFragment.REVIEW_EXTRA_CATEGORY_ID, c.getId());
		startActivityForResult(i,0);
	}

//	@Override
//	public void onActivityResult(int request)
	
	private class CategoryAdapter extends ArrayAdapter<Category> {
		public CategoryAdapter(ArrayList<Category> categories) {
			super(getActivity(), android.R.layout.simple_list_item_2, categories);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (null == convertView) {
				convertView = 
			getActivity().getLayoutInflater().inflate(R.layout.review_list_item_category, null);
			}
			
			Category c = getItem(position);
			
		    TextView titleTextView =
	        (TextView)convertView.findViewById(R.id.review_category_list_item_titleTextView);
	        titleTextView.setText(c.getTitle());
			
	        TextView statusTextView = (TextView)convertView.findViewById(R.id.review_budget_list_item_statusTextView);
	        statusTextView.setText("View Budget");
	        
			return convertView;
		}
	}
}