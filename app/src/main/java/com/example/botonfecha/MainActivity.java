package com.example.botonfecha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button btnFecha;
    TextView txtFecha;

    //donde se guardan las colas
    RequestQueue queue;

    String url = "https://abarral.colexio-karbo.com/2025-27/fecha/prueba.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnFecha = findViewById(R.id.btnFecha);
        txtFecha = findViewById(R.id.txtFecha);
        //donde se guardan las colas, crea una nueva en este contexto(main activity)
        queue = Volley.newRequestQueue(this);


        btnFecha.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                //limpiamos el caché para no mostrar respuestas viejgas
                queue.getCache().clear();

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>(){


                            @Override
                            public void onResponse(String s) {
                                txtFecha.setText(s);
                            }
                        },
                        new Response.ErrorListener(){

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                txtFecha.setText("Error: "+volleyError.getMessage());
                            }
                        }
                );

                queue.add(request);

            }
        });
    }
}

