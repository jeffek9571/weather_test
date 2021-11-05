package com.jwisdom.mykotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.jwisdom.mykotlin.databinding.ActivityMainBinding
import com.jwisdom.mykotlin.databinding.FragmentABinding
import com.jwisdom.mykotlin.databinding.ItemBinding
import com.jwisdom.mykotlin.databinding.ItemBindingImpl
import com.jwisdom.mykotlin.viewModel.PostViewModel
import kotlinx.coroutines.flow.collectLatest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    by activityViewModels()表示這個是依附在activity生命週期
    private val viewModel: PostViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = DataBindingUtil.inflate<FragmentABinding>(inflater,R.layout.fragment_a,container,false)
//        val view = inflater.inflate(R.layout.fragment_a, container, false)
//        view.findViewById<TextView>(R.id.tv1).setOnClickListener { Navigation.findNavController(view).navigate(R.id.BFragment2)}
//        return view

//        直接使用PostViewModel
//        binding.viewModel=viewModel

        viewModel.getXmlData()
        lifecycleScope.launchWhenStarted {
//            viewModel._load.collectLatest {
//                binding.viewModel=it
//            }
            viewModel._error_msg.collectLatest {
                binding.error=it
            }
        }



        binding.tv1.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.BFragment2) }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}