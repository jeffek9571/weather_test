package com.jwisdom.mykotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jwisdom.mykotlin.R
import com.jwisdom.mykotlin.data.Child
import com.jwisdom.mykotlin.data.Post
import com.jwisdom.mykotlin.databinding.ActivityMainBinding
import com.jwisdom.mykotlin.databinding.ItemBinding
import java.lang.IndexOutOfBoundsException

class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var post :ArrayList<Child> = arrayListOf()
    lateinit var context: Context

    constructor(mcontext:Context,mpost: ArrayList<Child>) :this(){
        post=mpost
        this.context=mcontext
        println("PostAdapter ${post.toString()}")
//        if (mpost != null) {
//            val previousSize: Int = post.size
//            post.clear()
//            post.addAll(mpost)
//            notifyItemRangeRemoved(0, previousSize)
//
////            notifyItemRangeInserted(0, mpost.size)
//            notifyItemRangeChanged(0, post.size)
//        }
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

//    單層陣列適用
//    private val differCallBack = object : DiffUtil.ItemCallback<Post>() {
//        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
//            return oldItem==newItem
//        }
//
//    }
//
//    private val differ = AsyncListDiffer(this,differCallBack)
//    var post :List<Post>
//        get() = differ.currentList
//        set(value) {differ.submitList(value)}



    override fun getItemCount() =post.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemBinding.apply {

            tv1.text = post[position].title
            Glide.with(context).load(post[position].image).centerCrop().into(iv1)

        }
    }


}