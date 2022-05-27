package com.example.learningnavigation.ui.Chores

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import androidx.fragment.app.Fragment
import com.example.learningnavigation.R
import com.example.learningnavigation.databinding.FragmentChoresBinding
//import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import androidx.collection.arrayMapOf
import com.example.learningnavigation.MainActivity


class ChoresFragment : Fragment() {


    private var _binding: FragmentChoresBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val booleanArray = BooleanArray(1000)

    private val activityArray = ArrayList<String>(100)




    private var tempDate = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //model= ViewModelProviders.of(requireActivity()).get(Communicator::class.java)


        _binding = FragmentChoresBinding.inflate(inflater, container, false)
        val root: View = binding.root



        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_list_item_multiple_choice, itemlist
        )


        //var x = 0
        //while(x <= 99){
         //   activityArray.set(x, "")
        //}


        binding.floatingButtonStartActivity.setOnClickListener{
            binding.listView.visibility = View.GONE
            binding.buttonAdd.visibility = View.VISIBLE
            binding.calendarView.visibility = View.VISIBLE
            setupCalendar()
            setHasOptionsMenu(true)
            binding.editTextActivityInput.visibility = View.VISIBLE
            binding.editTextDate.visibility = View.VISIBLE
            binding.floatingButtonStartActivity.visibility = View.GONE
            binding.floatingButtonStopActivity.visibility = View.VISIBLE
            binding.buttonDelete.visibility = View.GONE

        }

        binding.floatingButtonStopActivity.setOnClickListener{
            binding.listView.visibility = View.VISIBLE
            binding.buttonAdd.visibility = View.INVISIBLE
            binding.calendarView.visibility = View.INVISIBLE
            binding.editTextActivityInput.visibility = View.INVISIBLE
            binding.editTextDate.visibility = View.INVISIBLE
            binding.floatingButtonStopActivity.visibility = View.GONE
            binding.floatingButtonStartActivity.visibility = View.VISIBLE
            binding.buttonDelete.visibility = View.GONE

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

            (activity as MainActivity).tasksCreated ++

            //dateArray.(tempDate, binding.editTextDate.text.toString() + binding.editTextActivityInput.text.toString())
            //activityArray.set(tempDate, binding.editTextDate.text.toString() + binding.editTextActivityInput.text.toString())




        }

        binding.listView.setOnItemClickListener { adapterView, view, i, l ->

            val checkedTextView = view as CheckedTextView
            checkedTextView.isChecked = !checkedTextView.isChecked
            booleanArray.set(i, true)
            binding.listView.checkedItemPosition
            binding.buttonDelete.visibility = View.VISIBLE
            Toast.makeText(
                requireActivity(),
                "You Selected the item --> " + itemlist.get(i),
                Toast.LENGTH_SHORT
            ).show()

        }


        // Selecting and Deleting the items from the list when the delete button is pressed

        binding.buttonDelete.setOnClickListener {
            //val position: SparseBooleanArray = binding.listView.checkedItemPositions
            val count = binding.listView.count
            var item = count - 1
            /*
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                    adapter.add(itemlist.get(item))
                }
                item--
            }

             */

            //position.clear()

            while (item >= 0) {
                if (booleanArray[item]) {
                    adapter.remove(itemlist.get(item))
                    (activity as MainActivity).tasksCompleted ++
                }
                item--
            }
            adapter.notifyDataSetChanged()

            /*

            model!!.setMsgCommunicator("Tasks Created: " + tasksCreated.toString() + "Tasks Completed: " + tasksCompleted.toString())
            //Launch the data receiver fragment
            val myfragment = ReceiveFragment()
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.framefragmenthome, myfragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

             */

        }

        /*

        binding.buttonSortByDate.setOnClickListener {

            val count = binding.listView.count
            var item = count - 1
            while (item >= 0) {
                adapter.remove(itemlist.get(item))
                item--
            }
            adapter.notifyDataSetChanged()


            var orderedNum = activityArray.size -1

            var x = 0

            while(x <= orderedNum ){
                if(activityArray.get(x) != null){
                    adapter.add(activityArray.get(x))
                }
                orderedNum ++
            }
            adapter.notifyDataSetChanged()


        }


         */




        //calender


        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.chores_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sortDate ->{

            Toast.makeText(
                requireActivity(),
                "Sorting by Date",
                Toast.LENGTH_SHORT
    ).show()
}
}
return true
}


private fun setupCalendar() {
binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
// Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
val msg = (month + 1).toString() + "/" + dayOfMonth + "/" + year
binding.editTextDate.setText("Date: " + msg + " ")

tempDate = dayOfMonth
}
}





}



