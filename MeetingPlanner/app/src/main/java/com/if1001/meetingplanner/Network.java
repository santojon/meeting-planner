package com.if1001.meetingplanner;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;

/**
 * Created by Thiago on 6/12/2015.
 */
public class Network extends AsyncTask<URL,Integer,String> {

    public String result;

    protected void onPreExecute(){
        this.result = null;
    }

    protected String doInBackground(URL... urls) {
        OkHttpClient client = new OkHttpClient();
        Request request;
        try{
//            TextView t=(TextView)findViewById(R.id.textview);
            Response response;
            request = new Request.Builder().url(urls[0]).build();
            Log.v("HI", "pegou!");
            response = client.newCall(request).execute();
            this.result = response.body().string();
//                    t.setText(response.body().string());
        } catch(Exception e ){
            Log.v("HI", e.toString());
        }

//        int count = urls.length;
//        long totalSize = 0;
//        for (int i = 0; i < count; i++) {
//            totalSize += Downloader.downloadFile(urls[i]);
//            publishProgress((int) ((i / (float) count) * 100));
//            // Escape early if cancel() is called
//            if (isCancelled()) break;
//        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Long result) {
    }
}
