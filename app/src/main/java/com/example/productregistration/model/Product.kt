package com.example.productregistration.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val name: String,
    val price: Float,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {

    override fun toString(): String {
        return "$name - R$ ${String.format("%,.2f", price)}"
    }
}