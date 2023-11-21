package com.example.listadecompras.data.other

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.data.db.entities.ShoppingItem
import com.example.listadecompras.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items:  List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingItemAdapter.ShoppingViewHolder {

        Log.i("ShoppingItemAdapter", "Chamando onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemAdapter.ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.tvName.text = currentShoppingItem.name
        holder.tvAmout.text = "${currentShoppingItem.amount}"

        holder.ivDelete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        holder.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        holder.ivMinus.setOnClickListener {
            if(currentShoppingItem.amount > 0)
            {
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName : TextView
        val tvAmout : TextView
        val ivDelete : ImageView
        val ivPlus: ImageView
        val ivMinus: ImageView

        init {
            tvName = view.findViewById(R.id.tvName)
            tvAmout = view.findViewById(R.id.tvAmout)
            ivDelete = view.findViewById(R.id.ivDelete)
            ivPlus = view.findViewById(R.id.ivPlus)
            ivMinus = view.findViewById(R.id.ivMinus)
        }
    }
}