package com.example.practice8.UI.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.practice8.DATA.Model.FragmentDataTransaction;

public class FragmentTransactViewModel extends ViewModel {
    private MutableLiveData<FragmentDataTransaction> transactData = new MutableLiveData<>();
    public FragmentTransactViewModel() {
        transactData.setValue(new FragmentDataTransaction());
    }
    public MutableLiveData<FragmentDataTransaction> getTransactData() {
        return transactData;
    }
}
