package com.example.testproject.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.R
import com.example.testproject.databinding.FragmentFristBinding // 更改為您的正確包名
import com.example.testproject.model.MyItem
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

        with(binding) {

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
//            val adapter = FristAdapter(
//                (viewModel?.items?.value ?: mutableListOf()) as MutableList<MyItem>
//            ) // 使用 ViewModel 中的数据初始化适配器
            val adapter = FristAdapter(mutableListOf(MyItem(R.drawable.camper, "龜狗", "091234342"),
                MyItem(R.drawable.gudon, "國動", "090893473"),
                MyItem(R.drawable.asagaton, "統神", "0987466644")))
            recyclerView.adapter = adapter

            // 設置ItemTouchHelper
            val callback = ItemTouchHelperCallback(adapter)
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(binding.recyclerView)

            // 觀察LiveData變化（如果您的viewModel.items是LiveData）
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                Log.d("MyFragment", "Received data: $items")
                adapter.updateItems(items as MutableList<MyItem>)
                // 設置RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
//            val adapter = FristAdapter(mutableListOf()) // 假設為空列表，您可以根據需要提供數據
//            val adapter = FristAdapter(mutableListOf())
//            binding.recyclerView.adapter = adapter


//            // 設置ItemTouchHelper
//            val callback = ItemTouchHelperCallback(adapter)
//            val itemTouchHelper = ItemTouchHelper(callback)
//            itemTouchHelper.attachToRecyclerView(binding.recyclerView)
                println("Fragment節點")

                // 觀察LiveData變化（如果您的viewModel.items是LiveData）
//            viewModel?.items?.observe(viewLifecycleOwner) { items ->
//                println("資料3 : "+ items)
//                if (recyclerView.adapter == null) {
//                    recyclerView.adapter = FristAdapter(items as MutableList<MyItem>)
////                    recyclerView.adapter = adapter
//                    val callback = ItemTouchHelperCallback(recyclerView.adapter as FristAdapter)
//                    val itemTouchHelper = ItemTouchHelper(callback)
//                    itemTouchHelper.attachToRecyclerView(binding.recyclerView)
//                } else {
//                    (recyclerView.adapter as FristAdapter).updateItems(items as MutableList<MyItem>)
//                }
////                adapter.updateItems(items)
//            }
//            viewModel?.items?.observe(viewLifecycleOwner) { items ->
//                adapter.updateItems(items as MutableList<MyItem>)
//            }
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
            // 如果viewModel.items不是LiveData，請確保有相應的方法更新RecyclerView
        }
    }
}
