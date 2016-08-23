package com.tiliasolutions.tradingbot.getdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tiliasolutions.tradingbot.oandadata.CandleStickGranularity;
import com.tiliasolutions.tradingbot.oandadata.Instruments;
import com.tiliasolutions.tradingbot.oandadata.Urls;
import com.tiliasolutions.tradingbot.objects.CandleStick;

public class CandleSticksData {
	
	ArrayList<CandleStick> candleSticks = new ArrayList<CandleStick>();



	int count;
    String granularity;
    String instrument;
    String timeZone = "America%2FNew_York";
	

    public CandleSticksData(String instrument, String granularity, int count){
    	this.count = count;
    	this.instrument = instrument;
    	this.granularity = granularity;
    }
    
    //prepare REST url
	private String convertToUrl(){
		String data = ""; 
		data = String.format("%s%s?instrument=%s&count=%s&candleFormat=midpoint&granularity=%s&dailyAlignment=0&alignmentTimezone=%s", 
				Urls.BASEURL.toString(), 
				Urls.CANDLE_STICKS_URL.toString(),
				getInstrument(),
				String.valueOf(getCount()),
				getGranularity(),
				"America%2FNew_York"
				);
		return data;
	}
	
	//get JSON with all data
	private JSONArray getCandleSticks(String instrument, String granularity, int count){
		JSONArray result = null;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(this.convertToUrl());
        try {
            HttpResponse getResponse = client.execute(request);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            getResponse.getEntity().getContent(), "UTF-8"));
            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null;) {
                builder.append(line).append("\n");
            }
            result = new JSONObject(builder.toString()).getJSONArray("candles");
        } catch (Exception e) {
            e.printStackTrace();
}
		
		return result;		
	} 

	//populate Arraylist with data
	private void saveCandleSticksToArray(JSONArray values){
		for (int i = 0; i < values.length(); i++) {
		    
		    JSONObject candle = values.getJSONObject(i); 
		    candleSticks.add(new CandleStick(
		    		candle.getString("time"),
		    		candle.getDouble("openMid"),
		    		candle.getDouble("highMid"),
		    		candle.getDouble("lowMid"),
		    		candle.getDouble("closeMid"),
		    		candle.getInt("volume"),
		    		candle.getBoolean("complete")));
		  }
		
	}
	
	//save data from ArrayList to disk
	private void saveToDisk(){
		String path = "/home/ubuntu/Documents/";
		File dir = new File(path + this.getGranularity());

	    // attempt to create the directory here
	    boolean successful = dir.mkdir();
	    if (successful || dir.exists())
	    {
	      // creating the directory succeeded (or directory exists)
			for (CandleStick c : candleSticks){
				PrintWriter writer;
				try {
					writer = new PrintWriter(path + 
							this.getGranularity() + 
							"/" + c.getTime(), "UTF-8");
					writer.println(c.toString());
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}	    
		}
	    else
	    {
	      // creating the directory failed
	      System.out.println("failed trying to create the directory");
	    }
	  
	}

	
	
    public String getGranularity() {
		return granularity;
	}

	public String getInstrument() {
		return instrument;
	}
	
	
	public static void main(String[] args){
		CandleSticksData csd = new CandleSticksData(Instruments.EUR_USD.toString(),
				CandleStickGranularity.S5.toString(),
				1000);
		csd.saveCandleSticksToArray(csd.getCandleSticks(csd.getInstrument(),
				csd.getGranularity(), csd.getCount()));
		
		csd.saveToDisk();
	}
	
	
	
	public int getCount(){
		return count;
		
	}
	
}

