package com.nadina.android.testtask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.nadina.android.testtask.model.UserModel;

/**
 * Created by Nadina on 27.05.2017.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_NOTE_TABLE = " CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry._ID + " INTEGER PRIMARY KEY," +
                UserContract.UserEntry.USERNAME_COLUMN + " TEXT NOT NULL, " +
                UserContract.UserEntry.EMAIL_COLUMN + " TEXT NOT NULL, " +
                UserContract.UserEntry.LAST_LOGIN_COLUMN + " TEXT, " +
                UserContract.UserEntry.STATUS_COLUMN + " TEXT NOT NULL, " +
                UserContract.UserEntry.FRIENDS_COLUMN + " TEXT NOT NULL, " +
                UserContract.UserEntry.HANDS_PLAYED_COLUMN + " INTEGER NOT NULL, " +
                UserContract.UserEntry.HANDS_WON_COLUMN + " INTEGER NOT NULL, " +
                UserContract.UserEntry.BIGGEST_POT_WON_COLUMN + " INTEGER NOT NULL, " +
                UserContract.UserEntry.SITN_GO_WINS_COLUMN + " INTEGER NOT NULL, " +
                UserContract.UserEntry.SITN_GO_LOSES_COLUMN + " INTEGER NOT NULL, " +
                UserContract.UserEntry.COINS_COLUMN + " INTEGER NOT NULL, " +
                UserContract.UserEntry.LOCATION_COLUMN + " TEXT NOT NULL, " +
                UserContract.UserEntry.BEST_HAND_COLUMN + " TEXT NOT NULL, " +
                UserContract.UserEntry.PHOTO_COLUMN + " TEXT NOT NULL "
                + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public static ContentValues toContentValues(@NonNull UserModel user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry._ID, user.getId());
        values.put(UserContract.UserEntry.USERNAME_COLUMN, user.getUsername());
        values.put(UserContract.UserEntry.EMAIL_COLUMN, user.getEmail());
        values.put(UserContract.UserEntry.LAST_LOGIN_COLUMN, user.getLastLogin());
        values.put(UserContract.UserEntry.STATUS_COLUMN,user.getStatus());
        values.put(UserContract.UserEntry.FRIENDS_COLUMN,user.getFriends());
        values.put(UserContract.UserEntry.HANDS_PLAYED_COLUMN,user.getHandsPlayed());
        values.put(UserContract.UserEntry.HANDS_WON_COLUMN,user.getHandsWon());
        values.put(UserContract.UserEntry.BIGGEST_POT_WON_COLUMN,user.getBiggestPotWon());
        values.put(UserContract.UserEntry.SITN_GO_WINS_COLUMN,user.getSitNGoWins());
        values.put(UserContract.UserEntry.SITN_GO_LOSES_COLUMN,user.getSitNGoLoses());
        values.put(UserContract.UserEntry.COINS_COLUMN,user.getCoins());
        values.put(UserContract.UserEntry.LOCATION_COLUMN,user.getLocation());
        values.put(UserContract.UserEntry.BEST_HAND_COLUMN,user.getBestHand());
        values.put(UserContract.UserEntry.PHOTO_COLUMN,user.getPhoto());
        return values;
    }

}
