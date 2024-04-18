package com.example.practice8.UI.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.practice8.DATA.DataSources.Repository;
import com.example.practice8.DATA.Model.DataListRecycler;
import java.util.List;

public class ItemDataViewModel extends ViewModel {
    private Repository repository = new Repository();
    public LiveData<List<DataListRecycler>> getItems() {
        return repository.getItems();
    }
}