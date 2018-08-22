package com.baotrungtn.listmaker

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.text.InputType.TYPE_CLASS_TEXT
import android.widget.EditText

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList

    private var listFragment : ListDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = list.name

        listFragment = ListDetailFragment.newInstance(list)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, listFragment, getString(R.string.list_fragment_tag))
                .commit()
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, listFragment?.list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)

        super.onBackPressed()

    }
}
