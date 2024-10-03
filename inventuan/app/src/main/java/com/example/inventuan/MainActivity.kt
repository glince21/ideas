package com.example.inventuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGestionar = findViewById<Button>(R.id.btn_gestionar_productos)
        val btnStock = findViewById<Button>(R.id.btn_stock)
        val btnHistorial = findViewById<Button>(R.id.btn_historial_ventas)

        btnGestionar.setOnClickListener {
            startActivity(Intent(this, GestionarProductosActivity::class.java))
        }

        btnStock.setOnClickListener {
            startActivity(Intent(this, StockActivity::class.java))
        }

        btnHistorial.setOnClickListener {
            startActivity(Intent(this, HistorialVentasActivity::class.java))
        }
    }
}
