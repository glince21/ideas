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
    private lateinit var categoryEditText: EditText // Campo para la categoría

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        codeEditText = findViewById(R.id.edit_code)
        nameEditText = findViewById(R.id.edit_name)
        quantityEditText = findViewById(R.id.edit_quantity)
        categoryEditText = findViewById(R.id.edit_category) // Inicializa el campo de categoría

        val saveButton: Button = findViewById(R.id.btn_save)
        saveButton.setOnClickListener {
            val code = codeEditText.text.toString()
            val name = nameEditText.text.toString()
            val quantity = quantityEditText.text.toString().toIntOrNull()
            val category = categoryEditText.text.toString() // Captura la categoría

            // Validación de campos
            if (code.isNotEmpty() && name.isNotEmpty() && quantity != null && category.isNotEmpty()) {
                val newProduct = Product(code, name, quantity, category)
                val wasAdded = ProductList.addProduct(newProduct)

                if (wasAdded) {
                    Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()

                    // Verificación de stock bajo
                    if (quantity < 5) { // Puedes ajustar el umbral de stock bajo
                        Toast.makeText(this, "Advertencia: el producto tiene bajo stock", Toast.LENGTH_LONG).show()
                    }

                    finish()
                } else {
                    Toast.makeText(this, "Error: Producto con el mismo código ya existe", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Añadir botón de regresar
        val backButton: Button = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }
    }
}
