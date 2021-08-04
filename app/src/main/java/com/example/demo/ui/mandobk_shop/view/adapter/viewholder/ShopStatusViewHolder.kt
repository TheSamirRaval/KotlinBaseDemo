package com.example.demo.ui.mandobk_shop.view.adapter.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.common.model.StatusList
import com.example.demo.databinding.ItemShopStatusListBinding

class ShopStatusViewHolder(
        context: Context,
        private val itemBinding: ItemShopStatusListBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    //Display data from List
    fun bind(shopStatusList: StatusList) {
        itemBinding.shopStatusTV.text = shopStatusList.statusName
        itemBinding.shopTitleTV.text = "Shop-"+shopStatusList.statusId

        //Load Image use Common Class
//        itemBinding.shopTypeIV.loadShopCategoryLogo(shopData.shopPicture)
    }
}