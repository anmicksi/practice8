package com.example.practice8.UI.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.practice8.DATA.DataSources.Repository;
import com.example.practice8.DATA.Model.DataListRecycler;
public class DataListRecyclerViewModel extends ViewModel {
    private Repository repository = new Repository();
    public LiveData<DataListRecycler> getItem(int itemId) {
        return repository.getItem(itemId);
    }
}