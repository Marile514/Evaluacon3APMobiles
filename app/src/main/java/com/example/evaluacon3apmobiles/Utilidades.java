package com.example.evaluacon3apmobiles;

public class Utilidades {
    //Creación de las tablas.
    public static final String users_table = "CREATE TABLE usuario (id_Usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre_apellido TEXT, username TEXT, correo TEXT, password TEXT, pregunta TEXT, respuesta TEXT)";

    public static final String events_table = "CREATE TABLE evento (titulo TEXT, importancia TEXT, observacion TEXT, " +
            "lugar TEXT, fecha_evento DATE, tiempo_aviso TIME, id_usuario INTEGER NOT NULL," +
            "CONSTRAINT FK_USUARIOS FOREIGN KEY (id_usuario) REFERENCES usuario (id_Usuario)";
}
