package com.pronacej.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.pronacej.Pronacej.InfoPublica.ModalidadRecursosGrafico;
import com.pronacej.Pronacej.InfoPublica.RecursosHumanosGrafico;
import com.pronacej.Pronacej.R;

public class OpcionesRecursosActivity extends AppCompatActivity {

    private ConstraintLayout opcion1;
    private ConstraintLayout opcion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_recursos_humanos);

        initViews();
        setupListeners();

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(OpcionesRecursosActivity.this, CategoriaMenu.class);
            startActivity(intentHome);
        });

        ButtonBack.setOnClickListener(v -> onBackPressed());
    }

    private void initViews() {
        opcion1 = findViewById(R.id.Opcion1);
        opcion2 = findViewById(R.id.Opcion2);

        addTouchFeedback(opcion1);
        addTouchFeedback(opcion2);
    }

    private void setupListeners() {
        opcion1.setOnClickListener(v -> handleOpcion1Click());
        opcion2.setOnClickListener(v -> handleOpcion2Click());
    }

    private void handleOpcion1Click() {
        Intent intentOpcion1 = new Intent(this, RecursosHumanosGrafico.class);
        startActivity(intentOpcion1);
    }

    private void handleOpcion2Click() {
        Intent intentOpcion2 = new Intent(this, ModalidadRecursosGrafico.class);
        startActivity(intentOpcion2);
    }
    private void addTouchFeedback(View view) {
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setAlpha(0.7f);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.setAlpha(1.0f);
                    break;
            }
            return false;
        });
    }
}
