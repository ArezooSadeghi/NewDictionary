package com.example.newdictionary.repository;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.newdictionary.database.DBSchema;
import com.example.newdictionary.model.Word;

import java.util.UUID;

public class WordCursorWrapper extends CursorWrapper {
    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord() {
        UUID uuid = UUID.fromString(getString(getColumnIndex(DBSchema.WordTable.Cols.UUID)));
        String name = getString(getColumnIndex(DBSchema.WordTable.Cols.NAME));
        String mean = getString(getColumnIndex(DBSchema.WordTable.Cols.MEAN));
        return new Word(name, mean, uuid);
    }
}
