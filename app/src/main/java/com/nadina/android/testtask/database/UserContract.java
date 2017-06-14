package com.nadina.android.testtask.database;

import android.provider.BaseColumns;

/**
 * Created by Nadina on 27.05.2017.
 */

public class UserContract {

    public static final class UserEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "users_table";

        public static final String USERNAME_COLUMN = "username";
        public static final String EMAIL_COLUMN = "email";
        public static final String LAST_LOGIN_COLUMN = "lastlogin";
        public static final String STATUS_COLUMN = "status";
        public static final String FRIENDS_COLUMN = "friends";
        public static final String HANDS_PLAYED_COLUMN = "handsPlayed";
        public static final String HANDS_WON_COLUMN = "handsWon";
        public static final String BIGGEST_POT_WON_COLUMN = "biggest_pot_won";
        public static final String SITN_GO_WINS_COLUMN = "sitNGoWins";
        public static final String SITN_GO_LOSES_COLUMN = "sitNGoLoses";
        public static final String COINS_COLUMN = "coins";
        public static final String LOCATION_COLUMN = "location";
        public static final String BEST_HAND_COLUMN = "best_hand";
        public static final String PHOTO_COLUMN = "photo";


    }

}
