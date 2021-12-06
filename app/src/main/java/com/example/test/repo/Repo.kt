package com.example.test.repo

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.example.test.BuildConfig
import com.example.test.MainApplication
import com.example.test.R
import com.example.test.data.weather.Parameter
import com.example.test.data.weather.Time
import com.example.test.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow

object Repo {

    suspend fun getElement() : MutableStateFlow<ArrayList<Time>>{
//        val ai: ApplicationInfo = MainApplication.instance().packageManager
//            .getApplicationInfo( MainApplication.instance().packageName, PackageManager.GET_META_DATA)
//        val value = ai.metaData["keyValue"]
        var _Statedata = MutableStateFlow<ArrayList<Time>>(ArrayList<Time>())
        var total : ArrayList<Time> = ArrayList()
        val response = RetrofitClient.retrofit.getData(
//            value.toString()
            BuildConfig.KEY
            , arrayOf(MainApplication.instance().resources.getString(R.string.taipei))
            ,"JSON"
            , arrayOf("MinT")
        )
        if(response.isSuccessful){
            val result = response.body()!!
            for (element in result.records.location) {
                for (element1 in element.weatherElement) {
                    for (element2 in element1.time) {
                        total.add(element2)
                        total.add(Time("", Parameter("",""),""))
                    }
                }
            }
            _Statedata.value = total
            return _Statedata
        }else{
            return _Statedata
        }
    }
}