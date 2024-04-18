package com.example.practice8.DATA.DataSources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.practice8.DATA.Model.DataListRecycler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.practice8.R;

public class LocalDataSource implements DataSource {
    private Map<Integer, DataListRecycler> items = new HashMap<>();
    public LocalDataSource() {
        for (int i = 0; i < 200; i++) {
            String text = "item #" + (i + 1);
            items.put(R.drawable.question_box, new DataListRecycler(text,R.drawable.question_box));
        }
    }
    @Override
    public LiveData<List<DataListRecycler>> getItems() {
        MutableLiveData<List<DataListRecycler>> liveData = new MutableLiveData<>();
        liveData.setValue(new ArrayList<>(items.values()));
        return liveData;
    }
    @Override
    public LiveData<DataListRecycler> getItem(int itemId) {
        MutableLiveData<DataListRecycler> liveData = new MutableLiveData<>();
        liveData.setValue(items.get(itemId));
        return liveData;
    }
}