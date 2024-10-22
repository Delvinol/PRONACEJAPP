package com.pronacej.Pronacej.ResultadosSoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.pronacej.Pronacej.ActivitysPadres.CategoriaMenu;
import com.pronacej.Pronacej.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultadosDelitoSoa extends AppCompatActivity {

    private int agresiones_mujeres;
    private int autoaborto;
    private int exposicion_peligro;
    private int feminicidio;
    private int homicidio_c;
    private int homicidio_ct;
    private int homicidio_culposo;
    private int homicidio_s;
    private int homicidio_st;
    private int infanticidio;
    private int lesiones_culposas;
    private int lesiones_culposas_agravadas;
    private int lesiones_dolosas;
    private int lesiones_graves;
    private int lesiones_leves;
    private int lesiones_leves_agravadas;
    private int lesiones_leves_tentativa;
    private int maltrato;
    private int parricidio;
    private int sicariato;
    private int otros;
    private int trafico_drogas;
    private int apropiacion_ilicita;
    private int chantaje;
    private int danos_agravados;
    private int extorsion;
    private int extorsion_agravada;
    private int hurto_agravado;
    private int hurto_agravado_tentativa;
    private int hurto_ganado;
    private int hurto_simple;
    private int hurto_simple_tentativa;
    private int hurto_simple_dano;
    private int receptacion_agravada;
    private int robo;
    private int robo_agravado;
    private int robo_ganado;
    private int robo_tentativa;
    private int usurpacion_agravada;
    private int acoso;
    private int acoso_sexual;
    private int chantaje_sexual;
    private int coaccion;
    private int exhibiciones_obscenas;
    private int favorecimiento_prostitucion;
    private int proposiciones_sexuales;
    private int secuestro;
    private int tocamientos_menores;
    private int tocamientos_sin_consentimiento;
    private int violacion_menor;
    private int violacion_inconsciencia;
    private int violacion_sexual;
    private int violacion_menor_tentativa;
    private int violacion_tentativa;
    private int violacion_domicilio;
    private int conduccion_ebriedad;
    private int fabricacion_armas;
    private int fabricacion_materiales_peligrosos;
    private int produccion_peligro_comun;
    private int produccion_trafico_armas;
    private int trafico_microcomercializacion;
    private int trafico_insumos_quimicos;
    private int violacion_medidas_sanitarias;
    private int encubrimiento_personal;
    private int fuga_accidente;
    private int resistencia_autoridad;

    private TextView textViewTotalCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_delito_soa);

        Button ButtonBack = findViewById(R.id.buttonBack);
        Button ButtonHome = findViewById(R.id.buttonHome);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(ResultadosDelitoSoa.this, CategoriaMenu.class);
                startActivity(intentHome);
            }
        });

        ButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Obtener los valores de los delitos específicos desde el intent
        Intent intent = getIntent();
        agresiones_mujeres = intent.getIntExtra("agresiones_mujeres", 0);
        autoaborto = intent.getIntExtra("autoaborto", 0);
        exposicion_peligro = intent.getIntExtra("exposicion_peligro", 0);
        feminicidio = intent.getIntExtra("feminicidio", 0);
        homicidio_c = intent.getIntExtra("homicidio_c", 0);
        homicidio_ct = intent.getIntExtra("homicidio_ct", 0);
        homicidio_culposo = intent.getIntExtra("homicidio_culposo", 0);
        homicidio_s = intent.getIntExtra("homicidio_s", 0);
        homicidio_st = intent.getIntExtra("homicidio_st", 0);
        infanticidio = intent.getIntExtra("infanticidio", 0);
        lesiones_culposas = intent.getIntExtra("lesiones_culposas", 0);
        lesiones_culposas_agravadas = intent.getIntExtra("lesiones_culposas_agravadas", 0);
        lesiones_dolosas = intent.getIntExtra("lesiones_dolosas", 0);
        lesiones_graves = intent.getIntExtra("lesiones_graves", 0);
        lesiones_leves = intent.getIntExtra("lesiones_leves", 0);
        lesiones_leves_agravadas = intent.getIntExtra("lesiones_leves_agravadas", 0);
        lesiones_leves_tentativa = intent.getIntExtra("lesiones_leves_tentativa", 0);
        maltrato = intent.getIntExtra("maltrato", 0);
        parricidio = intent.getIntExtra("parricidio", 0);
        sicariato = intent.getIntExtra("sicariato", 0);
        otros = intent.getIntExtra("otros", 0);
        trafico_drogas = intent.getIntExtra("trafico_drogas", 0);
        apropiacion_ilicita = intent.getIntExtra("apropiacion_ilicita", 0);
        chantaje = intent.getIntExtra("chantaje", 0);
        danos_agravados = intent.getIntExtra("danos_agravados", 0);
        extorsion = intent.getIntExtra("extorsion", 0);
        extorsion_agravada = intent.getIntExtra("extorsion_agravada", 0);
        hurto_agravado = intent.getIntExtra("hurto_agravado", 0);
        hurto_agravado_tentativa = intent.getIntExtra("hurto_agravado_tentativa", 0);
        hurto_ganado = intent.getIntExtra("hurto_ganado", 0);
        hurto_simple = intent.getIntExtra("hurto_simple", 0);
        hurto_simple_tentativa = intent.getIntExtra("hurto_simple_tentativa", 0);
        hurto_simple_dano = intent.getIntExtra("hurto_simple_dano", 0);
        receptacion_agravada = intent.getIntExtra("receptacion_agravada", 0);
        robo = intent.getIntExtra("robo", 0);
        robo_agravado = intent.getIntExtra("robo_agravado", 0);
        robo_ganado = intent.getIntExtra("robo_ganado", 0);
        robo_tentativa = intent.getIntExtra("robo_tentativa", 0);
        usurpacion_agravada = intent.getIntExtra("usurpacion_agravada", 0);
        acoso = intent.getIntExtra("acoso", 0);
        acoso_sexual = intent.getIntExtra("acoso_sexual", 0);
        chantaje_sexual = intent.getIntExtra("chantaje_sexual", 0);
        coaccion = intent.getIntExtra("coaccion", 0);
        exhibiciones_obscenas = intent.getIntExtra("exhibiciones_obscenas", 0);
        favorecimiento_prostitucion = intent.getIntExtra("favorecimiento_prostitucion", 0);
        proposiciones_sexuales = intent.getIntExtra("proposiciones_sexuales", 0);
        secuestro = intent.getIntExtra("secuestro", 0);
        tocamientos_menores = intent.getIntExtra("tocamientos_menores", 0);
        tocamientos_sin_consentimiento = intent.getIntExtra("tocamientos_sin_consentimiento", 0);
        violacion_menor = intent.getIntExtra("violacion_menor", 0);
        violacion_inconsciencia = intent.getIntExtra("violacion_inconsciencia", 0);
        violacion_sexual = intent.getIntExtra("violacion_sexual", 0);
        violacion_menor_tentativa = intent.getIntExtra("violacion_menor_tentativa", 0);
        violacion_tentativa = intent.getIntExtra("violacion_tentativa", 0);
        violacion_domicilio = intent.getIntExtra("violacion_domicilio", 0);
        conduccion_ebriedad = intent.getIntExtra("conduccion_ebriedad", 0);
        fabricacion_armas = intent.getIntExtra("fabricacion_armas", 0);
        fabricacion_materiales_peligrosos = intent.getIntExtra("fabricacion_materiales_peligrosos", 0);
        produccion_peligro_comun = intent.getIntExtra("produccion_peligro_comun", 0);
        produccion_trafico_armas = intent.getIntExtra("produccion_trafico_armas", 0);
        trafico_microcomercializacion = intent.getIntExtra("trafico_microcomercializacion", 0);
        trafico_insumos_quimicos = intent.getIntExtra("trafico_insumos_quimicos", 0);
        violacion_medidas_sanitarias = intent.getIntExtra("violacion_medidas_sanitarias", 0);
        encubrimiento_personal = intent.getIntExtra("encubrimiento_personal", 0);
        fuga_accidente = intent.getIntExtra("fuga_accidente", 0);
        resistencia_autoridad = intent.getIntExtra("resistencia_autoridad", 0);

        // Referencia al TextView del total de cantidad
        textViewTotalCantidad = findViewById(R.id.textViewTotalCantidad);

        // Calcular el total de delitos
        int totalDelitos = agresiones_mujeres + autoaborto + exposicion_peligro + feminicidio + homicidio_c +
                homicidio_ct + homicidio_culposo + homicidio_s + homicidio_st + infanticidio +
                lesiones_culposas + lesiones_culposas_agravadas + lesiones_dolosas + lesiones_graves +
                lesiones_leves + lesiones_leves_agravadas + lesiones_leves_tentativa + maltrato +
                parricidio + sicariato + otros + trafico_drogas + apropiacion_ilicita + chantaje +
                danos_agravados + extorsion + extorsion_agravada + hurto_agravado + hurto_agravado_tentativa +
                hurto_ganado + hurto_simple + hurto_simple_tentativa + hurto_simple_dano + receptacion_agravada +
                robo + robo_agravado + robo_ganado + robo_tentativa + usurpacion_agravada + acoso +
                acoso_sexual + chantaje_sexual + coaccion + exhibiciones_obscenas + favorecimiento_prostitucion +
                proposiciones_sexuales + secuestro + tocamientos_menores + tocamientos_sin_consentimiento +
                violacion_menor + violacion_inconsciencia + violacion_sexual + violacion_menor_tentativa +
                violacion_tentativa + violacion_domicilio + conduccion_ebriedad + fabricacion_armas +
                fabricacion_materiales_peligrosos + produccion_peligro_comun + produccion_trafico_armas +
                trafico_microcomercializacion + trafico_insumos_quimicos + violacion_medidas_sanitarias +
                encubrimiento_personal + fuga_accidente + resistencia_autoridad;

        textViewTotalCantidad.setText(String.format("Total: %d", Math.round(totalDelitos)));

        // Calcular los porcentajes
        double porcentajeAgresionesMujeres = (double) agresiones_mujeres / totalDelitos * 100;
        double porcentajeAutoaborto = (double) autoaborto / totalDelitos * 100;
        double porcentajeExposicionPeligro = (double) exposicion_peligro / totalDelitos * 100;
        double porcentajeFeminicidio = (double) feminicidio / totalDelitos * 100;
        double porcentajeHomicidioCalificado = (double) homicidio_c / totalDelitos * 100;
        double porcentajeHomicidioTentativa = (double) homicidio_ct / totalDelitos * 100;
        double porcentajeHomicidioCulposo = (double) homicidio_culposo / totalDelitos * 100;
        double porcentajeHomicidioSimple = (double) homicidio_s / totalDelitos * 100;
        double porcentajeHomicidioSimpleTentativa = (double) homicidio_st / totalDelitos * 100;
        double porcentajeInfanticidio = (double) infanticidio / totalDelitos * 100;
        double porcentajeLesionesCulposas = (double) lesiones_culposas / totalDelitos * 100;
        double porcentajeLesionesCulposasAgravadas = (double) lesiones_culposas_agravadas / totalDelitos * 100;
        double porcentajeLesionesDolosas = (double) lesiones_dolosas / totalDelitos * 100;
        double porcentajeLesionesGraves = (double) lesiones_graves / totalDelitos * 100;
        double porcentajeLesionesLeves = (double) lesiones_leves / totalDelitos * 100;
        double porcentajeLesionesLevesAgravadas = (double) lesiones_leves_agravadas / totalDelitos * 100;
        double porcentajeLesionesLevesTentativa = (double) lesiones_leves_tentativa / totalDelitos * 100;
        double porcentajeMaltrato = (double) maltrato / totalDelitos * 100;
        double porcentajeParricidio = (double) parricidio / totalDelitos * 100;
        double porcentajeSicariato = (double) sicariato / totalDelitos * 100;
        double porcentajeOtros = (double) otros / totalDelitos * 100;
        double porcentajeTraficoDrogas = (double) trafico_drogas / totalDelitos * 100;
        double porcentajeApropiacionIlicita = (double) apropiacion_ilicita / totalDelitos * 100;
        double porcentajeChantaje = (double) chantaje / totalDelitos * 100;
        double porcentajeDanosAgravados = (double) danos_agravados / totalDelitos * 100;
        double porcentajeExtorsion = (double) extorsion / totalDelitos * 100;
        double porcentajeExtorsionAgravada = (double) extorsion_agravada / totalDelitos * 100;
        double porcentajeHurtoAgravado = (double) hurto_agravado / totalDelitos * 100;
        double porcentajeHurtoAgravadoTentativa = (double) hurto_agravado_tentativa / totalDelitos * 100;
        double porcentajeHurtoGanado = (double) hurto_ganado / totalDelitos * 100;
        double porcentajeHurtoSimple = (double) hurto_simple / totalDelitos * 100;
        double porcentajeHurtoSimpleTentativa = (double) hurto_simple_tentativa / totalDelitos * 100;
        double porcentajeHurtoSimpleDano = (double) hurto_simple_dano / totalDelitos * 100;
        double porcentajeReceptacionAgravada = (double) receptacion_agravada / totalDelitos * 100;
        double porcentajeRobo = (double) robo / totalDelitos * 100;
        double porcentajeRoboAgravado = (double) robo_agravado / totalDelitos * 100;
        double porcentajeRoboGanado = (double) robo_ganado / totalDelitos * 100;
        double porcentajeRoboTentativa = (double) robo_tentativa / totalDelitos * 100;
        double porcentajeUsurpacionAgravada = (double) usurpacion_agravada / totalDelitos * 100;
        double porcentajeAcoso = (double) acoso / totalDelitos * 100;
        double porcentajeAcosoSexual = (double) acoso_sexual / totalDelitos * 100;
        double porcentajeChantajeSexual = (double) chantaje_sexual / totalDelitos * 100;
        double porcentajeCoaccion = (double) coaccion / totalDelitos * 100;
        double porcentajeExhibicionesObscenas = (double) exhibiciones_obscenas / totalDelitos * 100;
        double porcentajeFavorecimientoProstitucion = (double) favorecimiento_prostitucion / totalDelitos * 100;
        double porcentajeProposicionesSexuales = (double) proposiciones_sexuales / totalDelitos * 100;
        double porcentajeSecuestro = (double) secuestro / totalDelitos * 100;
        double porcentajeTocamientosMenores = (double) tocamientos_menores / totalDelitos * 100;
        double porcentajeTocamientosSinConsentimiento = (double) tocamientos_sin_consentimiento / totalDelitos * 100;
        double porcentajeViolacionMenor = (double) violacion_menor / totalDelitos * 100;
        double porcentajeViolacionInconsciencia = (double) violacion_inconsciencia / totalDelitos * 100;
        double porcentajeViolacionSexual = (double) violacion_sexual / totalDelitos * 100;
        double porcentajeViolacionMenorTentativa = (double) violacion_menor_tentativa / totalDelitos * 100;
        double porcentajeViolacionTentativa = (double) violacion_tentativa / totalDelitos * 100;
        double porcentajeViolacionDomicilio = (double) violacion_domicilio / totalDelitos * 100;
        double porcentajeConduccionEbriedad = (double) conduccion_ebriedad / totalDelitos * 100;
        double porcentajeFabricacionArmas = (double) fabricacion_armas / totalDelitos * 100;
        double porcentajeFabricacionMaterialesPeligrosos = (double) fabricacion_materiales_peligrosos / totalDelitos * 100;
        double porcentajeProduccionPeligroComun = (double) produccion_peligro_comun / totalDelitos * 100;
        double porcentajeProduccionTraficoArmas = (double) produccion_trafico_armas / totalDelitos * 100;
        double porcentajeTraficoMicrocomercializacion = (double) trafico_microcomercializacion / totalDelitos * 100;
        double porcentajeTraficoInsumosQuimicos = (double) trafico_insumos_quimicos / totalDelitos * 100;
        double porcentajeViolacionMedidasSanitarias = (double) violacion_medidas_sanitarias / totalDelitos * 100;
        double porcentajeEncubrimientoPersonal = (double) encubrimiento_personal / totalDelitos * 100;
        double porcentajeFugaAccidente = (double) fuga_accidente / totalDelitos * 100;
        double porcentajeResistenciaAutoridad = (double) resistencia_autoridad / totalDelitos * 100;

        if (agresiones_mujeres > 0) {
            ((TextView) findViewById(R.id.textView1)).setText(String.valueOf(agresiones_mujeres));
            ((TextView) findViewById(R.id.textView1n)).setText("Agresiones Mujeres");
        } else {
            ((LinearLayout) findViewById(R.id.linear1)).setVisibility(View.GONE);
        }

        if (autoaborto > 0) {
            ((TextView) findViewById(R.id.textView2)).setText(String.valueOf(autoaborto));
            ((TextView) findViewById(R.id.textView2n)).setText("Autoaborto");
        } else {
            ((LinearLayout) findViewById(R.id.linear2)).setVisibility(View.GONE);

        }

        if (exposicion_peligro > 0) {
            ((TextView) findViewById(R.id.textView3)).setText(String.valueOf(exposicion_peligro));
            ((TextView) findViewById(R.id.textView3n)).setText("Exposición al Peligro");
        } else {
            ((LinearLayout) findViewById(R.id.linear3)).setVisibility(View.GONE);

        }

        if (feminicidio > 0) {
            ((TextView) findViewById(R.id.textView4)).setText(String.valueOf(feminicidio));
            ((TextView) findViewById(R.id.textView4n)).setText("Feminicidio");
        } else {
            ((LinearLayout) findViewById(R.id.linear4)).setVisibility(View.GONE);

        }

        if (homicidio_c > 0) {
            ((TextView) findViewById(R.id.textView5)).setText(String.valueOf(homicidio_c));
            ((TextView) findViewById(R.id.textView5n)).setText("Homicidio Calificado");
        } else {
            ((LinearLayout) findViewById(R.id.linear5)).setVisibility(View.GONE);

        }

        if (homicidio_ct > 0) {
            ((TextView) findViewById(R.id.textView6)).setText(String.valueOf(homicidio_ct));
            ((TextView) findViewById(R.id.textView6n)).setText("Homicidio Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear6)).setVisibility(View.GONE);

        }

        if (homicidio_culposo > 0) {
            ((TextView) findViewById(R.id.textView7)).setText(String.valueOf(homicidio_culposo));
            ((TextView) findViewById(R.id.textView7n)).setText("Homicidio Culposo");
        } else {
            ((LinearLayout) findViewById(R.id.linear7)).setVisibility(View.GONE);

        }

        if (homicidio_s > 0) {
            ((TextView) findViewById(R.id.textView8)).setText(String.valueOf(homicidio_s));
            ((TextView) findViewById(R.id.textView8n)).setText("Homicidio Simple");
        } else {
            ((LinearLayout) findViewById(R.id.linear8)).setVisibility(View.GONE);

        }

        if (homicidio_st > 0) {
            ((TextView) findViewById(R.id.textView9)).setText(String.valueOf(homicidio_st));
            ((TextView) findViewById(R.id.textView9n)).setText("Homicidio Simple Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear9)).setVisibility(View.GONE);

        }

        if (infanticidio > 0) {
            ((TextView) findViewById(R.id.textView10)).setText(String.valueOf(infanticidio));
            ((TextView) findViewById(R.id.textView10n)).setText("Infanticidio");
        } else {
            ((LinearLayout) findViewById(R.id.linear10)).setVisibility(View.GONE);

        }

        if (lesiones_culposas > 0) {
            ((TextView) findViewById(R.id.textView11)).setText(String.valueOf(lesiones_culposas));
            ((TextView) findViewById(R.id.textView11n)).setText("Lesiones Culposas");
        } else {
            ((LinearLayout) findViewById(R.id.linear11)).setVisibility(View.GONE);

        }

        if (lesiones_culposas_agravadas > 0) {
            ((TextView) findViewById(R.id.textView12)).setText(String.valueOf(lesiones_culposas_agravadas));
            ((TextView) findViewById(R.id.textView12n)).setText("Lesiones Culposas Agr");
        } else {
            ((LinearLayout) findViewById(R.id.linear12)).setVisibility(View.GONE);

        }

        if (lesiones_dolosas > 0) {
            ((TextView) findViewById(R.id.textView13)).setText(String.valueOf(lesiones_dolosas));
            ((TextView) findViewById(R.id.textView13n)).setText("Lesiones Dolosas");
        } else {
            ((LinearLayout) findViewById(R.id.linear13)).setVisibility(View.GONE);

        }

        if (lesiones_graves > 0) {
            ((TextView) findViewById(R.id.textView14)).setText(String.valueOf(lesiones_graves));
            ((TextView) findViewById(R.id.textView14n)).setText("Lesiones Graves");
        } else {
            ((LinearLayout) findViewById(R.id.linear14)).setVisibility(View.GONE);

        }

        if (lesiones_leves > 0) {
            ((TextView) findViewById(R.id.textView15)).setText(String.valueOf(lesiones_leves));
            ((TextView) findViewById(R.id.textView15n)).setText("Lesiones Leves");
        } else {
            ((LinearLayout) findViewById(R.id.linear15)).setVisibility(View.GONE);

        }

        if (lesiones_leves_agravadas > 0) {
            ((TextView) findViewById(R.id.textView16)).setText(String.valueOf(lesiones_leves_agravadas));
            ((TextView) findViewById(R.id.textView16n)).setText("Lesiones Leves Agr");
        } else {
            ((LinearLayout) findViewById(R.id.linear16)).setVisibility(View.GONE);

        }

        if (lesiones_leves_tentativa > 0) {
            ((TextView) findViewById(R.id.textView17)).setText(String.valueOf(lesiones_leves_tentativa));
            ((TextView) findViewById(R.id.textView17n)).setText("Lesiones Leves Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear17)).setVisibility(View.GONE);

        }

        if (maltrato > 0) {
            ((TextView) findViewById(R.id.textView18)).setText(String.valueOf(maltrato));
            ((TextView) findViewById(R.id.textView18n)).setText("Maltrato");
        } else {
            ((LinearLayout) findViewById(R.id.linear18)).setVisibility(View.GONE);

        }

        if (parricidio > 0) {
            ((TextView) findViewById(R.id.textView19)).setText(String.valueOf(parricidio));
            ((TextView) findViewById(R.id.textView19n)).setText("Parricidio");
        } else {
            ((LinearLayout) findViewById(R.id.linear19)).setVisibility(View.GONE);

        }

        if (sicariato > 0) {
            ((TextView) findViewById(R.id.textView20)).setText(String.valueOf(sicariato));
            ((TextView) findViewById(R.id.textView20n)).setText("Sicariato");
        } else {
            ((LinearLayout) findViewById(R.id.linear20)).setVisibility(View.GONE);

        }

        if (otros > 0) {
            ((TextView) findViewById(R.id.textView21)).setText(String.valueOf(otros));
            ((TextView) findViewById(R.id.textView21n)).setText("Otros");
        } else {
            ((LinearLayout) findViewById(R.id.linear21)).setVisibility(View.GONE);

        }

        if (trafico_drogas > 0) {
            ((TextView) findViewById(R.id.textView22)).setText(String.valueOf(trafico_drogas));
            ((TextView) findViewById(R.id.textView22n)).setText("Tráfico Ilícito de Drogas");
        } else {
            ((LinearLayout) findViewById(R.id.linear22)).setVisibility(View.GONE);

        }

        if (apropiacion_ilicita > 0) {
            ((TextView) findViewById(R.id.textView23)).setText(String.valueOf(apropiacion_ilicita));
            ((TextView) findViewById(R.id.textView23n)).setText("Apropiación Ilícita");
        } else {
            ((LinearLayout) findViewById(R.id.linear23)).setVisibility(View.GONE);

        }

        if (chantaje > 0) {
            ((TextView) findViewById(R.id.textView24)).setText(String.valueOf(chantaje));
            ((TextView) findViewById(R.id.textView24n)).setText("Chantaje");
        } else {
            ((LinearLayout) findViewById(R.id.linear24)).setVisibility(View.GONE);

        }

        if (danos_agravados > 0) {
            ((TextView) findViewById(R.id.textView25)).setText(String.valueOf(danos_agravados));
            ((TextView) findViewById(R.id.textView25n)).setText("Daños Agravados");
        } else {
            ((LinearLayout) findViewById(R.id.linear25)).setVisibility(View.GONE);

        }

        if (extorsion > 0) {
            ((TextView) findViewById(R.id.textView26)).setText(String.valueOf(extorsion));
            ((TextView) findViewById(R.id.textView26n)).setText("Extorsión");
        } else {
            ((LinearLayout) findViewById(R.id.linear26)).setVisibility(View.GONE);

        }

        if (extorsion_agravada > 0) {
            ((TextView) findViewById(R.id.textView27)).setText(String.valueOf(extorsion_agravada));
            ((TextView) findViewById(R.id.textView27n)).setText("Extorsión Agravada");
        } else {
            ((LinearLayout) findViewById(R.id.linear27)).setVisibility(View.GONE);

        }

        if (hurto_agravado > 0) {
            ((TextView) findViewById(R.id.textView28)).setText(String.valueOf(hurto_agravado));
            ((TextView) findViewById(R.id.textView28n)).setText("Hurto Agravado");
        } else {
            ((LinearLayout) findViewById(R.id.linear28)).setVisibility(View.GONE);

        }

        if (hurto_agravado_tentativa > 0) {
            ((TextView) findViewById(R.id.textView29)).setText(String.valueOf(hurto_agravado_tentativa));
            ((TextView) findViewById(R.id.textView29n)).setText("Hurto Agravado Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear29)).setVisibility(View.GONE);

        }

        if (hurto_ganado > 0) {
            ((TextView) findViewById(R.id.textView30)).setText(String.valueOf(hurto_ganado));
            ((TextView) findViewById(R.id.textView30n)).setText("Hurto Uso Ganado");
        } else {
            ((LinearLayout) findViewById(R.id.linear30)).setVisibility(View.GONE);

        }

        if (hurto_simple > 0) {
            ((TextView) findViewById(R.id.textView31)).setText(String.valueOf(hurto_simple));
            ((TextView) findViewById(R.id.textView31n)).setText("Hurto Simple");
        } else {
            ((LinearLayout) findViewById(R.id.linear31)).setVisibility(View.GONE);

        }

        if (hurto_simple_tentativa > 0) {
            ((TextView) findViewById(R.id.textView32)).setText(String.valueOf(hurto_simple_tentativa));
            ((TextView) findViewById(R.id.textView32n)).setText("Hurto Simple Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear32)).setVisibility(View.GONE);

        }

        if (hurto_simple_dano > 0) {
            ((TextView) findViewById(R.id.textView33)).setText(String.valueOf(hurto_simple_dano));
            ((TextView) findViewById(R.id.textView33n)).setText("Hurto Simple Daño");
        } else {
            ((LinearLayout) findViewById(R.id.linear33)).setVisibility(View.GONE);

        }

        if (receptacion_agravada > 0) {
            ((TextView) findViewById(R.id.textView34)).setText(String.valueOf(receptacion_agravada));
            ((TextView) findViewById(R.id.textView34n)).setText("Receptación Agravada");
        } else {
            ((LinearLayout) findViewById(R.id.linear34)).setVisibility(View.GONE);

        }

        if (robo > 0) {
            ((TextView) findViewById(R.id.textView35)).setText(String.valueOf(robo));
            ((TextView) findViewById(R.id.textView35n)).setText("Robo");
        } else {
            ((LinearLayout) findViewById(R.id.linear35)).setVisibility(View.GONE);

        }
        if (robo_agravado > 0) {
            ((TextView) findViewById(R.id.textView36)).setText(String.valueOf(robo_agravado));
            ((TextView) findViewById(R.id.textView36n)).setText("Robo Agravado");
        } else {
            ((LinearLayout) findViewById(R.id.linear36)).setVisibility(View.GONE);

        }

        if (robo_ganado > 0) {
            ((TextView) findViewById(R.id.textView37)).setText(String.valueOf(robo_ganado));
            ((TextView) findViewById(R.id.textView37n)).setText("Robo Ganado");
        } else {
            ((LinearLayout) findViewById(R.id.linear37)).setVisibility(View.GONE);

        }

        if (robo_tentativa > 0) {
            ((TextView) findViewById(R.id.textView38)).setText(String.valueOf(robo_tentativa));
            ((TextView) findViewById(R.id.textView38n)).setText("Robo Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear38)).setVisibility(View.GONE);

        }

        if (usurpacion_agravada > 0) {
            ((TextView) findViewById(R.id.textView39)).setText(String.valueOf(usurpacion_agravada));
            ((TextView) findViewById(R.id.textView39n)).setText("Usurpación Agravada");
        } else {
            ((LinearLayout) findViewById(R.id.linear39)).setVisibility(View.GONE);

        }

        if (acoso > 0) {
            ((TextView) findViewById(R.id.textView40)).setText(String.valueOf(acoso));
            ((TextView) findViewById(R.id.textView40n)).setText("Acoso");
        } else {
            ((LinearLayout) findViewById(R.id.linear40)).setVisibility(View.GONE);

        }

        if (acoso_sexual > 0) {
            ((TextView) findViewById(R.id.textView41)).setText(String.valueOf(acoso_sexual));
            ((TextView) findViewById(R.id.textView41n)).setText("Acoso Sexual");
        } else {
            ((LinearLayout) findViewById(R.id.linear41)).setVisibility(View.GONE);

        }

        if (chantaje_sexual > 0) {
            ((TextView) findViewById(R.id.textView42)).setText(String.valueOf(chantaje_sexual));
            ((TextView) findViewById(R.id.textView42n)).setText("Chantaje Sexual");
        } else {
            ((LinearLayout) findViewById(R.id.linear42)).setVisibility(View.GONE);

        }

        if (coaccion > 0) {
            ((TextView) findViewById(R.id.textView43)).setText(String.valueOf(coaccion));
            ((TextView) findViewById(R.id.textView43n)).setText("Coacción");
        } else {
            ((LinearLayout) findViewById(R.id.linear43)).setVisibility(View.GONE);

        }

        if (exhibiciones_obscenas > 0) {
            ((TextView) findViewById(R.id.textView44)).setText(String.valueOf(exhibiciones_obscenas));
            ((TextView) findViewById(R.id.textView44n)).setText("Exhibiciones Obscenas");
        } else {
            ((LinearLayout) findViewById(R.id.linear44)).setVisibility(View.GONE);

        }

        if (favorecimiento_prostitucion > 0) {
            ((TextView) findViewById(R.id.textView45)).setText(String.valueOf(favorecimiento_prostitucion));
            ((TextView) findViewById(R.id.textView45n)).setText("Favorecimiento Prostitución");
        } else {
            ((LinearLayout) findViewById(R.id.linear45)).setVisibility(View.GONE);

        }

        if (proposiciones_sexuales > 0) {
            ((TextView) findViewById(R.id.textView46)).setText(String.valueOf(proposiciones_sexuales));
            ((TextView) findViewById(R.id.textView46n)).setText("Proposiciones Sexuales");
        } else {
            ((LinearLayout) findViewById(R.id.linear46)).setVisibility(View.GONE);

        }

        if (secuestro > 0) {
            ((TextView) findViewById(R.id.textView47)).setText(String.valueOf(secuestro));
            ((TextView) findViewById(R.id.textView47n)).setText("Secuestro");
        } else {
            ((LinearLayout) findViewById(R.id.linear47)).setVisibility(View.GONE);

        }

        if (tocamientos_menores > 0) {
            ((TextView) findViewById(R.id.textView48)).setText(String.valueOf(tocamientos_menores));
            ((TextView) findViewById(R.id.textView48n)).setText("Tocamientos Menores");
        } else {
            ((LinearLayout) findViewById(R.id.linear48)).setVisibility(View.GONE);

        }

        if (tocamientos_sin_consentimiento > 0) {
            ((TextView) findViewById(R.id.textView49)).setText(String.valueOf(tocamientos_sin_consentimiento));
            ((TextView) findViewById(R.id.textView49n)).setText("Tocamientos Sin Consentimiento");
        } else {
            ((LinearLayout) findViewById(R.id.linear49)).setVisibility(View.GONE);

        }

        if (violacion_menor > 0) {
            ((TextView) findViewById(R.id.textView50)).setText(String.valueOf(violacion_menor));
            ((TextView) findViewById(R.id.textView50n)).setText("Violación Menor");
        } else {
            ((LinearLayout) findViewById(R.id.linear50)).setVisibility(View.GONE);

        }

        if (violacion_inconsciencia > 0) {
            ((TextView) findViewById(R.id.textView51)).setText(String.valueOf(violacion_inconsciencia));
            ((TextView) findViewById(R.id.textView51n)).setText("Violación Inconsciencia");
        } else {
            ((LinearLayout) findViewById(R.id.linear51)).setVisibility(View.GONE);

        }

        if (violacion_sexual > 0) {
            ((TextView) findViewById(R.id.textView52)).setText(String.valueOf(violacion_sexual));
            ((TextView) findViewById(R.id.textView52n)).setText("Violación Sexual");
        } else {
            ((LinearLayout) findViewById(R.id.linear52)).setVisibility(View.GONE);

        }

        if (violacion_menor_tentativa > 0) {
            ((TextView) findViewById(R.id.textView53)).setText(String.valueOf(violacion_menor_tentativa));
            ((TextView) findViewById(R.id.textView53n)).setText("Violación Menor Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear53)).setVisibility(View.GONE);

        }

        if (violacion_tentativa > 0) {
            ((TextView) findViewById(R.id.textView54)).setText(String.valueOf(violacion_tentativa));
            ((TextView) findViewById(R.id.textView54n)).setText("Violación Tentativa");
        } else {
            ((LinearLayout) findViewById(R.id.linear54)).setVisibility(View.GONE);

        }

        if (violacion_domicilio > 0) {
            ((TextView) findViewById(R.id.textView55)).setText(String.valueOf(violacion_domicilio));
            ((TextView) findViewById(R.id.textView55n)).setText("Violación Domicilio");
        } else {
            ((LinearLayout) findViewById(R.id.linear55)).setVisibility(View.GONE);

        }

        if (conduccion_ebriedad > 0) {
            ((TextView) findViewById(R.id.textView56)).setText(String.valueOf(conduccion_ebriedad));
            ((TextView) findViewById(R.id.textView56n)).setText("Conducción Ebriedad");
        } else {
            ((LinearLayout) findViewById(R.id.linear56)).setVisibility(View.GONE);

        }

        if (fabricacion_armas > 0) {
            ((TextView) findViewById(R.id.textView57)).setText(String.valueOf(fabricacion_armas));
            ((TextView) findViewById(R.id.textView57n)).setText("Fabricación Armas");
        } else {
            ((LinearLayout) findViewById(R.id.linear57)).setVisibility(View.GONE);

        }

        if (fabricacion_materiales_peligrosos > 0) {
            ((TextView) findViewById(R.id.textView58)).setText(String.valueOf(fabricacion_materiales_peligrosos));
            ((TextView) findViewById(R.id.textView58n)).setText("Fabricación Materiales Peligrosos");
        } else {
            ((LinearLayout) findViewById(R.id.linear58)).setVisibility(View.GONE);

        }

        if (produccion_peligro_comun > 0) {
            ((TextView) findViewById(R.id.textView59)).setText(String.valueOf(produccion_peligro_comun));
            ((TextView) findViewById(R.id.textView59n)).setText("Producción Peligro Común");
        } else {
            ((LinearLayout) findViewById(R.id.linear59)).setVisibility(View.GONE);

        }

        if (produccion_trafico_armas > 0) {
            ((TextView) findViewById(R.id.textView60)).setText(String.valueOf(produccion_trafico_armas));
            ((TextView) findViewById(R.id.textView60n)).setText("Producción Tráfico Armas");
        } else {
            ((LinearLayout) findViewById(R.id.linear60)).setVisibility(View.GONE);

        }

        if (trafico_microcomercializacion > 0) {
            ((TextView) findViewById(R.id.textView61)).setText(String.valueOf(trafico_microcomercializacion));
            ((TextView) findViewById(R.id.textView61n)).setText("Tráfico Microcomercialización");
        } else {
            ((LinearLayout) findViewById(R.id.linear61)).setVisibility(View.GONE);

        }

        if (trafico_insumos_quimicos > 0) {
            ((TextView) findViewById(R.id.textView62)).setText(String.valueOf(trafico_insumos_quimicos));
            ((TextView) findViewById(R.id.textView62n)).setText("Tráfico Insumos Químicos");
        } else {
            ((LinearLayout) findViewById(R.id.linear62)).setVisibility(View.GONE);

        }

        if (violacion_medidas_sanitarias > 0) {
            ((TextView) findViewById(R.id.textView63)).setText(String.valueOf(violacion_medidas_sanitarias));
            ((TextView) findViewById(R.id.textView63n)).setText("Violación Medidas Sanitarias");
        } else {
            ((LinearLayout) findViewById(R.id.linear63)).setVisibility(View.GONE);

        }

        if (encubrimiento_personal > 0) {
            ((TextView) findViewById(R.id.textView64)).setText(String.valueOf(encubrimiento_personal));
            ((TextView) findViewById(R.id.textView64n)).setText("Encubrimiento Personal");
        } else {
            ((LinearLayout) findViewById(R.id.linear64)).setVisibility(View.GONE);

        }

        if (fuga_accidente > 0) {
            ((TextView) findViewById(R.id.textView65)).setText(String.valueOf(fuga_accidente));
            ((TextView) findViewById(R.id.textView65n)).setText("Fuga Accidente");
        } else {
            ((LinearLayout) findViewById(R.id.linear65)).setVisibility(View.GONE);

        }

        if (resistencia_autoridad > 0) {
            ((TextView) findViewById(R.id.textView66)).setText(String.valueOf(resistencia_autoridad));
            ((TextView) findViewById(R.id.textView66n)).setText("Resistencia Autoridad");
        } else {
            ((LinearLayout) findViewById(R.id.linear66)).setVisibility(View.GONE);

        }

        // Configurar el gráfico de barras horizontales
        HorizontalBarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Configurar los datos para el gráfico en porcentaje
        List<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        int index = 0;

        if (porcentajeAgresionesMujeres > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeAgresionesMujeres, "Agresiones a Mujeres"));
            labels.add("Agresiones a Mujeres");
        }
        if (porcentajeAutoaborto > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeAutoaborto, "Autoaborto"));
            labels.add("Autoaborto");
        }
        if (porcentajeExposicionPeligro > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeExposicionPeligro, "Exposición al Peligro"));
            labels.add("Exposición al Peligro");
        }
        if (porcentajeFeminicidio > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeFeminicidio, "Feminicidio"));
            labels.add("Feminicidio");
        }
        if (porcentajeHomicidioCalificado > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHomicidioCalificado, "Homicidio Calificado"));
            labels.add("Homicidio Calificado");
        }
        if (porcentajeHomicidioTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHomicidioTentativa, "Tentativa de Homicidio"));
            labels.add("Tentativa de Homicidio");
        }
        if (porcentajeHomicidioCulposo > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHomicidioCulposo, "Homicidio Culposo"));
            labels.add("Homicidio Culposo");
        }
        if (porcentajeHomicidioSimple > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHomicidioSimple, "Homicidio Simple"));
            labels.add("Homicidio Simple");
        }
        if (porcentajeHomicidioSimpleTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHomicidioSimpleTentativa, "Tentativa de Homicidio Simple"));
            labels.add("Tentativa de Homicidio Simple");
        }
        if (porcentajeInfanticidio > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeInfanticidio, "Infanticidio"));
            labels.add("Infanticidio");
        }
        if (porcentajeLesionesCulposas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeLesionesCulposas, "Lesiones Culposas"));
            labels.add("Lesiones Culposas");
        }
        if (porcentajeLesionesCulposasAgravadas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeLesionesCulposasAgravadas, "Lesiones Culposas Agravadas"));
            labels.add("Lesiones Culposas Agravadas");
        }
        if (porcentajeLesionesDolosas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeLesionesDolosas, "Lesiones Dolosas"));
            labels.add("Lesiones Dolosas");
        }
        if (porcentajeLesionesGraves > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeLesionesGraves, "Lesiones Graves"));
            labels.add("Lesiones Graves");
        }
        if (porcentajeLesionesLeves > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeLesionesLeves, "Lesiones Leves"));
            labels.add("Lesiones Leves");
        }
        if (porcentajeLesionesLevesAgravadas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeLesionesLevesAgravadas, "Lesiones Leves Agravadas"));
            labels.add("Lesiones Leves Agravadas");
        }
        if (porcentajeLesionesLevesTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeLesionesLevesTentativa, "Tentativa de Lesiones Leves"));
            labels.add("Tentativa de Lesiones Leves");
        }
        if (porcentajeMaltrato > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeMaltrato, "Maltrato"));
            labels.add("Maltrato");
        }
        if (porcentajeParricidio > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeParricidio, "Parricidio"));
            labels.add("Parricidio");
        }
        if (porcentajeSicariato > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeSicariato, "Sicariato"));
            labels.add("Sicariato");
        }
        if (porcentajeOtros > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeOtros, "Otros"));
            labels.add("Otros");
        }
        if (porcentajeTraficoDrogas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeTraficoDrogas, "Tráfico de Drogas"));
            labels.add("Tráfico de Drogas");
        }
        if (porcentajeApropiacionIlicita > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeApropiacionIlicita, "Apropiación Ilícita"));
            labels.add("Apropiación Ilícita");
        }
        if (porcentajeChantaje > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeChantaje, "Chantaje"));
            labels.add("Chantaje");
        }
        if (porcentajeDanosAgravados > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeDanosAgravados, "Daños Agravados"));
            labels.add("Daños Agravados");
        }
        if (porcentajeExtorsion > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeExtorsion, "Extorsión"));
            labels.add("Extorsión");
        }
        if (porcentajeExtorsionAgravada > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeExtorsionAgravada, "Extorsión Agravada"));
            labels.add("Extorsión Agravada");
        }
        if (porcentajeHurtoAgravado > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHurtoAgravado, "Hurto Agravado"));
            labels.add("Hurto Agravado");
        }
        if (porcentajeHurtoAgravadoTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHurtoAgravadoTentativa, "Tentativa de Hurto Agravado"));
            labels.add("Tentativa de Hurto Agravado");
        }
        if (porcentajeHurtoGanado > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHurtoGanado, "Hurto de Ganado"));
            labels.add("Hurto de Ganado");
        }
        if (porcentajeHurtoSimple > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHurtoSimple, "Hurto Simple"));
            labels.add("Hurto Simple");
        }
        if (porcentajeHurtoSimpleTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHurtoSimpleTentativa, "Tentativa de Hurto Simple"));
            labels.add("Tentativa de Hurto Simple");
        }
        if (porcentajeHurtoSimpleDano > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeHurtoSimpleDano, "Hurto Simple con Daño"));
            labels.add("Hurto Simple con Daño");
        }
        if (porcentajeReceptacionAgravada > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeReceptacionAgravada, "Receptación Agravada"));
            labels.add("Receptación Agravada");
        }
        if (porcentajeRobo > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeRobo, "Robo"));
            labels.add("Robo");
        }
        if (porcentajeRoboAgravado > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeRoboAgravado, "Robo Agravado"));
            labels.add("Robo Agravado");
        }
        if (porcentajeRoboGanado > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeRoboGanado, "Robo de Ganado"));
            labels.add("Robo de Ganado");
        }
        if (porcentajeRoboTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeRoboTentativa, "Tentativa de Robo"));
            labels.add("Tentativa de Robo");
        }
        if (porcentajeUsurpacionAgravada > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeUsurpacionAgravada, "Usurpación Agravada"));
            labels.add("Usurpación Agravada");
        }
        if (porcentajeAcoso > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeAcoso, "Acoso"));
            labels.add("Acoso");
        }
        if (porcentajeAcosoSexual > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeAcosoSexual, "Acoso Sexual"));
            labels.add("Acoso Sexual");
        }
        if (porcentajeChantajeSexual > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeChantajeSexual, "Chantaje Sexual"));
            labels.add("Chantaje Sexual");
        }
        if (porcentajeCoaccion > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeCoaccion, "Coacción"));
            labels.add("Coacción");
        }
        if (porcentajeExhibicionesObscenas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeExhibicionesObscenas, "Exhibiciones Obscenas"));
            labels.add("Exhibiciones Obscenas");

        }
        if (porcentajeFavorecimientoProstitucion > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeFavorecimientoProstitucion, "Favorecimiento de la Prostitución"));
            labels.add("Favorecimiento de Prostituciòn");

        }
        if (porcentajeProposicionesSexuales > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeProposicionesSexuales, "Proposiciones Sexuales"));
            labels.add("Proposiciones Sexuales");

        }
        if (porcentajeSecuestro > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeSecuestro, "Secuestro"));

            labels.add("Secuestro");
        }
        if (porcentajeTocamientosMenores > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeTocamientosMenores, "Tocamientos a Menores"));

            labels.add("Tocamientos a Menores");
        }
        if (porcentajeTocamientosSinConsentimiento > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeTocamientosSinConsentimiento, "Tocamientos sin Consentimiento"));
            labels.add("Tocamientos sin Consentimiento");
        }
        if (porcentajeViolacionMenor > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeViolacionMenor, "Violación a Menor"));
            labels.add("Violación a Menor");
        }
        if (porcentajeViolacionInconsciencia > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeViolacionInconsciencia, "Violación por Inconsciencia"));
            labels.add("Violación por Inconsciencia");
        }
        if (porcentajeViolacionSexual > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeViolacionSexual, "Violación Sexual"));
            labels.add("Violación Sexual");
        }
        if (porcentajeViolacionMenorTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeViolacionMenorTentativa, "Tentativa de Violación a Menor"));
            labels.add("Tentativa de Violación a Menor");
        }
        if (porcentajeViolacionTentativa > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeViolacionTentativa, "Tentativa de Violación"));
            labels.add("Tentativa de Violación");
        }
        if (porcentajeViolacionDomicilio > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeViolacionDomicilio, "Violación de Domicilio"));
            labels.add("Violación de Domicilio");
        }
        if (porcentajeConduccionEbriedad > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeConduccionEbriedad, "Conducción en Ebriedad"));
            labels.add("Conducción en Ebriedad");
        }
        if (porcentajeFabricacionArmas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeFabricacionArmas, "Fabricación de Armas"));
            labels.add("Fabricación de Armas");
        }
        if (porcentajeFabricacionMaterialesPeligrosos > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeFabricacionMaterialesPeligrosos, "Fabricación de Materiales Peligrosos"));
            labels.add("Fabricación de Materiales Peligrosos");
        }
        if (porcentajeProduccionPeligroComun > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeProduccionPeligroComun, "Producción de Peligro Común"));
            labels.add("Producción de Peligro Común");
        }
        if (porcentajeProduccionTraficoArmas > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeProduccionTraficoArmas, "Producción y Tráfico de Armas"));
            labels.add("Producción y Tráfico de Armas");
        }
        if (porcentajeTraficoMicrocomercializacion > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeTraficoMicrocomercializacion, "Tráfico y Microcomercialización"));
            labels.add("Tráfico y Microcomercialización");
        }
        if (porcentajeTraficoInsumosQuimicos > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeTraficoInsumosQuimicos, "Tráfico de Insumos Químicos"));
            labels.add("Tráfico de Insumos Químicos");
        }
        if (porcentajeViolacionMedidasSanitarias > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeViolacionMedidasSanitarias, "Violación de Medidas Sanitarias"));
            labels.add("Violación de Medidas Sanitarias");
        }
        if (porcentajeEncubrimientoPersonal > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeEncubrimientoPersonal, "Encubrimiento Personal"));
            labels.add("Encubrimiento Personal");
        }
        if (porcentajeFugaAccidente > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeFugaAccidente, "Fuga tras Accidente"));
            labels.add("Fuga tras Accidente");
        }
        if (porcentajeResistenciaAutoridad > 0) {
            entries.add(new BarEntry(index++, (float) porcentajeResistenciaAutoridad, "Resistencia a la Autoridad"));

            labels.add("Resistencia a la Autoridad");
        }



        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej1));
        colors.add(getResources().getColor(R.color.Pronacej2));
        colors.add(getResources().getColor(R.color.Pronacej3));
        colors.add(getResources().getColor(R.color.Pronacej4));
        colors.add(getResources().getColor(R.color.Pronacej5));
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej7));
        colors.add(getResources().getColor(R.color.Pronacej8));
        colors.add(getResources().getColor(R.color.Pronacej9));
        colors.add(getResources().getColor(R.color.Pronacej10));

        BarDataSet dataSet = new BarDataSet(entries, "Delitos");
        dataSet.setColors(colors);
        BarData data = new BarData(dataSet);
        data.setValueTextSize(8f); // Aumenta el tamaño del texto

        // Nuevo formateador personalizado
        data.setValueFormatter(new ValueFormatter() {
            private DecimalFormat mFormat = new DecimalFormat("0.0");
            @Override
            public String getFormattedValue(float value) {
                return mFormat.format(value) + "%";
            }
        });

        barChart.setData(data);

        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(4f);
        barChart.getDescription().setEnabled(false);
        barChart.setExtraLeftOffset(10f);
        barChart.setExtraRightOffset(50f);

        // Configurar el eje X
        XAxis xAxis = barChart.getXAxis();

        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(labels.size());

        // Configurar el eje Y (izquierda y derecha)
        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setAxisMinimum(0f);

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        barChart.setData(data);
        barChart.invalidate();
    }
}