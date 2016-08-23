package com.tiliasolutions.tradingbot.oandadata;

public enum Urls {
	
	BASEURL("https://api-fxpractice.oanda.com"),
	CANDLE_STICKS_URL("/v1/candles"),	
	STREAM_BASEURL("https://stream-fxpractice.oanda.com/v3/prices?"),
	ACCOUNTS_BASEURL("https://api-fxpractice.oanda.com/v3/accounts");
	
	
	private String url;
	private Urls(String url){
		this.url = url;
	}
	
	@Override
	public String toString() {
		return url;
	}

}
