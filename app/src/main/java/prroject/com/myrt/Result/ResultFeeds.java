package prroject.com.myrt.Result;



import java.sql.Date;

public class ResultFeeds {

    String caption;

    String id_user;

    String gambar;
    String id_feed;
    java.sql.Date Date;

    String kategoori;

    String upvote;

    String video;



    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
    public String getId_feed() {
        return id_feed;
    }

    public void setId_feed(String id_feed) {
        this.id_feed = id_feed;
    }
    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public String getKategoori() {
        return kategoori;
    }

    public void setKategoori(String kategoori) {
        this.kategoori = kategoori;
    }

    public String getUpvote() {
        return upvote;
    }

    public void setUpvote(String upvote) {
        this.upvote = upvote;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
