package com.martarian.gobudget.Database;

public class Budget {
	int mId;
	double mBudget;
	String mCategory;
	
//	Create constructors
	public Budget() {
	}
	
	public Budget(double budget){
		this.mBudget = budget;
	}
	
	public Budget(double budget, String category) {
		this.mBudget = budget;
		this.mCategory = category;
	}
	
//	generate getters and setters
	public void setId(int id) {
		this.mId = id;
	}
	
	public void setBudget(int budget) {
		this.mBudget = budget;
	}
	
	public void setCategory(String category) {
		this.mCategory = category;
	}
	
	public int getId() {
		return this.mId;
	}
	
	public double getBudget() {
		return this.mBudget;
	}
	
	public String getCategory() {
		return this.mCategory;
	}
}
