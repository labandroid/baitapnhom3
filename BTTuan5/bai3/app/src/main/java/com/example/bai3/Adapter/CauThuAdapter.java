package com.example.bai3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bai3.Model.CauThu;
import com.example.bai3.R;

import java.util.List;

public class CauThuAdapter extends ArrayAdapter<CauThu> {
    List<CauThu> listCauThu;
    Context mContext;
    public CauThuAdapter(Context context, int resource, List<CauThu> objects) {
        super(context, resource, objects);
        this.listCauThu = objects;
        this.mContext = context;
        LayoutInflater.from(context);
    }

    @Override
    public int getPosition(CauThu item) {
        return listCauThu.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public CauThu getItem(int position) {
        return listCauThu.get(position);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemcauthu, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName =  convertView.findViewById(R.id.tvCountry);
            viewHolder.imCauThu =  convertView.findViewById(R.id.imIcon);
            viewHolder.imLaCo = convertView.findViewById(R.id.imIcon2);
            viewHolder.tvDes = convertView.findViewById(R.id.description);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CauThu cauThu = listCauThu.get(position);
        viewHolder.imCauThu.setBackgroundResource(cauThu.getResource());
        viewHolder.imLaCo.setBackgroundResource(cauThu.getResource2());
        viewHolder.tvName.setText(cauThu.getName());
        viewHolder.tvDes.setText(cauThu.getDes());
        return convertView;
    }
    public class ViewHolder {
        TextView tvName,tvDes;
        ImageView imCauThu,imLaCo;

    }
}
