package com.example.inventoryapp

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

object ProductList {
    private val db = FirebaseFirestore.getInstance()
    val products = mutableListOf<Product>()

    // Función para agregar un producto con validación de duplicados y de stock bajo
    fun addProduct(product: Product): Boolean {
        // Validar si el producto ya existe en la lista de productos
        if (products.any { it.code == product.code }) {
            return false // Si ya existe un producto con el mismo código, no se añade
        } else {
            products.add(product)
            addProductToDatabase(product)
            checkLowStock(product) // Verifica si el producto tiene stock bajo
            return true
        }
    }

    // Función para obtener la lista de productos desde la memoria (ya cargados de Firestore)
    fun fetchProducts(): List<Product> {
        return products.toList()
    }

    // Función para actualizar un producto en la lista y en Firebase
    fun updateProduct(oldProduct: Product, newCode: String, newName: String, newQuantity: Int, newCategory: String) {
        // Actualiza el producto en la lista en memoria
        oldProduct.code = newCode
        oldProduct.name = newName
        oldProduct.quantity = newQuantity
        oldProduct.category = newCategory

        // Luego, actualiza el producto en Firebase
        updateProductInDatabase(oldProduct)
        checkLowStock(oldProduct) // Verifica stock bajo después de actualizar
    }

    // Función para eliminar un producto de la lista y de Firebase
    fun removeProduct(product: Product) {
        products.remove(product) // Elimina el producto de la memoria
        removeProductFromDatabase(product) // Elimina el producto de Firebase
    }

    // Función para agregar un producto a Firebase
    private fun addProductToDatabase(product: Product) {
        db.collection("products").add(product)
            .addOnSuccessListener {
                // Producto añadido con éxito en Firebase
                println("Producto añadido en Firebase.")
            }
            .addOnFailureListener { e ->
                // Manejo de error si no se puede agregar el producto
                println("Error al añadir producto: ${e.message}")
            }
    }

    // Función para actualizar un producto en Firebase
    private fun updateProductInDatabase(product: Product) {
        db.collection("products")
            .whereEqualTo("code", product.code)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("products").document(document.id).set(product)
                }
            }
            .addOnFailureListener { e ->
                // Manejo de error si no se puede actualizar el producto
                println("Error al actualizar producto: ${e.message}")
            }
    }

    // Función para eliminar un producto de Firebase
    private fun removeProductFromDatabase(product: Product) {
        db.collection("products")
            .whereEqualTo("code", product.code)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("products").document(document.id).delete()
                }
            }
            .addOnFailureListener { e ->
                // Manejo de error si no se puede eliminar el producto
                println("Error al eliminar producto: ${e.message}")
            }
    }

    // Función para cargar productos desde Firebase y guardarlos en la lista en memoria
    fun loadProductsFromDatabase() {
        db.collection("products").get()
            .addOnSuccessListener { result ->
                products.clear() // Limpiar la lista de productos en memoria antes de cargar nuevos datos
                for (document in result) {
                    val product = document.toObject<Product>()
                    products.add(product) // Añadir el producto a la lista
                    checkLowStock(product) // Verificar stock bajo para cada producto
                }
            }
            .addOnFailureListener { e ->
                // Manejo de error si no se pueden cargar los productos
                println("Error al cargar productos desde Firestore: ${e.message}")
            }
    }

    // Función para añadir una venta al historial en Firebase
    fun addSale(sale: Sale) {
        db.collection("sales").add(sale)
            .addOnSuccessListener {
                // Venta añadida con éxito en Firebase
                println("Venta añadida en Firebase.")
            }
            .addOnFailureListener { e ->
                // Manejo de error si no se puede añadir la venta
                println("Error al añadir venta: ${e.message}")
            }
    }

    // Función para obtener el historial de ventas desde Firebase
    fun fetchSalesHistory(onResult: (List<Sale>) -> Unit) {
        db.collection("sales").get()
            .addOnSuccessListener { result ->
                val salesHistory = result.map { it.toObject<Sale>() }
                onResult(salesHistory)
            }
            .addOnFailureListener { e ->
                // Manejo de error si no se puede obtener el historial de ventas
                println("Error al obtener historial de ventas: ${e.message}")
            }
    }

    // Función para verificar si el stock es bajo y notificar al usuario
    private fun checkLowStock(product: Product) {
        if (product.quantity < 5) { // Puedes cambiar el umbral de stock bajo aquí
            // Aquí iría la lógica para notificar al usuario, como mostrar un Toast o notificación
            println("Advertencia: el producto ${product.name} tiene bajo stock.")
        }
    }
}
