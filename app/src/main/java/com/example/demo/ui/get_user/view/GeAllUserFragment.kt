package com.example.demo.ui.get_user.view

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.MainActivity
import com.example.demo.base.BaseFragment
import com.example.demo.common.room.model.User
import com.example.demo.databinding.FragmentGetAllUserBinding
import com.example.demo.ui.get_user.view.adapter.GetAllUserAdapter
import com.example.demo.ui.login.view.LoginFragment
import com.example.demo.ui.login.viewmodel.LoginViewModel
import com.example.demo.utils.KeysUtils
import com.example.demo.utils.SharedPrefsUtils

class GetAllUserFragment : BaseFragment() {

    private var _binding: FragmentGetAllUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: LoginViewModel

    private var arrayUserList: MutableList<User>? = null
    private var storeAdapter: GetAllUserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetAllUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    //This method is used to initialized Method
    private fun initView() {

        mainViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setAdapter()
        setObservers()
        setOnClickListeners()
        arrayUserList?.clear()
        mainViewModel.getAllUser()
    }

    //This method is used to set Adapter
    private fun setAdapter() {
        //initialize Get All User RecyclerView
        arrayUserList = mutableListOf()
        binding.getAllUserRV.setHasFixedSize(true)
        binding.getAllUserRV.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        storeAdapter = GetAllUserAdapter(requireContext()).apply {
            chatItemClick = {
                chatClickMethod(it)
            }
        }
        binding.getAllUserRV.adapter = storeAdapter
    }

    private fun chatClickMethod(receiverId: Int) {
        //USer Click
//        (activity as MainActivity).navigateToFragment(ChatFragment.newInstance(receiverId),this)
    }

    //This method is used to set Observers
    private fun setObservers() {
        mainViewModel.userDataList.observe(viewLifecycleOwner, Observer { userDataList ->
            Toast.makeText(context, "-" + userDataList + "-", Toast.LENGTH_SHORT).show()
            for ((i, v) in userDataList.withIndex()) {
                if (SharedPrefsUtils.getStringPreference(KeysUtils.keyUserId) != v.id.toString()) {
                    arrayUserList?.add(v)
                }
            }
            storeAdapter?.addItemList(arrayUserList)
        })

        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        })

    }

    //This method is used to Set OnClickListener
    private fun setOnClickListeners() {
        binding.logOutTV.setOnClickListener {
            val prefs =
                PreferenceManager.getDefaultSharedPreferences(activity)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.remove("userId")
            editor.apply()
            (activity as MainActivity).navigateToFragment(LoginFragment(), this)
        }

    }



}
