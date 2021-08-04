package com.example.demo.ui.my_order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo.base.BaseFragment
import com.example.demo.databinding.FragmentMyOrderBinding


class MyOrderFragment : BaseFragment() {

    private var _binding: FragmentMyOrderBinding? = null
    private val binding get() = _binding!!

    fun onPageSelected(pos:Int){}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyOrderBinding.inflate(layoutInflater, container, false)
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
        setAdapter()
        setObservers()
        setOnClickListeners()
    }
    /**
     * This method is used to set Recyclerview Data
     */
    private fun setAdapter() {}


    /**
     * This method is used to set observers
     */
    private fun setObservers() {}

    /**
     * This method is used to set click listeners for this screen
     */
    private fun setOnClickListeners(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}