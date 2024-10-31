package com.example.inventoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manageProductsButton: Button = findViewById(R.id.btn_manage_products)
        val stockButton: Button = findViewById(R.id.btn_stock)
        val salesHistoryButton: Button = findViewById(R.id.btn_sales_history)

        manageProductsButton.setOnClickListener {
            startActivity(Intent(this, ManageProductsActivity::class.java))
        }

        stockButton.setOnClickListener {
            startActivity(Intent(this, StockActivity::class.java))
        }

        salesHistoryButton.setOnClickListener {
            startActivity(Intent(this, SalesHistoryActivity::class.java))
        }
    }
}

