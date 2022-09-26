package com.juseung.myapplication

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CookingService {
    @GET("api/{keyId}/{serviceId}/{dataType}/{startIdx}/{endIdx}")
    suspend fun getDataCoroutine(
        @Path("keyId") keyId: String,
        @Path("serviceId") serviceId: String,
        @Path("dataType") dataType: String,
        @Path("startIdx") startIdx: String,
        @Path("endIdx") endIdx: String
    ): Response<CookingResponse>
}