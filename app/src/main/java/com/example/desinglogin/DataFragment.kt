package com.example.desinglogin

import Data
import DataAdaper
import MyRecyclerViewAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataFragment : Fragment() {

    companion object {
        private const val DATA_KEY = "dataList"

        fun newInstance(data: List<Data>): DataFragment {
            val fragment = DataFragment()
            val args = Bundle().apply {
                putSerializable(DATA_KEY, ArrayList(data))
            }
            fragment.arguments = args
            return fragment
        }
    }

    private var dataList: List<Data> = emptyList()
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var helper:helperclass
    private lateinit var adapter:MyRecyclerViewAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_data, container, false)
        swipe=view.findViewById(R.id.refresh)
        helper=helperclass(requireContext())
        swipe.setOnRefreshListener {
//            helper.fetchData()
           val newlist=helper.getAllData()
            // Update the dataList variable with the updated data
            dataList =newlist
            // Notify the adapter of the data change
            adapter.updateData(dataList)
            adapter.notifyDataSetChanged()
            // Stop the refreshing animation
            swipe.isRefreshing = false
        }
        dataList = arguments?.getSerializable(DATA_KEY) as? List<Data> ?: emptyList()
        val recyclerView: RecyclerView = view.findViewById(R.id.rec)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = MyRecyclerViewAdapter(dataList)
        recyclerView.adapter = adapter
       this@DataFragment.adapter = MyRecyclerViewAdapter(dataList)
        recyclerView.adapter = adapter


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            @SuppressLint("MissingInflatedId")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    // Delete the data at the swiped position
                    deleteDataFromDatabase(dataList[position].id)
                    // Remove the data from the list
                    dataList = dataList.filterIndexed { index, _ -> index != position }
                    // Notify the adapter of the change
                    adapter.removeItem(position)


                } else if (direction == ItemTouchHelper.RIGHT) {
                    // Show an alert dialog to update the data at the swiped position
                    val data = dataList[position]

                    val alertDialogBuilder = AlertDialog.Builder(requireContext())
                    alertDialogBuilder.setTitle("Update Data")

                    val view =
                        LayoutInflater.from(requireContext()).inflate(R.layout.updatedata, null)
                    val postIdEditText = view.findViewById<EditText>(R.id.postedit)
                    val idEditText = view.findViewById<EditText>(R.id.idedit)
                    val nameEditText = view.findViewById<EditText>(R.id.nameedit)
                    val emailEditText = view.findViewById<EditText>(R.id.emailedit)
                    val bodyEditText = view.findViewById<EditText>(R.id.bodyedit)

                    // Pre-fill EditText fields with current data
                    postIdEditText.setText(data.postId.toString())
                    idEditText.setText(data.id.toString())
                    nameEditText.setText(data.name)
                    emailEditText.setText(data.email)
                    bodyEditText.setText(data.body)
                    alertDialogBuilder.setView(view)

                    alertDialogBuilder.setPositiveButton("Update") { dialog, _ ->
                        // Implement the update logic here, for example:
//                        updateDataInDatabase(data.id)
                        val updatedPostId = postIdEditText.text.toString().toInt()
                        val updatedId = idEditText.text.toString().toInt()
                        val updatedName = nameEditText.text.toString()
                        val updatedEmail = emailEditText.text.toString()
                        val updatedBody = bodyEditText.text.toString()

                        updateData(
                            data.id,
                            updatedPostId,
                            updatedId,
                            updatedName,
                            updatedEmail,
                            updatedBody
                        )
                        val mutableDataList = dataList.toMutableList()

                        mutableDataList[position] =
                            Data(updatedPostId, updatedId, updatedName, updatedEmail, updatedBody)
                        dataList = mutableDataList.toList()
                        adapter.notifyItemChanged(position) // Notify adapter of the change at the specific position
                        dialog.dismiss()

                    }

                    alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
                        // Dismiss the dialog if the user cancels
                        dialog.dismiss()
                    }

                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()
                }

            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return view
    }
    private fun deleteDataFromDatabase(dataId: Int) {
        val helper = helperclass(requireContext())
        helper.deleteData(dataId)
    }
    private fun updateData(dataId: Int, updatedPostId: Int, updatedId: Int, updatedName: String, updatedEmail: String, updatedBody: String) {
        val helper = helperclass(requireContext())
        helper.updateData(Data(updatedPostId, updatedId, updatedName, updatedEmail, updatedBody))
    }
    private fun updateData1(dataId: Int, updatedPostId: Int, updatedId: Int, updatedName: String, updatedEmail: String, updatedBody: String) {
        val helper = helperclass(requireContext())

        // Update the data in the database
        val updatedData = Data(updatedPostId, updatedId, updatedName, updatedEmail, updatedBody)
        helper.updateData(updatedData)

        // Fetch the updated data from the database
        val newDataList = helper.getAllData()

        // Update the dataList variable with the updated data
        dataList = newDataList

        // Notify the adapter of the data change
        adapter.updateData(dataList)
    }

}