package net.skhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FindPwActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("비밀번호 찾기");
        setContentView(R.layout.activity_find_pw);
    }
}
