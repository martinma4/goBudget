package com.martarian.gobudget;

import java.text.NumberFormat;
import java.util.UUID;

import com.martarian.gobudget.Database.Budget;
import com.martarian.gobudget.Database.DatabaseHelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryFragment extends Fragment implements OnItemSelectedListener{
	
	public static final String EXTRA_CATEGORY_ID = "gobudget.CATEGORY_ID";
//	public static final String EXTRA_DATABASE_ID = "gobudget.DATABASE_ID";
	
	Category mCategory;
//	Create database helper
    DatabaseHelper mDBHelper;
	Budget mBudget;
    
//	Create array for categories
	String[] mStringCategories = {"Home","Auto", "Utilities", "Food", "Personal", "Activities"};
	
//	Create widgets Textviews, Buttons, EditText, Spinner 
	TextView mTitle;
	TextView mAmountLabel;
	TextView mBudgetDay;
	TextView mBudgetMonth;
	TextView mBudgetYear;
	Button mButtonDay;
	Button mButtonMonth;
	Button mButtonYear;
	EditText mAmount;
	Spinner mTimeSpinner;
	int databaseId;
//	Create mSelected for spinner selected, create mAmountText for amount
	String mSelected;
	String mAmountText;
	
//	Create amount categories and values
    String[] items = { "Day", "Month", "Year"};
    double mDayAmount;
    double mMonthAmount;
    double mYearAmount;
    
	public static CategoryFragment newInstance(UUID CategoryId){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CATEGORY_ID, CategoryId);
		CategoryFragment fragment = new CategoryFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	Bundle bundle = this.getArguments();
//	int databaseId = bundle.getInt(EXTRA_DATABASE_ID);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID categoryId = (UUID)getArguments().getSerializable(EXTRA_CATEGORY_ID);
		mCategory = CategoryLab.get(getActivity()).getCategory(categoryId);
//		final int databaseId = (int) getArguments().getInt(EXTRA_DATABASE_ID);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstaceState) {
		View v = inflater.inflate(R.layout.fragment_setbudget, parent, false);
		mTitle = (TextView)v.findViewById(R.id.set_budget_title);
		mTitle.setText(mCategory.getTitle());
		
		for(int i = 0; i < mStringCategories.length; i++) {
			if (mStringCategories[i] == mCategory.getTitle())
				{databaseId = i +1;
				break;}
		}
		
//		Retrieve database ID
//		final int databaseId = (int) getArguments().getInt(EXTRA_DATABASE_ID);
		//Get budgets		
		mBudgetDay = (TextView)v.findViewById(R.id.day_budget);
		mBudgetMonth = (TextView)v.findViewById(R.id.month_budget);
		mBudgetYear = (TextView)v.findViewById(R.id.year_budget);
		mButtonDay = (Button)v.findViewById(R.id.button_day);
		mButtonMonth = (Button)v.findViewById(R.id.button_month);
		mButtonYear = (Button)v.findViewById(R.id.button_year);
		
		mTimeSpinner = (Spinner)v.findViewById(R.id.time_type);
		
		mTimeSpinner.setOnItemSelectedListener(this);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		            android.R.layout.simple_spinner_item, items);
		
//		adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		mTimeSpinner.setAdapter(adapter);
		
		mAmount = (EditText)v.findViewById(R.id.amount_field);
//		mAmount.setText("0");
		mAmount.addTextChangedListener(new TextWatcher(){

			private String current="";

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
		        if(!s.toString().equals(current)){
		        	mAmount.removeTextChangedListener(this);

		            String cleanString = s.toString().replaceAll("[$,.]", "");

		            double parsed = Double.parseDouble(cleanString);
		            String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));

		            current = formatted;
		            mAmount.setText(formatted);
		            mAmount.setSelection(formatted.length());
		            mAmount.addTextChangedListener(this);
		            
		            mDayAmount = parsed/100;
		    		mBudgetDay.setText(String.valueOf(mDayAmount));
		    		mMonthAmount = parsed / 100 * 30;
		    		mBudgetMonth.setText(String.valueOf(mMonthAmount));
		    		mYearAmount = parsed / 100 * 365;
		    		mBudgetYear.setText(String.valueOf(mYearAmount));
		    		mDBHelper = new DatabaseHelper(getActivity());
		    		mDBHelper.updateBudget(databaseId, mMonthAmount, mCategory.getTitle());
		         }
				
			}
		
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
//				mBudget = mAmount.getText().toString();
				mAmountText = mAmount.getText().toString();
			}
			
		});
		
		mButtonDay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Set Day Budget!", Toast.LENGTH_SHORT).show();
				  			
				if(!(mAmountText == null)) {
	            String cleanString = mAmountText.toString().replaceAll("[$,.]", "");

	            double parsed = Double.parseDouble(cleanString);
	            String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));
	            
	            mDayAmount = parsed/100;
	    		mBudgetDay.setText(String.valueOf(mDayAmount));
	    		mMonthAmount = parsed / 100 * 30;
	    		mBudgetMonth.setText(String.valueOf(mMonthAmount));
	    		mYearAmount = parsed / 100 * 365;
	    		mBudgetYear.setText(String.valueOf(mYearAmount));
	    		mDBHelper = new DatabaseHelper(getActivity());
	    		mDBHelper.updateBudget(databaseId, mMonthAmount, mCategory.getTitle());
				         }
		}
					});
				
					
		
		mButtonMonth.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Toast.makeText(getActivity(), "Set Month Budget!", Toast.LENGTH_SHORT).show();
					if(!(mAmountText == null)) {
				            String cleanString = mAmountText.toString().replaceAll("[$,.]", "");

				            double parsed = Double.parseDouble(cleanString);
				            String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));
				            
				            mMonthAmount = parsed/100;
				    		mBudgetMonth.setText(String.valueOf(mMonthAmount));
				    		mDayAmount = parsed / 100 / 30;
				    		mBudgetDay.setText(String.valueOf(mDayAmount));
				    		mYearAmount = parsed / 100 * 12;
				    		mBudgetYear.setText(String.valueOf(mYearAmount));
				    		mDBHelper = new DatabaseHelper(getActivity());
				    		mDBHelper.updateBudget(databaseId, mMonthAmount, mCategory.getTitle());
				    		
					}
				}	
			});

		
		mButtonYear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  Toast.makeText(getActivity(), "Set   Year Budget!", Toast.LENGTH_SHORT).show();
					if(!(mAmountText == null)) {
			            String cleanString = mAmountText.toString().replaceAll("[$,.]", "");

				            double parsed = Double.parseDouble(cleanString);
				            String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));
				            
				            mYearAmount = parsed/100;
				    		mBudgetYear.setText(String.valueOf(mYearAmount));
				    		mDayAmount = parsed / 100 / 365;
				    		mBudgetDay.setText(String.valueOf(mDayAmount));
				    		mMonthAmount = parsed / 100 / 12;
				    		mBudgetMonth.setText(String.valueOf(mMonthAmount));
				    		mDBHelper = new DatabaseHelper(getActivity());
				    		mDBHelper.updateBudget(databaseId, mMonthAmount, mCategory.getTitle());
				    		
				         }
					}
				});

		return v;
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		mTimeSpinner.setSelection(position);
		  mSelected = (String) mTimeSpinner.getSelectedItem();
		  if (position == 1 ^ position == 2) {
		  Toast.makeText(this.getActivity(), "Dude! Virgin Only Button, sorry!", Toast.LENGTH_SHORT).show();}
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}

}






















