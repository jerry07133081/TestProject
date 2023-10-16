package com.example.testproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testproject.model.MyItem

class FristsViewModel: ViewModel() {
    val item: MutableLiveData<MyItem> by lazy { MutableLiveData<MyItem>() }
}