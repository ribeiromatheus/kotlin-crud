package com.example.productregistration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.productregistration.connection.ProductDatabase
import com.example.productregistration.model.Product
import kotlinx.android.synthetic.main.activity_registration_activity.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_activity)

        btnSubmit.setOnClickListener {
            create()

            finish()
        }
    }

    private fun create() {
        val name = edtName.text.toString()
        val price = edtPrice.text.toString()

        val product = Product(name, price.toFloat())

        val createProduct = ProductDatabase.getInstance(this).productDAO()

        createProduct.create(product)
    }
}