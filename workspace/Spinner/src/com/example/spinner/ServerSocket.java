package com.example.spinner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class ServerSocket {


	@SuppressWarnings("deprecation")
	public static String POST(String[] input) {
		for(String s: input){
		Log.e("", ""+s);	
		}
		
		String response_body = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = null;
		try {
			switch (Integer.parseInt(input[0])) {
				/*LOGIN */
			case 0:			
				httpPost = new HttpPost(input[1]);
				MultipartEntity login = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);
				
				httpPost.setEntity(login);
				break;
			
				
				
				
				
				
			default:
				break;
			}
			HttpResponse response = httpclient.execute(httpPost);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = in.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			response_body = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response_body;
	}

}
