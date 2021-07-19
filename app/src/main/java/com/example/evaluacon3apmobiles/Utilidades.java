package com.example.evaluacon3apmobiles;

public class Utilidades {
    //Constantes campos de la tabla usuario.
    public static final String USUARIO = "Usuario";
    public static final String ID_USUARIO = "Id del usuario";
    public static final String NOMBRE = "Nombre y apellido";
    public static final String APODO_USUARIO = "Nombre del usuario";
    public static final String CORREO = "Correo electronico";
    public static final String CONTRASENIA = "Contraseña";
    public static final String PREGUNTA = "Pregunta de seguridad";
    public static final String RESPUESTA = "Respuesta de seguridad";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +""+USUARIO+" ("+ID_USUARIO+" " +
    "INTEGER PRIMARY KEY, "+NOMBRE+ "TEXT, "+APODO_USUARIO+ "TEXT, "+CORREO+ "TEXT, "+CONTRASENIA+
            "TEXT, "+PREGUNTA+ "TEXT, "+RESPUESTA+ "TEXT";
}
