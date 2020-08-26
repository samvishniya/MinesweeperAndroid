package com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = HighScore.class, version = 2, exportSchema = false)
public abstract class HighScoreRoomDatabase extends RoomDatabase {

    public abstract HighScoreDAO highScoreDAO();




}
