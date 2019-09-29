package interview.upendra.com.basicstructure.dynamiclist.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoAccess {

    @Insert
    Long insertTask(DatabaseEntity note);


    @Query("SELECT * FROM DatabaseEntity")
    LiveData<List<DatabaseEntity>> fetchAllData();

    @Query("SELECT * FROM DatabaseEntity WHERE album =:album")
    LiveData<List<DatabaseEntity>> fetchDataByAlbum(String album);

    @Query("SELECT * FROM DatabaseEntity WHERE artist =:artist")
    LiveData<List<DatabaseEntity>> fetchDataByArtist(String artist);

    @Update
    void updateTask(DatabaseEntity note);


    @Delete
    void deleteTask(DatabaseEntity note);
}
