package com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "highscore_table" )public class HighScore {

    @NonNull @PrimaryKey @ColumnInfo(name="nickname")
    private String nickname;

    // not sure how to use two columns, how do i input inital data? a map?

    @NonNull @ColumnInfo(name="score")
    private int score;


    public HighScore(@NonNull String nickname, @NonNull int score){
        this.nickname=nickname;
      this.score=score;
    }

       public int getScore(){
        return score;
    }

// ignore this constructor its for testing without scores, just to check rectlcerview works for strings
    /*
    public HighScore(@NonNull String nickname){
        this.nickname=nickname;
    }
*/




    public String getNickname(){
        return nickname;
    }

}
