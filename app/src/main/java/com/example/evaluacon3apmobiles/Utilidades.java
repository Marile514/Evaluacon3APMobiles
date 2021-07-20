package com.example.evaluacon3apmobiles;

public class Utilidades {
    //Constantes campos de la tabla usuario.
    public static final String USUARIO = "usuario";
    public static final String ID_USUARIO = "id_usuario";
    public static final String NOMBRE = "nombre_apellido";
    public static final String USENAME = "username";
    public static final String CORREO = "correo";
    public static final String PASSWORD = "password";
    public static final String PREGUNTA = "pregunta";
    public static final String RESPUESTA = "respuesta";

    public static final String users_table = "CREATE TABLE usuario (id_Usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre_apellido TEXT, username TEXT, correo TEXT, password TEXT, pregunta TEXT, respuesta TEXT)";
}
