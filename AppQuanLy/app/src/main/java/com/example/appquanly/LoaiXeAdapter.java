package com.example.appquanly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.appquanly.LoaiXeModel;

import java.util.List;

public class LoaiXeAdapter extends BaseAdapter {
    List<LoaiXeModel> listLoaiXe;
    Context mContext;

    public LoaiXeAdapter(List<LoaiXeModel> listLoaiXe, Context mContext){
        this.listLoaiXe = listLoaiXe;
        this.mContext = mContext;
    }

    @Override
	//Gía trị cần trả về
    //trả về số phần tử.
    public int getCount(){
        return listLoaiXe.size();
    }

    @Override
	//Trả về đối tượng ở vị trí nào đó
    public Object getItem(int position){
        return listLoaiXe.get(position);
    }

    @Override
	//Hàm trả về 1 ID của phần tử. ở vị trí position.
    public long getItemId(int position){
        return 0;
    }

    @Override
	//Tự động được gọi khi listview sẵn sàng hiển thị,
    //Để tránh đơ hoặc Crash app khi dữ liệu rất lớn.Mỗi khi getView tại phải gọi FindviewByID trên mọi tài nguyên..Ta sử dụng một lớp gọi là viewHolder.
//convertView là View hiển thị phần tử.Nếu là null thì tạo mới. hoặc có thể nạp từ layout item_loaixe
    public View getView(int position, View convertView, ViewGroup parent){
       ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_loaixe,parent,false );
            viewHolder = new ViewHolder();
            viewHolder.tvCheck = convertView.findViewById(R.id.isCheck);
            viewHolder.tvTenLoai = convertView.findViewById(R.id.tvTenLoai);
            viewHolder.tvXuatXu = convertView.findViewById(R.id.tvXuatXu);

			//để lưu trữ đối tượng viewHolder trong converView, sử dụng phương thức setTag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
		//cuối cùng gán dữ liệu vào vị trí position vào view 
        LoaiXeModel loaiXeModel = listLoaiXe.get(position);
        if(loaiXeModel.isCheck()){
            viewHolder.tvCheck.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tvCheck.setVisibility(View.GONE);
        }
		//cập nhật các textview t dùng setText
        viewHolder.tvTenLoai.setText(loaiXeModel.getTenLoai());
        viewHolder.tvXuatXu.setText(loaiXeModel.getXuatXu());

		//trả về đối tượng view này.
        return convertView;

    }
	// lưu trữ các phần hiện diện của layout
    private class ViewHolder{
        TextView tvCheck,tvTenLoai,tvXuatXu;
    }
}