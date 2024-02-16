package com.example.desinglogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginForm : AppCompatActivity() {
    private lateinit var btn:Button
    private lateinit var button:Button
    private lateinit var emailtext:TextInputEditText
    private lateinit var passtext:TextInputEditText
    private lateinit var faq:TextView
    private lateinit var term:TextView
    private lateinit var privacy:TextView
    private lateinit var sec:TextView
    private lateinit var help:TextView
    private lateinit var share:ImageButton
    private lateinit var email:TextInputEditText
    private lateinit var helper:helperclass

    var emailisSet=false
    var passisSet=false
    val url="https://www.google.com/"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)
        btn=findViewById(R.id.register)
        emailtext=findViewById(R.id.email)
        passtext=findViewById(R.id.pass)
        faq=findViewById(R.id.faq)
        term=findViewById(R.id.term)
        privacy=findViewById(R.id.privacy)
        sec=findViewById(R.id.sec)
        help=findViewById(R.id.help)
        share=findViewById(R.id.share)
        email=findViewById(R.id.email)
        helper=helperclass(this)
        helper.writableDatabase
        underLineText()
        share.setOnClickListener(){
            shareText()
        }
        btn.setOnClickListener(){
            if(emailtext.text.toString().isEmpty() && passtext.text.toString().isEmpty()){
                emailtext.setError("Email is rquiired")
                passtext.setError("Password is required")
            }else{
                val email=emailtext.text.toString()
                if(emailisSet && email.equals("admin@gmail.com") && passisSet){
                    btn.isEnabled=true
                    val intent=Intent(this,Coroutines::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"Correct Credential",Toast.LENGTH_SHORT).show()
                    val sharedPreference =  getSharedPreferences("emaild", MODE_PRIVATE)
                    var editor = sharedPreference.edit()
                    editor.putString("email",email)

                    editor.commit()

                }else{
                    Toast.makeText(this,"Invalid Credential",Toast.LENGTH_SHORT).show()
                }
            }
        }
        emailtext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            emailtext.setError(null)
            }
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                emailtext.setError(null)
                btn.isEnabled=false
                passtext.setText("")
                passtext.setError(null)
            }
            override fun afterTextChanged(s: Editable) {
                if(!isValidEmail(emailtext.text.toString())){
                    emailtext.setError("Invalid Email")
                }else{
                    emailtext.setError(null)
                }
            }
        })
        passtext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
         passtext.setError(null)
            }
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                passtext.setError(null)
                btn.isEnabled=false
            }
            override fun afterTextChanged(s: Editable) {
                if(!isValidPassword(passtext.text.toString())){
                    passtext.setError("Invaild Password")
                }else{
                    passtext.setError(null)
                    btn.isEnabled=true
                }
            }
        })
    }
    //underlineText
    private fun underLineText(){
        //Underline under text
        val content1 = SpannableString("FAQs")
        content1.setSpan(UnderlineSpan(), 0, content1.length, 0)
        faq.setText(content1)
        faq.setOnClickListener(){
            urlRedirect(url)
        }
        val content2 = SpannableString("Terms")
        content2.setSpan(UnderlineSpan(), 0, content2.length, 0)
        term.setText(content2)
        term.setOnClickListener(){
            urlRedirect(url)
        }
        val content3 = SpannableString("Privacy")
        content3.setSpan(UnderlineSpan(), 0, content3.length, 0)
        privacy.setText(content3)
        privacy.setOnClickListener(){
            urlRedirect(url)
        }
        val content4 = SpannableString("Security")
        content4.setSpan(UnderlineSpan(), 0, content4.length, 0)
        sec.setText(content4)
        sec.setOnClickListener(){
            urlRedirect(url)
        }
        val content5 = SpannableString("Help")
        content5.setSpan(UnderlineSpan(), 0, content5.length, 0)
        help.setText(content5)
        help.setOnClickListener(){
            urlRedirect(url)
        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun urlRedirect(url:String){
        val intent=Intent(this,WebActivity::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
    }
    private fun isValidEmail(email:String):Boolean{

        val reg=Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{3,3})")
        emailisSet=true
        return reg.matches(email)

    }
    private fun isValidPassword(pass:String):Boolean{
        if(pass.equals("admin")){
            passisSet=true
            return true
        }else{
            passisSet=false
            return false
        }
    }
    private fun shareText(){
        val shareIntent=Intent(Intent.ACTION_SEND)
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey this is the  content I want to share")
        startActivity(Intent.createChooser(shareIntent,"Share via"))
    }
}