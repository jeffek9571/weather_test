package com.jwisdom.mykotlin

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class mLinearlayout : LinearLayoutManager {

    constructor(context: Context?,orientation:Int,reverseLayout:Boolean) : super(context,orientation,reverseLayout)
    constructor(context: Context?) : super(context)

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        //防止滑動時 資料變動的錯誤崩潰 sdk31不適用
        try{
            super.onLayoutChildren(recycler, state)
        }catch (e: IndexOutOfBoundsException){

        }

    }
    //防止滑動時 資料變動的錯誤崩潰 sdk31適用
//    override fun supportsPredictiveItemAnimations(): Boolean {
//        return false
//    }




}