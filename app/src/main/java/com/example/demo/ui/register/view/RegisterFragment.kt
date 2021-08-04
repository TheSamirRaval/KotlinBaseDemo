package com.example.demo.ui.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demo.MainActivity
import com.example.demo.base.BaseFragment
import com.example.demo.base.BaseViewModelFactory
import com.example.demo.common.room.model.User
import com.example.demo.databinding.FragmentRegisterBinding
import com.example.demo.ui.login.viewmodel.LoginViewModel
import com.example.demo.ui.main.view.MainFragment
import com.example.demo.utils.KeysUtils
import com.example.demo.utils.SharedPrefsUtils

class RegisterFragment : BaseFragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    //This method is used to initialized Method
    private fun initView() {

        loginViewModel =
                ViewModelProvider(
                        this,
                        BaseViewModelFactory { LoginViewModel() }).get(LoginViewModel::class.java)
        setAdapter()
        setObservers()
        setOnClickListeners()
    }

    //This method is used to set Adapter
    private fun setAdapter() {}

    //This method is used to set Observers
    private fun setObservers() {
        loginViewModel.userValueRegister.observe(viewLifecycleOwner, Observer { userValue ->
            loginViewModel.checkUserAvailableOrNot(
                binding.emailET.text.toString(),
                binding.passwordET.text.toString()
            )

        })
        loginViewModel.userData.observe(viewLifecycleOwner, Observer { userData ->
            SharedPrefsUtils.setStringPreference(KeysUtils.keyUserId,userData.id.toString())
            (activity as MainActivity).navigateToFragment(MainFragment(), this)
        })
        loginViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        })

    }

    //This method is used to Set OnClickListener
    private fun setOnClickListeners() {
        binding.registerBTN.setOnClickListener {
            val user = User(
                name = binding.nameET.text.toString(),
                email = binding.emailET.text.toString(),
                password = binding.confirmPasswordET.text.toString(),
                place = binding.placeET.text.toString()
            )
            loginViewModel.insertUser(user)
        }
    }

}
