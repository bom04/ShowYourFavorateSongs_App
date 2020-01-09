package net.skhu;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
   final static private String url="http://10.0.2.2:8080/PHP_connection.php";
    public static final String tag="myTag";
   private Map<String,String> parameters;

   public LoginRequest(String email, String password, Response.Listener<String> listener) {
       super(Method.POST, url, listener, null);
       Log.d("tag","loginRequest 실행중");
       parameters = new HashMap<>();
       parameters.put("email", email);
       parameters.put("password", password);


   }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}
