package com.jwisdom.mykotlin.viewModel

import androidx.lifecycle.*
import com.jwisdom.mykotlin.data.Child
import com.jwisdom.mykotlin.data.Info
import com.jwisdom.mykotlin.data.Post
import com.jwisdom.mykotlin.data.xml.*
import com.jwisdom.mykotlin.retrofit.ApiInterface
import com.jwisdom.mykotlin.retrofit.RetrofitClient
import com.jwisdom.mykotlin.utils.Errorutil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private var _livedata = MutableLiveData<ArrayList<Child>>()
    val mlivedata :LiveData<ArrayList<Child>> = _livedata

//    private var _Statedata = MutableStateFlow<Post>(Post(emptyList(),""))
//    val mStatedata : StateFlow<Post> = _Statedata.asStateFlow()
    private var _Statedata = MutableStateFlow<ArrayList<Child>>(ArrayList<Child>())
    val mStatedata : StateFlow<ArrayList<Child>> = _Statedata.asStateFlow()
    var total : ArrayList<Child> = ArrayList()

    private var _Xml = MutableStateFlow<Content>(Content("","",""))
    val mXml : StateFlow<Content> = _Xml.asStateFlow()

    var _load = MutableStateFlow<Boolean>(false)
    var _error = MutableStateFlow<Boolean>(false)
    var _error_msg = MutableStateFlow<String>("")


    fun getData()  {
        viewModelScope.launch(Dispatchers.IO) {
//            val api : ApiInterface = RetrofitClient.instance.create(ApiInterface::class.java)
            val response = RetrofitClient.retrofit.getPost()
            if(response.isSuccessful){
//                _livedata.postValue(response.body())
                val result = response.body()!!
                for (element in result.info) {
                    for (element1 in element.child) {
                        total.add(element1)
                    }
                }
                _livedata.postValue(total)
            }else{
                _error.value=true
            }
        }
    }


    fun getStateData()  {
        viewModelScope.launch(Dispatchers.IO) {
//            val api : ApiInterface = RetrofitClient.instance.create(ApiInterface::class.java)
            _load.value=true
            val response = RetrofitClient.retrofit.getPost()
            if(response.isSuccessful){
                val result = response.body()!!
                for (element in result.info) {
                    for (element1 in element.child) {
                        total.add(element1)
                    }
                }
                _Statedata.value= total
                _load.value = false
            }else{
                _error.value=true
//                _error_msg.value= Errorutil().parseError(response).message
//                _error_msg.value= response.message()
            }
        }
    }

    fun getXmlData()  {
        viewModelScope.launch(Dispatchers.IO) {
//            val api : ApiInterface = RetrofitClient.instance.create(ApiInterface::class.java)
            val response = RetrofitClient.retrofit_xml.getXml()
            if(response.isSuccessful){
                val result = response.body()!!
                _error_msg.value = result.message
            }else{
                _error_msg.value= response.message()
            }
        }
    }


}