package com.example.david.thu10aprilandroidsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.david.thu10aprilandroidsqlite.ShoppingListDbContract.ShoppingItemEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ShoppingListDbHelper dbHelper;
    List<ShoppingItem> shoppingList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ShoppingListDbHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();


       //ter addItemToDatabase(new ShoppingItem("ost", 6), db);

        readItemsFromDatabase(db);

        for (ShoppingItem item : shoppingList ) {
            Log.d("David", "onCreate: " + item.name);
        }
    }

    void addItemToDatabase(ShoppingItem item, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(ShoppingListDbContract.ShoppingItemEntry.COLUMN_ITEM_NAME, item.name);
        values.put(ShoppingListDbContract.ShoppingItemEntry.COLUMN_ITEM_COUNT, item.count);

        db.insert(ShoppingListDbContract.ShoppingItemEntry.TABLE_NAME, null, values);
    }


    void readItemsFromDatabase( SQLiteDatabase db) {
        String[] itemColumns = {
                ShoppingItemEntry.COLUMN_ITEM_NAME,
                ShoppingItemEntry.COLUMN_ITEM_COUNT
        };

        Cursor cursor = db.query(ShoppingItemEntry.TABLE_NAME, itemColumns, null, null, null, null, ShoppingItemEntry.COLUMN_ITEM_NAME + " DESC");

        int itemNamePos = cursor.getColumnIndex(ShoppingItemEntry.COLUMN_ITEM_NAME);
        int itemCountPos = cursor.getColumnIndex(ShoppingItemEntry.COLUMN_ITEM_COUNT);

        shoppingList.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(itemNamePos);
            int count = cursor.getInt(itemCountPos);

            ShoppingItem item =  new ShoppingItem(name, count);
            shoppingList.add(item);
        }
        cursor.close();

    }


    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
