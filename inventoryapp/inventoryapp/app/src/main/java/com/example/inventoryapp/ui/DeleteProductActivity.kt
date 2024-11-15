package com.example.inventoryapp

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteProductActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_product)

        listView = findViewById(R.id.list_view)
        updateProductList()

        listView.setOnItemClickListener { _, _, position, _ ->
            val product = ProductList.products[position]
            ProductList.removeProduct(product)
            Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
            updateProductList()
        }

        val backButton: Button = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun updateProductList() {

        val productNames = ProductList.fetchProducts().map { "${it.code} - ${it.name}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productNames)
        listView.adapter = adapter
    }
}
