package com.example.inventoryapp

import java.util.Date

data class Sale(
    val productCode: String = "",
    val quantity: Int = 0,
    val date: String = "" // Podrías usar un tipo `Date` si prefieres trabajar con fechas
)
