package com.example.zc_ch.login;

import android.provider.BaseColumns;

/**
 * Created by zc_ch on 3/1/2017.
 */

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String  COLUMN_NAME_HEIGHT="height";
        public static final String  COLUMN_NAME_WEIGHT="weight";


        public static final String TABLE_NAME_FOOD="food";
        public static final String COLUMN_NAME_FOOD_NAME="food_name";
        public static final String COLUMN_NAME_FOOD_ADDRESS="food_address";
        public static final String COLUMN_NAME_FOOD_CONSISTENT="food_consistent";
        public static final String COLUMN_NAME_FOOD_PRICE="food_price";
        //        public static final String COLUMN_NAME_FOOD_DESCRIPTION="food_description";
        public static final String COLUMN_NAME_FOOD_DRAWABLE="food_drawable";
        public static final String COLUMN_NAME_FOOD_COOK="food_cook";


        public static final String TABLE_NAME_HISTORY="history";
        public static final String COLUMN_NAME_HISTORY_FOOD_NAME="history_name";
        public static final String COLUMN_NAME_HISTORY_FOOD_LOCATION="history_location";

        public static final String TABLE_NAME_TAG="tag";
        public static final String COLUMN_NAME_TAG_USER="user";
        public static final String COLUMN_NAME_TAG_KEY="key";
        public static final String COLUMN_NAME_TAG_VALUE="value";
//        public static final String COLUMN_NAME_TAG_MEAT="meat";
//        public static final String COLUMN_NAME_TAG_VEGETABLES="vegetables";
//        public static final String COLUMN_NAME_TAG_FRUIT="fruit";
    }


}
