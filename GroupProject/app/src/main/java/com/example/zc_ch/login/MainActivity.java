package com.example.zc_ch.login;

import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager mConnMgr;
    private TextView mTextView;
    EditText wInput ;
    TextView wOutput;
    String[] country;
    String  name,consistent,address,price;
    int position;
    HashMap<String,Double> tag = new HashMap<>();

    final List<Data> data= new ArrayList<Data>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tag.put("duck",4.0);
        tag.put("sausage",4.0);
        tag.put("rice",4.0);
        tag.put("egg",4.0);
        tag.put("chicken",4.0);
        tag.put("chili",4.0);
        tag.put("bread",4.0);
        tag.put("tomato",4.0);tag.put("curry",4.0);tag.put("beef",4.0);tag.put("pork",4.0);tag.put("bean sprouts",4.0);
        tag.put("noodle",4.0);tag.put("fish",4.0);tag.put("pasta",4.0);tag.put("cheese",4.0);tag.put("salad",4.0);tag.put("vegetable",4.0);
        tag.put("ice cream",4.0);tag.put("lettuce",4.0);tag.put("turnip",4.0);tag.put("fish ball",4.0);
        tag.put("corn",4.0);tag.put("potato",4.0);
        data.add(new Data("Sibao Rice","duck,sausage,rice,egg","CityU Ac1 Canteen","19",R.drawable.sibao,0));
        data.add(new Data("Steamed Chicken with Chili Sauce","chicken,chili","CityU Ac1 Canteen","21",R.drawable.koshui,1));
        data.add(new Data("scrambled eggs and pancake","egg,bread","CityU Ac3 Canteen","19",R.drawable.egg_pancak,4));
        data.add(new Data("Toast Made Dish","bread,egg,tomato","CityU Ac3 Canteen","19",R.drawable.tusi,2));
        data.add(new Data("Beef Curry","beef,curry,rice","CityU Ac3 Canteen","25",R.drawable.gali,3));
        data.add(new Data("Pasta","pasta,pork,tomato","CityU Ac3 Canteen","21",R.drawable.pasta,3));
        data.add(new Data("Pork Baked Rice","pork,rice,cheese,tomato","CityU Ac3 Canteen","23",R.drawable.jufan,0));
        data.add(new Data("sandwich","bread,tomato,salad,lettuce","CityU Ac3 Canteen","15",R.drawable.sandwich,0));
        data.add(new Data("Baked Potato","potato","CityU Ac3 Canteen","18",R.drawable.bake_potato,0));
        data.add(new Data("Pizza","sausage,bread,cheese,tomato","CityU Ac3 Canteen","48",R.drawable.pizza,7));
        data.add(new Data("Ice Cream Waffle","ice cream,bread","CityU Ac3 Canteen","21",R.drawable.ice_cream_waffle,4));
        data.add(new Data("Cart Noodle","noodle,fish ball,turnip","CityU Ac1 Canteen","19",R.drawable.cart_noodle,2));
        data.add(new Data("Shuang Song","rice,vegetable,pork","CityU Ac1 Canteen","25",R.drawable.suangsong,0));
        data.add(new Data("Teppanyaki","beef,rice,corn","CityU Ac1 Canteen","38",R.drawable.teppanyaki,7));
        data.add(new Data("Fried Chicken Curry","chicken,curry","CityU Ac1 Canteen","28",R.drawable.curry_chicken,6));
        data.add(new Data("Thai pork noodle","pork,noodle","CityU Ac2 Canteen","23",R.drawable.thai_pork_noodle,3));
        data.add(new Data("Hainanese Chicken Rice","chicken,rice","CityU Ac2 Canteen","25",R.drawable.hainanese_chicken_rice,3));
        data.add(new Data("Spicy hot pot","beef,bean sprouts,chili","CityU Ac2 Canteen","25",R.drawable.spicy_hot_pot,4));
        data.add(new Data("Chinese Food","rice,pork,fish,tomato","CityU Ac2 Canteen","25",R.drawable.chinese_food,0));


        initialScore(tag,data);
        Recommend(data);

    }
    public void Recommend(List<Data> mData) {
        Random r = new Random();
        Double t = r.nextDouble()*4+2;

        position = searchScore(t,mData);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView name =(TextView) findViewById(R.id.name);
        TextView price = (TextView) findViewById(R.id.price);
        TextView target = (TextView) findViewById(R.id.target);
        TextView address = (TextView) findViewById(R.id.address);



        imageView.setImageResource(mData.get(position).getDrawable());
        name.setText("Name: "+mData.get(position).getName());
        price.setText("Price: "+mData.get(position).getPrice()+"HKD");
        target.setText("Consituent: "+mData.get(position).getConsistent());
        address.setText("Address: "+mData.get(position).getAddress());

    }
    public void initialScore(HashMap<String,Double> map,List<Data> mData){
        for (int j= 0;j<mData.size();j++){
            Double score=0.0;
            String []targets=mData.get(j).getTargets();
            for (int i=0; i< targets.length;i++){
                score +=map.get(targets[i]);
            }
            score += mData.get(j).getCook();
            score= score/(targets.length+1);
            mData.get(j).setScore(score);

        }
    }
    public int searchScore(Double t,List<Data> mData){
        Double [] k = new Double[mData.size()];
        k[0]= abs(t - mData.get(0).getScore());
        int z=0;
        for (int i = 0;i<mData.size()-1;i++){
            k[i+1]=abs(t - mData.get(i+1).getScore());
            if(k[0]>k[i+1]){
                k[0]=k[i+1];
                z=i+1;
            }
        }return  z;

    }
    public void like(View view){
        String [] targets;
        Double tmp;
        targets=data.get(position).getTargets();
        for(int i = 0; i<targets.length;i++){
            tmp = tag.get(targets[i]);
            tmp+=1;
            tag.put(targets[i],tmp);
        }
        Toast.makeText(this,tag.get(targets[0]).toString(),Toast.LENGTH_LONG).show();
    }
    public void hate(View view){
        String [] targets;
        Double tmp;
        targets=data.get(position).getTargets();
        for(int i = 0; i<targets.length;i++){
            tmp = tag.get(targets[i]);
            tmp-=1;
            tag.put(targets[i],tmp);
        }
        Toast.makeText(this,tag.get(targets[0]).toString(),Toast.LENGTH_LONG).show();
    }
}

