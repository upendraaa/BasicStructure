package interview.upendra.com.basicstructure.dynamiclist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {

    @SerializedName("data")
    public ArrayList<SubItem> list;
}
