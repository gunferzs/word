package ru.words.gunferzs.words.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GunFerzs on 04.09.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class WordDBHelper extends SQLiteOpenHelper {

    String[] strings = {"шашлык", "бегемот", "вакцина", "грейпфрут", "диспетчер", "жираф"};

    public WordDBHelper(Context context) {
        super(context, WordContract.DATABASE_NAME, null, WordContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WordContract.WordEntry.CREATE_TABLE);
        ContentValues cv = new ContentValues();
        for (String string : strings) {
            cv.put(WordContract.WordEntry.COLUMN_NAME_WORD,string);
            cv.put(WordContract.WordEntry.COLUMN_NAME_CATEGORY,string);
            db.insert(WordContract.WordEntry.TABLE_NAME, "null", cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(WordContract.WordEntry.DELETE_TABLE);
        onCreate(db);
    }
}
