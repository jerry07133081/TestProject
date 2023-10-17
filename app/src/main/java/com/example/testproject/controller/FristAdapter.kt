package com.example.testproject.controller

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.lifecycle.findViewTreeLifecycleOwner
//import androidx.navigation.Navigation
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//import com.example.testproject.model.MyItem
//import com.example.testproject.viewmodel.FristsViewModel
//import com.example.testproject.databinding.FragmentFristItemBinding
//import java.util.*
//
//class FristAdapter(
//    private var items: List<MyItem>,
//    private val onItemMoveListener: OnItemMoveListener? // 添加此行
//) : RecyclerView.Adapter<FristAdapter.FristViewHolder>() {
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    }
//
//    fun onItemMove(fromPosition: Int, toPosition: Int) {
//        // 數據交換位置
//        if (fromPosition < toPosition) {
//            for (i in fromPosition until toPosition) {
//                Collections.swap(items, i, i + 1)
//            }
//        } else {
//            for (i in fromPosition downTo toPosition + 1) {
//                Collections.swap(items, i, i - 1)
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition)
//    }
//
//    interface OnItemMoveListener {
//        fun onItemMove(fromPosition: Int, toPosition: Int)
//    }
//
//    inner class FristViewHolder(val itemViewBinding: FragmentFristItemBinding) :
//        RecyclerView.ViewHolder(itemViewBinding.root)
//
//    fun updateItems(items: List<MyItem>){
//        this.items = items
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FristViewHolder {
//        val itemViewBinding = FragmentFristItemBinding.inflate(
//            LayoutInflater.from(parent.context), parent, false
//        )
//        itemViewBinding.viewModel = FristsViewModel()
//        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
//        return FristViewHolder(itemViewBinding)
//    }
//    override fun onBindViewHolder(holder: FristViewHolder, position: Int) {
//        val item = items[position]
//        with(holder) {
//            // 將欲顯示的friend物件指派給LiveData，就會自動更新layout檔案的view顯示
//            itemViewBinding.viewModel?.item?.value = item
//            val bundle = Bundle()
//            bundle.putSerializable("item", item)
//            itemView.setOnLongClickListener{
//                onItemMoveListener?.onItemMove(holder.adapterPosition,1)
//                true
//            }
//        }
//    }
//
//
//
//}
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.databinding.FragmentFristItemBinding // 更改為您的正確包名
import com.example.testproject.model.MyItem
import java.util.Collections

class FristAdapter(private var items: MutableList<MyItem>) :
    RecyclerView.Adapter<FristAdapter.FristViewHolder>() {

    // 更新數據的方法
    fun updateItems(newItems: MyItem) {
        items.clear()
        items.addAll(listOf(newItems))
        notifyDataSetChanged()
    }

    // 在項目移動時調用此方法
    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FristViewHolder {
        val binding = FragmentFristItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FristViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FristViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class FristViewHolder(private val binding: FragmentFristItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyItem) {
            binding.viewModel = item // 假設您的數據綁定變量名為 item
            binding.executePendingBindings()
        }
    }
}