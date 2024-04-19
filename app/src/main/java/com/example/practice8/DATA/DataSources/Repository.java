package com.example.practice8.DATA.DataSources;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.example.practice8.DATA.DataSources.Files.AppSpecificDataSources;
import com.example.practice8.DATA.DataSources.Files.CommonFileDataSources;
import com.example.practice8.DATA.DataSources.Room.AppDatabase;
import com.example.practice8.DATA.DataSources.Room.DAO.ListDAO;
import com.example.practice8.DATA.DataSources.Room.Entities.Category;
import com.example.practice8.DATA.DataSources.SharedPreferences.SharedPreferencesDS;
import com.example.practice8.DATA.Model.DataListRecycler;
import java.util.List;
import java.util.Map;

public class Repository {
    private AppSpecificDataSources appSpecificDataSources;
    private CommonFileDataSources commonFileDataSources;
    private SharedPreferencesDS LocalDS;
    private AppDatabase database;
    public Repository() {}
    public Repository(Context context, String appSpecificDSFileName, String commonFDSFileName) {
        this.appSpecificDataSources = new AppSpecificDataSources(context, appSpecificDSFileName);
        this.commonFileDataSources = new CommonFileDataSources(context, commonFDSFileName);
    }
    public void writeAppSpecDS(String inputContent) {
        appSpecificDataSources.writeAppSpecificDS("\n" + inputContent);
    }
    public String readAppSpecDS() { return appSpecificDataSources.readAppSpecificDS(); }
    public boolean writeCommonFileDS(String inputContent) {
        return commonFileDataSources.writeContent("\n" + inputContent);
    }
    public String readCommonFileDS() { return commonFileDataSources.readFile(); }
    private DataSource dataSource = new LocalDataSource();
    public LiveData<List<DataListRecycler>> getItems() { return dataSource.getItems(); }
    public LiveData<DataListRecycler> getItem(int itemID) { return dataSource.getItem(itemID); }
    public void createLocalDS(Context context) {
        if (LocalDS == null) {
            LocalDS = new SharedPreferencesDS(context);
        }
    }
    public String getLocalName() {
        if (LocalDS == null) return null;
        else return LocalDS.getString("Name");
    }
    public Integer getLocalImg() {
        if (LocalDS == null) return null;
        else return LocalDS.getInt("Img");
    }
    public void setLocalName(String name) {
        if (LocalDS == null) return;
        else LocalDS.writeString("Name", name);
    }
    public void setLocalImg(int img) {
        if (LocalDS == null) return;
        else LocalDS.writeInt("Img", img);
    }
    public DataListRecycler getItem() {
        if (LocalDS == null) return null;
        else return new DataListRecycler(LocalDS.getString("Name"), LocalDS.getInt("Img"));
    }
    public void createDatabase(Context context, Map<String, Integer> values) {
        if (database != null) return;
        database = Room.databaseBuilder(context, AppDatabase.class, "List")
                .allowMainThreadQueries().build();
        ListDAO listDAO = database.listDAO();
        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            insertCategory(entry.getKey(), entry.getValue());
        }
    }
    public Category getCategory(String itemName) {
        return database.listDAO().getCategoryByName(itemName);
    }
    public void insertCategory(String itemName, int img) {
        Category cathegory= new Category();
        cathegory.itemsName = itemName;
        cathegory.img = img;
        database.listDAO().insertCategory(cathegory);
    }
}