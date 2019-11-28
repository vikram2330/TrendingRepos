package com.vikram.trendingrepos.ui.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vikram.trendingrepos.R
import com.vikram.trendingrepos.TrendingReposApp
import com.vikram.trendingrepos.data.model.NetworkResponse
import com.vikram.trendingrepos.databinding.FragmentHomeBinding
import com.vikram.trendingrepos.ui.base.BaseFragment
import com.vikram.trendingrepos.utils.hide
import com.vikram.trendingrepos.utils.setDivider
import com.vikram.trendingrepos.utils.show

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TrendingReposApp.get().applicationComponent.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        viewModel.getRepositories()
    }

    private fun initUI() {
        binding.rvRepository.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvRepository.setDivider(R.drawable.divider_drawable)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.errorLayout.btnRetry.setOnClickListener {
            viewModel.getRepositories()
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.forceRefreshRepositories()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun initObservers() {
        viewModel.getResponseLiveData().observe(this, Observer {
            when (it) {
                is NetworkResponse.Loading -> {
                    showLoading()
                }
                is NetworkResponse.Success -> {
                    loadList(it.data)
                }
                is NetworkResponse.ResponseError -> {
                    showError()
                }
            }
        })
    }

    private fun showError() {
        binding.errorLayout.root.show()
        binding.viewLoading.hide()
        binding.rvRepository.hide()
    }

    private fun showLoading() {
        binding.viewLoading.show()
        binding.rvRepository.hide()
        binding.errorLayout.root.hide()
    }

    fun loadList(list: List<RepositoryItem>) {
        binding.viewLoading.hide()
        binding.rvRepository.show()
        binding.errorLayout.root.hide()
        binding.rvRepository.adapter = RepositoryAdapter(list)
    }


}