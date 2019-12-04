package com.solochen.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.solochen.kotlin.R
import com.solochen.kotlin.ui.activity.MainActivity

class WelcomeFragment : Fragment() {

    private lateinit var mBtnToRegister: Button
    private lateinit var mBtnToLogin: Button
    private lateinit var mBtnToMain: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setListener()
    }


    private fun initViews(view: View) {
        mBtnToRegister = view.findViewById(R.id.btn_to_register)
        mBtnToLogin = view.findViewById(R.id.btn_to_login)
        mBtnToMain = view.findViewById(R.id.btn_to_main)
    }

    private fun setListener() {
        mBtnToRegister.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        mBtnToLogin.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        mBtnToMain.setOnClickListener {
            MainActivity.start(context!!)
        }
    }

}
