package com.example.desinglogin

import Data
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class helperclass(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        val DATABASE_NAME = "DetailsofApi.db"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "DetailsofApi"
        val userid = "userId"
        val postId="postid"
        val email = "email"
        val name="name"
        val body = "body"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + "(" +
                userid + " INTEGER PRIMARY KEY, " +
                postId + " INTEGER, " +
                name + " TEXT, " +
                email + " TEXT, " +
                body + " TEXT)"
                )
        try {
            db?.execSQL(query)
            Log.d("Hellperclass","Table created ")
        }catch (e :Exception){
            Log.d("Error","Error in creating table : ${e.message}")
        }
    }




    public fun delete(id: Int){
        val db=this.writableDatabase
        db.delete(TABLE_NAME, "$userid = ?", arrayOf(id.toString()))
        db.close()

    }
    @SuppressLint("Range")
    fun getAllData(): List<Data> {
        val dataList = mutableListOf<Data>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM DetailsofApi", null)
        cursor.use {
            while (it.moveToNext()) {
                val postid = it.getInt(it.getColumnIndex("postid"))
                val userid = it.getInt(it.getColumnIndex("userId"))
                val name = it.getString(it.getColumnIndex("name"))
                val email = it.getString(it.getColumnIndex("email"))
                val body = it.getString(it.getColumnIndex("body"))
                val data = Data(postid, userid, name, email, body)
                dataList.add(data)
            }
        }
        return dataList
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun updateData(data: Data) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("postid", data.postId)
            put("name", data.name)
            put("email", data.email)
            put("body", data.body)
        }
        val selection = "userid = ?"
        val selectionArgs = arrayOf(data.id.toString())
        db.update("DetailsofApi", values, selection, selectionArgs)
    }
//commments
    fun deleteData(dataId: Int) {

        val db = writableDatabase
        val selection = "userid = ?"
        val selectionArgs = arrayOf(dataId.toString())
        db.delete("DetailsofApi", selection, selectionArgs)
    }

}