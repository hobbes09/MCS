package com.mcs.http.client;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class GetRequestManager {
	
	private String base_url;
	private HashMap<String, String> params;
	public String uri;
	private String responseJson;
	
	public String getBase_url() {
		return base_url;
	}
	public void setBase_url(String base_url) {
		this.base_url = base_url;
	}
	public HashMap<String, String> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public boolean sendGetRequest(){
		return false;
	}
	
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	public boolean sendGetRequestFromUri(){

		String url = this.uri.toString();
		
		try {
			HttpClient httpclient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			
			//add headers here if any required
			HttpResponse response = httpclient.execute(request);
			
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			
			BufferedReader rd = new BufferedReader(
								new InputStreamReader(response.getEntity().getContent()));
			
			StringBuffer result = new StringBuffer();
			String line = "";
			while((line = rd.readLine()) != null){
				result.append(line);
			}
			System.out.println(result.toString());
			this.responseJson = result.toString();
			
			return true;
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	

}
