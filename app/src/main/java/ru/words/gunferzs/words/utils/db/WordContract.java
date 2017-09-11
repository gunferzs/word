package ru.words.gunferzs.words.utils.db;

import android.provider.BaseColumns;

/**
 * Created by GunFerzs on 04.09.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class WordContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "word.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    public WordContract() {
    }

    public static abstract class WordEntry implements BaseColumns {
        public static final String TABLE_NAME = "wordTable";
        public static final String COLUMN_NAME_ENTRY_ID = "wordid";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_CATEGORY = "category";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_WORD + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_CATEGORY + TEXT_TYPE + " )";

        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
