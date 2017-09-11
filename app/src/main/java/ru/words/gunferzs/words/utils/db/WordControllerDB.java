package ru.words.gunferzs.words.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by GunFerzs on 04.09.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class WordControllerDB {
    WordDBHelper wordDBHelper;

    public WordControllerDB(@NonNull Context context) {
        wordDBHelper = new WordDBHelper(context);
    }

    public long insert(String word, String category){
        SQLiteDatabase db = wordDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WordContract.WordEntry.COLUMN_NAME_WORD, word);
        values.put(WordContract.WordEntry.COLUMN_NAME_CATEGORY, category);
        return db.insert(WordContract.WordEntry.TABLE_NAME,
                "null",
                values);
    }

    public long insert(ContentValues contentValues){
        SQLiteDatabase db = wordDBHelper.getWritableDatabase();
        return db.insert(WordContract.WordEntry.TABLE_NAME,
                "null",
                contentValues);
    }

    public Cursor query(String[] projection, String selection,
                        String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = wordDBHelper.getReadableDatabase();
        return db.query(WordContract.WordEntry.TABLE_NAME,
                projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    public Cursor queryAll(){
        SQLiteDatabase db = wordDBHelper.getReadableDatabase();
        return db.query(WordContract.WordEntry.TABLE_NAME,
                new String[]{WordContract.WordEntry.COLUMN_NAME_WORD,
                        WordContract.WordEntry.COLUMN_NAME_CATEGORY}, null, null,
                null, null, WordContract.WordEntry.COLUMN_NAME_WORD+" ASC");
    }

    public ArrayList<String> getWords(){
        Cursor c = queryAll();
        ArrayList<String> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(c.getString(c.getColumnIndexOrThrow(WordContract.WordEntry.COLUMN_NAME_WORD)));

            } while (c.moveToNext());
        }
        return result;
    }

    public int delete(String selection, String[] selectionArgs){
        SQLiteDatabase db = wordDBHelper.getWritableDatabase();
        return db.delete(WordContract.WordEntry.TABLE_NAME, selection, selectionArgs);
    }

    public int update(ContentValues values, String selection,
                      String[] selectionArgs){
        SQLiteDatabase db = wordDBHelper.getWritableDatabase();
        return db.update(WordContract.WordEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
