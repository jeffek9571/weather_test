package com.jwisdom.mykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jwisdom.mykotlin.data.User
import com.jwisdom.mykotlin.databinding.ActivityMainBinding
import com.jwisdom.mykotlin.model.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        val model = ViewModelProvider(this)[viewModel::class.java]
        model.getUsers().observe(this, Observer<List<User>>{ users ->
            // update UI
        })


        btn.setOnClickListener {
            finish()
        }


        GlobalScope.launch(Dispatchers.IO) {
//            val time = System.currentTimeMillis()
            val time = measureTimeMillis {
                val result = async  {
                    val result1 = Athread()
                    result1
                }
                val result1 = async {
                    val result2 = Bthread()
                    result2
                }
                val ans = "${result.await()+ result1.await()}"
                runOnUiThread {
                    textView.text = ans
                }

                println(ans)
            }


//            val result2 = async {
//                val result3 = Bthread()
//                result3
//            }
//
//            val result3 = async {
//                val result4 = Bthread()
//                result4
//            }
//            val result4 = async {
//                val result4 = Bthread()
//                result4
//            }
//            val result5 = async {
//                val result4 = Bthread()
//                result4
//            }
//            val result6 = async {
//                val result4 = Bthread()
//                result4
//            }

//            這樣用不好
//            var ans1:Int?=0
//            var ans2:Int?=0
//            val result = launch  {
//                ans1 = Athread()
//            }
//            val result1 = launch {
//                ans2 = Bthread()
//            }
//            result.join()
//            result1.join()
//            val ans = "${ans2?.let { ans1?.plus(it) }}"

//            val ans = "${result.await()+ result1.await()}"
//            val ans = "${result.await()+ result1.await()+result2.await()+result3.await()+result4.await()+result5.await()+result6.await()}"

//            runOnUiThread {
//                textView.text = ans
//            }
//
//            println(ans)
            println("${time}")
//            println("${System.currentTimeMillis()-time}")
        }

        println("onCreate")

    }

    suspend fun Athread() : Int{
        val name = Thread.currentThread().name
        var x=0
        println("$name, start")
        try {
            for (i in 0..4) {
                println("$name, loopat$i")
                x++
            }
            for (i in 0..4) {
                println("$name, sleepat$i")
                Thread.sleep(1000)
            }
//            runOnUiThread(Runnable { tv.setText("" + getValue() + bt.getValue()) })

            println("$name, end")
        } catch (e: Exception) {
        }
        return x
    }

    suspend fun Bthread() : Int{
        val name = Thread.currentThread().name
        var y=0
        println("$name, start")
        try {
            for (i in 0..4) {
                println("$name, loopat$i")
                y++
            }
            for (i in 0..4) {
                println("$name, sleepat$i")
                Thread.sleep(1000)
            }
//            runOnUiThread(Runnable { tv.setText("" + getValue() + bt.getValue()) })

            println("$name, end")
        } catch (e: Exception) {
        }
        return y
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
        super.onDestroy()
        println("destroy")
    }
}