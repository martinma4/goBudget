package com.martarian.gobudget;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ReviewCategoryPagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		final ArrayList<Category> categories = CategoryLab.get(this).getCategories();
		
		FragmentManager fm = getSupportFragmentManager();
		
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm){
			
			@Override
			public int getCount() {
				return categories.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				// TODO Auto-generated method stub
				UUID CategoryId = categories.get(pos).getId();
				return ReviewCategoryFragment.newInstance(CategoryId);
			}
			
		});
		
		UUID CagetoryId = (UUID)getIntent().getSerializableExtra(CategoryFragment.EXTRA_CATEGORY_ID);
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getId().equals(CagetoryId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}
	}
}
