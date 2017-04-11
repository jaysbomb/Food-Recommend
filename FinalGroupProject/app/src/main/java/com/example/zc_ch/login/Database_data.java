package com.example.zc_ch.login;

/**
 * Created by zc_ch on 3/31/2017.
 */

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Database_data extends Application{
    private String b;
    private user current_user;
    private Tag current_tag;
    private List<Data> current_data=new ArrayList<Data>();
    private List<History> current_history= new ArrayList<History>();

    public String getB(){
        return this.b;
    }
    public void setB(String c){
        this.b= c;
    }

    public void setCurrent_tag(Tag current_tag){
        this.current_tag=current_tag;
    }
    public Tag getCurrent_tag(){return this.current_tag;}

    public void setCurrent_user(user currentUser){
        this.current_user=currentUser;
    }
    public user getCurrent_user(){return this.current_user;}

    public void setCurrent_data(Data currentdata){
        this.current_data.add(currentdata);
    }
    public List<Data> getCurrent_data(){
        return this.current_data;
    }
    public void setCurrent_history(History currenthistory){
        this.current_history.add(currenthistory);
    }

    public List<History> getCurrent_history(){
        return this.current_history;
    }
    @Override
    public void onCreate(){
        super.onCreate();
    }
}
