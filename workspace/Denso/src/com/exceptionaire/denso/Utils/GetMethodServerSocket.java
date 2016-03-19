package com.exceptionaire.denso.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.exceptionaire.denso.MyApplication;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class GetMethodServerSocket {


	private static GetMethodServerSocket instance;

	public static synchronized GetMethodServerSocket getInstance() {

		if (instance == null) {

			instance = new GetMethodServerSocket();
		}
		return instance;
	}

	private GetMethodServerSocket() {
		
	}

	public String GET(String[] input) {
		for (String s : input) {
			Log.e("", "" + s);
		}
		int timeoutConnection = 3000;
		int timeoutSocket = 5000;
		String response_body = null;

		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		HttpGet httpGet = null;
		try {
			switch (Integer.parseInt(input[0])) {
			/* GET ALL Categories */
			case 0:
				httpGet = new HttpGet(input[1]);
				break;
			default:
				break;
			}
			HttpResponse response = httpclient.execute(httpGet);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = in.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			response_body = sb.toString();
		} catch (SocketTimeoutException bug) {
			bug.printStackTrace();
			
			response_body="SocketTimeoutException";
		} catch (ConnectTimeoutException bug) {
			bug.printStackTrace();
			Log.e("ConnectTimeoutException","ConnectTimeoutException");	
			response_body="ConnectTimeoutException";
		} catch (Exception bug) {
			bug.printStackTrace();
		}
		return response_body;
	}

}
