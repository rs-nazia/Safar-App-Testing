package com.safar.mobile.data.network

import com.safar.mobile.model.Destination
import retrofit2.http.GET

interface ApiService {
    @GET("api/destinations/trending")
    suspend fun getTrendingDestinations(): List<Destination>

    @GET("api/destinations/community")
    suspend fun getCommunityDestinations(): List<Destination>
}
