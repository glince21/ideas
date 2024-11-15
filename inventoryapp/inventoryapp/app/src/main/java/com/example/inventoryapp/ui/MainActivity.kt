package com.example.inventoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de botones
        val manageProductsButton: Button = findViewById(R.id.btn_manage_products)
        val stockButton: Button = findViewById(R.id.btn_stock)
        val salesHistoryButton: Button = findViewById(R.id.btn_sales_history)

        // Cargar productos desde Firestore o desde la memoria
        ProductList.loadProductsFromDatabase()

        // Configurar acción para el botón de "Gestionar productos"
        manageProductsButton.setOnClickListener {
            startActivity(Intent(this, ManageProductsActivity::class.java))
        }

        // Configurar acción para el botón de "Stock"
        stockButton.setOnClickListener {
            // Aquí puedes agregar una validación si no hay productos en la base de datos
            val products = ProductList.fetchProducts() // Obtener los productos correctamente
            if (products.isEmpty()) {
                Toast.makeText(this, "No hay productos en el inventario", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, StockActivity::class.java))
            }
        }

        // Configurar acción para el botón de "Historial de ventas"
        salesHistoryButton.setOnClickListener {
            // Aquí también puedes agregar alguna validación si es necesario
            startActivity(Intent(this, SalesHistoryActivity::class.java))
        }
    }
}
