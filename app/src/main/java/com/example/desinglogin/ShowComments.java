package com.example.desinglogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShowComments extends AppCompatActivity {

    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    AdapterClass adapterClass;
    helper hp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_comments);
        recyclerView=findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterClass=new AdapterClass();
        recyclerView.setAdapter(adapterClass);
        hp=new helper(ShowComments.this);


        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setMessage("Do you want display message");
        alertDialog.setTitle("Do you want to continue");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog=ProgressDialog.show(ShowComments.this,"Loding","Please wait");
          new FetchComments().execute();
            }
        });
        alertDialog.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent=new Intent(ShowComments.this,LoginForm.class);
                startActivity(intent);
            }
        });
        alertDialog.show();

    }



    class FetchComments extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url=new URL(" https://jsonplaceholder.typicode.com/comments");
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();

                InputStream inputStream=new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                String line;
                while((line= reader.readLine()) != null){
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        @Override
        protected void onPostExecute(String s) {

            progressDialog.dismiss();
            if(s!=null){
                displayComments(s);
            }else{
                Toast.makeText(ShowComments.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }
    private void displayComments(String s) {
        JSONArray json= null;
        try {
            json = new JSONArray(s);
            List<Class> com=new ArrayList<>();
            for(int i=0;i<3;i++){
                JSONObject jsonObject=json.getJSONObject(i);
                int postid=jsonObject.getInt("postId");
                int id=jsonObject.getInt("id");
                String name=jsonObject.getString("name");
                String email=jsonObject.getString("email");
                String body=jsonObject.getString("body");
                Class cl=new Class(postid,id,name,email,body);
                com.add(cl);

            }
            adapterClass.setComm(com);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}