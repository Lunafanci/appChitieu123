package com.example.expensemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanager.R;
import com.example.expensemanager.entity.LoaiThu;

import java.util.List;

public class LoaiThuRecycleViewAdapter extends RecyclerView.Adapter<LoaiThuRecycleViewAdapter.LoaiThuViewHodler> {
    private LayoutInflater mLayoutInflater;
    private List<LoaiThu> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public LoaiThuRecycleViewAdapter(Context context) {
        mLayoutInflater=LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        LoaiThuRecycleViewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        LoaiThuRecycleViewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public LoaiThuViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.recycleview_loai_thu_item,parent,false);

        return new LoaiThuViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuViewHodler holder, int position) {
            if(mList !=null){
                holder.tvName.setText(mList.get(position).ten);
                holder.position= position;
            }
    }

    @Override
    public int getItemCount() {
        if(mList ==null)
        return 0;
        return mList.size();
    }
    public LoaiThu getItem(int position){
        if(mList==null || position>mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<LoaiThu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class LoaiThuViewHodler extends RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView ivEdit,ivView;
        public CardView cv;
        public int position;
        public LoaiThuViewHodler(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            ivView=itemView.findViewById(R.id.ivView);
            ivEdit=itemView.findViewById(R.id.ivEdit);
            cv=(CardView) itemView;
            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemViewClickListener !=null){
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemEditClickListener != null) {
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
