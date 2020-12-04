package com.youthfireit.dailydeals.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UserSQLiteHelper extends SQLiteOpenHelper
{


    private static final String DATABASE_NAME = "arabian_pure.db";
    private static final String TABLE_NAME = "user_info";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String ADDRESS = "address";
    private static final String DELIVERY_PHONE = "delivery_phone";
    private static final String DELIVERY_ADDRESS = "delivery_address";
    private static final String CITY_ID = "city_id";
    private static final String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_NAME+"("+NAME+" VARCHAR(255),"+EMAIL+" VARCHAR(255),"+PHONE_NUMBER+" VARCHAR(11),"+ADDRESS+" VARCHAR(255),"+DELIVERY_PHONE+" VARCHAR(255),"+DELIVERY_ADDRESS+" VARCHAR(255),"+CITY_ID+" VARCHAR(255));";

    private static final int VERSION_NUMBER = 1;
    private Context context;
    public UserSQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            db.execSQL(CREATE_USER_TABLE);
            Toast.makeText(context, "onCreate", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
