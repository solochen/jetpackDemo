package com.solochen.kotlin.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.solochen.kotlin.R
import com.solochen.kotlin.databinding.FragmentMainHotBinding
import com.solochen.kotlin.ui.adapter.HotVideoAdapter
import com.solochen.kotlin.viewmodel.AppViewModelProvider
import com.solochen.kotlin.viewmodel.model.HotVideoModel
import im.ene.toro.widget.PressablePlayerSelector

/**
 * A simple [Fragment] subclass.
 */
class HotFragment : Fragment() {

    private val viewModel: HotVideoModel by viewModels {
        AppViewModelProvider.providerHotVideoModel(requireContext())
    }

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mSelector: PressablePlayerSelector
    private lateinit var mAdapter: HotVideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainHotBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_hot, container, false)

        initRecycler(binding)

        viewModel.getRefreshLiveData().observe(this, Observer {
            mAdapter.submitList(it)
        })

        return binding.root
    }

    private fun initRecycler(binding: FragmentMainHotBinding) {
        mLayoutManager = LinearLayoutManager(context)
        binding.recycler.layoutManager = mLayoutManager
        mSelector = PressablePlayerSelector(binding.recycler)
        binding.recycler.playerSelector = mSelector

        mAdapter = HotVideoAdapter(mSelector)
        binding.recycler.adapter = mAdapter
    }



}
