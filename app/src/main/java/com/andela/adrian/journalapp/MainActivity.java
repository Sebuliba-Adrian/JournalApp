package com.andela.adrian.journalapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;

    ThoughtsAdapter adapter;
    List<Thought> thoughts = new ArrayList<>();

    long initialCount;

    int modifyPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_list);
        fab = findViewById(R.id.fab);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        recyclerView.setLayoutManager(gridLayoutManager);

        initialCount = Thought.count(Thought.class);

        if (savedInstanceState != null)
            modifyPos = savedInstanceState.getInt("modify");


        if (initialCount >= 0) {

            thoughts = Thought.listAll(Thought.class);

            adapter = new ThoughtsAdapter(MainActivity.this, thoughts);
            recyclerView.setAdapter(adapter);

            if (thoughts.isEmpty())
                Snackbar.make(recyclerView, "No thoughts added.", Snackbar.LENGTH_LONG).show();

        }

        // tinting FAB icon
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_add_24dp);
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, Color.WHITE);
            DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);

            fab.setImageDrawable(drawable);

        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AddThoughtActivity.class);
                startActivity(i);

            }
        });


        // Handling swipe to delete
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //Remove swiped item from list and notify the RecyclerView

                final int position = viewHolder.getAdapterPosition();
                final Thought thought = thoughts.get(viewHolder.getAdapterPosition());
                thoughts.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(position);

                thought.delete();
                initialCount -= 1;

                Snackbar.make(recyclerView, "Thought deleted", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                thought.save();
                                thoughts.add(position, thought);
                                adapter.notifyItemInserted(position);
                                initialCount += 1;

                            }
                        })
                        .show();
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        adapter.SetOnItemClickListener(new ThoughtsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("Main", "click");

                Intent i = new Intent(MainActivity.this, AddThoughtActivity.class);
                i.putExtra("isEditing", true);
                i.putExtra("thought_title", thoughts.get(position).title);
                i.putExtra("thought", thoughts.get(position).thought);
                i.putExtra("thought_time", thoughts.get(position).time);

                modifyPos = position;

                startActivity(i);
            }
        });

    }



}
