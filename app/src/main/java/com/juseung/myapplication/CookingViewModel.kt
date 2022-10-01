package com.juseung.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juseung.myapplication.Repository.CookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CookingViewModel(cookingRepository: CookingRepository) : ViewModel() {
    private val repository = CookingRepository()

    private val _cookingByIdLiveData = MutableLiveData<CookingResponse?>()
    val cookingByIdLiveData: LiveData<CookingResponse?> = _cookingByIdLiveData


    fun refreshcooking(keyId: String,
                       serviceId: String,
                       dataType: String,
                       startIdx: String,
                       endIdx: String) {
        viewModelScope.launch {
            val response = repository.getDataCoroutine(keyId, serviceId, dataType, startIdx, endIdx)
            _cookingByIdLiveData.postValue(response)
        }


    }



}