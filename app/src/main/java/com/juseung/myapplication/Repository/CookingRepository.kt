package com.juseung.myapplication.Repository

import com.juseung.myapplication.CookingResponse
import com.juseung.myapplication.RetrofitApi
import retrofit2.http.Path


class CookingRepository {
    suspend fun getDataCoroutine(
        keyId: String,
        serviceId: String,
        dataType: String,
        startIdx: String,
        endIdx: String
    ) : CookingResponse? {
        val request = RetrofitApi.cookingService.getDataCoroutine(keyId, serviceId, dataType, startIdx, endIdx)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }
}

