package com.example.demo.ui.mandobk_shop.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.common.constant.AppConstant
import com.example.demo.common.model.StatusList
import com.example.demo.databinding.ItemNoItemBinding
import com.example.demo.databinding.ItemShopStatusListBinding
import com.example.demo.ui.mandobk_shop.view.adapter.viewholder.NoItemViewHolder
import com.example.demo.ui.mandobk_shop.view.adapter.viewholder.ShopStatusViewHolder


class ShopStatusAdapter(
        var context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var arrayShopStatusList: MutableList<StatusList> = mutableListOf()

    /**
     * This method is used to set ArrayList
     */
    fun addItemList(arrayShopStatusList1: MutableList<StatusList>?) {
        if (arrayShopStatusList1 != null) {
            arrayShopStatusList = arrayShopStatusList1
        }
        notifyDataSetChanged()
    }

    /**
     * This method is used to Create View Holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == AppConstant.ViewType.NoData) {
            //view = layoutInflater.inflate(R.layout.item_no_item, parent, false)
            val binding = ItemNoItemBinding.inflate(layoutInflater, parent, false)
            NoItemViewHolder(binding)
        } else {
            val itemBinding =
                    ItemShopStatusListBinding.inflate(layoutInflater, parent, false)
            ShopStatusViewHolder(
                    context,
                    itemBinding
            )
        }
    }

    /**
     * This method is used to Binding Adapter
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ShopStatusViewHolder) {
            try {
                holder.bind(arrayShopStatusList[position])
            } catch (e: Exception) {

            }
        }
    }

    /**
     * This method is used to count of List
     */
    override fun getItemCount(): Int {
        return if (arrayShopStatusList.isEmpty()) {
            1 //No item found
        } else {
            arrayShopStatusList.size
        }
    }

    /**
     * This method is used to set Item View Type
     */
    override fun getItemViewType(position: Int): Int {
        return if (arrayShopStatusList.isEmpty()) {
            AppConstant.ViewType.NoData
        } else {
            AppConstant.ViewType.Data
        }
    }

}