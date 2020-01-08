package net.skhu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {
    public static final String tag="myTag";
    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "user_idx";
    private static final String TAG_NAME = "password";
    private static final String TAG_ADD = "nickname";

    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    ListView list;


    GetDataJSON task;
    TextView txtview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = new GetDataJSON();
        Log.d(tag,"실행");
        txtview = (TextView)findViewById(R.id.txtView);
        //list = (ListView) findViewById(R.id.listView);
        //personList = new ArrayList<HashMap<String, String>>();
        //getData("http://192.168.219.102/PHP_connection.php"); //수정 필요
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.btn_join) {
            Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_findPw) {
            Log.d(tag,"비번찾기 누름");
            Intent intent = new Intent(getApplicationContext(), FindPwActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_login) {
            Log.d(tag,"로그인 누름");
            EditText e = (EditText) findViewById(R.id.email);
            String email = e.getText().toString();
            EditText p = (EditText) findViewById(R.id.password);
            String password = p.getText().toString();
            Log.d(tag,"execute 실행전");
            task.execute(email, password);
            Log.d(tag,"execute 실행후");
            //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            //startActivity(intent);
        }
    }

    private class GetDataJSON extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.d(tag,"doInBackground 실행중");
            BufferedReader bufferedReader = null;
            try {
                String email = params[0];
                String password = params[1];
                Log.d(tag,"doInBackground 실행중2");
                String uri = "http://192.168.219.102/PHP_connection.php?email=" + email + "&password=" + password;
                URL url = new URL(uri);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(uri));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();

                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //txtview.setText("Login Successful");
            Log.d("tag","result:"+result);
            if(result=="1") {
                Log.d("tag","성공!");
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        }

    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String name = c.getString(TAG_NAME);
                String address = c.getString(TAG_ADD);

                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_ID, id);
                persons.put(TAG_NAME, name);
                persons.put(TAG_ADD, address);

                personList.add(persons);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.list_item,
                    new String[]{TAG_ID, TAG_NAME, TAG_ADD},
                    new int[]{R.id.id, R.id.name, R.id.address}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}



