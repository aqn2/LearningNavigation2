package com.example.learningnavigation.ui.Personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.learningnavigation.MainActivity
import com.example.learningnavigation.R
import com.example.learningnavigation.databinding.FragmentPersonalBinding
import org.w3c.dom.Text

class PersonalFragment : Fragment() {

    companion object {
        val BUNDLE_GREETING2_INFO = "greeting2 info"
    }

    private var _binding: FragmentPersonalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentPersonalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewTC: TextView = binding.textViewTasksCreated
        val textViewTC2: TextView = binding.textViewTasksCompleted
        val textViewPM: TextView = binding.textViewTasksPerMonth
        val textViewPM2: TextView = binding.textViewTasksPerMonthCompleted

        textViewTC.text = "Tasks Created: " + (activity as MainActivity).tasksCreated.toString()

        textViewTC2.text = "Tasks Completed: " + (activity as MainActivity).tasksCompleted.toString()

        textViewPM.text = "Tasks Created Per Month: " + ((activity as MainActivity).tasksCreated/12).toString()

        textViewPM2.text = "Tasks Completed Per Month: " + ((activity as MainActivity).tasksCompleted/12).toString()


        binding.buttonPersonalBack.setOnClickListener{
            findNavController().navigate(R.id.nav_chores)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




