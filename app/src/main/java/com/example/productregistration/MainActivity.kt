package com.example.productregistration

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.productregistration.connection.ProductDatabase
import com.example.productregistration.model.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        lstProducts.setOnItemLongClickListener { _, _, position, _ ->
            showAlertDialog(position)
        }
    }

    override fun onResume() {
        super.onResume()

        loadList("name", 1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ascName -> loadList("name", 1)
            R.id.descName -> loadList("name", 0)
            R.id.ascPrice -> loadList("price", 1)
            R.id.descPrice -> loadList("price", 0)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadList(attribute: String, sort: Int) {
        val products: List<Product> =
            ProductDatabase
                .getInstance(this)
                .productDAO()
                .read(attribute, sort)

        val productsList = ArrayAdapter(this, android.R.layout.simple_list_item_1, products)

        lstProducts.adapter = productsList
    }

    private fun deleteProduct(position: Int) {
        val product = lstProducts.getItemAtPosition(position) as Product

        ProductDatabase.getInstance(this).productDAO().delete(product)

        loadList("name", 1)
    }

    private fun showAlertDialog(position: Int): Boolean {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Editar ou Excluir Produto")
        alertDialog.setMessage("Deseja excluir ou editar o produto?")
        alertDialog.setPositiveButton("Excluir") { _: DialogInterface, _: Int -> deleteProduct(position) }
        alertDialog.setNegativeButton("Editar", null)

        alertDialog.show()

        return true
    }
}