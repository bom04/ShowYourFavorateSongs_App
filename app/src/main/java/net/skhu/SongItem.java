package net.skhu;

public class SongItem {
    String title;
    String singer;
    int num;

    public SongItem(String title,String singer,int num) {
        this.title=title;
        this.singer=singer;
        this.num=num;
    }
    public String getTitle() {
        return title;
    }
    public String getSinger() {
        return singer;
    }
    public int getNum() {
        return num;
    }

}