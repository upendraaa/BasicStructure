package interview.upendra.com.basicstructure.dynamiclist.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import interview.upendra.com.basicstructure.dynamiclist.SubItem;

public class DataRepository {

    private String DB_NAME = "db_task";

    private AppDatabase appDatabase;
    public DataRepository(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
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

}
