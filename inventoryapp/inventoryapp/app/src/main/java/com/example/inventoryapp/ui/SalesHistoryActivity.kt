package com.example.inventoryapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class SalesHistoryActivity : AppCompatActivity() {

    private lateinit var salesListView: ListView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_history)

        salesListView = findViewById(R.id.sales_list_view)

        // Cargar el historial de ventas desde Firestore
        fetchSalesHistory()

        // Añadir botón de regresar
        val backButton: Button = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish() // Cierra la actividad y regresa a la anterior
        }
    }

    // Función para cargar el historial de ventas desde Firebase Firestore
    private fun fetchSalesHistory() {
        val salesHistory = mutableListOf<String>()

        // Accede a la colección de ventas en Firestore
        db.collection("sales").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val sale = document.toObject(Sale::class.java)
                    val saleDetails = "Producto: ${sale.productCode}, Cantidad: ${sale.quantity}, Fecha: ${sale.date}"
                    salesHistory.add(saleDetails) // Añade la venta al historial
                }

                // Si hay ventas, mostramos los datos en el ListView
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, salesHistory)
                salesListView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                // Si hay un error al cargar las ventas
                salesHistory.add("No se pudo cargar el historial de ventas.")
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, salesHistory)
                salesListView.adapter = adapter
            }
    }
}
