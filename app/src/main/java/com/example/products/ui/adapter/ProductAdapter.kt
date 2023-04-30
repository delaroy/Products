package com.example.products.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.products.databinding.ListItemHeaderBinding
import com.example.products.databinding.ListItemProductBinding


class ProductAdapter(val productDetails: ProductDetailClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var items: ArrayList<ListItem> = ArrayList()

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType) {
           ListItem.TYPE_HEADER -> {
               HeaderViewHolder(ListItemHeaderBinding.inflate(
                   LayoutInflater.from(parent.context), parent, false))
           }
           ListItem.TYPE_PRODUCT -> {
               ProductViewHolder(ListItemProductBinding.inflate(
                   LayoutInflater.from(parent.context), parent, false))
           }
           else -> throw IllegalStateException("unsupported item type")
       }
   }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ListItem.TYPE_HEADER -> {
                val header = items[position] as HeaderItem
                val hHolder = holder as HeaderViewHolder
                header.let {
                    hHolder.apply {
                        bind(header)
                        itemView.tag = header
                    }
                }
            }
            ListItem.TYPE_PRODUCT -> {
                val product: ProductItem = items[position] as ProductItem
                val pHolder = holder as ProductViewHolder
                product.let {
                    pHolder.apply {
                        bind(product)
                        itemView.tag = product
                    }
                }

                holder.itemView.setOnClickListener {
                    productDetails.clickOnItem(
                        product
                    )
                }
            }
            else -> throw java.lang.IllegalStateException("unsupported item type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    fun submitList(itemList: ArrayList<ListItem>?){
        items = itemList!!
        notifyDataSetChanged()
    }


    inner class HeaderViewHolder(private val binding: ListItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HeaderItem) {
            binding.apply {
                listItem = item
                executePendingBindings()
            }
        }
    }

    inner class ProductViewHolder(private val binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductItem) {
            binding.apply {
                listItem = item
                executePendingBindings()
            }
        }
    }
}


interface ProductDetailClick {
    fun clickOnItem(data: ProductItem)
}