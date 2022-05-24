package com.example.learningnavigation.ui.Personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.learningnavigation.R
import com.example.learningnavigation.databinding.FragmentPersonalBinding

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

        val textView: TextView = binding.textView3
        textView.text = "This is the Greeting Fragment"


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}