package com.example.practice8.DATA.DataSources.Files;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class CommonFileDataSources {
    private final String fileName;
    private final Context context;
    private File file;
    public CommonFileDataSources(Context context, String fileName) {
        this.fileName = fileName;
        this.context = context;
    }
    public boolean writeContent(String inputContent) {
        if (!inputContent.isEmpty()) {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                if (checkPermission()) {
                    File sdcard = Environment.getExternalStorageDirectory();
                    if (file == null || !file.exists()) {
                        String dir = sdcard.getAbsolutePath() + "/MyFolder/";
                        file = new File(dir + fileName + ".txt");
                    }
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(inputContent.getBytes());
                        fileOutputStream.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
    public String readFile() {
        if (file == null || !file.exists()) return null;
        FileInputStream fileInputStream = null;
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
