package com.pronacej.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pronacej.Pronacej.Model.AuthRequest;
import com.pronacej.Pronacej.Model.AuthResponse;
import com.pronacej.Pronacej.R;
import com.pronacej.Pronacej.Utils.Client;
import com.pronacej.Pronacej.Utils.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView btnRegistrar;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.bntRegistrar);

        loginService = Client.getClient("http://appconsulta.pronacej.gob.pe:8081").create(LoginService.class);

        btnLogin.setOnClickListener(v -> loginUser());
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthRequest loginRequest = new AuthRequest(email, password);

        Call<AuthResponse> loginCall = loginService.authenticate(loginRequest);
        loginCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse loginResponse = response.body();
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userName", loginResponse.getName());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, CategoriaMenu.class);
                    startActivity(intent);
                    finish();
                } else {
                    handleLoginError(response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLoginError(int responseCode) {
        String errorMessage;
        switch (responseCode) {
            case 401:
                errorMessage = "Email o contraseña incorrectos";
                break;
            case 404:
                errorMessage = "Usuario no encontrado";
                break;
            default:
                errorMessage = "Contraseña incorrecta";
                break;
        }
        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}