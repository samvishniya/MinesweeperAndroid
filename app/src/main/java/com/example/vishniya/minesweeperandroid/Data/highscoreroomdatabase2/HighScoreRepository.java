package com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class HighScoreRepository {

    private HighScoreDAO highScoreDAO;
    private LiveData<List<HighScore>> allHighScores;

    HighScoreRepository(Application application){
        HighScoreRoomDatabase db = getDatabase(application);
        highScoreDAO=db.highScoreDAO();
        allHighScores=highScoreDAO.getAllHighScores();

    }

    // wrapped for getall highscore method
    public LiveData<List<HighScore>> getAllHighScores(){
        return allHighScores;
    }

    // wrapper for insert method - must be done off-mainthread
    public void insert(final HighScore highScore){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                highScoreDAO.insert(highScore);
            }
        });
    }



    private static Context context;
    // todo callback - onopen neds to reload database , ideally without deleting all content needs to be done off main thread, using alternative to asyntaskprivate static class PopulateDbAsync {
    // todo only load the intial data if db is empty? otherwise need to load the actual highscores
    // todo decide if to put the callbakc method and getDatabase instadd database class and make it private instead


    private static HighScoreRoomDatabase INSTANCE;

    public static HighScoreRoomDatabase getDatabase( Context context){
        if (INSTANCE == null) {
            HighScoreRepository.context =context;
            synchronized (HighScoreRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    // this code uses rooms db builder to create a roomdb object called word_database in the app context from the wordroomdb class
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HighScoreRoomDatabase.class, "highscore_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            // this callback is defined in this class, it populates the db in the background
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



    public static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @Override

        // if the db is found to be empty when opened we want to load some initial highscore data - this must be done off the ui thread
        // otherwise it should save previous db entries
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d("DATABASE", "Database opening");
            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {

                @Override
                public void run() {
                    // if anyhighscore array length <1 (i.e array empty) put initial scores
                   if (getDatabase(context).highScoreDAO().getAnyHighScore().length <1){
                       addInitialHighScores(context);

                   }
                }
            });
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        //    String[] nicknames = {"mario", "luigi", "peach"};

            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {

                @Override
                public void run() {
                  addInitialHighScores(context);
                }
            });
        }
    };

    private static void addInitialHighScores(Context context){

        // first clear database (this is a relic from previous exercise)
      //  getDatabase(context).highScoreDAO().deleteAll();

        List<HighScore> nicknameList= new ArrayList<>();

        nicknameList.add((new HighScore("LUIGI", 100)));
        nicknameList.add((new HighScore("MARIO", 200)));

        // is this line legit? its calling the same method that itself is being called by...
        getDatabase(context).highScoreDAO().insert(nicknameList);

    }


}
