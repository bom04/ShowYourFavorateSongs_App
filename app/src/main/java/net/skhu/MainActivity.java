package net.skhu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    public static final String tag="myTag";
    public String email;
    public String password;

    public MainActivity() {
    }

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
            email = idText.getText().toString();
            password = passwordText.getText().toString();
            Log.d("tag","email:"+email+"/ password:"+password);
            new SigninActivity().execute(email,password);
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





