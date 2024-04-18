package com.example.practice8.UI.View;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.practice8.DATA.DataSources.Repository;
import com.example.practice8.R;

public class FirstScreen extends Fragment {
    private final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_screen, container, false);
        TextView itemName = view.findViewById(R.id.textViewLinearLayout);
        Repository repository = new Repository(this.getContext(), "file",
                "file1");
        //repository.writeAppSpecDS("Что-то написано");
        //itemName.setText(repository.readAppSpecDS());
        /* if (!repository.writeCommonFileDS("Что-то написано")) {
            requestPermission();
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repository.writeCommonFileDS("Что-то написано");
        }
        itemName.setText(repository.readAppSpecDS()); */
        ImageView imageView = view.findViewById(R.id.image_view);
        repository.createLocalDS(this.getContext());
        repository.setLocalName("Ежедневная награда");
        repository.setLocalImg(R.drawable.question_box);
        itemName.setText(repository.getItem().getName());
        imageView.setImageResource(repository.getItem().getImage());
        Button toSecondScreen = view.findViewById(R.id.to_second_screen);
        Button toThirdScreen = view.findViewById(R.id.to_third_screen);
        toSecondScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true).replace(R.id.fragment_container_view,
                                new SecondScreen()).addToBackStack(null).commit();
            }
        });
        toThirdScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true).replace(R.id.fragment_container_view,
                                new ThirdScreen()).addToBackStack(null).commit();
            }
        });
        return view;
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "Требуется разрешение на запись на карту памяти", Toast.LENGTH_LONG).show();
        }
        else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            Log.e("value", "Разрешение есть.");
        }
    }
}