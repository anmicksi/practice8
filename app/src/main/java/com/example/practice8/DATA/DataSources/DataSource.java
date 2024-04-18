package com.example.practice8.DATA.DataSources;
import androidx.lifecycle.LiveData;
import com.example.practice8.DATA.Model.DataListRecycler;
import java.util.List;

public interface DataSource {
    LiveData<List<DataListRecycler>> getItems();
    LiveData<DataListRecycler> getItem(int itemId);
}