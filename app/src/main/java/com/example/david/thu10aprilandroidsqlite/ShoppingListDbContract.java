package com.example.david.thu10aprilandroidsqlite;

import android.provider.BaseColumns;

/**
 * Created by david on 2018-04-10.
 */

public class ShoppingListDbContract {
    private ShoppingListDbContract() {}

    public static class ShoppingItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "shopping_item";
        public static final String COLUMN_ITEM_NAME = "item_name";
        public static final String COLUMN_ITEM_COUNT = "item_count";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_ITEM_NAME + " TEXT UNIQUE NOT NULL, " +
                        COLUMN_ITEM_COUNT + " INTEGER NOT NULL)";

    }






}
