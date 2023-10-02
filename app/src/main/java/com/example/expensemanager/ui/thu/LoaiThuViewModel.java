package com.example.expensemanager.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensemanager.entity.LoaiThu;
import com.example.expensemanager.repository.LoaiThuRepository;

import java.util.List;

public class LoaiThuViewModel extends AndroidViewModel {
    private LoaiThuRepository mLoaiThuRepository;
    private LiveData<List<LoaiThu>> mAllLoaiThu;

    public LoaiThuViewModel(@NonNull Application application) {
        super(application);
        mLoaiThuRepository=new LoaiThuRepository(application);
        mAllLoaiThu=mLoaiThuRepository.getmAllLoaiThu();

    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    public void insert(LoaiThu loaiThu){
        mLoaiThuRepository.insert(loaiThu);
    }
    // TODO: Implement the ViewModel
    public void delete(LoaiThu loaiThu){
        mLoaiThuRepository.delete(loaiThu);
    }
    public void update(LoaiThu loaiThu){
        mLoaiThuRepository.update(loaiThu);
    }
}