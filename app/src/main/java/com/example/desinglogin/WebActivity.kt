package com.example.desinglogin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebActivity : AppCompatActivity() {
    private lateinit var web:WebView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        web=findViewById(R.id.web)



        val url=intent.getStringExtra("url")

        if(url!=null){
            web.settings.javaScriptEnabled=true
            web.loadUrl(url)
            web.webViewClient= WebViewClient()
        }
    }

    override fun onBackPressed() {

        if(web.canGoBack()){
            web.goBack()
        }else{
            super.onBackPressed()
        }
    }
}