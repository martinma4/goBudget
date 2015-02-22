package com.martarian.gobudget;

import android.support.v4.app.Fragment;

public class BudgetListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new BudgetListFragment();	
	}

}
