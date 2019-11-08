package interview.upendra.com.basicstructure;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import interview.upendra.com.basicstructure.dynamiclist.ArtistViewModel;
import interview.upendra.com.basicstructure.dynamiclist.Item;
import interview.upendra.com.basicstructure.dynamiclist.SelectListener;
import interview.upendra.com.basicstructure.dynamiclist.SubItem;
import interview.upendra.com.basicstructure.dynamiclist.ViewModelFactory;
import interview.upendra.com.basicstructure.dynamiclist.adapter.ItemAdapter;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements SelectListener {

    TextView textView;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    AppCompatSpinner spinnerByName, spinnerBySize;

    ArrayList<SubItem> subItems = new ArrayList<>();

    ArtistViewModel artistViewModel;
    Item item;

    private ViewModelFactory mViewModelFactory;

    private final CompositeDisposable mDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvAlbum);
        spinnerByName = findViewById(R.id.spFilter);
        spinnerBySize = findViewById(R.id.spGridSize);

        mViewModelFactory = new ViewModelFactory();
        itemAdapter = new ItemAdapter();
        itemAdapter.setData(new ArrayList<SubItem>());

        artistViewModel = new ViewModelProvider(this, mViewModelFactory).get(ArtistViewModel.class);
        artistViewModel.init();

        artistViewModel.getArtistRepository().observe(  this, new Observer<Item>() {
            @Override
            public void onChanged(Item response) {
                 item = response;
                 if(item!=null)
                     subItems = item.list;

                itemAdapter.setData(subItems);
                itemAdapter.notifyDataSetChanged();
            }
        });


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

        setAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setAdapter(){
         itemAdapter.setData(subItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(itemAdapter);


    }



    @Override
    public void onSelectTitle(String title) {
        if(itemAdapter==null)
            return;

        itemAdapter.setFilter(title);
        itemAdapter.setData(subItems);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onSelectPosition(int pos) {
        if(itemAdapter==null)
            return;

        itemAdapter.setSize(pos);
        itemAdapter.setData(subItems);
        recyclerView.setAdapter(itemAdapter);
    }
}
