package com.example.mythirdapp2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mythirdapp2.R
import com.example.mythirdapp2.Task
import com.example.mythirdapp2.TaskRepository
import com.example.mythirdapp2.databinding.FragmentHomeBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        binding.btnSave.setOnClickListener {
            onSaveButtonClick()
        }
        binding.btnNew.setOnClickListener {
            onNewButtonClick()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onSaveButtonClick() {
        //[Android & Kotlin] View Binding はfindViewByIdの後継
        //https://akira-watson.com/android/kotlin/view-binding.html
        //TODO("LocalDate format yyyy-MM-dd 2019-07-04 is expected") //https://codechacha.com/ja/kotlin-examples-current-date-and-time/
        val currentDateTime = LocalDate.now()
        val formatter = DateTimeFormatter.ISO_DATE
        val formattedDateTime = currentDateTime.format(formatter)

        var task: Task = Task(formattedDateTime,
            binding.edttxtStartTime.text.toString(),
            binding.edttxtEndTime.text.toString(),
            binding.edttxtMemo.text.toString())

        //https://qiita.com/uhooi/items/471b14cb74adadebc33c
        //FragmentにおけるActivityとContextの使い分け
        // TaskRepository.insertTask(applicationContext, task)
        TaskRepository.insertTask(requireContext(), task)

        val arrayTask = TaskRepository.loadAllTask(requireContext())
        val singleTaskBuilder = StringBuilder()
        val i = 0
        singleTaskBuilder.append(arrayTask.get(i).id).append(",")
            .append(arrayTask.get(i).date).append(",")
            .append(arrayTask.get(i).starttime).append(",")
            .append(arrayTask.get(i).endtime).append(",")
            .append(arrayTask.get(i).memo)
            .append(System.getProperty("line.separator"))
        
        binding.txtvwMsg.text = "" + singleTaskBuilder + getResources().getString(R.string.txtvw_saved_msg)
    }

    fun onNewButtonClick() {
        //[Android & Kotlin] View Binding はfindViewByIdの後継
        //https://akira-watson.com/android/kotlin/view-binding.html
        binding.edttxtStartTime.setText("")
        binding.edttxtEndTime.setText("")
        binding.edttxtMemo.setText("")
        binding.txtvwMsg.text = getResources().getString(R.string.txtvw_new_msg)
    }
}
