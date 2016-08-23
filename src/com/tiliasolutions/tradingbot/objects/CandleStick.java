package com.tiliasolutions.tradingbot.objects;

public class CandleStick {
	String time;
	double openMid;
	double highMid;
	double lowMid;
	double closeMid;
	int volume;
	boolean complete;
	
	String toStr=null;
	
	

	public CandleStick(String time,	double openMid, double highMid,
				double lowMid, double closeMid,	int volume,	boolean complete){
		this.time		=time;
		this.openMid	=openMid;
		this.highMid	=highMid;
		this.lowMid		=lowMid;
		this.closeMid	=closeMid;
		this.volume		=volume;
		this.complete	=complete;
		toStr = "Candle :   " + time + "\n" +
				"openMid :  " + String.valueOf(openMid) + "\n" +
				"highMid :  " + String.valueOf(highMid) + "\n" +
				"lowMid :   " + String.valueOf(lowMid) + "\n" +
				"closeMid : " + String.valueOf(closeMid) + "\n" +
				"volume :   " + String.valueOf(volume) + "\n" +
				"complete : " + String.valueOf(complete) + "\n";
	}
	
	public String getTime() {
		return time;
	}

	
	public double getOpenMid() {
		return openMid;
	}


	public double getHighMid() {
		return highMid;
	}


	public double getLowMid() {
		return lowMid;
	}


	public double getCloseMid() {
		return closeMid;
	}


	public int getVolume() {
		return volume;
	}



	public boolean isComplete() {
		return complete;
	}


	@Override
	public String toString() {
		return toStr;
	}

}
