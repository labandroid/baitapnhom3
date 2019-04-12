package com.example.bai5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai5.Model.CustomItem;
import com.example.bai5.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CustomItem> {
    List<CustomItem> listItems;
    Context mContext;
    public CustomAdapter( Context context, int resource, List<CustomItem> objects) {
        super(context, resource, objects);
        this.listItems = objects;
        this.mContext = context;
        LayoutInflater.from(context);
    }

    @Override
    public int getPosition(CustomItem item) {
        return listItems.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public CustomItem getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemcustom, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName =  convertView.findViewById(R.id.tvName);
            viewHolder.imIcon =  convertView.findViewById(R.id.imIcon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CustomItem ct = listItems.get(position);
        viewHolder.imIcon.setBackgroundResource(ct.getResource());
        viewHolder.tvName.setText(ct.getName());
        return convertView;
    }
    public class ViewHolder {
        TextView tvName;
        ImageView imIcon;

    }
}
