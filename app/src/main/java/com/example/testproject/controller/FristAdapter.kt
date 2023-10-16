package com.example.testproject.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.model.MyItem
import com.example.testproject.viewmodel.FristsViewModel
import com.example.testproject.databinding.FragmentFristItemBinding

class FristAdapter (private var items: List<MyItem>) :
    RecyclerView.Adapter<FristAdapter.FristViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    fun updateItems(items: List<MyItem>){
        this.items = items
        notifyDataSetChanged()
    }
    class FristViewHolder(val itemViewBinding: FragmentFristItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FristViewHolder {
        val itemViewBinding = FragmentFristItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = FristsViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return FristViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FristViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            // 將欲顯示的friend物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.item?.value = item
            val bundle = Bundle()
            bundle.putSerializable("item", item)
//            itemView.setOnClickListener {
//                Navigation.findNavController(it)
//                    .navigate(R.id.action_friendsFragment_to_friendDetailFragment, bundle)
//            }
        }
    }


}
