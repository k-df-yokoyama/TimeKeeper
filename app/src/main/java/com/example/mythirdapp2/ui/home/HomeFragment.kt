package com.example.mythirdapp2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mythirdapp2.R
import com.example.mythirdapp2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
//        (2022/09/18)SAM変換を逆変換してみる
//        https://developer.android.com/reference/androidx/lifecycle/Observer?hl=ja
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        homeViewModel.text.observe(viewLifecycleOwner, Observer<String> { text ->
//            textView.text = text
//        })
        homeViewModel.text.observe(viewLifecycleOwner, object: Observer<String> {
            override fun onChanged(text: String) {
                textView.text = text
            }
        })
        binding.buttonHome.setOnClickListener { view ->
                root.findNavController().navigate(
                    R.id.action_navigation_home_to_navigation_dashboard)
        }
        binding.btnNew.setOnClickListener {
            //ToDo: 以下でtextViewを引数として渡しているのは不適切と思われる
            onNewButtonClick(textView)
            //edttxt_start_time
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onNewButtonClick(view: View) {
        //[Android & Kotlin] View Binding はfindViewByIdの後継
        //https://akira-watson.com/android/kotlin/view-binding.html
        binding.edttxtStartTime.setText("")
        binding.edttxtEndTime.setText("")
        binding.edttxtMemo.setText("")
        binding.txtvwMsg.text = "New!"
    }
}
