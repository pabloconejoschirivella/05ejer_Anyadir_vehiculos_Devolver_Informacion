package com.example.a05ejer_anyadir_vehiculos_devolver_informacion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a05ejer_anyadir_vehiculos_devolver_informacion.modelos.Bici;
import com.example.a05ejer_anyadir_vehiculos_devolver_informacion.modelos.Coche;
import com.example.a05ejer_anyadir_vehiculos_devolver_informacion.modelos.Moto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    //atributos vista
    private TextView txtCuentaCoches;
    private TextView txtCuentaMotos;
    private TextView txtCuentaBicis;
    private Button btnAnyadirCoche;
    private Button btnAnyadirMoto;
    private Button btnAnyadirBici;

    //Atributos Logica
    private ArrayList<Coche> listaCoche;
    private ArrayList<Moto> listaMoto;
    private ArrayList<Bici> listaBici;



    //Atributos eventos
    //ActivityResultLaunchers
    private ActivityResultLauncher<Intent> launcherCoches;
    private ActivityResultLauncher<Intent> launcherMotos;
    private ActivityResultLauncher<Intent> launcherBicis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVariables();

        inicializaLaunchers();


        btnAnyadirCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_add_coche.class);
                launcherCoches.launch(intent);
            }
        });

        btnAnyadirMoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_add_moto.class);
                launcherMotos.launch(intent);
            }
        });

        btnAnyadirBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_add_bici.class);
                launcherBicis.launch(intent);
            }
        });

    }

    private void inicializaLaunchers() {

        //el for result es porque espera el resultado al cerrar la ventana
        launcherCoches = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //esto se activa cuando se cierra la ventana(onActivityResult)
                        //si la ventana se ha cerrado bien == RESULT_OK
                        if (result.getResultCode() == RESULT_OK){
                            //Si trae info entra
                            if (result.getData()!=null && result.getData().getExtras() != null){
                                Bundle bundle = result.getData().getExtras();
                                //sacar del bundle la info segun el tag
                                Coche coche = (Coche) bundle.getSerializable("CAR");
                                //meter coche en la lista
                                if (coche!= null){
                                    listaCoche.add(coche);
                                    txtCuentaCoches.setText("COCHES: "+listaCoche.size());
                                }

                                Toast.makeText(MainActivity.this, coche.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "ACCION CANCELADA", Toast.LENGTH_SHORT).show();
                        }



                    }
                });

        launcherMotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //esto se activa cuando se cierra la ventana(onActivityResult)
                        //si la ventana se ha cerrado bien == RESULT_OK
                        if (result.getResultCode() == RESULT_OK){
                            //Si trae info entra
                            if (result.getData()!=null && result.getData().getExtras() != null){
                                Bundle bundle = result.getData().getExtras();
                                //sacar del bundle la info segun el tag
                                Moto moto = (Moto) bundle.getSerializable("MOTO");
                                //meter moto en la lista
                                if (moto!=null){
                                    listaMoto.add(moto);

                                    txtCuentaMotos.setText("MOTOS: "+listaMoto.size());
                                }


                                Toast.makeText(MainActivity.this, moto.toString(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "no estan los datos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "ACCION CANCELADA", Toast.LENGTH_SHORT).show();
                        }



                    }
                });

        launcherBicis = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //esto se activa cuando se cierra la ventana(onActivityResult)
                        //si la ventana se ha cerrado bien == RESULT_OK
                        if (result.getResultCode() == RESULT_OK){
                            //Si trae info entra
                            if (result.getData()!=null && result.getData().getExtras() != null){
                                Bundle bundle = result.getData().getExtras();
                                //sacar del bundle la info segun el tag
                                Bici bici = (Bici) bundle.getSerializable("BICI");
                                //meter bici en la lista
                                if (bici!=null){
                                    listaBici.add(bici);
                                    txtCuentaBicis.setText("BICIS: "+listaBici.size());
                                }


                                Toast.makeText(MainActivity.this, bici.toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "ACCION CANCELADA", Toast.LENGTH_SHORT).show();
                        }



                    }
                });

    }

    private void inicializarVariables() {

        txtCuentaCoches = findViewById(R.id.lblCantidadCochesMain);
        txtCuentaMotos = findViewById(R.id.lblCantidadMotosMain);
        txtCuentaBicis = findViewById(R.id.lblCantidadBicisMain);
        btnAnyadirCoche = findViewById(R.id.btnAddCocheMain);
        btnAnyadirMoto = findViewById(R.id.btnAddMotoMain);
        btnAnyadirBici = findViewById(R.id.btnAddBiciMain);

        listaCoche = new ArrayList<>();
        listaMoto = new ArrayList<>();
        listaBici = new ArrayList<>();

    }
}