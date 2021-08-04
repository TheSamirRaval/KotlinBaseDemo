package com.example.demo.ui.login.view

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
import com.example.demo.databinding.FragmentLoginBinding
import com.example.demo.ui.login.viewmodel.LoginViewModel
import com.example.demo.ui.main.view.MainFragment
import com.example.demo.ui.register.view.RegisterFragment
import com.example.demo.utils.KeysUtils
import com.example.demo.utils.SharedPrefsUtils


class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
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
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * This method is used to initialize views for this screen
     */
    private fun initView() {
        loginViewModel =
                ViewModelProvider(
                        this,
                        BaseViewModelFactory { LoginViewModel() }).get(LoginViewModel::class.java)
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
    private fun setObservers() {
        loginViewModel.userData.observe(viewLifecycleOwner, Observer { userValue ->
            SharedPrefsUtils.setStringPreference(KeysUtils.keyUserId, userValue.id.toString())
            (activity as MainActivity).navigateToFragment(MainFragment(), this)
        })

        loginViewModel.userValueLogin.observe(viewLifecycleOwner, Observer { userValue ->
            Toast.makeText(context, "User is not available", Toast.LENGTH_SHORT).show()
        })

    }

    /**
     * This method is used to set click listeners for this screen
     */
    private fun setOnClickListeners() {
        binding.loginBTN.setOnClickListener {
            loginViewModel.checkUserAvailableOrNot(
                    binding.emailET.text.toString(),
                    binding.passwordET.text.toString()
            )
        }

        binding.registerBTN.setOnClickListener {
            (activity as MainActivity).navigateToFragment(RegisterFragment(), this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
