package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UsuarioActivity extends AppCompatActivity {
    private TextView tvNombresUsuarios;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        inits();
        cargaBundle();
    }

    private void inits(){
        tvNombresUsuarios = findViewById(R.id.tvUsuarios);
    }

    private void cargaBundle(){
        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("usuario");
        tvNombresUsuarios.setText(tvNombresUsuarios.getText() + " " + usuario.getNombreUsuario());
    }
}