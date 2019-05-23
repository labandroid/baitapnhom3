package com.example.appquanly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

//Triển khai 1 adapter với Spinner.
public class LoaiXeAdapterSpinner extends BaseAdapter {
    List<LoaiXeModel> listLoaiXe;
    Context mContext;

    public LoaiXeAdapterSpinner(List<LoaiXeModel> listLoaiXe, Context mContext) {
        this.listLoaiXe = listLoaiXe;
        this.mContext = mContext;
    }

    @Override
    //Gía trị cần trả về
    //Data đổ lên view cần phải có số lượng cụ thể. vì vậy trả về số dòng DATA cần đổ
    public int getCount() {
        return listLoaiXe.size();
    }

    @Override
    //Lấy dữ liệu tại vị trí xác định trong DATA.
    public Object getItem(int position) {
        return listLoaiXe.get(position);
    }

    @Override
    //Hàm trả về giá trị kiểu Long vị trí item trong adapter
    public long getItemId(int position) {
        return 0;
    }

    @Override
    //Tự động được gọi khi listview sẵn sàng hiển thị,
    //phương thức này ta set layout cho danh sách các item sử dụng lớp LayoutInflater
    //Sau đó thêm dữ liệu vào View như textview,
    //Để tránh đơ hoặc Crash app khi dữ liệu rất lớn.Mỗi khi getView tại phải gọi FindviewByID trên mọi tài nguyên..Ta sử dụng một lớp gọi là viewHolder.
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LoaiXeModel loaiXe = listLoaiXe.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemcustom,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.itemName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(loaiXe.getTenLoai());
        return convertView;
    }
    private class ViewHolder{
        TextView tvName;
    }
}
