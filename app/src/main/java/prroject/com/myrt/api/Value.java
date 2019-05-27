package prroject.com.myrt.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import prroject.com.myrt.Result.ResultFeeds;
import prroject.com.myrt.Result.ResultUser;

public class Value {
    @SerializedName("value")
    String value;
    @SerializedName("message")
    String message;
    List<ResultUser> result;
    List<ResultFeeds> results;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<ResultUser> getResultUsers() {
        return result;
    }
    public List<ResultFeeds> getResultFeed(){ return  results;}
}
