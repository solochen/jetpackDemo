package com.solochen.kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.solochen.kotlin.R
import com.solochen.kotlin.data.DBCode
import com.solochen.kotlin.databinding.FragmentRegisterBinding
import com.solochen.kotlin.viewmodel.AppViewModelProvider
import com.solochen.kotlin.viewmodel.model.RegisterModel
import org.jetbrains.anko.support.v4.toast

class RegisterFragment : Fragment() {

    private val registerModel: RegisterModel by viewModels {
        AppViewModelProvider.providerRegisterModel(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        binding.model = registerModel

        binding.btnLogin.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btnRegister.setOnClickListener {
            val accountIsEmpty: Boolean? = registerModel.account.value?.isEmpty()
            val pwIsEmpty: Boolean? = registerModel.password.value?.isEmpty()
            val pwAgainIsEmpty: Boolean? = registerModel.passwordAgain.value?.isEmpty()
            if (accountIsEmpty!!) {
                toast("账号不能为空！")
                return@setOnClickListener
            }

            if (pwIsEmpty!! || pwAgainIsEmpty!!) {
                toast("请输入密码！")
                return@setOnClickListener
            }

            if (registerModel.password.value != registerModel.passwordAgain.value) {
                toast("输入密码不一致！")
                return@setOnClickListener
            }

            registerModel.register()
        }

        registerModel.registerId.observe(viewLifecycleOwner, Observer {
            when (it) {
                DBCode.REGISTER_ERROR_EXIST -> toast("账号已存在！")
                DBCode.REGISTER_OK -> {
                    toast("注册成功！")
                    LoginFragment.navTo(
                        findNavController(),
                        registerModel.loginUser.value!!.account,
                        registerModel.loginUser.value!!.password
                    )
                }
                else -> toast("未知错误！")
            }
        })

        return binding.root
    }


}
