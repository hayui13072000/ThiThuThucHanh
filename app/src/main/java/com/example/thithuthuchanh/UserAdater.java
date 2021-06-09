package com.example.thithuthuchanh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdater extends RecyclerView.Adapter<UserAdater.UserViewHolder> {

    private final ArrayList<User> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public UserAdater(ArrayList<User> listData, Context context) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public UserAdater.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new UserViewHolder(itemView);
    }

    private EditText txtName, txtId, txtAge, txtClass;

    @Override
    public void onBindViewHolder(@NonNull UserAdater.UserViewHolder holder, int position) {
        User user=listData.get(position);
        holder.tvId.setText(user.getId());
        holder.tvName.setText(user.getName());
        holder.tvAge.setText(String.valueOf(user.getAge()));
        holder.tvClass.setText(user.getIclass());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ListApi.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("id", user.getId());
//                bundle.putString("name", user.getName());
//                bundle.putInt("age", user.getAge());
//                bundle.putString("class", user.getIclass());
//
//                intent.putExtra("BUNDLE", bundle);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvClass;
        private TextView tvAge;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.tvId);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvClass = itemView.findViewById(R.id.tvClass);
            this.tvAge = itemView.findViewById(R.id.tvAge);
        }
    }
}
