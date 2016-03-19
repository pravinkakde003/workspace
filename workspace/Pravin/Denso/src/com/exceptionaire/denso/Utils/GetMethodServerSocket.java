package com.exceptionaire.denso.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Base64;
import android.util.Log;

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
		for (String s : input) {
			Log.e("", "" + s);
		}

		String response_body = null;
		HttpClient httpclient = new DefaultHttpClient();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response_body;
	}

}
