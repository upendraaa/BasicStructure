package interview.upendra.com.basicstructure.dynamiclist;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubItem implements Serializable {

    @SerializedName("Artist")
    public String artist;

    @SerializedName("Album")
    public String album;

    @SerializedName("Name")
    public String name;
}
