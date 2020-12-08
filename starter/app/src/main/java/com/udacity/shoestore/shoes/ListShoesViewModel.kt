package com.udacity.shoestore.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListShoesViewModel : ViewModel() {


    private val _shoesList = MutableLiveData<ArrayList<Shoe>>()
    val shoesList: LiveData<ArrayList<Shoe>>
        get() = _shoesList

    init {
        _shoesList.value = arrayListOf()
    }


    fun addOnList(shoe: Shoe) {
        val list = _shoesList.value
        list?.add(shoe)
        _shoesList.value = list
    }
}