package com.example.demo.ui.get_user.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.common.constant.AppConstant
import com.example.demo.common.room.model.User
import com.example.demo.databinding.ItemNoItemBinding
import com.example.demo.databinding.ItemUserListBinding
import com.example.demo.ui.get_user.view.adapter.viewholder.GetAllUserViewHolder
import com.example.demo.ui.mandobk_shop.view.adapter.viewholder.NoItemViewHolder

class GetAllUserAdapter(val context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var arrayStoreList : MutableList <User> = mutableListOf()
    var chatItemClick : ((Int) -> Unit)? = null

    fun addItemList(arrayStoreList1: MutableList<User>?){
        arrayStoreList = arrayStoreList1!!
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when(viewType){
           AppConstant.ViewType.NoData ->{
               val binding = ItemNoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
               NoItemViewHolder(binding)
           }
           else -> {
               val itemBinding =
                   ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
               return GetAllUserViewHolder(itemBinding,chatItemClick)
           }
       }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is GetAllUserViewHolder){
            holder.bind(arrayStoreList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (arrayStoreList.isEmpty()) {
            1 //No item found
        } else {
            arrayStoreList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(arrayStoreList.isEmpty()){
            AppConstant.ViewType.NoData
        }else{
            AppConstant.ViewType.Data
        }

    }

}