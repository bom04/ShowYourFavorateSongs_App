package net.skhu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class SongSearch  extends AsyncTask<String,String,String> {
    public static final String tag="myTag";
    public String[] searchingResult;
    public Context context;

    public SongSearch(Context context){
        this.context=context;
    }
    @Override
    protected String doInBackground(String... arg0) {
        try {
            String singer = (String) arg0[0];
            String link = "http://192.168.219.104:80/songList.php?singer=" + singer;
            Log.d("tag","가수:"+singer);
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }

            in.close();
            return sb.toString();
        } catch(Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Log.d("tag", "result:"+result);
        if(result.equalsIgnoreCase("song not found")) {
            Log.d("tag", "노래 검색 결과 없음");
        } else {
            Log.d("tag", "노래 있음!");
            searchingResult=result.split("/");
            for(int i=0;i<searchingResult.length;i++) {
                Log.d("tag", searchingResult[i]+"\n");
            }
        }
        Intent intent = new Intent(context, SearchingSongViewActivity.class);
                //,SearchingSongViewActivity.class);
        intent.putExtra("result",searchingResult); /*송신*/
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
