package com.example.listadecompras.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.data.db.ShoppingDatabase
import com.example.listadecompras.data.db.entities.ShoppingItem
import com.example.listadecompras.data.other.ShoppingItemAdapter
import com.example.listadecompras.data.repositories.ShoppingRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val rvShoppingItems = findViewById<RecyclerView>(R.id.rvShoppingItems)
        val btNewShoppingItem = findViewById<FloatingActionButton>(R.id.newShoppingItem)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        btNewShoppingItem.setOnClickListener {
            AddShoppingItemDialog(this,
                object: AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                        val algo = 23
                    }
                }).show()
        }

    }
}