package com.example.demo.ui.mandobk_shop.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.base.BaseFragment
import com.example.demo.base.BaseViewModelFactory
import com.example.demo.base.rxjava.autoDispose
import com.example.demo.base.rxjava.throttleClicks
import com.example.demo.common.model.StatusList
import com.example.demo.databinding.FragmentMandobkShopBinding
import com.example.demo.ui.mandobk_shop.view.adapter.ShopStatusAdapter
import com.example.demo.ui.mandobk_shop.viewmodel.MandoBkShopViewModel
import timber.log.Timber


class MandoBkShopFragment : BaseFragment() {

    private var _binding: FragmentMandobkShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var mandoBkShopViewModel: MandoBkShopViewModel

    private var arrayStatusList : MutableList<StatusList>?=null
    private lateinit var shopByTypeAdapter:ShopStatusAdapter

    fun onPageSelected(pos:Int){}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMandobkShopBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * This method is used to initialize views for this screen
     */
    private fun initView(){
        mandoBkShopViewModel =
            ViewModelProvider(
                this,
                BaseViewModelFactory { MandoBkShopViewModel() })
                .get(MandoBkShopViewModel::class.java)

        setAdapter()
        setObservers()
        setOnClickListeners()
        callApiForGetStatus()

    }
    /**
     * This method is used to set Recyclerview Data
     */
    private fun setAdapter() {

        //initialize Shop Type  RecyclerView
        arrayStatusList = mutableListOf()
        binding.shopTypeRV.setHasFixedSize(true)
        binding.shopTypeRV.layoutManager = LinearLayoutManager(
            activity.applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        shopByTypeAdapter = ShopStatusAdapter(activity)
        binding.shopTypeRV.adapter = shopByTypeAdapter
    }

    /**
     * This method is used to set observers
     */
    private fun setObservers() {

        mandoBkShopViewModel.statusList.observe(viewLifecycleOwner, Observer {statusList ->
            Timber.tag("statusList").e("-$statusList")
            arrayStatusList?.clear()
            arrayStatusList?.addAll(statusList)
            shopByTypeAdapter.addItemList(arrayStatusList)
        })
    }

    /**
     * This method is used to set click listeners for this screen
     */
    private fun setOnClickListeners(){
        binding.shopNameET.throttleClicks().subscribe(){
            //Search Shop Name EditText Click

        }.autoDispose(compositeDisposable)
    }

    /**
     * This method is used to call Api for Get Status
     */
    private fun callApiForGetStatus() {
        mandoBkShopViewModel.callGetStatusApi()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}