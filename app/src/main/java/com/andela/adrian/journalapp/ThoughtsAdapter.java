package com.andela.adrian.journalapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adrian on 01/07/2018.
 */

public class ThoughtsAdapter extends RecyclerView.Adapter<ThoughtsAdapter.ThoughtsVH> {



    @Override
    public ThoughtsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ThoughtsVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ThoughtsVH extends RecyclerView.ViewHolder {
        public ThoughtsVH(View itemView) {
            super(itemView);
        }
    }
}
