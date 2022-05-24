package com.example.learningnavigation.ui.Instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.learningnavigation.R
import com.example.learningnavigation.databinding.FragmentInstructionBinding
import java.util.Calendar.getInstance

class InstructionFragment : Fragment() {

    companion object {
        val BUNDLE_GALLERY_INFO = "gallery info"
    }


    private var _binding: FragmentInstructionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(


        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,



    ): View? {



        _binding = FragmentInstructionBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.calendarView.setOnClickListener() {
                setupCalendar()

        }


        binding.buttonActivity.setOnClickListener {

            setupCalendar()

            binding.editTextActivity.visibility = View.VISIBLE
            binding.editTextDate.visibility = View.VISIBLE
            binding.switchImportance.visibility = View.VISIBLE






            /*
            // to move between fragments, we don't use intent
            // we find the nav controller and tell it to execute an action
            // to send information between fragments, we use a bundle instead of putting
            // extras into an intent.
            val bundle = Bundle()
            bundle.putString(BUNDLE_GALLERY_INFO, "Greetings from Custom1")
            // with a bundle, just add it as a parameter
            root.findNavController().navigate(R.id.action_nav_custom1_to_nav_gallery, bundle)

             */
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupCalendar() {
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = (month + 1).toString() + "/" + dayOfMonth + "/" + year
            binding.editTextDate.setText(msg)
        }
    }

}
