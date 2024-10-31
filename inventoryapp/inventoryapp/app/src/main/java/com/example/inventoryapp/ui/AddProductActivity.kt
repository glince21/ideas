package com.example.inventoryapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    private lateinit var codeEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var quantityEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        codeEditText = findViewById(R.id.edit_code)
        nameEditText = findViewById(R.id.edit_name)
        quantityEditText = findViewById(R.id.edit_quantity)

        val saveButton: Button = findViewById(R.id.btn_save)
        saveButton.setOnClickListener {
            val code = codeEditText.text.toString()
            val name = nameEditText.text.toString()
            val quantity = quantityEditText.text.toString().toIntOrNull()

            if (code.isNotEmpty() && name.isNotEmpty() && quantity != null) {
                ProductList.addProduct(Product(code, name, quantity))
                Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
