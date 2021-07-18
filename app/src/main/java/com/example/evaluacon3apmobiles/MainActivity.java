package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText user, contrasena, correo;
    private Button btnSesion, btnRecuperar, btnRegistro;
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
        correo = findViewById(R.id.tiextCorreo);
        crearArchivoShared();
        leerArchivoShared();
    }

    public boolean valNameUser(TextInputEditText user){
        if(!user.getText().toString().isEmpty()){
            return true;
        }else{
            user.setError("No ingresó el nombre del usuario");
            return false;
        }
    }

    private boolean valEmail(TextInputEditText correo){
        if(!correo.getText().toString().isEmpty()){
            String strCorreo = correo.getText().toString();
            //Usar el método validarCorreo2(strCorreo) para la expresión regular.
            if(validarCorreo(strCorreo)){
                return true;
            } else {
                correo.setError("No es un Correo!");
                return false;
            }
        } else {
            correo.setError("Correo vacío");
            return false;
        }
    }

    public boolean validarCorreo(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
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
        correo.setText(email);
        contrasena.setText(pass);

        SharedPreferences.Editor editarLogin = sdps.edit();
        editarLogin.putString("Nombre del usuario", user.getText().toString());
        editarLogin.putString("Correo electrónico", correo.getText().toString());
        editarLogin.putString("Contraseña", contrasena.getText().toString());
        editarLogin.putBoolean("Recordar sesion", true);
        editarLogin.commit();
    }

    private void leerArchivoShared(){
        SharedPreferences sdps = getSharedPreferences("login_usuario", Context.MODE_PRIVATE);
        String restoredText = sdps.getString("text", null);

        if(restoredText != null){
            String nombreUsuario = sdps.getString("Nombre del usuario", "");
            String correoUsuario = sdps.getString("Correo electrónico", "");
            String password = sdps.getString("Contraseña", "");
            user.setText(nombreUsuario);
            correo.setText(correoUsuario);
            contrasena.setText(password);
        }
    }

    private void events(){
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valNameUser(user);
                valEmail(correo);
                valPassword(contrasena);
                if(valNameUser(user) && valPassword(contrasena)){
                    Toast.makeText(MainActivity.this, "Usuario " + user.getText().toString() +" registrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}