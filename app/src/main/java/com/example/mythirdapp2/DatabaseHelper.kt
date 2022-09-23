package com.example.mythirdapp2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //クラス内のprivate定数を宣言するためにcompanion objectブロックとする。
    companion object {
        //データベースファイル名の定数フィールド
        private const val DATABASE_NAME = "timesavingbox.db"
        //バージョン情報の定数フィールド
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //テーブル作成用SQL文字列の作成
        val sb = StringBuilder()
        sb.append("CREATE TABLE timesavingbox (")
        sb.append("_id INTEGER,")
        sb.append("date TEXT,")
        sb.append("starttime TEXT,")
        sb.append("endtime TEXT,")
        sb.append("memo TEXT,")
        sb.append("PRIMARY KEY(_id,date)")
        sb.append(");")
        val sql = sb.toString()

        //SQLの実行
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
