package com.tiliasolutions.tradingbot.oandadata;

public enum Instruments {

	
	EUR_USD("EUR_USD"),
	GBP_USD("GPB_USD");
	
	private String instrument;
	
	private Instruments (String instrument){
		this.instrument = instrument;
	}
	
	@Override
	public String toString() {
		return instrument;
	}
	
}
