package interview.upendra.com.basicstructure.dynamiclist.network;

import interview.upendra.com.basicstructure.dynamiclist.Constant;
import interview.upendra.com.basicstructure.dynamiclist.Item;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET(Constant.GET_DATA)
    Call<Item> getAllData();
}