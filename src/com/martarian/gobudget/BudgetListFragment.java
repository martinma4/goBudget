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

public class BudgetListFragment extends ListFragment {
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
////		Set extra value for CategoryFragment
//		CategoryFragment fragment = new CategoryFragment();
//		Bundle bundle = new Bundle();
//		bundle.putInt(CategoryFragment.EXTRA_DATABASE_ID, position + 1);
//		fragment.setArguments(bundle);

//		Set extra value for pager activity and start intent
		Category c = ((CategoryAdapter)getListAdapter()).getItem(position);
		Intent i = new Intent(getActivity(), CategoryPagerActivity.class);
		i.putExtra(CategoryFragment.EXTRA_CATEGORY_ID, c.getId());
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
			getActivity().getLayoutInflater().inflate(R.layout.list_item_category, null);
			}
			
			Category c = getItem(position);
			
		    TextView titleTextView =
	        (TextView)convertView.findViewById(R.id.category_list_item_titleTextView);
	        titleTextView.setText(c.getTitle());
			
	        TextView statusTextView = (TextView)convertView.findViewById(R.id.budget_list_item_statusTextView);
	        statusTextView.setText("Wiz Set");
	        
			return convertView;
		}
	}
}