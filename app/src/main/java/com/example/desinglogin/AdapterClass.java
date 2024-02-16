package com.example.desinglogin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
private List<Class> comm;

public AdapterClass(){
    this.comm=new ArrayList<>();

}

public void setComm(List<Class> comments){
    this.comm=comments;
    notifyDataSetChanged();
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_layout,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Class c=comm.get(position);
    holder.postid.setText(String.valueOf(c.getPostid()));
    holder.id.setText(String.valueOf(c.getId()));
    holder.name.setText(String.valueOf(c.getName()));
    holder.email.setText(String.valueOf(c.getEmail()));
    holder.body.setText(String.valueOf(c.getBody()));

    }

    @Override
    public int getItemCount() {
        return comm.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView postid;
        TextView id;
        TextView name;
        TextView email;
        TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        postid=itemView.findViewById(R.id.postid);
        id=itemView.findViewById(R.id.id);
        name=itemView.findViewById(R.id.name);
        email=itemView.findViewById(R.id.email);
        body=itemView.findViewById(R.id.body);
        }
    }
}
