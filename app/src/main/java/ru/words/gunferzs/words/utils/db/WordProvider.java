package ru.words.gunferzs.words.utils.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by GunFerzs on 04.09.2017.
 */

@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
public class WordProvider extends ContentProvider {

    static final String AUTHORITIES = "ru.words.gunferzs.words.providers.Words";

    static final String WORD_PATH = "words";

    public static final Uri WORD_CONTENT_URI = Uri.parse("content://"
            + AUTHORITIES + "/" + WORD_PATH);

    static final String WORD_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITIES + "/" + WORD_PATH;

    static final String WORD_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITIES + "/" + WORD_PATH;

    static final int URI_CONTACTS = 1;

    static final int URI_CONTACTS_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITIES, WORD_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITIES, WORD_PATH + "/#", URI_CONTACTS_ID);
    }

    WordControllerDB wordControllerDB;

    @Override
    public boolean onCreate() {
        wordControllerDB = new WordControllerDB(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch(uriMatcher.match(uri)){
            case URI_CONTACTS:
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = WordContract.WordEntry.COLUMN_NAME_WORD + " ASC";
                }
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = WordContract.WordEntry.COLUMN_NAME_ENTRY_ID + " = " + id;
                } else {
                    selection = selection + " AND " +
                            WordContract.WordEntry.COLUMN_NAME_ENTRY_ID + " = " + id;
                }
                break;
        }
        Cursor c = wordControllerDB.query(projection, selection, selectionArgs, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), WORD_CONTENT_URI);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(uriMatcher.match(uri)){
            case URI_CONTACTS:
                return WORD_CONTENT_TYPE;
            case URI_CONTACTS_ID:
                return WORD_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) != URI_CONTACTS)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        Uri resultUri = ContentUris.withAppendedId(WORD_CONTENT_URI,
                wordControllerDB.insert(values));
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = WordContract.WordEntry.COLUMN_NAME_ENTRY_ID + " = " + id;
                } else {
                    selection = selection + " AND " +
                            WordContract.WordEntry.COLUMN_NAME_ENTRY_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        int cnt = wordControllerDB.delete(selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = WordContract.WordEntry.COLUMN_NAME_ENTRY_ID + " = " + id;
                } else {
                    selection = selection + " AND " +
                            WordContract.WordEntry.COLUMN_NAME_ENTRY_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        int cnt = wordControllerDB.update(values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;

    }
}
