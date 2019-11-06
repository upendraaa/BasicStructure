package interview.upendra.com.basicstructure.dynamiclist.database;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import interview.upendra.com.basicstructure.BasicApplication;
import interview.upendra.com.basicstructure.dynamiclist.SubItem;

public class DataRepository {

    private String DB_NAME = "db_task";
    private MutableLiveData<ArrayList<SubItem>> subItemMutableLiveData;

    private AppDatabase appDatabase;
    public DataRepository() {
        appDatabase = Room.databaseBuilder(BasicApplication.getContext(), AppDatabase.class, DB_NAME).build();
    }


    public void insertItem(SubItem subItem) {
        DatabaseEntity databaseEntity = new DatabaseEntity();
        databaseEntity.album = databaseEntity.album;
        databaseEntity.artist = databaseEntity.artist;
        databaseEntity.name = databaseEntity.name;
    }

    private void insertTask(final DatabaseEntity databaseEntity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.daoAccess().insertTask(databaseEntity);
                return null;
            }
        }.execute();
    }


    public LiveData<List<DatabaseEntity>> fetchAllData() {
        return appDatabase.daoAccess().fetchAllData();
    }

    public LiveData<List<DatabaseEntity>> fetchAllDataByAlbum(String album) {
        return appDatabase.daoAccess().fetchDataByAlbum(album);
    }

    public LiveData<List<DatabaseEntity>> fetchAllDataByArtist(String artist) {
        return appDatabase.daoAccess().fetchDataByArtist(artist);
    }


    public List<SubItem> getItems()
    {
        LiveData<List<DatabaseEntity>> databaseEntities=  fetchAllData();
        ArrayList<SubItem> subItems = new ArrayList<>();
        if(databaseEntities==null || databaseEntities.getValue()==null)
            return subItems;

        for(DatabaseEntity databaseEntity:databaseEntities.getValue()){
            SubItem subItem = new SubItem();
            subItem.album = databaseEntity.album;
            subItem.artist = databaseEntity.artist;
            subItem.name = databaseEntity.name;
            subItems.add(subItem);
        }
        return subItems;

    }

}
