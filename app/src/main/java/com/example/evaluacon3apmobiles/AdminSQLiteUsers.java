package com.example.evaluacon3apmobiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class AdminSQLiteUsers extends SQLiteOpenHelper {
    final String users_table = "CREATE TABLE usuario (id_Usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre_apellido TEXT, username TEXT, correo TEXT, password TEXT, pregunta TEXT, respuesta TEXT)";

    final String events_table = "CREATE TABLE evento (id_evento INT PRIMARY KEY AUTOINCREMENT, titulo TEXT, " +
            "importancia TEXT, observacion TEXT, lugar TEXT, fecha_evento TEXT, tiempo_aviso TEXT, id_Usuario INTEGER NOT NULL," +
            "CONSTRAINT FK_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id_Usuario))";

    public AdminSQLiteUsers(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(users_table);
        db.execSQL(events_table);
        Log.d("TAG_","Base de datos creado");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
