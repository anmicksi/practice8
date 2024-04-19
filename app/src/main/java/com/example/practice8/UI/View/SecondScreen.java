package com.example.practice8.UI.View;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import com.example.practice8.DATA.DataSources.Room.Entities.Category;
import com.example.practice8.R;
import com.example.practice8.UI.ViewModel.ItemDataViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SecondScreen extends Fragment {
    private ItemDataViewModel viewModel;
    private final static String TAG = "NewTag123";
    public static ArrayList<String> Items() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add("item #" + (i + 1));
        }
        return list;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_screen, container, false);
        ListView itemsList = view.findViewById(R.id.list_view);
        ArrayList<String> list = Items();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            String line = "item #" + (i + 1);
            map.put(line, R.drawable.question_box);
        }
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity()
                        .getApplication())).get(ItemDataViewModel.class);
        viewModel.createList(this.getContext(), map);
        String itemName = getArguments().getString("itemName");
        viewModel.getLiveData().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                TextView itemN = view.findViewById(R.id.text_view);
                ImageView itemDescription = view.findViewById(R.id.image_view);
                Category list1 = item.getCategory(itemName);
                itemN.setText(list1.itemsName);
                itemDescription.setImageResource(list1.img);
            }
        });
        MyListAdapter adapter = new MyListAdapter(getContext(), R.layout.item_list, list);
        itemsList.setAdapter(adapter);
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getContext(), list.get(position), Toast.LENGTH_LONG).show();
                Log.d(TAG, list.get(position));
            }
        });
        return view;
    }
}
