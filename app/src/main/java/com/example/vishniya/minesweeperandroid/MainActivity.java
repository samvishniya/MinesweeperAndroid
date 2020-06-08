package com.example.vishniya.minesweeperandroid;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import Logic.InputFilterMinMax;
import Logic.Minecell;
import Logic.Minefield;

public class MainActivity extends AppCompatActivity {

    // global variables

    private EditText userInputField;
    private Button submitGridSizeButton;
    private TextView gridSizeQuestionText;
    private Chronometer timer;


    private ViewGroup layout;


// TODO add reset button to go again
    // TODO ADD functionality to reset after game over
    // TODO INTEGRATE WITH HIGHSCORE database


    private int gridSquaresCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign id values to each variable using findviewbyid to initialize them
        // remember to typecast always when using findviewbyid
        userInputField = findViewById(R.id.chooseGameSizeText);
        // this ensures user only enters num between 1-20 BUT HAVING difficulty:/
        //TODO    want to ensure no entry less than 3, but need them to be able to type the digits 1 and 2...
        // TODO add toast upon entering wrong number
        userInputField.setFilters(new InputFilter[]{new InputFilterMinMax("3", "20")});

        submitGridSizeButton = findViewById(R.id.submitButton);

        gridSizeQuestionText = findViewById(R.id.gridSizeQuestionText);

        timer = findViewById(R.id.timer);

        layout = (ViewGroup) submitGridSizeButton.getParent();

        // submit button
        submitGridSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int gridSizeNumber = Integer.parseInt(userInputField.getText().toString());
                if (gridSizeNumber >= 3 && gridSizeNumber <= 20) {

                    // here im showing 2 diff methods of removing a view - view.gone, and .removeview
                    submitGridSizeButton.setVisibility(View.GONE);
                    gridSizeQuestionText.setVisibility(View.GONE);

                    if (null != layout) {
                        layout.removeView(userInputField);
                        timer.setVisibility(View.VISIBLE);
                    }
                    populateButtons(gridSizeNumber);
                }
            }

        });
        // int size = getUserInputGridSize();
        //       populateButtons(6);


    }










    private void populateButtons(int gridSize) {


        gridSquaresCount = gridSize * gridSize;
        final Minefield gameMineField = new Minefield(gridSize, this);


        //   table.setShrinkAllColumns(true);

        //  table.setStretchAllColumns(true);

        //  table.layout
        android.support.v7.widget.GridLayout grid = findViewById(R.id.xmlGrid);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        grid.setColumnCount(gridSize);
        grid.setRowCount(gridSize);


        for (int rowNum = 0; rowNum <= gridSize - 1; rowNum++) {


            for (int columnNum = 0; columnNum <= gridSize - 1; columnNum++) {


                ImageView cellBtn = (gameMineField.getCellContent(rowNum, columnNum).getDisplayedImage());
              //  cellBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
                cellBtn.setAdjustViewBounds(true);
              //  cellBtn.setMaxWidth();
                grid.addView(cellBtn);
                android.support.v7.widget.GridLayout.LayoutParams param =new  android.support.v7.widget.GridLayout.LayoutParams();
                param.height = android.support.v7.widget.GridLayout.LayoutParams.MATCH_PARENT;
            param.width = android.support.v7.widget.GridLayout.LayoutParams.MATCH_PARENT;

               // param.rightMargin = 5;
              //  param.topMargin = 5;
                //param.setGravity(Gravity.FILL);
                param.columnSpec = android.support.v7.widget.GridLayout.spec(columnNum);
                param.rowSpec = android.support.v7.widget.GridLayout.spec(rowNum);





                cellBtn.setLayoutParams (param);



// todo sort out margins

                final int finalRowNum = rowNum;
                final int finalColumnNum = columnNum;
                cellBtn.setOnClickListener(new ImageView.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        // toggle first, then get new image, then disable clickable
                        gameMineField.getCellContent(finalRowNum, finalColumnNum).togglePressed();


                    }
                });

                //   tableRow.set

            }

        }

    }


}
