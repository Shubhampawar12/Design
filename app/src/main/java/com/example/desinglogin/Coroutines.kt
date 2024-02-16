package com.example.desinglogin

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class Coroutines : AppCompatActivity() {
    private val string = "https://jsonplaceholder.typicode.com/comments"
    private lateinit var rec: RecyclerView
    lateinit var progressDialog: ProgressDialog
    lateinit var adapclass: AdapterClass
    private lateinit var url: URL
    private lateinit var viewpager: ViewPager2
    private lateinit var help:helperclass
    private lateinit var fab:FloatingActionButton


    val comments = mutableListOf<Class>()
    val rec1: MutableList<DataClass> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        rec = findViewById(R.id.co_rec)
        adapclass = AdapterClass()
        rec.adapter = adapclass
        rec.layoutManager = LinearLayoutManager(this)
        help= helperclass(this)
        fab=findViewById(R.id.fab)



        Log.d("Thread", Thread.currentThread().name)
        val options= arrayOf("Ok","SKip","ViewPager")
        val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this)
        alertDialog.setMessage("Do you want display message")
        alertDialog.setTitle("Do you want to continue")
        alertDialog.setPositiveButton(
            "Ok"
        ) { dialog, which ->
            progressDialog = ProgressDialog.show(this@Coroutines, "Loding", "Please wait")


            fetchComments()
        }
        alertDialog.setNegativeButton(
            "Skip"
        ) { dialog, which ->
            dialog.dismiss()
            val intent = Intent(this@Coroutines, LoginForm::class.java)
            startActivity(intent)
        }
            .setNeutralButton("ViewPager"){dialog,which->
                showViewPager()
                dialog.dismiss()

            }
        alertDialog.show()


        fab.setOnClickListener(){
            val options = arrayOf("Sort by Name", "Sort by Email")
            AlertDialog.Builder(this@Coroutines)
                .setTitle("Sort Options")
                .setItems(options) { dialog, which ->
                    when (which) {
                        0 -> sortByAttribute("name")
                        1 -> sortByAttribute("email")
                    }
                }
                .show()
        }


    }

    private fun showViewPager() {
        val intent=Intent(this@Coroutines,Viewpager::class.java)
        startActivity(intent)
    }


    private fun fetchComments(): List<Class> {
        val savedData= mutableListOf<Class>()
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("Thread", Thread.currentThread().name)
            url = URL(string)
            val urlConnection = url.openConnection() as HttpURLConnection
            val inputstream = urlConnection.getInputStream()
            val jsontxt = inputstream.bufferedReader().use(BufferedReader::readText)
            val jsonArray = JSONArray(jsontxt)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val postid = jsonObject.getInt("postId")
                val id = jsonObject.getInt("id")
                val name = jsonObject.getString("name")
                val email = jsonObject.getString("email")
                val body = jsonObject.getString("body")
                val comm = Class(postid, id, name, email, body)
                comments.add(comm)
                savedData.add(comm)
                fab.visibility = View.VISIBLE




            }
//            saveToDatabase(savedData)
            suspen()
        }


        return comments
    }


    private fun sortByAttribute(attribute :String){
        when(attribute){
            "name"->{
                comments.sortBy { it.name }
            }
            "email"->{
                comments.sortBy { it.email }
            }

        }
        adapclass.notifyDataSetChanged()
    }

//    @SuppressLint("SuspiciousIndentation")
//    private fun saveToDatabase(obj:List<Class>) {
//     val insert=help.writableDatabase;
//
//        obj.forEach{obj->
//            val values=ContentValues().apply {
//                put("userId",obj.id)
//                put("postid",obj.postid)
//                put("email",obj.email)
//                put("name",obj.name)
//                put("body",obj.body)
//
//            }
//            insert.insert("DetailsofApi",null,values)
//
//        }
//
//        help.close()
//
//    }

    suspend fun suspen() {
        withContext(Dispatchers.Main) {
            adapclass.setComm(comments)
            progressDialog.dismiss() // Dismiss the progress dialog here
        }
    }

}


