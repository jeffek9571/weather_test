package com.jwisdom.mykotlin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jwisdom.mykotlin.adapter.PostAdapter
import com.jwisdom.mykotlin.data.Child
import com.jwisdom.mykotlin.data.Post
import com.jwisdom.mykotlin.databinding.ActivityMainBinding
import com.jwisdom.mykotlin.viewModel.PostViewModel
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private val model = lazy { ViewModelProvider(this)[PostViewModel::class.java] }

    private lateinit var postadapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
//        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)


//        model.getUsers().observe(this, Observer<List<User>>{ users ->
//            // update UI
//        })

//        LiveData mode
//        model.value.getData()
//        model.value.mlivedata.observe(this){
//            var title : ArrayList<String> = ArrayList()
//            lifecycleScope.launch(Dispatchers.IO) {
//                for(element in it.info){
//                    for(element1 in element.child){
//                        println("${Thread.currentThread().name +element1.title}")
//                        title.add(element1.title)
//                    }
//                }
//                withContext(Dispatchers.Main){
//                    binding.tv2.text = title.toString()
//                }
//            }
//
//
//        }

        binding.rv.apply {
            postadapter = PostAdapter()
            adapter = postadapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }

//        StateFlow mode
        model.value.getStateData()
        lifecycleScope.launchWhenStarted {
            var total : ArrayList<Child> = ArrayList()

            model.value.mStatedata.collectLatest {
                binding.model=it
                binding.progressBar.isVisible=true
                if(it.status=="false"){
//                    binding.progressBar.isVisible=false
                    return@collectLatest
                }
                lifecycleScope.launch(Dispatchers.IO){
                    for(element in it.info){
                        for(element1 in element.child){
                            total.add(element1)
                        }
                    }

                    withContext(Dispatchers.Main){
                        if(total.size>0){
//                            binding.tv2.text = total.toString()
                            binding.rv.apply {
                                postadapter = PostAdapter(context,total)
                                adapter = postadapter
                                layoutManager = LinearLayoutManager(this@MainActivity)

                            }
                        }
                        binding.progressBar.isVisible=false

                    }
                }

            }
        }





        binding.btn.setOnClickListener {
            finish()
        }


        lifecycleScope.launch(Dispatchers.IO) {
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
//                runOnUiThread {
//                    textView.text = ans
//                }
//                or
                withContext(Dispatchers.Main){
                    binding.textView.text = ans
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

    private suspend fun Athread() : Int{
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

    private suspend fun Bthread() : Int{
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

//    fun scroll(){
//        val left = AnimatorSet()
//        val right = AnimatorSet()
//        val slide = ObjectAnimator.ofFloat(lv, View.TRANSLATION_X,0f,-300f)
//        val alpha = ObjectAnimator.ofFloat(lv, View.ALPHA,1f,1f)
//        with(left) {
//            duration = 0
//            play(slide).with(alpha)
//            start()
//            addListener(object : AnimatorListenerAdapter(){
//                override fun onAnimationEnd(animation: Animator?) {
//                    super.onAnimationEnd(animation)
//                    val slide1 = ObjectAnimator.ofFloat(lv, View.TRANSLATION_X,-300f,0f)
//                    val alpha1 = ObjectAnimator.ofFloat(lv, View.ALPHA,1f,1f)
//                    with(right){
//                        startDelay=500
//                        duration = 500
//                        play(slide).with(alpha)
//                        start()
//                    }
//
//                }
//            })
//        }
//    }

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