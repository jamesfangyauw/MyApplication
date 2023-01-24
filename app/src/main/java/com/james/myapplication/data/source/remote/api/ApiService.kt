package com.james.myapplication.data.source.remote.api

import com.james.myapplication.base.BaseResponse
import com.james.myapplication.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    
    @GET("users")
    suspend fun fetchUsers(
        @Query("page") page: Int,
    ): BaseResponse<List<UserResponse>>
}