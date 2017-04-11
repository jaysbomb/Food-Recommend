package com.example.zc_ch.login;

import java.util.Date;

/**
 * Created by zc_ch on 4/10/2017.
 */

public class History {

    public String email;
    public String name;
    public Date date;
    public Integer pic;
        public History() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

    public History(String email,String name,Date date,Integer pic){
        super();
        this.email=email;
        this.name = name;
        this.date=date;
        this.pic=pic;

    }
    public Date getDate() {
        return date;
    }

    public void setDrawable(Date date) {
        this.date = date;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }



}
