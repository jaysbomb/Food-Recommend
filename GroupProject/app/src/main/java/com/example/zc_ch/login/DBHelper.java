package com.example.zc_ch.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by zc_ch on 3/1/2017.
 */


public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "test1.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_USER + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_HEIGHT + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT + " TEXT);";

    private static final String SQL_CREATE_ENTRIES_FOOD =
            "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME_FOOD + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_FOOD_NAME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_FOOD_CONSISTENT+ " TEXT,"+
                    FeedReaderContract.FeedEntry.COLUMN_NAME_FOOD_ADDRESS + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_FOOD_PRICE+ " TEXT,"+
                    FeedReaderContract.FeedEntry.COLUMN_NAME_FOOD_DRAWABLE+ " REAL,"+
                    FeedReaderContract.FeedEntry.COLUMN_NAME_FOOD_COOK+ " REAL);";

    private static final String SQL_CREATE_ENTRIES_HISTORY =
            "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME_HISTORY + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_HISTORY_FOOD_NAME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_HISTORY_FOOD_LOCATION + " TEXT);";

    private static final String SQL_CREATE_ENTRIES_TAG=
            "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME_TAG + " ("+
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TAG_USER + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TAG_KEY + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TAG_VALUE + " REAL" +
                    ");";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_FOOD =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME_FOOD;

    private static final String SQL_DELETE_ENTRIES_HISTORY =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME_HISTORY;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(SQL_CREATE_ENTRIES);
            Log.i("test", "create Database------------->"+SQL_CREATE_ENTRIES);
            db.execSQL(SQL_CREATE_ENTRIES_FOOD);
            Log.d("test","create Database------------->"+SQL_CREATE_ENTRIES_FOOD);
            db.execSQL(SQL_CREATE_ENTRIES_HISTORY);
            Log.d("test","create Database------------->"+SQL_CREATE_ENTRIES_HISTORY);
            db.execSQL(SQL_CREATE_ENTRIES_TAG);
            Log.d("test","create Database------------->"+SQL_CREATE_ENTRIES_TAG);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
//        db.execSQL(SQL_CREATE_ENTRIES);
//        db.execSQL(SQL_DELETE_ENTRIES_FOOD);
//        db.execSQL(SQL_DELETE_ENTRIES_HISTORY);
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_FOOD);
        db.execSQL(SQL_CREATE_ENTRIES_HISTORY);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public boolean user_register(String user, String password, String height, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("password", password);
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean exist_user (String user) {
        Log.d("test","exist_user");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from account where user=?",  new String[] {user,});
        if (res.getCount()!=0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean check_user(String email,String password)
    {
        Log.d("test","exist_user");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from account where user=? and password=?",  new String[] {email,password});
        if (res.getCount()!=0) {
            return true;
        } else {
            return false;
        }
    }

//    public ArrayList<ArrayList<String>> GetAllFood()  {
//        ArrayList<String> array_list = new ArrayList<String>();
//        ArrayList<ArrayList<String>> final_arrayList =new ArrayList<ArrayList<String>>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from food order by food_name ", null );
//        res.moveToFirst();
//        String name=res.getString(1);
//        Log.d("food_name",String.valueOf(res.getColumnIndex("food_name")));
//        Log.d("name",name);
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex("food_name")));
////            Log.d("name",array_list.toString());
//            array_list.add(res.getString(res.getColumnIndex("food_location")));
////            Log.d("name",array_list.toString());
//            array_list.add(res.getString(res.getColumnIndex("food_tag")));
////            Log.d("name",array_list.toString());
//            array_list.add(res.getString(res.getColumnIndex("food_price")));
////            Log.d("name",array_list.toString());
//            array_list.add(res.getString(res.getColumnIndex("food_description")));
////            Log.d("name",array_list.toString());
//            final_arrayList.add(array_list);
//
//            array_list.clear();
//            res.moveToNext();
//        }
//        return final_arrayList;
//    }




//    public ArrayList<String> GetTag( String user) {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor res = db.rawQuery("select * from tag where user=?", new String[]{user,});
//        res.moveToFirst();
//        array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_TAG_USER)));
//        array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_TAG_MEAT)));
//        array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_TAG_VEGETABLES)));
//        array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_TAG_FRUIT)));
//        Log.d("gettag",array_list.toString());
//        return array_list;
//    }

//    public void SaveTag(String user){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues newValues = new ContentValues();
//        newValues.put("user", user);
//        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_TAG, null, newValues);
//    }

//    public void UpdateTag(String user, int meat, int vegetables,int fruit){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues newValues = new ContentValues();
//        newValues.put("meat", meat);
//        newValues.put("vegetables",vegetables);
//        newValues.put("fruit",fruit);
//
//
//        String[] args = new String[]{user,};
//        db.update(FeedReaderContract.FeedEntry.TABLE_NAME_TAG, newValues, "user=?", args);
//    }

//    public void SaveFood(String name,String location,String price,String tag,String description){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("food_name", name);
//        contentValues.put("food_location", location);
//        contentValues.put("food_tag", tag);
//        contentValues.put("food_price", price);
//        contentValues.put("food_description", description);
//        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_FOOD, null, contentValues);
//
//    }
}