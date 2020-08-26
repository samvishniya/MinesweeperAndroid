package com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HighScoreDAO {


    /*
    Gets one highscore into an array
     */

    @Query("SELECT * from highscore_table LIMIT 1")
    HighScore[] getAnyHighScore();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(HighScore highscore);

    // to insert multiple - takes list<HighScore>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<HighScore> highScores);

    // custom query to delete all entries from table
    @Query("DELETE FROM highscore_table")
    void deleteAll();

    // todo change to sort bh score
    // custom query to get all scores and sort them descending
    @Query("SELECT*from highscore_table ORDER BY score DESC")
    LiveData<List<HighScore>> getAllHighScores();
}
