package com.example.mythirdapp2

import android.content.Context

object TaskRepository {
    fun loadAllTask(context: Context): Array<Task> {
        val db = TaskDatabase.getInstance(context)
        return db.taskDao().loadAllTask()
    }

    fun insertTask(context: Context, task: Task) {
        val db = TaskDatabase.getInstance(context)
        db.taskDao().insertTask(task)
    }
}
