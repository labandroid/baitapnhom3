package com.example.customlistview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customlistview.Model.Country;
import com.example.customlistview.R;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    List<Country> listCountry;
    Context mContext;
    public CountryAdapter(Context context, int resource, List<Country> objects) {
        super(context, resource, objects);
        this.listCountry = objects;
        this.mContext = context;
        LayoutInflater.from(context);
    }

    @Override
    public int getPosition(Country item) {
        return listCountry.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Country getItem(int position) {
        return listCountry.get(position);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemcountry, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName =  convertView.findViewById(R.id.tvCountry);
            viewHolder.imCountry =  convertView.findViewById(R.id.imIcon);
            viewHolder.tvDanSo = convertView.findViewById(R.id.danso);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Country country = listCountry.get(position);
        viewHolder.imCountry.setBackgroundResource(country.getResource());
        viewHolder.tvName.setText(country.getName());
        viewHolder.tvDanSo.setText(country.getDanSo());
        return convertView;
    }
    public class ViewHolder {
        TextView tvName,tvDanSo;
        ImageView imCountry;

    }
}
