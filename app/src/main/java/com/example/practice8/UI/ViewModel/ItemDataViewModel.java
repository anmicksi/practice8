package com.example.practice8.UI.ViewModel;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.practice8.DATA.DataSources.Repository;
import com.example.practice8.DATA.Model.DataListRecycler;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ItemDataViewModel extends ViewModel {
    private Repository repository = new Repository();
    private final MutableLiveData<Repository> liveData = new MutableLiveData<>(new Repository());
    public LiveData<List<DataListRecycler>> getItems() {
        return repository.getItems();
    }
    public void createList(Context context, Map<String, Integer> cathegories) {
        Objects.requireNonNull(liveData.getValue()).createDatabase(context, cathegories);
    }
    public LiveData<Repository> getLiveData() {
        return liveData;
    }
}