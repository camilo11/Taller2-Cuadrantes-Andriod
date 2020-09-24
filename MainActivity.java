package com.example.Taller2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button puntoMedio, distancia, cuadrante;
    private EditText x1, x2, y1, y2;
    public TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x1 = findViewById(R.id.numX1);
        x2 = findViewById(R.id.numX2);
        y1 = findViewById(R.id.numY1);
        y2 = findViewById(R.id.numY2);

        result = findViewById(R.id.txtResult);

        puntoMedio = findViewById(R.id.btnPoint);
        distancia = findViewById(R.id.btnDitance);
        cuadrante = findViewById(R.id.btnQuadrant);

        puntoMedio.setOnClickListener(this);
        distancia.setOnClickListener(this);
        cuadrante.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m1:
                x1.setText(random() + "");
                x2.setText(random() + "");
                y1.setText(random() + "");
                y2.setText(random() + "");
                break;
            case R.id.m2:
                if (TextUtils.isEmpty(x1.getText().toString()) || TextUtils.isEmpty(x2.getText().toString()) ||
                        TextUtils.isEmpty(y1.getText().toString()) || TextUtils.isEmpty(y2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_LONG).show();
                } else {
                    float x01 = Float.parseFloat(x1.getText().toString());
                    float x02 = Float.parseFloat(x2.getText().toString());
                    float y01 = Float.parseFloat(y1.getText().toString());
                    float y02 = Float.parseFloat(y2.getText().toString());

                    result.setText(CalDistancia(x01, x02, y01, y02) + "");
                }
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        if (TextUtils.isEmpty(x1.getText().toString()) || TextUtils.isEmpty(x2.getText().toString()) ||
                TextUtils.isEmpty(y1.getText().toString()) || TextUtils.isEmpty(y2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid Data", Toast.LENGTH_LONG).show();
        } else {

            float x01 = Float.parseFloat(x1.getText().toString());
            float x02 = Float.parseFloat(x2.getText().toString());
            float y01 = Float.parseFloat(y1.getText().toString());
            float y02 = Float.parseFloat(y2.getText().toString());

            switch (view.getId()) {
                case R.id.btnPoint:
                    result.setText("(" + CalMedio(x01, x02) + "," + CalMedio(y01, y02) + ")");
                    break;
                case R.id.btnDitance:
                    result.setText(CalPendiente(x01, x02, y01, y02) + "");
                    break;
                case R.id.btnQuadrant:
                    result.setText("(x1,y1): " + CalCuadrante(x01, y01) + " (x2,y2): " + CalCuadrante(x02, y02));
            }
        }

    }

    public int random() {
        int menor = -50, mayor = 50, r;
        return r = (int) (Math.random() * (menor - mayor + 1) + mayor);
    }

    public float CalMedio(float a, float b) {
        float r = (a + b) / 2;
        return r;
    }

    public float CalPendiente(float a1, float a2, float b1, float b2) {
        float r = (b2 - b1) / (a2 - a1);
        return r;
    }

    public int CalCuadrante(float a, float b) {
        if (a == 0 || b == 0) return 0;
        else if (a > 0 && b > 0) return 1;
        else if (a < 0 && b > 0) return 2;
        else if (a < 0 && b < 0) return 3;
        else return 4;
    }

    public int CalDistancia(float x1, float x2, float y1, float y2) {
        return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }


}