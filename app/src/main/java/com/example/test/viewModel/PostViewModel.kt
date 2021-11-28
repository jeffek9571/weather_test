package com.example.test.viewModel

import androidx.lifecycle.*
import com.example.test.data.weather.Time
import com.example.test.network.internet
import com.example.test.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class PostViewModel : ViewModel() {

//    private var _Statedata = MutableStateFlow<ArrayList<Child>>(ArrayList<Child>())
//    val mStatedata : StateFlow<ArrayList<Child>> = _Statedata.asStateFlow()

    private var _Statedata = MutableStateFlow<ArrayList<Time>>(ArrayList<Time>())
    val mStatedata : StateFlow<ArrayList<Time>> = _Statedata.asStateFlow()

    var _load = MutableStateFlow<Boolean>(false)
    var _error = MutableStateFlow<Boolean>(false)
    var _error_msg = MutableStateFlow<String>("")

    var _connection : MutableLiveData<Boolean> = MutableLiveData()
    val connection : LiveData<Boolean> =  _connection
    val func :Observer<Boolean> = Observer {
        _connection.value=it
    }

    fun check(){
        internet.cld.observeForever(func)
    }


    fun getStateData()  {
        viewModelScope.launch(Dispatchers.Main) {
//            val api : ApiInterface = RetrofitClient.instance.create(ApiInterface::class.java)
            _load.value=true
//            delay(200)
            connection.observeForever {
                when(it){
                    true->{
                        val response = async {
                            Repo.getElement()
                        }
                        //可以抓coroutine exception
                        try{
                            response.invokeOnCompletion {
                                when(it){
                                    null-> {

                                        launch(Dispatchers.IO){
                                            _Statedata.value= response.await().value
                                        }
                                        _load.value=false
                                    }
                                    else-> {
                                        _load.value=false
                                    }
                                }

                            }
                        }catch (e:IOException){
                            _load.value=false
                        }


                    }
                    false->{
                        _load.value=false
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        internet.cld.removeObserver(func)
    }
}