package com.example.inventoryapp

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StockActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        listView = findViewById(R.id.list_view)
        updateProductList()

        // Añadir botón de regresar
        val backButton: Button = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun updateProductList() {
        // Usar fetchProducts() en lugar de getProducts()
        val products = ProductList.fetchProducts().map { product ->
            "${product.code} - ${product.name} (Categoría: ${product.category}): ${product.quantity}"
        }

        // Mostrar advertencias de stock bajo
        val lowStockProducts = ProductList.fetchProducts().filter { it.quantity < 5 }
        if (lowStockProducts.isNotEmpty()) {
            Toast.makeText(this, "Alerta: hay productos con bajo stock", Toast.LENGTH_LONG).show()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products)
        listView.adapter = adapter
    }
}
