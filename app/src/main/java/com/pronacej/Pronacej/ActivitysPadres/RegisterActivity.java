package com.pronacej.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.Model.RegisterRequest;
import com.pronacej.Pronacej.Model.RegisterResponse;
import com.pronacej.Pronacej.Model.User;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.Utils.Client;
import com.pronacej.Pronacej.Utils.LoginService;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etLastName, etEmail, etEntity, etDni;
    private Button btnRegister;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etEntity = findViewById(R.id.etEntity);
        etDni = findViewById(R.id.etDni);
        btnRegister = findViewById(R.id.btnRegister);

        // Crear instancia de ApiService
        loginService = Client.getClient("http://appconsulta.pronacej.gob.pe:8081/").create(LoginService.class);
        //loginService = Client.getClient("http://192.168.0.101:8080/").create(LoginService.class);

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String name = etName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String entity = etEntity.getText().toString().trim();
        String dni = etDni.getText().toString().trim();

        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || entity.isEmpty() || dni.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Ningún dato debe estar vacío", Toast.LENGTH_SHORT).show();
        } else if (dni.length() != 8) {
            Toast.makeText(RegisterActivity.this, "El DNI debe tener 8 dígitos", Toast.LENGTH_SHORT).show();
        } else if (!email.contains("@")) {
            Toast.makeText(RegisterActivity.this, "El email debe contener '@'", Toast.LENGTH_SHORT).show();
        } else {
            // Verificar si el DNI o email ya están registrados
            loginService.getAllUsers().enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.isSuccessful()) {
                        List<User> users = response.body();
                        boolean isDniRegistered = false;
                        boolean isEmailRegistered = false;

                        for (User user : users) {
                            if (user.getDni() != null && user.getDni().equals(dni)) {
                                isDniRegistered = true;
                            }
                            if (user.getEmail().equals(email)) {
                                isEmailRegistered = true;
                            }
                        }

                        if (isDniRegistered) {
                            Toast.makeText(RegisterActivity.this, "Este DNI ya está registrado", Toast.LENGTH_SHORT).show();
                        } else if (isEmailRegistered) {
                            Toast.makeText(RegisterActivity.this, "Este email ya está registrado", Toast.LENGTH_SHORT).show();
                        } else {
                            // Proceder con el registro
                            proceedWithRegistration(name, lastName, email, entity, dni);
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error al verificar usuarios existentes", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Error de conexión al verificar usuarios", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void proceedWithRegistration(String name, String lastName, String email, String entity, String dni) {
        int typeUserId = 2;
        int state = (short)0;
        LocalDate expirationDate = null;
        RegisterRequest registerRequest = new RegisterRequest(typeUserId, name, lastName, email, entity, expirationDate, (short) state, dni);

        Call<RegisterResponse> registerCall = loginService.register(registerRequest);
        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    Toast.makeText(RegisterActivity.this, "Solicitud Enviada", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
