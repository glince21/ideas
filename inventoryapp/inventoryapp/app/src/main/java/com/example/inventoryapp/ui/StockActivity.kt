package com.example.inventoryapp

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.ArrayAdapter
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
        // Cambia aquí el nombre de la función de getProducts() a fetchProducts()
        val products = ProductList.fetchProducts().map { "${it.code} - ${it.name}: ${it.quantity}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, products)
        listView.adapter = adapter
    }
}
