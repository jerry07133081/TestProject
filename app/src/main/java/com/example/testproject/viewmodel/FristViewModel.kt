package com.example.testproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testproject.model.MyItem
import com.example.testproject.R

class FristViewModel : ViewModel() {
    // 原始好友列表
    private var itemList = mutableListOf<MyItem>()
    // 受監控的LiveData，一旦指派新值就會更新好友列表畫面
    val items: MutableLiveData<List<MyItem>> by lazy { MutableLiveData<List<MyItem>>() }

    init {
        loadFriends()
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始好友列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            items.value = itemList
        } else {
            val searchFriendList = mutableListOf<MyItem>()
            itemList.forEach { item ->
                if (item.name.contains(newText, true)) {
                    searchFriendList.add(item)
                }
            }
            items.value = searchFriendList
        }
    }

    /** 模擬取得遠端資料 */
    private fun loadFriends() {
        println("資料輸入...")
        val friendList = mutableListOf<MyItem>()
        friendList.add(MyItem(R.drawable.camper, "龜狗", "091234342"))
        friendList.add(MyItem(R.drawable.gudon, "國動", "090893473"))
        friendList.add(MyItem(R.drawable.asagaton, "統神", "0987466644"))
        this.itemList = friendList
        this.items.value = this.itemList
    }
}