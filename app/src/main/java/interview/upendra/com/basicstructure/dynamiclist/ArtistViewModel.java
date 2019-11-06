package interview.upendra.com.basicstructure.dynamiclist;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import interview.upendra.com.basicstructure.dynamiclist.database.DataRepository;
import interview.upendra.com.basicstructure.dynamiclist.network.NetworkRepository;

public class ArtistViewModel extends ViewModel {

    MutableLiveData<Item> itemMutableLiveData;

    private NetworkRepository networkRepository;
    private DataRepository dataRepository;

    public void init(){
        if (itemMutableLiveData != null){
            return;
        }
        networkRepository = networkRepository.getInstance();
        dataRepository = new DataRepository();

        itemMutableLiveData = networkRepository.getItem();

        if(itemMutableLiveData == null || itemMutableLiveData.getValue()==null){
            List<SubItem> subItems = dataRepository.getItems();
            Item item = new Item();
            item.list = (ArrayList<SubItem>) subItems;
            if(subItems!=null ){
                itemMutableLiveData = new MutableLiveData<>();
            itemMutableLiveData.setValue(item);}

        }else{
            if(itemMutableLiveData.getValue() == null)
            {
                for(SubItem subItem: itemMutableLiveData.getValue().list)
                {
                    dataRepository.insertItem(subItem);
                }
            }

        }

    }

    public LiveData<Item> getArtistRepository() {
        return itemMutableLiveData;
    }

}
