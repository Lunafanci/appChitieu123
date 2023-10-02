package com.example.expensemanager.ui.thu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.expensemanager.R;
import com.example.expensemanager.adapter.ItemClickListener;
import com.example.expensemanager.adapter.LoaiThuRecycleViewAdapter;
import com.example.expensemanager.dialog.LoaiThuDetailDialog;
import com.example.expensemanager.dialog.LoaiThuDialog;
import com.example.expensemanager.entity.LoaiThu;

import java.util.List;

public class LoaiThuFragment extends Fragment {
private RecyclerView mRv;
private LoaiThuRecycleViewAdapter mAdapter;

    private LoaiThuViewModel mViewModel;

    public static LoaiThuFragment newInstance() {

        return new LoaiThuFragment();
    }
    public LoaiThuViewModel getViewModel(){
        return mViewModel;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv=view.findViewById(R.id.recyclerView);
        mAdapter=new LoaiThuRecycleViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final LoaiThuFragment currentFragment= this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu=mAdapter.getItem(position);
                LoaiThuDialog dialog=new LoaiThuDialog(getActivity(),currentFragment,loaiThu);
                dialog.show();

            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu=mAdapter.getItem(position);
                LoaiThuDetailDialog dialog=new LoaiThuDetailDialog(getActivity(),currentFragment,loaiThu);
                dialog.show();
            }
        });
        ItemTouchHelper helper=new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position =viewHolder.getAdapterPosition();
                        LoaiThu lt=mAdapter.getItem(position);

                        Toast.makeText(getActivity(), "Loại thu đã được xóa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lt);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiThuViewModel.class);
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
        // TODO: Use the ViewModel
    }

}