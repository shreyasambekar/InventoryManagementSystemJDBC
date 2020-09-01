package com.psl.training.model;

public enum Unit {
	KGS(50),
	LBS(150),
	GALLONS(100),
	NUMBER(200),
	GRAMS(2500);
	
	public int min;
	
	Unit(final int min) {
		this.min = min;
	}

	public int getMinQuantity() {
		return this.min;
	}
}
