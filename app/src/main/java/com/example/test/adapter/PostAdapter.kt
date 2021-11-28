package com.example.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.test.databinding.ItemBinding

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentContainerView

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.test.R
import com.example.test.data.weather.Time
import com.example.test.databinding.Item1Binding
import com.example.test.factory.Temp


class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var post :ArrayList<Time> = arrayListOf()
    private lateinit var _context: Context
    private var mCheck =false
    private val LAYOUT_ONE = 0
    private val LAYOUT_TWO = 1

    constructor(mcontext:Context,mpost: ArrayList<Time>,check:Boolean) :this(){
        mpost?.let{
            post=mpost
        }

        mCheck = check

        _context=mcontext
        println("PostAdapter ${post.toString()}")
    }


    inner class PostViewHolder(val itemBinding: ItemBinding?,val item1Binding: Item1Binding?) : RecyclerView.ViewHolder(
        itemBinding?.root ?: item1Binding!!.root
    )

//    inner class PostViewHolder(val item1Binding: Item1Binding) : RecyclerView.ViewHolder(item1Binding.root)





    override fun getItemCount() : Int {
        try {
           return post.size
        }catch (e:Exception){
            return 0
        }

    }

    override fun getItemViewType(position: Int): Int {
        if(position%2==0){
            return LAYOUT_ONE
        }else{
            return LAYOUT_TWO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        if(viewType==LAYOUT_ONE){
            return PostViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item,parent,false),null)
        }else{
            return PostViewHolder(null,DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item1,parent,false))
        }


    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        when(holder.itemViewType){
            LAYOUT_ONE->{
                holder.itemBinding?.apply {

                    val holder_para = post[holder.adapterPosition]
                    val word = holder_para.startTime + "\n" + holder_para.endTime + "\n" +
                            holder_para.parameter.parameterName + holder_para.parameter.parameterUnit

                    mo = holder_para
//                    tv1.apply {
//                        text = word
//                    }

                    bt.setOnClickListener {
                        holder.itemView.rootView.findViewById<FragmentContainerView>(R.id.fragmentContainerView).isVisible = true
                        val navController: NavController =
                            Navigation.findNavController(holder.itemView.rootView.findViewById(R.id.fragmentContainerView))
                        navController.navigateUp()


                        val bundle = bundleOf("amount" to word)
                        navController.navigate(R.id.AFragment, bundle)

            //                navController.navigate(R.id.AFragment)
                    }
                }
            }

            LAYOUT_TWO->{
                if(mCheck){

                    holder.item1Binding?.apply {
                        iv1.apply {
                            Glide.with(_context).load(Temp.Factory.create(post[holder.adapterPosition-1].parameter.parameterName.toInt())).centerCrop()
                                .transition(DrawableTransitionOptions.withCrossFade(400))
                                .thumbnail(0.2f)
//                .transition(DrawableTransitionOptions.withCrossFade().transition(R.anim.mglide))
                                .into(this)

                        }
                    }

                }
            }
        }
    }





    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
    }


}