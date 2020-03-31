package net.skhu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchingSongViewActivity extends AppCompatActivity {
    SearchingSongViewAdapter recyclerView2Adapter;
    ArrayList<SongItem> arrayList;
    String[] songResult; // 검색한 결과 여기에 저장됨

    public SearchingSongViewActivity(String[] result) {
        songResult=new String[result.length];
        for(int i=0;i<result.length;i++) {
            songResult[i]=result[i];
            Log.d("tag","songResult:"+songResult[i]+"\n");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag","searchingsongviewactivity");
        setContentView(R.layout.activity_searching_song_view);
        arrayList = new ArrayList<SongItem>();

        Intent intent = getIntent(); /*데이터 수신*/
        String[] r= intent.getExtras().getStringArray("result"); /*배열*/
        songResult=new String[r.length];
        for(int i=0;i<r.length;i++) {
            songResult[i]=r[i];
            Log.d("tag","songResult:"+songResult[i]+"\n");
        }
        for(int i=0;i<songResult.length;i++) {
            String[] result=songResult[i].split("^");
            arrayList.add(new SongItem(result[0],result[1],Integer.parseInt(result[2])));

        }
        recyclerView2Adapter = new SearchingSongViewAdapter(this, arrayList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerView2Adapter);


    }
}
