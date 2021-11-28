package com.example.test

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import android.provider.MediaStore

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.test.viewModel.PostViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//private val registry: ActivityResultRegistry
class BFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    val thumbnailLiveData = MutableLiveData<Bitmap>()
//
//    val takePicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview(), registry) {
//            bitmap: Bitmap? -> thumbnailLiveData.setValue(bitmap)
//    }

    val args : BFragmentArgs by navArgs()
    private lateinit var check : LiveData<Boolean>

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
        val view = inflater.inflate(R.layout.fragment_b, container, false)
//        全局只有一個實例

        view.findViewById<TextView>(R.id.tv2).text= args.hi


        return view
    }

    override fun onStop() {
        super.onStop()
//        check.removeObservers(this)
//        internet.remove(this)
        println("stopbbb")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("destroybbb")
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment BFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            BFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}