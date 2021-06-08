package com.example.mymap

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mymap.api.ApiUtils
import com.example.mymap.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() , OnMapReadyCallback {
    private lateinit var mMap : GoogleMap
    private lateinit var binding: ActivityMainBinding
    private var lastMarkerSat : LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment =supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        ApiUtils.apiService?.getPolyline()?.enqueue(object  : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
            }

        })

    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        val tbilisi = LatLng(41.7151,44.8271)
        val secondTbilisi = LatLng(40.00,44.00)
        mMap.addMarker(MarkerOptions().position(tbilisi))
        mMap.addMarker(MarkerOptions().position(secondTbilisi))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tbilisi))

        val line = mMap.addPolyline(PolylineOptions().width(3F).color(Color.RED))
        line.points = arrayListOf(LatLng(41.7151,44.8271),LatLng(40.00,44.00)
        )

        mMap.setOnMapClickListener {
            mMap.addMarker(MarkerOptions().position(it))
            if(lastMarkerSat != null){
                val newLine = mMap.addPolyline(PolylineOptions().width(3F).color(Color.RED))
                newLine.points = arrayListOf(it,lastMarkerSat)
                lastMarkerSat = null
            }else {
                lastMarkerSat = LatLng(it.latitude,it.longitude)
            }

        }

    }
}