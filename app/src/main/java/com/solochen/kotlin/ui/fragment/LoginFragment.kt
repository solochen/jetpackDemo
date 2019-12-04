package com.solochen.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.solochen.kotlin.R


class LoginFragment : Fragment() {


    lateinit var mBtnRegister: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBtnRegister = view.findViewById(R.id.btn_register)

        mBtnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }

}
