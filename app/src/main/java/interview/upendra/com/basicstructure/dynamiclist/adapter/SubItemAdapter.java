package interview.upendra.com.basicstructure.dynamiclist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import interview.upendra.com.basicstructure.R;
import interview.upendra.com.basicstructure.dynamiclist.SubItem;

public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemHolder> {


    ArrayList<SubItem> subItems;

    public SubItemAdapter(ArrayList<SubItem> list)
    {

       this.subItems = list;
    }



    @NonNull
    @Override
    public SubItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_item_view,parent,false);
        return new SubItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemHolder holder, int position) {
        SubItem subItem = subItems.get(position);
        holder.textView.setText(subItem.name);

    }

    @Override
    public int getItemCount() {
        return subItems.size();
    }

    class SubItemHolder extends RecyclerView.ViewHolder{


        public TextView textView;
        public SubItemHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = (itemView).findViewById(R.id.tvSubItem);
        }
    }
}
