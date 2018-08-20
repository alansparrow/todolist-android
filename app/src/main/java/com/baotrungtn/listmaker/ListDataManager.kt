package com.baotrungtn.listmaker

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by baotrungtn@gmail.com on 21.August.2018
 **/


class ListDataManager(val context: Context) {
    fun saveList(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()
    }

    fun readList(): ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferencesContents = sharedPreferences.all
        val tasks = ArrayList<TaskList>()

        for (item in sharedPreferencesContents) {
            val itemHashSet = item.value as HashSet<String>
            val list = TaskList(item.key, ArrayList(itemHashSet))
            tasks.add(list)
        }

        return tasks
    }
}