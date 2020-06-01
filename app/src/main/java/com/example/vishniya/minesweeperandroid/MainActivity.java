package com.example.vishniya.minesweeperandroid;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
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



/*

    private void submitGridSize(){

        //Button submitGridSizeButton = findViewById(R.id.submitButton);



       // submitGridSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInputField = (EditText) findViewById(R.id.chooseGameSizeText);
                Editable gridSize = userInputField.getText();
                int gridSizeNumber = Integer.parseInt(userInputField.getText().toString());
                populateButtons(gridSizeNumber);

            }
        });


    }

*/

/*
    private boolean getUserInputGridSize() {

        final EditText userInputField = (EditText) findViewById(R.id.chooseGameSizeText);

        userInputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                                                     public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                                         boolean handled = false;


                                                         if (actionId == EditorInfo.IME_ACTION_SEND) {

                                                             Editable gridSize = userInputField.getText();
                                                             int gridSizeNumber = Integer.valueOf(userInputField.getText().toString());
                                                             populateButtons(gridSizeNumber);
                                                             handled = true;
                                                         }
                                                         return handled;

                                                     }
                                                 });



        return true;


    }

*/
/*
    private boolean getUserInputGridSize() {

        final EditText userInputField = (EditText) findViewById(R.id.chooseGameSizeText);

        userInputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                                                     public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                                         boolean handled = false;


                                                         if (actionId == EditorInfo.IME_ACTION_SEND) {

                                                             Editable gridSize = userInputField.getText();
                                                             int gridSizeNumber = Integer.valueOf(userInputField.getText().toString());
                                                             populateButtons(gridSizeNumber);
                                                             handled = true;
                                                         }
                                                         return handled;

                                                     }
                                                 });



        return true;


    }

*/


    private void populateButtons(int gridSize) {



        gridSquaresCount=gridSize*gridSize;
        final Minefield gameMineField = new Minefield(gridSize, this);


     //   table.setShrinkAllColumns(true);

      //  table.setStretchAllColumns(true);

      //  table.layout
        TableLayout table =  findViewById(R.id.tableForButtons);
        ConstraintLayout.LayoutParams tableParams = new ConstraintLayout.LayoutParams(

                ConstraintLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT



        );

                table.setLayoutParams(tableParams);

        for (int rowNum = 0; rowNum <= gridSize - 1; rowNum++) {
             TableRow tableRow = new TableRow(this);



            final TableLayout.LayoutParams params = new TableLayout.LayoutParams(

                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT



                    );



            tableRow.setLayoutParams(params);

           // params.setMargins(1,0,0,0);
         //   tableRow.setPadding(1,1,1,1);

            for (int columnNum = 0; columnNum <= gridSize - 1; columnNum++) {

               // String imageButtonName = "imageButton" + "Row" + (rowNum+1) + "Column"+(columnNum+1);
                //System.out.println(imageButtonName);
                 ImageView cellBtn = (gameMineField.getCellContent(rowNum,columnNum).getDisplayedImage());
               cellBtn.setScaleType(ImageView.ScaleType.FIT_XY);

              //  cellBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);

// todo sort out margins
                // watch out for finals causing weird stuiff
                // had to make temp final ints in order to use the inner class below (onclick)
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
                tableRow.addView(cellBtn);
            }
            table.addView(tableRow,params);
        }

    }



}
