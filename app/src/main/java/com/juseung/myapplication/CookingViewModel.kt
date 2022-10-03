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

class CookingViewModel(private val cookingRepository: CookingRepository) : ViewModel() {
    private val _cookingRepositories = MutableLiveData<List<Row>>()
    val cookingRepositories = _cookingRepositories




    fun requestCookingRepositories(keyId: String,
                       serviceId: String,
                       dataType: String,
                       startIdx: String,
                       endIdx: String) {
        CoroutineScope(Dispatchers.IO).launch {
            cookingRepository.getDataCoroutine(keyId, serviceId, dataType, startIdx, endIdx).let { response ->
            if(response.isSuccessful) {
                response.body()?.cOOKRCP01?.row?.let{
                    _cookingRepositories.postValue(it)

                }
            }
            }
        }


    }



}