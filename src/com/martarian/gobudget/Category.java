package com.martarian.gobudget;

import java.util.Currency;
import java.util.UUID;

public class Category {
	private UUID mId;
	private String mTitle;
	private Currency mAmount;
	
	public Category() {
		mId = UUID.randomUUID();
	}
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	
	public UUID getId() {
		return mId;
	}

	public Currency getAmount() {
		return mAmount;
	}

	public void setAmount(Currency amount) {
		mAmount = amount;
	}
		
}
