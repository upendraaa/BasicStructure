package interview.upendra.com.basicstructure.dynamiclist.network;

import androidx.lifecycle.MutableLiveData;
import interview.upendra.com.basicstructure.dynamiclist.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {


    private static NetworkRepository networkRepository;
    MutableLiveData<Item> itemMutableLiveData;


    public static NetworkRepository getInstance(){
        if (networkRepository == null){
            networkRepository = new NetworkRepository();
        }
        return networkRepository;
    }

    private GetDataService getDataService;

    public NetworkRepository(){
        getDataService = RetrofitInstance.cteateService(GetDataService.class);
    }

    public MutableLiveData<Item> getItem(){
        itemMutableLiveData = new MutableLiveData<>();
        getDataService.getAllData().enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call,
                                   Response<Item> response) {
                if (response.isSuccessful()){
                    itemMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                itemMutableLiveData.setValue(null);
            }
        });
        return itemMutableLiveData;
    }
}
