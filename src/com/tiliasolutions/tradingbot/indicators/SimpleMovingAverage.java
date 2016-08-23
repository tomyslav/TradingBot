package com.tiliasolutions.tradingbot.indicators;

import java.util.ArrayList;

import com.tiliasolutions.tradingbot.objects.CandleStick;

public class SimpleMovingAverage {

	ArrayList<CandleStick> candles;
	int numberOfCandles;
	double sma;


	public SimpleMovingAverage(ArrayList<CandleStick> candles){
		this.candles = candles;
		this.numberOfCandles = candles.size();
	}
	
	
	
	public double calculateSMA(){
		double totalSumOfPrices = 0.0;
		for(CandleStick c : candles){
			totalSumOfPrices +=c.getOpenMid();
		}
		return totalSumOfPrices/numberOfCandles;
	}
	

	
	public double getSma() {
		return calculateSMA();
	}



}
