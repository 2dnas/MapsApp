package com.example.mymap.api

object ApiUtils {

    private const val BASE_URL = "https://maps.googleapis.com/maps/api/"

    val apiService : ApiService?
    get() = RetrofitClient.getClient(BASE_URL)?.create(ApiService::class.java)
}