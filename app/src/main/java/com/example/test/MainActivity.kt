package com.example.test

//import kotlinx.android.synthetic.main.activity_main.*
import android.app.*
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.test.adapter.PostAdapter
import com.example.test.databinding.ActivityMainBinding
import com.example.test.datastore.DataStore.userPreferencesDataStore
import com.example.test.network.internet
import com.example.test.viewModel.PostViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.*

class MainActivity : AppCompatActivity() {

    private val model = lazy { ViewModelProvider(this)[PostViewModel::class.java] }

    private var postadapter : PostAdapter?=null
    private lateinit var binding: ActivityMainBinding
    private var update : Boolean = true
    var recyclerViewState : Parcelable?=null
    private val Times = intPreferencesKey("time")


    override fun onConfigurationChanged(newConfig: Configuration) {
        update = false
        super.onConfigurationChanged(newConfig)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
        this, R.layout.activity_main)

        Welcome_control()

        Connection_control()


        Progrssbar_control()


        println("onCreate")

    }
    private fun Welcome_control(){
        lifecycleScope.launch {
            val login_time = async {
                com.example.test.datastore.DataStore.read(Times.toString())
            }
            when(login_time.await().toString()){
                "1"->Toast.makeText(this@MainActivity,resources.getString(R.string.welcome),Toast.LENGTH_LONG).show()
                else-> com.example.test.datastore.DataStore.save(Times.toString(),1)
            }
        }

    }


    private fun Connection_control(){
        model.value.check()
        model.value.connection.observe(this,{
            when(it){
                true-> {


                    if(binding.rv.adapter==null || binding.rv.layoutManager==null && update){
                        update=false
                        println("dddddddd")
                        State_Flow(it)
                    }

                }


                false->{
                    update = true
                    Snackbar.make(binding.bt,"check network",Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK",object : View.OnClickListener{
                            override fun onClick(p0: View?) {
                            }

                        })
                        .show()
                    lifecycleScope.launch(Dispatchers.Main) {
                        //儲存Recyclerview當前位置狀態
                        binding.rv.apply {
                            recyclerViewState = this.layoutManager?.onSaveInstanceState()
                            layoutManager?.onRestoreInstanceState(recyclerViewState)

                        }
                    }
                }
            }
        })
    }

    private fun Progrssbar_control(){
        lifecycleScope.launchWhenCreated {
            model.value._load.collectLatest {
                when(it){
                    true->{
                        withContext(Dispatchers.Main) {
                            binding.progressBar.isVisible=true
                        }

                    }
                    false->{
                        withContext(Dispatchers.Main) {
                            binding.progressBar.isVisible=false
                        }

                    }
                }
            }
        }
    }

    private fun State_Flow(check:Boolean){
        lifecycleScope.launchWhenCreated {

            model.value.getStateData()
            model.value.mStatedata.collectLatest {
                if(it.size>0){
                    binding.rv.apply {

                        postadapter = PostAdapter(this@MainActivity,it,check)
                        adapter = postadapter
                        layoutManager = GridLayoutManager(this@MainActivity,1)

                    }
                }

            }
        }
    }

    override fun onBackPressed() {
        val navcontro = Navigation.findNavController(this, R.id.fragmentContainerView)

        when(Navigation.findNavController(this, R.id.fragmentContainerView).currentDestination?.id) {
            R.id.AFragment-> {
                binding.fragmentContainerView.isVisible = false
                navcontro.navigateUp() //destroy fragment
                navcontro.popBackStack()
            }
            R.id.BFragment2-> {

                navcontro.popBackStack()

            }
            else -> {
                super.onBackPressed()
                finish()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        println("stop")
    }

    override fun onPause() {
        super.onPause()
        println("pause")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
    }

    override fun onStart() {
        super.onStart()
        println("onStart")
    }

    override fun onDestroy() {
        binding.rv.apply {
            adapter=null
        }
        binding.unbind()
        postadapter=null
        internet.remove(this)
        CoroutineScope(Dispatchers.IO).launch{
            Glide.get(MainApplication.instance()).clearDiskCache()
            withContext(Dispatchers.Main){
                Glide.get(MainApplication.instance()).clearMemory()
            }
        }

        super.onDestroy()
//        check.removeObservers(this)


        println("destroy")
    }
}