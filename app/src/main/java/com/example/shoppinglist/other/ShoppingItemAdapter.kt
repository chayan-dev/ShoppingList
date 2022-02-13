package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    lateinit var tvName:TextView
    lateinit var ivdelete:ImageView

    class ShoppingViewHolder(val binding: shopping ): RecyclerView.ViewHolder()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.shoppping_item,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

        tvName=holder.itemView.findViewById(R.id.tvName)
        ivdelete=holder.itemView.findViewById<ImageView>(R.id.ivDelete)


        val curShoppingItem=items[position]

        holder.itemView.tvName.text="${curShoppingItem.amount}"

        holder.itemView.ivdelete.setOnClickListener{
            viewModel.delete(curShoppingItem)
        }
        holder.itemView.ivPLus.setOnClickListener{
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        holder.itemView.ivMinus.SetOnClickListener{
            if(curShopping.amount>0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}