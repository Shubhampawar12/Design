package com.example.desinglogin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(private val context:Context,private val commentlist:List<Class>) : PagerAdapter() {
    override fun getCount(): Int {
    return commentlist.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view ==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=LayoutInflater.from(context).inflate(R.layout.pageviewer,container,false)
        val comment=commentlist[position]
        val postid:TextView=view.findViewById(R.id.postid)
        val id:TextView=view.findViewById(R.id.id)
        val name:TextView=view.findViewById(R.id.name)
        val email:TextView=view.findViewById(R.id.email)
        val  body:TextView=view.findViewById(R.id.body)

        postid.text="Post id : ${comment.postid}"
        id.text="id :${comment.id}"
        name.text="Name : ${comment.name}"
        email.text="Email :${comment.email}"
        body.text="BOdy : ${comment.body}"
        container.addView(view)

        return view

    }
}