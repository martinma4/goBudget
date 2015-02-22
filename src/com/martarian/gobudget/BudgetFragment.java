package com.martarian.gobudget;

import java.util.UUID;

import com.martarian.gobudget.Database.Budget;
import com.martarian.gobudget.Database.DatabaseHelper;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class BudgetFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "criminalintent.CRIME_ID";

    private Button mTimeButton, mCategoryButton;
    private TextView mWelcomeTextView; 
    DatabaseHelper db;
    double mDefaultBudget = (double)0.00;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
//        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_budget, parent, false);
//      If table not exists, inflate table with all categories
        
        db = new DatabaseHelper(getActivity());
        if (!db.isTableExists("BudgetsPerMonth", false)) {
            Budget budget1 = new Budget (mDefaultBudget, "Home");
            Budget budget2 = new Budget (mDefaultBudget, "Auto");
            Budget budget3 = new Budget (mDefaultBudget, "Utilities");
            Budget budget4 = new Budget (mDefaultBudget, "Food");
            Budget budget5 = new Budget (mDefaultBudget, "Personal");
            Budget budget6 = new Budget (mDefaultBudget, "Activities");

        	db.createBudget(budget1);
	    	db.createBudget(budget2);
	    	db.createBudget(budget3);
	    	db.createBudget(budget4);
	    	db.createBudget(budget5);
	    	db.createBudget(budget6);
        }

        
        mTimeButton = (Button)v.findViewById(R.id.set_time);
        mTimeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), ReviewBudgetListActivity.class);
				v.getContext().startActivity(i);
			}
		});
        
        mCategoryButton = (Button)v.findViewById(R.id.set_category);
        mCategoryButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), BudgetListActivity.class);
				v.getContext().startActivity(i);
			}
		});
        return v; 
    }
}
