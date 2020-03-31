package net.skhu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchingSongViewAdapter extends RecyclerView.Adapter<SearchingSongViewAdapter.ViewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<SongItem> arrayList;

    class ViewHolder extends RecyclerView.ViewHolder  { // 결국 이렇게 viewholder만든건 코드 중복 없이 한번만 멤버변수 만들어서 쓰려고
        TextView title, singer,num;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            singer = view.findViewById(R.id.singer);
            num = view.findViewById(R.id.num);
        }

        public void setData() {
            SongItem item = arrayList.get(getAdapterPosition()); // 몇번째 위치인지 반환
            title.setText(item.getTitle());
            singer.setText(item.getSinger());
            num.setText(item.getNum());
        }
    }

    public SearchingSongViewAdapter(Context context, ArrayList<SongItem> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.songitem, viewGroup, false); // view를 자세히 보려면 item2.xml파일을 보면 됨->view가 root인 linearLayout객체를 받음
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {
        viewHolder.setData();
    }




}
