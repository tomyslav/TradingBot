package com.tiliasolutions.tradingbot.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;


import com.tiliasolutions.tradingbot.oandadata.Instruments;
import com.tiliasolutions.tradingbot.oandadata.Urls;

public class StreamData {
	
	//not available for v20 api
	
	
	
	
	HttpClient httpClient;
	
	String instrument;
	String accountId;
	String accessToken;
	
	public StreamData(String instrument, String accountId, String accessToken){
		this.instrument = instrument;
		this.accountId = accountId;
		this.accessToken = accessToken;
	}

	
	
	
	
	public static void main(String[] args){
		 
		 
		StreamData sd = new StreamData(Instruments.EUR_USD.toString(),
				"accountid",
				"access-token");
		sd.connect();
	}
	
	

	
	
	private void connect(){
		httpClient = HttpClientBuilder.create().build();

		HttpUriRequest httpGet = new HttpGet(Urls.STREAM_BASEURL + 
				"accountId=" + getAccountId() + 
				"&instruments=" + getInstrument());
		httpGet.setHeader(new BasicHeader("Authorization", "Bearer " + accessToken));
		 System.out.println("Executing request: " + httpGet.getRequestLine());
		 
		HttpResponse getResponse = null;
		try {
			getResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 HttpEntity entity = getResponse.getEntity();

		 

			System.out.println(getResponse);

		 
		//if (getResponse.getStatusLine().getStatusCode() == 200 && entity != null) {
			if (true) {
             InputStream stream = null;
             
			try {
				stream = entity.getContent();
	             BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

	             StringBuilder builder = new StringBuilder();
	             for (String line = null; (line = reader.readLine()) != null;) {
	                 builder.append(line).append("\n");
	             }
	       		System.out.println(builder);

			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


                 
         }
     }
         
	
	
	
	
	
	public String getAccessToken() {
		return accessToken;
	}

	public String getInstrument() {
		return instrument;
	}

	public String getAccountId() {
		return accountId;
	}
	
	

}
