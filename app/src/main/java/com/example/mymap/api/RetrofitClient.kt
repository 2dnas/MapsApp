package com.example.mymap.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    private var retrofit : Retrofit? = null

    fun getClient(base_url : String) : Retrofit? {
        val interceptorLogging = HttpLoggingInterceptor()
        interceptorLogging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptorLogging)
            .build()

        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit
    }
}
