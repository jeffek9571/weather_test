package com.example.test.factory

import com.example.test.R

//Factory pattern
class Temp private constructor() {
    object Factory{
        fun create(pizzatype:Int) : Int{
            return when(pizzatype){
                in -100..17-> R.drawable.cold
                in 18..26-> R.drawable.warm
                in 27..42 ->R.drawable.hot
                else->R.drawable.death
            }
        }
    }

}