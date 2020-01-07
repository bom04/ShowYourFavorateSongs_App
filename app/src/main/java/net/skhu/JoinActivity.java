package net.skhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("회원가입");
        setContentView(R.layout.activity_join);
    }
}
