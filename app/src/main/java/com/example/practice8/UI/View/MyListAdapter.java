package com.example.practice8.UI.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.example.practice8.R;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {
    private final LayoutInflater inflater;
    private final int layout;
    private final ArrayList<String> items;
    public MyListAdapter(Context context, int resource,
                         ArrayList<String> items) {
        super(context, resource, items);
        this.items = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater
                .inflate(this.layout, parent, false);
        TextView textView = view.findViewById(R.id.item_list_text_view);
        ImageView imageView = view.findViewById(R.id.item_list_image_view);
        String item = items.get(position);
        textView.setText(item);
        imageView.setImageDrawable(parent.getContext().getDrawable(R.drawable.question_box));
        return view;
    }

}
