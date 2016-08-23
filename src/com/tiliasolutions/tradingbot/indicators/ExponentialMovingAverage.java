package com.tiliasolutions.tradingbot.indicators;

import java.util.ArrayList;

import com.tiliasolutions.tradingbot.objects.CandleStick;

public class ExponentialMovingAverage {
	//https://www.oanda.com/forex-trading/learn/forex-indicators/exponential-moving-average
	ArrayList<CandleStick> candles;
	int numberOfCandles;
	double ema;
	double oldEma = 0.0;
	double smoothingFactor;
	
	public ExponentialMovingAverage(ArrayList<CandleStick> candles){
		this.candles = candles;
		this.numberOfCandles = candles.size();
		this.smoothingFactor = 2/(1 + numberOfCandles);
		if (this.oldEma == 0.0){
			this.oldEma = new SimpleMovingAverage(candles).calculateSMA();
		}
	}
	
	

	public double calculateEMA(){
		//New EMA value = SF X New Price + (1- SF) X Previous EMA value.
	ema = getSmoothingFactor() * candles.get(candles.size()-1).getCloseMid() + 
				(1-getSmoothingFactor()) * getOldEma();
		return ema;
	}
	
	
	
	public int getNumberOfCandles() {
		return numberOfCandles;
	}



	public double getEma() {
		return calculateEMA();
	}



	public double getOldEma() {
		return oldEma;
	}



	public double getSmoothingFactor() {
		return smoothingFactor;
	}


	
	
	
}
