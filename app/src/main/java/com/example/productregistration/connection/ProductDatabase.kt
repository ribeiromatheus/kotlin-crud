package com.example.productregistration.connection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productregistration.dao.ProductDAO
import com.example.productregistration.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO

    companion object {
        private var database: ProductDatabase? = null

        private val DATABASE = "Product"

        fun getInstance(context: Context): ProductDatabase {
            return database ?: createDatabase(context).also { database = it }
        }

        private fun createDatabase(context: Context): ProductDatabase {
            return Room
                .databaseBuilder(context, ProductDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }
}