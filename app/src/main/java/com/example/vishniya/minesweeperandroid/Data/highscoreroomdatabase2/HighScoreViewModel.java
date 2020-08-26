package com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HighScoreViewModel extends AndroidViewModel {
    private HighScoreRepository repository;
    private LiveData<List<HighScore>> allHighScores;


    public HighScoreViewModel(@NonNull Application application) {
        super(application);
        repository = new HighScoreRepository(application);
        allHighScores=repository.getAllHighScores();
    }

    // wrapper abstraction of method instead repository
    public LiveData<List<HighScore>> getAllHighScores() {
        return allHighScores;
    }

    // wrapper abstraction of insert method

    public void insert(HighScore highScore){
        repository.insert(highScore);
    }

}
