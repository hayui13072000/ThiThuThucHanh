package com.example.thithuthuchanh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdater extends BaseAdapter {

    private List<User> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public UserAdater(Context aContext,  List<User> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_item, null);

        TextView name = (TextView)convertView.findViewById(R.id.tvName);
        name.setText(listData.get(position).getName());

        TextView id = (TextView)convertView.findViewById(R.id.tvId);
        id.setText(listData.get(position).getId());

        TextView age = (TextView)convertView.findViewById(R.id.tvAge);
        age.setText(listData.get(position).getAge());

        TextView iclass = (TextView)convertView.findViewById(R.id.tvClass);
        iclass.setText(listData.get(position).getIclass());

        return convertView;
    }
}
