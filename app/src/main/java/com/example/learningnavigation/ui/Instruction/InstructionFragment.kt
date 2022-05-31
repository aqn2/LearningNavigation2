package com.example.learningnavigation.ui.Instruction
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningnavigation.MainActivity
import com.example.learningnavigation.R
import com.example.learningnavigation.databinding.FragmentInstructionBinding
import com.example.learningnavigation.ui.Chores.ChoresFragment
import com.example.learningnavigation.ui.Instruction.InstructionAdapter
import com.example.learningnavigation.ui.Instruction.Instructions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Calendar.getInstance

class InstructionFragment : Fragment() {

    companion object {
        val EXTRA_INSTRUCTION = "The instruction"
        val TAG = "InstructionsListFragment"
    }

    lateinit var adapter: InstructionAdapter
    private var _binding: FragmentInstructionBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        _binding = FragmentInstructionBinding.inflate(layoutInflater)


        val inputStream = resources.openRawResource(R.raw.instructions)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Instructions>>() {}.type
        val instructionsList = gson.fromJson<List<Instructions>>(jsonString, type)
        Log.d(TAG, "onCreateView: $instructionsList")
        adapter = InstructionAdapter(instructionsList)
        binding.recyclerViewInstructionsList.adapter = adapter
        binding.recyclerViewInstructionsList.layoutManager = LinearLayoutManager(this.activity)

        binding.buttonInstructionBack.setOnClickListener{
            findNavController().navigate(R.id.nav_chores)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
