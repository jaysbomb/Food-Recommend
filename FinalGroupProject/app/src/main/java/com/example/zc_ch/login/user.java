package com.example.zc_ch.login;

/**
 * Created by zc_ch on 3/30/2017.
 */

public class user {

    public String email;
    public String username;
    public String height;
    public String weight;
    public Integer healths;

    public user() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public user(String email,String username,String height,String weight, Integer healths) {
        this.email=email;
        this.username=username;
        this.height=height;
        this.weight=weight;
        this.healths=healths;
    }

}
