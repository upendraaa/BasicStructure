package interview.upendra.com.basicstructure;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import interview.upendra.com.basicstructure.dynamiclist.Item;
import interview.upendra.com.basicstructure.dynamiclist.SelectListener;
import interview.upendra.com.basicstructure.dynamiclist.SubItem;
import interview.upendra.com.basicstructure.dynamiclist.adapter.ItemAdapter;
import interview.upendra.com.basicstructure.dynamiclist.database.DataRepository;
import interview.upendra.com.basicstructure.dynamiclist.database.DatabaseEntity;
import interview.upendra.com.basicstructure.dynamiclist.network.GetDataService;
import interview.upendra.com.basicstructure.dynamiclist.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SelectListener {

    TextView textView;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    AppCompatSpinner spinnerByName, spinnerBySize;

    ArrayList<SubItem> subItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvAlbum);
        spinnerByName = findViewById(R.id.spFilter);
        spinnerBySize = findViewById(R.id.spGridSize);

        spinnerByName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onSelectTitle(spinnerByName.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerBySize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onSelectPosition(Integer.valueOf(spinnerBySize.getSelectedItem().toString()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        getData();
        super.onStart();
    }

    private void setAdapter(Item item){
        this.subItems = item.list;
         itemAdapter = new ItemAdapter(item.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(itemAdapter);


    }


    public void getData(){

        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Item> call = service.getAllData();
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> i, Response<Item> response) {
                Log.d("Response",response.message());
                addToDatabase(response.body());
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                List<SubItem> subItems = getItems();
                Item item = new Item();
                item.list = (ArrayList<SubItem>) subItems;
                setAdapter(item);
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToDatabase(Item item)
    {
        DataRepository dataRepository = new DataRepository(getApplicationContext());

        for(SubItem subItem: item.list)
        {
           dataRepository.insertItem(subItem);
        }
    }

    private List<SubItem> getItems()
    {
        DataRepository dataRepository = new DataRepository(getApplicationContext());

        List<DatabaseEntity> databaseEntities= (List<DatabaseEntity>) dataRepository.fetchAllData();
        ArrayList<SubItem> subItems = new ArrayList<>();

        for(DatabaseEntity databaseEntity:databaseEntities){
            SubItem subItem = new SubItem();
            subItem.album = databaseEntity.album;
            subItem.artist = databaseEntity.artist;
            subItem.name = databaseEntity.name;
            subItems.add(subItem);
        }
        return subItems;

    }

    @Override
    public void onSelectTitle(String title) {
        if(itemAdapter==null)
            return;

        itemAdapter.setFilter(title);
        itemAdapter.updateData(subItems);
    }

    @Override
    public void onSelectPosition(int pos) {
        if(itemAdapter==null)
            return;

        itemAdapter.setSize(pos);
        itemAdapter.updateData(subItems);
    }
}
