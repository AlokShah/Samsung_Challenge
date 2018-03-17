package com.example.alokshah.samsung_movieapp;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by alokshah on 3/17/18.
 */

public class GetDataTask extends AsyncTask<String, Void, String> {

    public DataInterface delegate = null;
    @Override
    protected String doInBackground(String... urls) {

        return GET(urls[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {


            HttpClient httpclient = new DefaultHttpClient();


            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));


            inputStream = httpResponse.getEntity().getContent();


            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Error";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bf.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}