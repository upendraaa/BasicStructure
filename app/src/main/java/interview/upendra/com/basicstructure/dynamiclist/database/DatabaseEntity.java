package interview.upendra.com.basicstructure.dynamiclist.database;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DatabaseEntity implements Serializable {

        @PrimaryKey(autoGenerate = true)
        public int id;

    @ColumnInfo(name = "artist")
    public String artist;

    @ColumnInfo(name = "album")
    public String album;

    @ColumnInfo(name = "name")
    public String name;




}
