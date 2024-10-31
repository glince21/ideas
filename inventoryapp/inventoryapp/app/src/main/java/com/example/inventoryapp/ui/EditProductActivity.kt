package com.example.inventoryapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProductActivity : AppCompatActivity() {

    private lateinit var codeEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var quantityEditText: EditText
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val productCode = intent.getStringExtra("PRODUCT_CODE")
        product = ProductList.products.find { it.code == productCode } ?: return

        codeEditText = findViewById(R.id.edit_code)
        nameEditText = findViewById(R.id.edit_name)
        quantityEditText = findViewById(R.id.edit_quantity)

        codeEditText.setText(product.code)
        nameEditText.setText(product.name)
        quantityEditText.setText(product.quantity.toString())

        val saveButton: Button = findViewById(R.id.btn_save)
        saveButton.setOnClickListener {
            val newCode = codeEditText.text.toString()
            val newName = nameEditText.text.toString()
            val newQuantity = quantityEditText.text.toString().toIntOrNull()

            if (newCode.isNotEmpty() && newName.isNotEmpty() && newQuantity != null) {
                ProductList.updateProduct(product, newCode, newName, newQuantity)
                Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

