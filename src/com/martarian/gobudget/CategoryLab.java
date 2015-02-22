package com.martarian.gobudget;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CategoryLab {
	private ArrayList<Category> mCategories;

	private static  CategoryLab sCategoryLab;
	private Context mAppContext;
	
	private CategoryLab(Context appContext) {
		mAppContext = appContext;
		mCategories = new ArrayList<Category>();
		String[] CNameArray;
		CNameArray = new String[6];
		CNameArray[0] = "Home";
		CNameArray[1] = "Auto";
		CNameArray[2] = "Utilities";
		CNameArray[3] = "Food";
		CNameArray[4] = "Personal";
		CNameArray[5] = "Activities";
		
		for(int i=0; i < CNameArray.length; i++) {
			Category c = new Category();
			c.setTitle(CNameArray[i]);
		mCategories.add(c);
		}
	}
		public static CategoryLab get(Context c) {
			if (sCategoryLab == null) {
				sCategoryLab = new CategoryLab(c.getApplicationContext());
			}
			return sCategoryLab;
		}
		
		public Category getCategory(UUID id) {
			for (Category c : mCategories) {
				if (c.getId().equals(id))
					return c;
			}
			return null;
		}
		
		public ArrayList<Category> getCategories() {
			return mCategories;
		}
	}


