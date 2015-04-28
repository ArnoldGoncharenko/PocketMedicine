package com.example.arnold.pocketmedicine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Arnold on 4/23/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper
{
    public static final String TABLE_MEDICINE = "medicine";
    public static final String COLUMN_ID = "_id";
    public static final String GENERIC_NAME = "GenericName";
    public static final String TRADE_NAME = "TradeName";
    public static final String CLASSIFICATION = "Classification";
    public static final String HIGH_RISK = "HighRisk";
    public static final String AUTHORIZED = "AuthorizedForCommunityAdmin";
    public static final String AUTH_FIRST_DOSE = "AuthorizedForFirstDose";
    public static final String RPNAPPROP = "RPNAppropriate";
    public static final String APPROVED_ROUTE = "ApprovedRoute";
    public static final String SPECIAL_INSTRUCTIONS = "SpecialInstructions";

    private static final String DATABASE_NAME = "medicine.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MEDICINE + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + GENERIC_NAME + " text not null, "
            + TRADE_NAME + " text not null, "
            + CLASSIFICATION + " text not null, "
            + HIGH_RISK + " text not null, "
            + AUTHORIZED + " text not null, "
            + AUTH_FIRST_DOSE + " text not null, "
            + RPNAPPROP + " text not null, "
            + APPROVED_ROUTE + " text not null, "
            + SPECIAL_INSTRUCTIONS + " text not null"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        onCreate(db);
    }
}