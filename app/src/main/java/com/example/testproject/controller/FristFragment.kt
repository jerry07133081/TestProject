package com.example.testproject.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.viewmodel.FristViewModel
import com.example.testproject.databinding.FragmentFristBinding


class FristFragment : Fragment() {
    private lateinit var binding: FragmentFristBinding
    val viewModel: FristViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFristBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            //沒有layoutManager會沒recyclerview畫面
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                val adapter = recyclerView.adapter as FristAdapter?
                if (adapter == null) {
                    recyclerView.adapter = FristAdapter(items)
                } else {
                    adapter.updateItems(items)
                }

            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel?.search(newText)
                    return true
                }
                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })
        }
    }
}