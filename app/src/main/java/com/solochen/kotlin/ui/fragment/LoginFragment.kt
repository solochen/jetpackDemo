package com.solochen.kotlin.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.solochen.kotlin.R
import com.solochen.kotlin.data.cache.UserCache
import com.solochen.kotlin.databinding.FragmentLoginBinding
import com.solochen.kotlin.ui.activity.MainActivity
import com.solochen.kotlin.viewmodel.AppViewModelProvider
import com.solochen.kotlin.viewmodel.model.LoginModel
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class LoginFragment : Fragment() {

    private val loginModel: LoginModel by viewModels {
        AppViewModelProvider.providerLoginModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.model = loginModel
        binding.btnLogin.setOnClickListener loginBtn@{
            if (TextUtils.isEmpty(loginModel.account.value)) {
                toast("账号不能为空!")
                return@loginBtn
            }

            if (loginModel.password.value!!.isEmpty()) {
                toast("密码不能为空!")
                return@loginBtn
            }

            loginModel.login().observe(this, Observer { user ->
                user.let {
                    if (user == null) {
                        toast("用户名或密码错误！")
                        return@Observer
                    }
                    toast("登录成功！")
                    UserCache.instance.login(user)
                    startActivity<MainActivity>()
                    activity?.finish()
                }


            })
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        val account = arguments?.getString(PARAMS_ACCOUNT)
        val pw = arguments?.getString(PARAMS_PASSWORD)
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pw)) {
            loginModel.account.value = account
            loginModel.password.value = pw
        }
        return binding.root
    }

    companion object {
        private const val PARAMS_ACCOUNT = "key_account"
        private const val PARAMS_PASSWORD = "key_password"
        fun navTo(controller: NavController, account: String, pw: String) {
            val bundle = Bundle()
            bundle.putString(PARAMS_ACCOUNT, account)
            bundle.putString(PARAMS_PASSWORD, pw)
            controller.navigate(R.id.loginFragment, bundle)
        }
    }


}
