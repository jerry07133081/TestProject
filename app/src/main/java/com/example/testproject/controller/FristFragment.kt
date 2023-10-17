package com.example.testproject.controller
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.SearchView
//import androidx.fragment.app.viewModels
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.testproject.viewmodel.FristViewModel
//import com.example.testproject.databinding.FragmentFristBinding
//
//class FristFragment : Fragment(),FristAdapter.OnItemMoveListener {
//    private lateinit var binding: FragmentFristBinding
//    val viewModel: FristViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentFristBinding.inflate(inflater, container, false)
//        binding.viewModel = viewModel
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//
//        with(binding) {
//
//            val adapter = FristAdapter()
//            recyclerView.adapter = adapter
//            val callback = ItemTouchHelperCallback(adapter)
//            val touchHelper = ItemTouchHelper(callback)
//            touchHelper.attachToRecyclerView(recyclerView)
//
//            recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//            viewModel?.items?.observe(viewLifecycleOwner) { items ->
//                val adapter = recyclerView.adapter as FristAdapter?
//                if (adapter == null) {
//                    recyclerView.adapter = FristAdapter(items)
//                } else {
//                    adapter.updateItems(items)
//                }
//                val itemTouchHelperCallback = adapter?.let { ItemTouchHelperCallback(it) }
//                val itemTouchHelper: ItemTouchHelper? = itemTouchHelperCallback?.let { ItemTouchHelper(it) }
//                if (itemTouchHelper != null) {
//                    itemTouchHelper.attachToRecyclerView(recyclerView)
//                }
//
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    viewModel?.search(newText)
//                    return true
//                }
//
//                override fun onQueryTextSubmit(text: String): Boolean {
//                    return false
//                }
//            })
//        }
//        }
//    }
//
//}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.databinding.FragmentFristBinding // 更改為您的正確包名
import com.example.testproject.viewmodel.FristsViewModel

class FristFragment : Fragment() {
    private lateinit var binding: FragmentFristBinding
    val viewModel: FristsViewModel by viewModels { requireParentFragment().defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFristBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 設置RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = FristAdapter(mutableListOf()) // 假設為空列表，您可以根據需要提供數據
        binding.recyclerView.adapter = adapter

        // 設置ItemTouchHelper
        val callback = ItemTouchHelperCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        // 觀察LiveData變化（如果您的viewModel.items是LiveData）
        viewModel.item.observe(viewLifecycleOwner) { item ->
            adapter.updateItems(item)
        }

        // 如果viewModel.items不是LiveData，請確保有相應的方法更新RecyclerView
    }
}
