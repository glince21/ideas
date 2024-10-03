package com.example.inventuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HistorialVentasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_ventas)

        val btnRegresar = findViewById<Button>(R.id.btn_regresar)
        btnRegresar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
