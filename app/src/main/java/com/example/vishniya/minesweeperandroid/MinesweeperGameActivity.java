package com.example.vishniya.minesweeperandroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;

import android.widget.Chronometer;

import android.widget.ImageView;

import android.widget.TextView;

import Logic.Minefield;


// TODO add reset button to go again
// TODO ADD functionality to reset after game over
// TODO INTEGRATE WITH HIGHSCORE database


public class MinesweeperGameActivity extends AppCompatActivity {

    // global variables
    private Chronometer timer;
    private TextView completionTime;

    private int gridSizeNumber;
    private int gridSquaresCount;
    private int screen_width;
    private int screen_height;
    private android.support.v7.widget.GridLayout grid;

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

        timer=findViewById(R.id.timer);

        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        grid = findViewById(R.id.xmlGrid);

        grid.post(new Runnable(){
            @Override
            public void run(){
                populateButtons(gridSizeNumber);
            }
                  }


        );



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
    // todo     Resets the game, sending you back to the mainactivity and clock
     */
    public void gameReset(boolean gameWon) {
        timer.stop();
        timer.getText();

        if (gameWon) {

        } else {

        }
    }


    /*
    creates the buttons for the minesweeper grid
     */
    private void populateButtons(int gridSize) {


        gridSquaresCount = gridSize * gridSize;
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
                android.support.v7.widget.GridLayout.LayoutParams param = new android.support.v7.widget.GridLayout.LayoutParams();

        param.setMargins(1, 1, 0, 0);
                param.width =( grid.getWidth()-param.rightMargin-param.leftMargin)/ gridSize- param.rightMargin - param.leftMargin;
                param.height = param.width;

                param.setGravity(Gravity.FILL_HORIZONTAL);
                param.columnSpec = android.support.v7.widget.GridLayout.spec(columnNum);
                param.rowSpec = android.support.v7.widget.GridLayout.spec(rowNum);


                cellBtn.setLayoutParams(param);
                grid.addView(cellBtn);

                // todo fix these finals
                final int finalRowNum = rowNum;
                final int finalColumnNum = columnNum;
                cellBtn.setOnClickListener(new ImageView.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        // toggle first, then get new image, then disable clickable,
                        // and if this cell was 'safe' toggle a cascade of all neighbouring cells
                        if (gameMineField.revealCell(finalRowNum, finalColumnNum)) {
                            timer.stop();
                            // this gets the time in seconds since game started
                            // todo send this to a highscore database
                            int elapsed = (int) (SystemClock.elapsedRealtime() - timer.getBase()) / 1000;

                            completionTime.setText(String.valueOf(elapsed));
                        }

                    }

                });
            }

        }

    }


}
