package com.martarian.gobudget.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

//	Logcat name
	private static final String LogcatName = "DatabaseHelper";
	
//	Database Version
	private static final int DATABASE_VERSION = 1;
	
//	Database Name, tables and column names
	private static final String DATABASE_NAME = "BudgetsPerMonth";
	private static final String TABLE_BUDGET = "budgets";
	private static final String KEY_ID = "id";
	private static final String KEY_BUDGET = "budget";
	private static final String KEY_CATEGORY = "category";
	
//	Create table budgets statement
	private static final String CREATE_TABLE_BUDGETS = "CREATE TABLE "
			+ TABLE_BUDGET + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BUDGET
			+ " REAL," + KEY_CATEGORY + " TEXT" +")";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_BUDGETS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables and Create new table
		db.execSQL("drop table if exists " + TABLE_BUDGET);
		onCreate(db);
	}
	
	public boolean isTableExists(String tableName, boolean openDb) {
    	SQLiteDatabase db = this.getWritableDatabase();
		if(openDb) {
	        if(db == null || !db.isOpen()) {
	        	db = getReadableDatabase();
	        }

	        if(!db.isReadOnly()) {
	        	db.close();
	        	db = getReadableDatabase();
	        }
	    }

	    Cursor cursor = db.rawQuery("select * from "+TABLE_BUDGET, null);
	    if(cursor!=null) {
	        if(cursor.getCount()>0) {
	                            cursor.close();
	            return true;
	        }
	                    cursor.close();
	    }
	    return false;
	}
	
//	create a budget
	public boolean createBudget(Budget budget) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_BUDGET, budget.getBudget());
		values.put(KEY_CATEGORY, budget.getCategory());
		
		db.insert(TABLE_BUDGET, null, values);
		return true;
	}
	
//	Update a budget
	public boolean updateBudget(int id, double budget, String category) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_BUDGET, budget);
		values.put(KEY_CATEGORY, category);		
		
		db.update(TABLE_BUDGET, values, "id=" + id, null);
		return true;
	}
	
	public Cursor getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select " + KEY_BUDGET + " from " + TABLE_BUDGET + 
			"where id=" + id + "", null);
		return res;
	}
	
	public Cursor getBudgetData(String category) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select " + KEY_BUDGET + " from " + TABLE_BUDGET + "where category=" + category, null);
		return res;
	}
}















