package com.martarian.gobudget;

import android.support.v4.app.Fragment;

public class ReviewBudgetListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new ReviewBudgetListFragment();	
	}

}
