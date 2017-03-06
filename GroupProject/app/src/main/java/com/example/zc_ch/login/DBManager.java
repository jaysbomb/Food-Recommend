package com.example.zc_ch.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public void user_register(String user, String password, String height, String weight) {
//        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();  //开始事务
        try {
            Log.d("DBmanager","starting register user");
            db.execSQL("INSERT INTO account VALUES(null, ?, ?, ?, ?)", new Object[]{user, password, height, weight});
            db.setTransactionSuccessful();  //设置事务成功完成
            Log.d("DBmanager","ok");

        } finally {
            db.endTransaction();    //结束事务
        }
    }

    public void add(List<Data> datas) {
        db.beginTransaction();  //开始事务
        try {
            Log.d("DBmanager","insert the values into food");
            for (Data data : datas) {
                db.execSQL("INSERT INTO food VALUES(null, ?, ?, ?, ?, ?, ?)", new Object[]{data.name, data.consistent, data.address,data.price,data.drawable,data.cook});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
            Log.d("DBmanager","ok");

        } finally {
            db.endTransaction();    //结束事务
        }
    }

    public void addTag(List<Tag> tags){
        db.beginTransaction();  //开始事务
        try {
            Log.d("DBmanager","insert the values into tag");
            for (Tag tag : tags) {
                db.execSQL("INSERT INTO tag VALUES(null, ?, ?, ?)", new Object[]{tag.user, tag.key, tag.value});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
            Log.d("DBmanager","ok");
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    public ArrayList<Tag> getTag(String user){
        Log.d("DBmanager","start get tag");
        ArrayList<Tag> tags = new ArrayList<Tag>();
        Cursor c = db.rawQuery("SELECT * FROM tag where user=? ", new String[]{ user,});
        while (c.moveToNext()) {
            Tag tag = new Tag();
            tag.user=c.getString(c.getColumnIndex("user"));
            tag.value = c.getDouble(c.getColumnIndex("value"));
            tag.key = c.getString(c.getColumnIndex("key"));
            tags.add(tag);
        }
        c.close();
        Log.d("DBmanager","ok");
        return tags;
    }

    public void updateTag(Tag tag){
        ContentValues cv = new ContentValues();
        cv.put("value", tag.value);
        db.update("tag", cv, "user = ? and key= ?", new String[]{tag.user,tag.key});
    }

    public List<Data> query() {
        Log.d("DBmanager","start get data");
        ArrayList<Data> datas = new ArrayList<Data>();
        Cursor c = db.rawQuery("SELECT * FROM food ", null);
        c.moveToFirst();
        while (c.moveToNext()) {
            Data data = new Data();
            data.name = c.getString(c.getColumnIndex("food_name"));
            data.consistent = c.getString(c.getColumnIndex("food_consistent"));
            data.drawable=c.getInt(c.getColumnIndex("food_drawable"));
            data.address = c.getString(c.getColumnIndex("food_address"));
            data.price = c.getString(c.getColumnIndex("food_price"));
            data.cook =c.getInt(c.getColumnIndex("food_cook"));
            datas.add(data);
        }
        c.close();
        Log.d("DBmanager","ok");
        return datas;
    }


    public void closeDB() {
        db.close();
    }
}