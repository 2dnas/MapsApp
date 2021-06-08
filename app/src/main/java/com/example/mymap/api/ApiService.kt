package com.example.mymap.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("directions/json?origin=Disneyland&destination=Universal+Studios+Hollywood&key=AIzaSyB-yEH8RVIoPRFQBDEmHcdx2Azm_1bmF2c")
    fun getPolyline() : Call<String>

}