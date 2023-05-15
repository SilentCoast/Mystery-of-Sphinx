package com.template;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {
    ArrayList<Item> items;
    LayoutInflater layoutInflater;
    ItemClickListener mClickListener;
    public ItemsRecyclerAdapter(Context context,ArrayList<Item> items){
        layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int positionInList = position % items.size();
        holder.imageView.setImageDrawable(items.get(positionInList).getImage());
    }

    public Item getItem(int id){
        return items.get(id);
    }


    @Override
    public int getItemCount() {
        return  Integer.MAX_VALUE;
        //return items.size();
    }

    public void setClicklistener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recyclerImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener!=null) mClickListener.onItemClick(view,getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }
}
