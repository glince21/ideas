package com.example.inventoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ManageProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_products)

        val addButton: Button = findViewById(R.id.btn_add_product)
        val deleteButton: Button = findViewById(R.id.btn_delete_product)
        val backButton: Button = findViewById(R.id.btn_back)

        addButton.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

        deleteButton.setOnClickListener {
            startActivity(Intent(this, DeleteProductActivity::class.java))
        }


        backButton.setOnClickListener {
            finish()
        }
    }
}
