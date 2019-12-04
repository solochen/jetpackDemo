package com.solochen.kotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.solochen.kotlin.R

class RegisterFragment : Fragment() {

    lateinit var mBtnLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBtnLogin = view.findViewById(R.id.btn_login)

        mBtnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }


}
