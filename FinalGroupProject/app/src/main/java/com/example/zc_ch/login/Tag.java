package com.example.zc_ch.login;

import java.util.HashMap;

/**
 * Created by lenovo on 2017/3/4.
 */

public class Tag {
    public String email;
    public HashMap<String,Double> tag_map =new HashMap<>();

    public Tag(){}

    public Tag(String email){
        super();
        this.email = email;
        this.tag_map.put("duck",4.0);
        this.tag_map.put("sausage",4.0);
        this.tag_map.put("rice",4.0);
        this.tag_map.put("egg",4.0);
        this.tag_map.put("chicken",4.0);
        this.tag_map.put("chili",4.0);
        this.tag_map.put("bread",4.0);
        this.tag_map.put("tomato",4.0);this.tag_map.put("curry",4.0);this.tag_map.put("beef",4.0);this.tag_map.put("pork",4.0);this.tag_map.put("bean sprouts",4.0);
        this.tag_map.put("noodle",4.0);this.tag_map.put("fish",4.0);this.tag_map.put("pasta",4.0);this.tag_map.put("cheese",4.0);this.tag_map.put("salad",4.0);this.tag_map.put("vegetable",4.0);
        this.tag_map.put("ice cream",4.0);this.tag_map.put("lettuce",4.0);this.tag_map.put("turnip",4.0);this.tag_map.put("fish ball",4.0);
        this.tag_map.put("corn",4.0);this.tag_map.put("potato",4.0);




    }


}
