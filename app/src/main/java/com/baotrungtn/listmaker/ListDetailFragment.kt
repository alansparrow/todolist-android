package com.baotrungtn.listmaker

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class ListDetailFragment : Fragment() {

    lateinit var list: TaskList
    lateinit var listItemsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list = arguments!!.getParcelable(MainActivity.INTENT_LIST_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_detail, container, false)
        view?.let {
            listItemsRecyclerView = it.findViewById<RecyclerView>(R.id.list_items_recycler_view)
            listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
            listItemsRecyclerView.layoutManager = LinearLayoutManager(activity)

            it.findViewById<FloatingActionButton>(R.id.add_task_button)
                    ?.setOnClickListener {
                        showCreateTaskDialog()
                    }
        }

        return view
    }

    private fun addTask(task: String) {
        (listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter).addItem(task)
    }

    companion object {

        @JvmStatic
        fun newInstance(list: TaskList): ListDetailFragment {
            val fragment = ListDetailFragment()
            val args = Bundle()
            args.putParcelable(MainActivity.INTENT_LIST_KEY, list)
            fragment.arguments = args
            return fragment
        }
    }

    private fun showCreateTaskDialog() {
        val context: Context = activity as Context
        val taskEditText = EditText(context)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(context).setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task, { dialog, _ ->
                    val task = taskEditText.text.toString()
                    addTask(task)
                    dialog.dismiss()
                })
                .create()
                .show()
    }
}
