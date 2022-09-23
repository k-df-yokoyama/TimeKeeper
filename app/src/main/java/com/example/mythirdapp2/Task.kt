package com.example.mythirdapp2

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
/*
@Entity(tableName = "timesavingbox",
    indices = {@Index(value ={"date"}, unique = true)})
 */
@Entity(tableName = "timesavingbox")
class Task(date:String, starttime:String, endtime:String, memo:String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var date:String = date
    var starttime:String? = starttime
    var endtime:String? = endtime
    var memo:String? = memo
}
