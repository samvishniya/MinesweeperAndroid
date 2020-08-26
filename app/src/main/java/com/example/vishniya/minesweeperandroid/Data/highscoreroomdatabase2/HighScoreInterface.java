package com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vishniya.minesweeperandroid.R;

import java.util.List;

public class HighScoreInterface extends AppCompatActivity {

    private EditText nicknameEntry;
    private HighScoreViewModel highScoreViewModel;
    // todo this score should be removed and replaced by an intent extra fromthe game's activity
    private int newScore = 999;

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int extraScore = intent.getIntExtra("EXTRA_SCORE",-1);

        if(extraScore!=-1){
            newScore =extraScore;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int extraScore = intent.getIntExtra("EXTRA_SCORE",-1);

        if(extraScore!=-1){
            newScore =extraScore;

        }




        nicknameEntry=findViewById(R.id.nickname_entry_edittext);

        RecyclerView recyclerView = findViewById(R.id.highscore_recyclerview);
        final HighScoreListAdapter adapter = new HighScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        // todo fix this deprecated
        highScoreViewModel = ViewModelProviders.of(this).get(HighScoreViewModel.class);

        highScoreViewModel.getAllHighScores().observe(this, new Observer<List<HighScore>>() {
            @Override
            public void onChanged(@Nullable final List<HighScore> nicknames) {
                // Update the cached copy of the words in the adapter.
                adapter.setNicknames(nicknames);
            }
        });


    }

    public void saveHighscore(View view) {
        // creating a new highscore using input name
        // todo also use a int highscore from system
        String nickname=nicknameEntry.getText().toString();
        if(nicknameEntry!=null){
            HighScore highScore = new HighScore(nickname, newScore);
            highScoreViewModel.insert(highScore);
            Toast.makeText(this,"Your highscore is saved", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"A nickname must be entered in order to save the highscore", Toast.LENGTH_SHORT).show();
        }
    }
}
