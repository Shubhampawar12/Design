package com.example.desinglogin

import DataAdaper
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager

class Viewpager : AppCompatActivity() {

    private lateinit var view: ViewPager
    private lateinit var pageradapter:DataAdaper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        view=findViewById(R.id.viewpager)
        val helper = helperclass(this)
        val dataList =helper.getAllData()
        pageradapter=DataAdaper(supportFragmentManager, listOf(dataList))
        view.adapter=pageradapter
    }
}
