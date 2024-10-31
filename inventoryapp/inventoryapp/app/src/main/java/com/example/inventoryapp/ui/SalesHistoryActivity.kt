package com.example.inventoryapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SalesHistoryActivity : AppCompatActivity() {

    private lateinit var salesHistoryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_history)

        salesHistoryTextView = findViewById(R.id.text_view_sales_history)

        // Aquí podrías cargar datos del historial de ventas
        salesHistoryTextView.text = "Historial de ventas estará disponible aquí."

        // Añadir botón de regresar
        val backButton: Button = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish() // Cierra la actividad y regresa a la anterior
        }
    }
}
