package com.example.vishniya.minesweeperandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import Logic.InputFilterMinMax;

public class MainActivity extends AppCompatActivity {

    // global variables

    EditText userInputField;
    Button submitGridSizeButton;
    ViewGroup layout;



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

        layout = (ViewGroup) submitGridSizeButton.getParent();

        // submit button
        submitGridSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int gridSizeNumber = Integer.parseInt(userInputField.getText().toString());
                if (gridSizeNumber >= 3 && gridSizeNumber <= 20) {

                    // here im showing 2 diff methods of removing a view - view.gone, and .removeview
                    submitGridSizeButton.setVisibility(View.GONE);

                    if (null != layout) {
                        layout.removeView(userInputField);
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


    private void populateButtons(int gridSize) {


        TableLayout table =  findViewById(R.id.tableForButtons);

        for (int rowNum = 0; rowNum <= gridSize - 1; rowNum++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for (int columnNum = 0; columnNum <= gridSize - 1; columnNum++) {
                ImageButton imageButton = new ImageButton(this);


                imageButton.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


                tableRow.addView(imageButton);
            }
        }

    }


}
