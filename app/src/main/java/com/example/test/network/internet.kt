package com.example.test.network

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.MainApplication
import com.example.test.utils.ConnectionLiveData

object internet : LifecycleOwner {
    val cld = ConnectionLiveData(MainApplication.instance())
    var _connection : MutableLiveData<Boolean> = MutableLiveData()
    val connection : LiveData<Boolean> =  _connection

    fun check(application: Application,lifecycleOwner: LifecycleOwner) :LiveData<Boolean>{

//        var _connection : MutableLiveData<Boolean> = MutableLiveData()
//        val connection : LiveData<Boolean> =  _connection

        cld.observe(lifecycleOwner,{
            _connection.value=it
            println("ccccccc ${_connection}")
        })

        return connection
    }
    fun remove(lifecycleOwner: LifecycleOwner){
//        connection.removeObservers(lifecycleOwner)
        cld.removeObservers(lifecycleOwner)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }
}