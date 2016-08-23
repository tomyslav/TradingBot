package com.tiliasolutions.tradingbot.oandadata;


public enum CandleStickGranularity {

	S5(5),	//	5s
	S10(10),	//	10s
	S15(15),	//	15s
	S30(30),	//	30s
	M1(60	*	1),	//	1min
	M2(60	*	2),	//	2mins
	M3(60	*	3),	//	3mins
	M5(60	*	5),	//	5mins
	M10(60	*	10),	//	10mins
	M15(60	*	15),	//	15mins
	M30(60	*	30),	//	30mins
	H1(60	*	60),	//	1hr
	H2(60	*	60	*	2),	//	2hr
	H3(60	*	60	*	3),	//	3hr
	H4(60	*	60	*	4),	//	4hr
	H6(60	*	60	*	6),	//	6hr
	H8(60	*	60	*	8),	//	8hr
	H12(60	*	60	*	12),	//	12hr
	D(60	*	60	*	24),	//	1day
	W(60	*	60	*	24	*	7),	//	1wk
	M(60	*	60	*	24	*	30);	//	1mth
	
	private final long granularityInSeconds;
	private CandleStickGranularity (long granularityInSeconds){
		this.granularityInSeconds = granularityInSeconds;
	}
	
	public long getGranularityInSeconds(){
		return granularityInSeconds;
	}
	
}
