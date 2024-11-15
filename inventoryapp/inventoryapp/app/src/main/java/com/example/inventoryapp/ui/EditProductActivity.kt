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
    private lateinit var categoryEditText: EditText  // Agregar el campo para categoría
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        // Obtener el código del producto desde el Intent
        val productCode = intent.getStringExtra("PRODUCT_CODE")
        product = ProductList.products.find { it.code == productCode } ?: return

        // Inicializar los campos
        codeEditText = findViewById(R.id.edit_code)
        nameEditText = findViewById(R.id.edit_name)
        quantityEditText = findViewById(R.id.edit_quantity)
        categoryEditText = findViewById(R.id.edit_category)  // Referencia al nuevo campo de categoría

        // Rellenar los campos con los valores actuales del producto
        codeEditText.setText(product.code)
        nameEditText.setText(product.name)
        quantityEditText.setText(product.quantity.toString())
        categoryEditText.setText(product.category)  // Asignar la categoría existente

        // Configurar el botón de guardar
        val saveButton: Button = findViewById(R.id.btn_save)
        saveButton.setOnClickListener {
            // Obtener los nuevos valores
            val newCode = codeEditText.text.toString()
            val newName = nameEditText.text.toString()
            val newQuantity = quantityEditText.text.toString().toIntOrNull()
            val newCategory = categoryEditText.text.toString()  // Obtener la categoría

            // Validar los datos
            if (newCode.isNotEmpty() && newName.isNotEmpty() && newQuantity != null && newCategory.isNotEmpty()) {
                // Actualizar el producto con los nuevos valores
                ProductList.updateProduct(product, newCode, newName, newQuantity, newCategory)
                Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
                finish()  // Cerrar la actividad
            } else {
                // Mostrar mensaje de error si falta algún campo
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
