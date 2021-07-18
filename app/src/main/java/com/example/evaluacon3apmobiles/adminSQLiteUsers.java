package com.example.evaluacon3apmobiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class adminSQLiteUsers extends SQLiteOpenHelper {


    public adminSQLiteUsers(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USUARIO(idUsuario integer primary key, nombre text, nombreUsuario text, correo text," +
                "password text, securityQuestion text, securityAnswer text)");
        Log.d("TAG_","Base de datos creado");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
