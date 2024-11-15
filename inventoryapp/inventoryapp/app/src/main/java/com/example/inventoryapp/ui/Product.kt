package com.example.inventoryapp

data class Product(
    var code: String = "",
    var name: String = "",
    var quantity: Int = 0,
    var category: String = ""
)