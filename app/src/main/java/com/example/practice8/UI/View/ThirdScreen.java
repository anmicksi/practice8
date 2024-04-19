package com.example.practice8.UI.View;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.practice8.DATA.Model.DataListRecycler;
import com.example.practice8.R;
import com.example.practice8.UI.ViewModel.ItemDataViewModel;

public class ThirdScreen extends Fragment {
    private ItemDataViewModel viewModel;
    public ArrayList<DataListRecycler> Items = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_screen, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < 200; i++) {
            String line = "item #" + (i+1);
            DataListRecycler list = new DataListRecycler(line, R.drawable.question_box);
            Items.add(list);
        }
        RecyclerView itemsList = view.findViewById(R.id.recycler_view);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(getContext(), Items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        itemsList.setLayoutManager(layoutManager);
        itemsList.setAdapter(adapter);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            String line = "item #" + (i+1);
            map.put(line, R.drawable.question_box);
        }
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity()
                        .getApplication())).get(ItemDataViewModel.class);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), item -> {
            adapter.notifyDataSetChanged();
        });
        itemsList.setAdapter(adapter);
    }
}
