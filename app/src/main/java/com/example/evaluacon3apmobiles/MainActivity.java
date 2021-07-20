package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText user, contrasena;
    private Button btnSesion, btnRecuperar, btnRegistro;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        events();
    }

    private void inits(){
        user = findViewById(R.id.tiextNombreUsuario);
        contrasena = findViewById(R.id.tiextPassword);
        btnSesion = findViewById(R.id.btnAcceso);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnRegistro = findViewById(R.id.btnRegistrar);
        crearArchivoShared();
        leerArchivoShared();
        baseDatos();
    }

    public boolean valNameUser(TextInputEditText user){
        if(!user.getText().toString().isEmpty()){
            return true;
        }else{
            user.setError("No ingresó el nombre del usuario");
            return false;
        }
    }

    public boolean valPassword(TextInputEditText contrasena){
        if(!contrasena.getText().toString().isEmpty()){
            return true;
        }else{
            contrasena.setError("No ingresó la contraseña");
            return false;
        }
    }

    private void crearArchivoShared(){
        SharedPreferences sdps = getSharedPreferences("login_usuarios", Context.MODE_PRIVATE);
        String usuario = sdps.getString("Nombre del usuario", "");
        String email = sdps.getString("Correo electrónico", "");
        String pass = sdps.getString("Contraseña", "");
        user.setText(usuario);
        contrasena.setText(pass);

        SharedPreferences.Editor editarLogin = sdps.edit();
        editarLogin.putString("Nombre del usuario", user.getText().toString());
        editarLogin.putString("Contraseña", contrasena.getText().toString());
        editarLogin.putBoolean("Recordar sesion", true);
        editarLogin.commit();
    }

    private void leerArchivoShared(){
        SharedPreferences sdps = getSharedPreferences("login_usuario", Context.MODE_PRIVATE);
        String restoredText = sdps.getString("text", null);

        if(restoredText != null){
            String nombreUsuario = sdps.getString("Nombre del usuario", "");
            String password = sdps.getString("Contraseña", "");
            user.setText(nombreUsuario);
            contrasena.setText(password);
        }
    }

    private void events(){
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valNameUser(user);
                valPassword(contrasena);
                if(valNameUser(user) && valPassword(contrasena)){
                    Toast.makeText(MainActivity.this, "Usuario " + user.getText().toString() +" acceso a sesión", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrarUsuario.class);
                startActivity(i);
            }
        });
    }
    //Metodo que te retorne una lista de usuarios.
    private void baseDatos() {
        try{
            AdminSQLiteUsers conn = new AdminSQLiteUsers(this, "dbUsuarios", null, 1);
            SQLiteDatabase db = conn.getReadableDatabase();
            Usuario usuario = null;
            String sql = "SELECT * FROM usuario";
            Cursor c = db.rawQuery(sql, null);

            while(c.moveToNext()){
                usuario = new Usuario();
                usuario.setIdUsuario(c.getInt(0));
                usuario.setNombre(c.getString(1));
                usuario.setNombreUsuario(c.getString(2));
                usuario.setCorreo(c.getString(3));
                usuario.setPassword(c.getString(4));
                usuario.setSecurityQuestion(c.getString(5));
                usuario.setSecurityAnswer(c.getString(6));
                listaUsuarios.add(usuario);
            }
        }catch (Exception ex){
            Log.d("TAG_", "databaseUsers: " + ex.toString());
        }
    }
}