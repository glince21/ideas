package com.example.inventoryapp

object ProductList {
    val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    // Cambiado el nombre del método para evitar la colisión
    fun fetchProducts(): List<Product> {
        return products.toList()
    }

    fun updateProduct(oldProduct: Product, newCode: String, newName: String, newQuantity: Int) {
        oldProduct.code = newCode
        oldProduct.name = newName
        oldProduct.quantity = newQuantity
    }

    fun removeProduct(product: Product) {
        products.remove(product)
    }
}

