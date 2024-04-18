package com.example.practice8.DATA.DataSources.Files;
import android.content.Context;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AppSpecificDataSources {
    private final Context context;
    private final File file;
    private final String fileName;
    public AppSpecificDataSources(Context context, String filename) {
        this.context = context;
        this.fileName = filename;
        this.file = new File(context.getFilesDir(), filename);
    }
    public String readAppSpecificDS() {
        if (!file.exists()) return null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(fileName);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String contents = null;
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            contents = stringBuilder.toString();
        }
        return contents;
    }
    public void writeAppSpecificDS(@NonNull String fileContent) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            fileOutputStream.write(fileContent.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
