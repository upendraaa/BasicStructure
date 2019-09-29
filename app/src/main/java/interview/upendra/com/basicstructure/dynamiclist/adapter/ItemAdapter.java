package interview.upendra.com.basicstructure.dynamiclist.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import interview.upendra.com.basicstructure.R;
import interview.upendra.com.basicstructure.dynamiclist.SubItem;

import static androidx.recyclerview.widget.RecyclerView.RecycledViewPool;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>  {


    private RecycledViewPool viewPool = new RecycledViewPool();
    private ArrayList<SubItem> itemArrayList;
    private int columnCount = 1;
    private String filter = "Album";
    SubItemAdapter subItemAdapter;

    public ItemAdapter(ArrayList<SubItem> list)
    {
        this.itemArrayList = list;
    }

    public void setFilter(String filter)
    {
        this.filter = filter;
    }

    public void setSize(int size)
    {
        this.columnCount = size;
    }


    public void updateData(ArrayList<SubItem> list)
    {
        this.itemArrayList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        SubItem item = itemArrayList.get(position);
        holder.tvTitle.setText(item.album);

        if(!TextUtils.isEmpty(filter) && "Album".equalsIgnoreCase(filter))
        {
            subItemAdapter = new SubItemAdapter(getFilteredItemsByName(item.album));
        }else{
            subItemAdapter = new SubItemAdapter(getFilteredItemsByName(item.artist));
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                holder.rvSubItem.getContext(),columnCount);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rvSubItem.setLayoutManager(gridLayoutManager);
        holder.rvSubItem.setAdapter(subItemAdapter);
        holder.rvSubItem.setRecycledViewPool(viewPool);


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public ArrayList<SubItem> getFilteredItemsByName(final String name)
    {
        ArrayList<SubItem> list = new ArrayList<>();
        for(SubItem subItem: itemArrayList)
        {
            if(subItem.album.equalsIgnoreCase(name))
            {
                list.add(subItem);
            }
        }

        return list;
    }

    public ArrayList<SubItem> getFilteredItemsByArtist(final String artist)
    {
        ArrayList<SubItem> list = new ArrayList<>();
        for(SubItem subItem: itemArrayList)
        {
            if(subItem.artist.equalsIgnoreCase(artist))
            {
                list.add(subItem);
            }
        }

        return list;
    }

    class ItemHolder extends ViewHolder{

        TextView tvTitle;
        RecyclerView rvSubItem;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            this.tvTitle = (itemView).findViewById(R.id.tvTitle);
            this.rvSubItem = (itemView).findViewById(R.id.rvGrid);
        }
    }
}

