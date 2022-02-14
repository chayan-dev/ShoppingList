package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.databinding.ShopppingItemBinding
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

//    lateinit var tvName:TextView
//    lateinit var ivdelete:ImageView

    inner class ShoppingViewHolder(val itemBinding: ShopppingItemBinding ): RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {

        val view=ShopppingItemBinding.inflate(LayoutInflater.from(parent.context))
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {

//        tvName=itemBinding.tvName
//        ivdelete=holder.itemView.findViewById<ImageView>(R.id.ivDelete)


        val curShoppingItem=items[position]

        holder.itemBinding.tvName.text=curShoppingItem.name
        holder.itemBinding.tvAmount.text="${curShoppingItem.amount}"

        holder.itemBinding.ivDelete.setOnClickListener{
            viewModel.delete(curShoppingItem)
        }
        holder.itemBinding.ivPlus.setOnClickListener{
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }
        holder.itemBinding.ivMinus.setOnClickListener{
            if(curShoppingItem.amount>0){
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}