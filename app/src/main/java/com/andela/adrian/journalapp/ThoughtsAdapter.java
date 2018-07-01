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

    private ItemClickListener clickListener;



    public ThoughtsAdapter(Context context, List<Thought> thoughts) {
        this.context = context;
        this.thoughts = thoughts;

    }


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
            title = (TextView) itemView.findViewById(R.id.thought_item_title);
            thought = (TextView) itemView.findViewById(R.id.thought_item_desc);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }





    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
