package com.example.evaluacon3apmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class cambiarPassword extends AppCompatActivity {
    private TextInputEditText email;
    private Button btnResetPass;
    private String etCorreo = "";  //El objeto almacenado escrito en el textlayout de correo.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);

        inits();
        eventReset();
    }

    private void inits(){
        email = findViewById(R.id.tiCorreoEdit);
        btnResetPass = findViewById(R.id.btnAceptarCambio);
    }

    private void eventReset(){
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCorreo = email.getText().toString();
                if(!etCorreo.isEmpty()){
                    resetPassword();
                }else{
                    Toast.makeText(cambiarPassword.this, "Debe ingresar el correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetPassword(){

    }
}