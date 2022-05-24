package com.example.learningnavigation.ui.Chores

import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.learningnavigation.databinding.FragmentChoresBinding
import kotlinx.android.synthetic.main.activity_main.*
import androidx.drawerlayout.widget.DrawerLayout




class ChoresFragment : Fragment() {


    private var _binding: FragmentChoresBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChoresBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_list_item_multiple_choice, itemlist
        )


        binding.floatingButtonStartActivity.setOnClickListener{
            binding.listView.visibility = View.GONE
            binding.buttonAdd.visibility = View.VISIBLE
            binding.calendarView.visibility = View.VISIBLE
            setupCalendar()
            binding.editTextActivityInput.visibility = View.VISIBLE
            binding.editTextDate.visibility = View.VISIBLE
            binding.floatingButtonStartActivity.visibility = View.GONE
            binding.floatingButtonStopActivity.visibility = View.VISIBLE

        }

        binding.floatingButtonStopActivity.setOnClickListener{
            binding.listView.visibility = View.VISIBLE
            binding.buttonAdd.visibility = View.INVISIBLE
            binding.calendarView.visibility = View.INVISIBLE
            binding.editTextActivityInput.visibility = View.INVISIBLE
            binding.editTextDate.visibility = View.INVISIBLE
            binding.floatingButtonStopActivity.visibility = View.GONE
            binding.floatingButtonStartActivity.visibility = View.VISIBLE

        }



        binding.buttonAdd.setOnClickListener {

            binding.listView.visibility = View.VISIBLE
            binding.buttonAdd.visibility = View.INVISIBLE
            binding.calendarView.visibility = View.INVISIBLE
            binding.editTextActivityInput.visibility = View.INVISIBLE
            binding.editTextDate.visibility = View.INVISIBLE
            binding.floatingButtonStopActivity.visibility = View.GONE
            binding.floatingButtonStartActivity.visibility = View.VISIBLE

            itemlist.add(binding.editTextDate.text.toString() + binding.editTextActivityInput.text.toString())
            binding.listView.adapter = adapter
            adapter.notifyDataSetChanged()
            // This is because every time when you add the item the input space or the eidt text space will be cleared
            binding.editTextActivityInput.text.clear()


        }

        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            binding.listView.checkedItemPosition
            binding.buttonDelete.visibility = View.VISIBLE
            android.widget.Toast.makeText(
                requireActivity(),
                "You Selected the item --> " + itemlist.get(i),
                android.widget.Toast.LENGTH_SHORT
            ).show()

        }


        // Selecting and Deleting the items from the list when the delete button is pressed

        binding.buttonDelete.setOnClickListener {
            val position: SparseBooleanArray = binding.listView.checkedItemPositions
            val count = binding.listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                    adapter.add(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()

        }




        //calender


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
            binding.editTextDate.setText("Date: " + msg + " ")
        }
    }




}


