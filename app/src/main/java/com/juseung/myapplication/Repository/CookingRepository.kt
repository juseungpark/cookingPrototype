package com.juseung.myapplication.Repository

import com.juseung.myapplication.CookingResponse
import com.juseung.myapplication.RetrofitApi
import retrofit2.http.Path


class CookingRepository {
    private val cookingClient = RetrofitApi.cookingService
    suspend fun getDataCoroutine(
        keyId: String,
        serviceId: String,
        dataType: String,
        startIdx: String,
        endIdx: String
    ) = cookingClient.getDataCoroutine(keyId, serviceId, dataType, startIdx, endIdx)
}


