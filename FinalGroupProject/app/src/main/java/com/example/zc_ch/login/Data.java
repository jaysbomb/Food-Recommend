package com.example.zc_ch.login;

/**
 * Created by lenovo on 2017/3/4.
 */

public class Data {
    public String name, consistent,address,price;
    public Integer drawable,cook;
    public Double score;
    public String [] targets;

    public Data(){}

    public Data(String name, String consistent, String address, String price, Integer drawable,Integer cook,double score){
        super();
        this.name = name;
        this.consistent = consistent;
        this.drawable = drawable;
        this.address= address;
        this.price=price;
        this.cook = cook;
        this.score=score;

    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getConsistent(){
        return consistent;
    }

    public void setConsistent(String consistent){
        this.consistent = consistent;
    }

    public String [] getTargets(){ targets=consistent.split(",");
        return targets;}
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }
    public Integer getDrawable() {
        return drawable;
    }

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }
    public Integer getCook() {
        return cook;
    }

    public void setCook(Integer cook) {
        this.cook = cook;
    }
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
