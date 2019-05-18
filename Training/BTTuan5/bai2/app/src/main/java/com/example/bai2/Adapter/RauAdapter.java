package com.example.bai2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai2.Model.RauTraiCay;
import com.example.bai2.R;

import java.util.List;

public class RauAdapter extends ArrayAdapter<RauTraiCay> {
    List<RauTraiCay> listCountry;
    Context mContext;
    public RauAdapter(Context context, int resource, List<RauTraiCay> objects) {
        super(context, resource, objects);
        this.listCountry = objects;
        this.mContext = context;
        LayoutInflater.from(context);
    }

    @Override
    public int getPosition(RauTraiCay item) {
        return listCountry.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public RauTraiCay getItem(int position) {
        return listCountry.get(position);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemsrau, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName =  convertView.findViewById(R.id.tvCountry);
            viewHolder.imCountry =  convertView.findViewById(R.id.imIcon);
            viewHolder.tvDes = convertView.findViewById(R.id.description);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        RauTraiCay rau = listCountry.get(position);
        viewHolder.imCountry.setBackgroundResource(rau.getResource());
        viewHolder.tvName.setText(rau.getName());
        viewHolder.tvDes.setText(rau.getDes());
        return convertView;
    }
    public class ViewHolder {
        TextView tvName,tvDes;
        ImageView imCountry;

    }
}
