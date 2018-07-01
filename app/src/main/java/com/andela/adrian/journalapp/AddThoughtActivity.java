package com.andela.adrian.journalapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class AddThoughtActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;

    EditText etTitle, etDesc;

    String title, note;
    long time;

    boolean editingThought;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thought);

        toolbar = findViewById(R.id.addnote_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_clear_24dp);

        getSupportActionBar().setTitle("Add new note");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        etTitle = findViewById(R.id.addnote_title);
        etDesc = findViewById(R.id.addnote_desc);

        fab = findViewById(R.id.addnote_fab);

        editingThought = getIntent().getBooleanExtra("isEditing", false);
        if (editingThought) {
            title = getIntent().getStringExtra("thought_title");
            note = getIntent().getStringExtra("thought");
            time = getIntent().getLongExtra("thought_time", 0);

            etTitle.setText(title);
            etDesc.setText(note);

        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Add thought to DB

                String newTitle = etTitle.getText().toString();
                String newDesc = etDesc.getText().toString();
                long newTime = System.currentTimeMillis();


                /**
                 * TODO: Check if thought exists before saving
                 */
                if (!editingThought) {
                    Thought note = new Thought(newTitle, newDesc, newTime);
                    note.save();
                } else {
                    List<Thought> thoughts = Thought.find(Thought.class, "title = ?", title);
                    if (thoughts.size() > 0) {

                        Thought thought = thoughts.get(0);
                        thought.title = newTitle;
                        thought.thought = newDesc;
                        thought.time = newTime;

                        thought.save();

                    }

                }

                finish();


            }
        });


    }
}
