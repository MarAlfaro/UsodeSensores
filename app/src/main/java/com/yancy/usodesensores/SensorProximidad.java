package com.yancy.usodesensores;


import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class SensorProximidad extends AppCompatActivity {
    Sensor miSensor;
    SensorManager administradorDeSensores;
    SensorEventListener disparadorEventoSensor;
    Button btnValor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_proximidad);
        btnValor = (Button) findViewById(R.id.btnValor);
        //Inicializar mi sensor
        administradorDeSensores = (SensorManager) getSystemService(SENSOR_SERVICE);
        miSensor = administradorDeSensores.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(miSensor==null){
            Toast.makeText(this, "Su dispositivo no tiene el sensor de proximidad", Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(this, "Sensor de Proximidad detectado", Toast.LENGTH_LONG).show();
        }
        disparadorEventoSensor = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                //Colocar el codigo de lo que queremos que haga nuestra app
                //cuando se acerque o se aleje el objeto del sensor

                if(sensorEvent.values[0] < miSensor.getMaximumRange()){
                    //Condicion para determinar cuando se acerque
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(61,27,25));
                    btnValor.setBackgroundColor(Color.rgb(122,54,50));
                    btnValor.setTextColor(Color.rgb(224,99,92));
                }else{
                    //Que es lo que va hacer cuando se aleje
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(224,5,228));
                    btnValor.setBackgroundColor(Color.rgb(186,4,171));
                    btnValor.setTextColor(Color.rgb(59,1,54));
                }
                btnValor.setText("Valor del sensor: "+
                        sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        iniciarSensor();
    }
    public void iniciarSensor(){
        administradorDeSensores.registerListener(disparadorEventoSensor,miSensor,(2000*1000));
    }
    public void detenerSensor(){
        administradorDeSensores.unregisterListener(disparadorEventoSensor);
    }
    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }
    @Override
    protected void onResume() {
        iniciarSensor();
        super.onResume();
    }
}

