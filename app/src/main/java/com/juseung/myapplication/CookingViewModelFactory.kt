package com.juseung.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juseung.myapplication.Repository.CookingRepository

class CookingViewModelFactory(private val cookingRepository: CookingRepository):
ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CookingRepository::class.java).newInstance(cookingRepository)
    }
}