package net.skhu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("회원가입");
        setContentView(R.layout.activity_join);
    }
}
