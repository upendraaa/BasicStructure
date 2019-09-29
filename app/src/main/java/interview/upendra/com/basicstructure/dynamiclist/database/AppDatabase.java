package interview.upendra.com.basicstructure.dynamiclist.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DatabaseEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
