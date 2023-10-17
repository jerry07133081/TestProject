package com.example.testproject.controller

//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.RecyclerView
//import com.example.testproject.databinding.FragmentFristBinding
//
//class ItemTouchHelperCallback(private val adapter: FristAdapter) : ItemTouchHelper.Callback() {
//    override fun isLongPressDragEnabled(): Boolean {
//        return true
//    }
//
//    override fun isItemViewSwipeEnabled(): Boolean {
//        return false
//    }
//
//    override fun getMovementFlags(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder
//    ): Int {
//        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
//        val swipeFlags = 0
//        return makeMovementFlags(dragFlags, swipeFlags)
//    }
//
//    override fun onMove(
//        recyclerView: RecyclerView,
//        viewHolder: RecyclerView.ViewHolder,
//        target: RecyclerView.ViewHolder
//    ): Boolean {
//        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
//        return true
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        // 不需要实现，因为我们禁用了滑动操作
//    }
//}
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: FristAdapter) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean = true

    override fun isItemViewSwipeEnabled(): Boolean = false // 如果您想支持滑動刪除，可以改為 true

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0) // 不支持滑動操作，所以滑動標誌為 0
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true // 返回 true 表示處理了移動事件
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // 如果您支持滑動刪除，這裡可以處理滑動刪除事件
    }
}