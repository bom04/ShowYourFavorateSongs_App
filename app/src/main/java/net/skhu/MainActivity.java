package net.skhu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {
    public static final String tag="myTag";
//    HttpPost httppost;
//    StringBuffer buffer;
//    HttpResponse response;
//    HttpClient httpclient;
//    List<NameValuePair> nameValuePairs;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Log.d(tag,"실행");
//        //list = (ListView) findViewById(R.id.listView);
//        //personList = new ArrayList<HashMap<String, String>>();
//        //getData("http://192.168.219.102/PHP_connection.php"); //수정 필요
//    }
//
//    public void btnClick(View view) {
//        if (view.getId() == R.id.btn_join) {
//            Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
//            startActivity(intent);
//        } else if (view.getId() == R.id.btn_findPw) {
//            Log.d(tag,"비번찾기 누름");
//            Intent intent = new Intent(getApplicationContext(), FindPwActivity.class);
//            startActivity(intent);
//        } else if (view.getId() == R.id.btn_login) {
//            Log.d(tag,"로그인 누름");
////            EditText e = (EditText) findViewById(R.id.email);
////            String email = e.getText().toString();
////            EditText p = (EditText) findViewById(R.id.password);
////            String password = p.getText().toString();
//            Log.d(tag,"login 실행전");
//            login();
//            Log.d(tag,"login 실행후");
//            //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//            //startActivity(intent);
//        }
//    }
//
//   public void login() {
//       try {
//           httpclient = new DefaultHttpClient();
//           httppost = new HttpPost("http://192.168.219.102/PHP_connection.php");
//           nameValuePairs = new ArrayList<NameValuePair>(2);
//           EditText e = (EditText) findViewById(R.id.email);
//           EditText p = (EditText) findViewById(R.id.password);
//           nameValuePairs.add(new BasicNameValuePair("email",e.getText().toString()));
//           nameValuePairs.add(new BasicNameValuePair("password", p.getText().toString()));
//           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//           response = httpclient.execute(httppost);
//           ResponseHandler<String> responseHandler = new BasicResponseHandler();
//           final String response = httpclient.execute(httppost, responseHandler);
//           System.out.println("Response : " + response);
//
//
//           if (response.equalsIgnoreCase("User Found")) {
//               runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
//                       Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
//                   }
//               });
//                Log.d("tag","성공했습니다");
//               startActivity((new Intent(MainActivity.this, HomeActivity.class)));
//               finish();
//           } else {
//               Log.d("tag","실패했습니다");
//               Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
//           }
//       }
//       catch(Exception e)
//       {
//           //dialog.dismiss();
//           System.out.println("Exception : " + e.getMessage());
//       }
//   }
    public void btnClick(View view) {
        if (view.getId() == R.id.btn_join) {
            Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_findPw) {
            Log.d(tag, "비번찾기 누름");
            Intent intent = new Intent(getApplicationContext(), FindPwActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_login) {
            Log.d(tag, "로그인 누름");
            idText = (EditText) findViewById(R.id.email);
            passwordText = (EditText) findViewById(R.id.password);
            final String email = idText.getText().toString();
            final String password = passwordText.getText().toString();
            //4. 콜백 처리부분(volley 사용을 위한 ResponseListener 구현 부분)
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("tag","response 실행중");
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        Toast.makeText(getApplicationContext(), "success" + success, Toast.LENGTH_SHORT).show();

                        //서버에서 보내준 값이 true이면?
                        if (success) {

                            //Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                            //String email = jsonResponse.getString("email");
                            //String userPassword = jsonResponse.getString("password");

                            //로그인에 성공했으므로 MainActivity로 넘어감
                            Log.d("tag","로그인 성공");
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                            intent.putExtra("email", userID);
//                            intent.putExtra("password", userPassword);
                            startActivity(intent);

                        } else {//로그인 실패시
                            Log.d("tag", "로그인 실패");
//                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                                builder.setMessage("Login failed")
//                                        .setNegativeButton("retry", null)
//                                        .create()
//                                        .show();


                        }

                    } catch (JSONException e) {
                        Log.d("tag","response catch 오류");
                        e.printStackTrace();
                    }
                }
            };

            LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(loginRequest);
        }
    }
EditText idText;
    EditText passwordText;
    Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag","시작이야!~");
        //강의에서 final을 추가시켜


    }
}





