package com.example.listadecompras.ui.shoppinglist

import com.example.listadecompras.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}