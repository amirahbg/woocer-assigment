package com.app.woocerassignment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.woocerassignment.R
import com.app.woocerassignment.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val productsAdapter by lazy {
        ProductsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productsRecyclerView.adapter = productsAdapter
        binding.productsRecyclerView.layoutManager = GridLayoutManager(
            requireContext(), 2
        )

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getAllProducts()
        }

        lifecycleScope.launchWhenCreated {
            async {
                viewModel.products.collect {
                    productsAdapter.submitList(it)
                }
            }

            async {
                viewModel.message.collect {
                    Toast.makeText(requireContext(), it, LENGTH_LONG).show()
                }
            }
        }
    }
}