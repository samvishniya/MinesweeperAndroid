package com.example.vishniya.minesweeperandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Chronometer;

import android.widget.GridLayout;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2.HighScoreInterface;

import Logic.Minefield;

/*
Win low
Game ->popupwindow -> showhighscore but no entry

Win high ->popup -> enter highscore

Lose -> popup -> back to game setup menu
 */

// TODO add reset button to go again
// TODO ADD functionality to reset after game over
// TODO INTEGRATE WITH HIGHSCORE database


public class MinesweeperGameActivity extends AppCompatActivity {

    // global variables
    private Chronometer timer;
    private TextView completionTime;


    public static final String EXTRA_SCORE = "com.example.vishniya.minesweeperandroid.EXTRA.score";


    private int gridSizeNumber;

    private androidx.gridlayout.widget.GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove actionbar at top
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        Intent intent = getIntent();
        gridSizeNumber = intent.getIntExtra(MainActivity.EXTRA_GRID_SIZE, 2);

        setContentView(R.layout.minesweeperview);

        completionTime = findViewById(R.id.textView);

        timer = findViewById(R.id.timer);

        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        grid = findViewById(R.id.xmlGrid);

        grid.post(new Runnable() {
                      @Override
                      public void run() {
                          populateButtons(gridSizeNumber);
                      }
                  }


        );

    }

    /*
Returns the elapsed time in ms
 */

    private int findElapsedTime() {
        return (int) (SystemClock.elapsedRealtime() - timer.getBase());
    }

/*
Returns a score based on the size of the grid and the time taken
 */

    private int calcScore() {
        return gridSizeNumber * gridSizeNumber * 10000 / (findElapsedTime() / 10);

    }

    /*
    launches HighScoreActivity and sends an intent with your highscore (time)
     */

    private void showGameFinishedPopupWindow(final Boolean gameWon, final int score) {


        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        // todo what should that nukll be?
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(grid, Gravity.CENTER, 0, 0);
        // with a popupwindow you can only access its contents using getcontentview(
        TextView popupText = popupWindow.getContentView().findViewById(R.id.popup_text);

        String endMessage = "You Lost";
        if (gameWon) {
            endMessage = "You won " + "with a score of " + score;
        } else {

        }
        popupText.setText(endMessage);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                popupWindow.dismiss();
                if (gameWon) {
                    startHighScoreActivity(score);
                } else {
                    gameReset();
                }

                return true;
            }
        });

    }


    private void startHighScoreActivity(int score) {
           Intent intent = new Intent(this, HighScoreInterface.class);
            intent.putExtra("EXTRA_SCORE", score);

            startActivity(intent);


    }


/*
    private void findScreenResolution() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screen_width = dm.widthPixels;
        screen_height = dm.heightPixels;

    }
*/

    /*
     * Ends the game/grid
     * if a loss, shows textview then sends you to main
     * if a win, shows at extview then sends you to the highscoreactivity
     * todo     Resets the game, sending you back to the mainactivity and clock
     *  todo reset game if won, but towards highscore database
     *   todo change this to fit in with new highscore module not original activity -- or just delete it?
     *
     */
    public void gameReset() {


        finish();
    }


    /*
    creates the buttons for the minesweeper grid
     */
    private void populateButtons(final int gridSize) {


        final Minefield gameMineField = new Minefield(gridSize, this);

        //  table.layout

        //  grid.setAlignmentMode(GridLayout.ALIGN_MARGINS);
        grid.setColumnCount(gridSize);
        grid.setRowCount(gridSize);
        grid.setUseDefaultMargins(false);


        // int cellSize = grid.getWidth()/gridSize - grid.get

        for (int rowNum = 0; rowNum <= gridSize - 1; rowNum++) {


            for (int columnNum = 0; columnNum <= gridSize - 1; columnNum++) {

                // add imageviews according to size first,
                // ImageView cellBtn = new ImageView(this);
                //cellBtn.setImageResource(R.drawable.gridsquare0);

                // then add images to imageview

                ImageView cellBtn = (gameMineField.getCellContent(rowNum, columnNum).getDisplayedImage());
                //  cellBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //  cellBtn.setAdjustViewBounds(true);
                //  cellBtn.setMaxWidth();
               GridLayout.LayoutParams param = new GridLayout.LayoutParams();

                param.setMargins(1, 1, 0, 0);
                param.width = (grid.getWidth() - param.rightMargin - param.leftMargin) / gridSize - param.rightMargin - param.leftMargin;
                param.height = param.width;

                param.setGravity(Gravity.FILL_HORIZONTAL);
                param.columnSpec = GridLayout.spec(columnNum);
                param.rowSpec = GridLayout.spec(rowNum);


                cellBtn.setLayoutParams(param);
                grid.addView(cellBtn);

                // todo fix these finals
                final int finalRowNum = rowNum;
                final int finalColumnNum = columnNum;
                cellBtn.setOnClickListener(new ImageView.OnClickListener() {

                    // reveals the contents of the cell
                    // depending its the final cell, or a mine, or both or neither, game may stop
                    // calculating cells reamining is difficult due to cascade method inside minefield - now done internally in minefield class.
                    @Override
                    public void onClick(View v) {
                        // toggle first, then get new image, then disable clickable,
                        // and if this cell was 'safe' toggle a cascade of all neighbouring cells
                        if (gameMineField.revealCell(finalRowNum, finalColumnNum)) {
                            timer.stop();

                            completionTime.setText(String.valueOf(findElapsedTime() / 1000));
                            // score of 0 since lost
                            showGameFinishedPopupWindow(false, 0);
                        }
                        if (gameMineField.getUnrevealedCellsCount() < gridSize + 1) {
                            timer.stop();
                            int score = calcScore();
                            //todo maybe change this completion time text view...?
                            completionTime.setText(String.valueOf(findElapsedTime() / 1000));
                            showGameFinishedPopupWindow(true, score);
                        }
                    }

                });
            }

        }

    }


}
