package com.andela.adrian.journalapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adrian on 01/07/2018.
 */

public class ThoughtsAdapter extends RecyclerView.Adapter<ThoughtsAdapter.ThoughtsVH> {
    Context context;
    List<Thought> thoughts;

    OnItemClickListener clickListener;



    @Override
    public ThoughtsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thought_item, parent, false);
        ThoughtsVH viewHolder = new ThoughtsVH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ThoughtsVH holder, int position) {
        holder.title.setText(thoughts.get(position).getTitle());
        holder.thought.setText(thoughts.get(position).getThought());

    }

    @Override
    public int getItemCount() {
        return thoughts.size();
    }

    public class ThoughtsVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, thought;
        public ThoughtsVH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.thought_item_title);
            thought = itemView.findViewById(R.id.thought_item_desc);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


}
