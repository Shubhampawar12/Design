package com.example.desinglogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyPageAdapter(private val data : List<Class>) :RecyclerView.Adapter<MyPageAdapter.Viewholder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.apidetails,parent,false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int =data.size



    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val current=data[position]
        holder.bind(current)
    }

    class Viewholder(itemview: View): RecyclerView.ViewHolder(itemview){
        var postid: TextView? = null
        var id: TextView? = null
        var name: TextView? = null
        var email: TextView? = null
        var body: TextView? = null

        fun ViewHolder(itemView: View) {
            postid = itemView.findViewById(R.id.postid)
            id = itemView.findViewById(R.id.id)
            name = itemView.findViewById(R.id.name)
            email = itemView.findViewById(R.id.email)
            body = itemView.findViewById(R.id.body)
        }
        fun bind(data:Class){
            postid?.text=data.postid.toString()
            id?.text=data.id.toString()
            name?.text=data.name.toString()
            email?.text=data.email.toString()
            body?.text=data.body.toString()
        }

    }

}