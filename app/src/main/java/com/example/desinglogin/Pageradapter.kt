package com.example.desinglogin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Pageradapter(private val records:List<Class>, private val helperclass: helperclass)  :RecyclerView.Adapter<Pageradapter.PagerViewholder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Pageradapter.PagerViewholder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.pageviewer,parent,false)
        return PagerViewholder(view)
    }

    override fun onBindViewHolder(holder: Pageradapter.PagerViewholder, position: Int) {
        val record=records[position]
        holder.bind(record)
    }
    fun onDeleteItem(position: Int){
        val item=records[position]
        helperclass.delete(item.id)
    }

    override fun getItemCount(): Int = records.size

    inner class PagerViewholder(itermview: View):RecyclerView.ViewHolder(itermview){
        private val postid:TextView=itemView.findViewById(R.id.postid)
        private val id:TextView=itemView.findViewById(R.id.id)
        private val name:TextView=itemView.findViewById(R.id.name)
        private val email:TextView=itermview.findViewById(R.id.email)
        private val body:TextView=itemView.findViewById(R.id.body)

        fun bind(record:Class){
            postid.text=record.postid.toString()
            id.text=record.id.toString()
            name.text=record.name.toString()
            email.text=record.email.toString()
            body.text=record.body.toString()
        }
    }
}