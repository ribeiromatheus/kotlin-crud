package com.example.productregistration.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.productregistration.model.Product

@Dao
interface ProductDAO {
    @Insert
    fun create(product: Product)

    @Query(
        "SELECT * FROM Product ORDER BY " +
                "CASE WHEN :attribute = 'name' AND :sort = 1 THEN name END ASC, " +
                "CASE WHEN :attribute = 'name' AND :sort = 0 THEN name END DESC, " +
                "CASE WHEN :attribute = 'price' AND :sort = 1 THEN price END ASC, " +
                "CASE WHEN :attribute = 'price' AND :sort = 0 THEN price END DESC"
    )
    fun read(attribute: String, sort: Int): List<Product>

    @Delete
    fun delete(product: Product)
}